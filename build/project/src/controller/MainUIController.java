package controller;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.FileTree;
import model.MyContextMenu;
import model.PictureFile;
import model.PictureNode;
import service.ChangeService;
import service.PaneListener;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import action.CopyAction;
import action.DeleteAction;
import action.OpenAction;
import action.PasteAction;
import action.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.FlowPane;

import javafx.scene.control.ToolBar;

import javafx.scene.control.TreeView;

public class MainUIController implements Initializable {

	private MainUIController mainUI;
	private ArrayList<PictureNode> pictures;
	private ArrayList<File> files;
	public static String theFilePath;
	@FXML
	private TreeView<PictureFile> treeview;
	@FXML
	private FlowPane flowPane;
	@FXML
	private Text text;
	@FXML
	private Text textTwo;
	@FXML
	private ToolBar toolBar;
	@FXML
	private Button openBtn;
	@FXML
	private Button copyBtn;
	@FXML
	private Button pasteBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button PPT;
	
    public void setList() {
    	files=new ArrayList<File>();
    	for(int i=0;i<pictures.size();i++) {
    		files.add(pictures.get(i).getImageFile());
    	}
    	ChangeService.files=files;
    }
	public MainUIController() {
		mainUI = this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initData();
	}

	public void initData() {
		pictures = new ArrayList<>();
		treeview = new FileTree(mainUI, treeview).gettreeView();
		// showPicture();
		new PaneListener(flowPane,mainUI);
		new MyContextMenu(flowPane, mainUI,false);
	}

	public FlowPane getFlowPane() {
		return flowPane;
	}

	public  ObservableList<Node> getFlowPaneChildren() {
		return flowPane.getChildren();
	}
	public Text getText() {
		return text;
	}
	public Text getTextTwo() {
		return textTwo;
	}

	public ArrayList<PictureNode> getPictures() {
		return pictures;
	}

	public void addPictures(PictureNode pNode) {
		pictures.add(pNode);
	}

	/*
	 * 用作刷新图片显示界面
	 */
	public void showPicture() {
		System.out.println("233");
		flowPane.getChildren().remove(0, flowPane.getChildren().size());
		for (PictureNode pNode : pictures) {
			flowPane.getChildren().add(pNode);
		}
	}

	public void clearPictures() {
		pictures.clear();
	}

	public void removePictures(PictureNode pNode) {
		for (PictureNode pictureNode : pictures) {
			if (pictureNode.equals(pNode)) {
				pictures.remove(pNode);
				break;
			}
		}
	}

	// Event Listener on Button[#openBtn].onAction
	@FXML
	public void openBtnAction(ActionEvent event) {
		 new OpenAction();
	}

	// Event Listener on Button[#copyBtn].onAction
	@FXML
	public void copyBtnAction(ActionEvent event) {
		new CopyAction();
	}

	// Event Listener on Button[#pasteBtn].onAction
	@FXML
	public void pasteBtnAction(ActionEvent event) {
		new PasteAction(mainUI);
	}

	// Event Listener on Button[#deleteBtn].onAction
	@FXML
	public void deleteBtnAction(ActionEvent event) {
		new DeleteAction(mainUI);
	}
	@FXML
	public void PPTAction(ActionEvent event) {
		new PPTAction();
	}

}
