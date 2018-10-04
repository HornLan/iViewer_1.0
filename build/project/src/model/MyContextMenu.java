package model;

import java.io.File;
import java.util.List;

import action.CopyAction;
import action.CutAction;
import action.DeleteAction;
import action.OpenAction;
import action.PasteAction;
import action.RenameAction;
import controller.MainUIController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import service.MouseEvenHandler;

public class MyContextMenu {
	MainUIController mainUI;
	 ContextMenu contextMenu;
	 public MyContextMenu(Node node,MainUIController mainUI,boolean choice) {
		 this.mainUI = mainUI;
		 if(choice) {
			 PictureMenu(node);
		 }
		 nullMenu(node);
		
	}
	public void PictureMenu(Node node) {
		contextMenu = new ContextMenu();
		MenuItem open = new MenuItem("打开");
		MenuItem copy = new MenuItem("复制");
		MenuItem cut = new MenuItem("剪切");
		MenuItem rename = new MenuItem("重命名");
		MenuItem delete = new MenuItem("删除");
		
		contextMenu.getItems().addAll(open,copy,delete,cut,rename);
		
		open.setOnAction(e->{
			new OpenAction();
		});
		copy.setOnAction(e->{
			new CopyAction();
		});
		cut.setOnAction(e->{
			new CutAction();
		});
		rename.setOnAction(e->{
			new RenameAction(mainUI);
		});
		delete.setOnAction(e->{
			new DeleteAction(mainUI);
		});
		
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			if (e.getButton() == MouseButton.SECONDARY)
				contextMenu.show(node, e.getScreenX(), e.getScreenY());
			else {
				if (contextMenu.isShowing())
					contextMenu.hide();
			}
		});
	}
	
	 public void nullMenu(Node node) {
		ContextMenu mouseRightClickMenu = new ContextMenu();
		MenuItem paste = new MenuItem("粘贴");
		MenuItem all = new MenuItem("全选");
		mouseRightClickMenu.getItems().add(paste);
		mouseRightClickMenu.getItems().add(all);
		
		paste.setOnAction(e ->{
			new PasteAction(mainUI);
		});
		all.setOnAction(e->{
			for (Node childrenNode :  mainUI.getFlowPane().getChildren()) {
				if (childrenNode instanceof PictureNode) {
					((PictureNode) childrenNode).setSelected(true);
				}
			}
		});
		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			Node clickNode = e.getPickResult().getIntersectedNode();
			// System.out.println(clickNode.toString());
			if (clickNode instanceof FlowPane && !(clickNode instanceof PictureNode) && !(clickNode instanceof Text)) {// 鼠标点击非图片节点
				PictureNode.clearSelected();// 清空已选

				if (e.getButton() == MouseButton.SECONDARY) {// 鼠标右键
					Clipboard clipboard = Clipboard.getSystemClipboard();
					List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));
					if (files.size() <= 0) {
						paste.setDisable(true);
					} else {
						paste.setDisable(false);
					}
					mouseRightClickMenu.show(node, e.getScreenX(), e.getScreenY());
				} else {
					if (mouseRightClickMenu.isShowing()) {
						mouseRightClickMenu.hide();
					}
				}
			} else {
				if (mouseRightClickMenu.isShowing()) {
					mouseRightClickMenu.hide();
				}
			}
		});
	}

}
