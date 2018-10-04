package model;

import java.io.File;
import java.net.MalformedURLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import controller.MainUIController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FileTree {

	private MainUIController mainUI;
	private TreeView<PictureFile> treeView;
	private TreeItem<PictureFile> root;

	private final File[] rootPath = File.listRoots();

	public FileTree(MainUIController mianUI, TreeView<PictureFile> treeView) {

		this.mainUI = mianUI;
		// System.out.println(mianUI);
		this.treeView = treeView;
		root = new TreeItem<PictureFile>(new PictureFile("root"));
		root.setExpanded(true);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		buildFileTree();
		getSelected();

	}

	private void buildFileTree() {
		for (int i = 0; i < rootPath.length; i++) {
			FileTreeItem item = new FileTreeItem(new PictureFile(rootPath[i]));
			root.getChildren().add(item);
		}
	}

	public TreeView<PictureFile> gettreeView() {
		return treeView;
	}

	/**
	 * 显示目录文件下的图片
	 */
	private void getSelected() {
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<PictureFile>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<PictureFile>> observable, TreeItem<PictureFile> oldValue,
					TreeItem<PictureFile> newValue) {
				mainUI.getFlowPane().getChildren().remove(0, mainUI.getFlowPane().getChildren().size());
				PictureFile pFile = treeView.getSelectionModel().getSelectedItem().getValue();

				if (pFile.isDirectory()) {
					
					MainUIController.theFilePath = pFile.getImageFile().getAbsolutePath();
					int total = 0;
					double size = 0;
//					boolean first = true;
					PictureFile[] pictureFiles = pFile.listPictures();
					mainUI.clearPictures();
					for (PictureFile pictureFile : pictureFiles) {
						if (pictureFile.isPicture()) {
					
							total++;
							size += pFile.length();
							try {
								PictureNode pictureNode = new PictureNode(pictureFile, mainUI);
//								System.out.println("pic");
								mainUI.getFlowPane().getChildren().add(pictureNode);
								mainUI.addPictures(pictureNode);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}

						}
					}
					mainUI.showPicture();
					mainUI.getText().setText(total + "张图片， 共" + (int)size + "Byte");
				}
			}

		});
	}
}
