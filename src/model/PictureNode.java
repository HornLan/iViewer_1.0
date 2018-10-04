package model;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import action.OpenAction;
import controller.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.MouseEvenHandler;

public class PictureNode extends Label{
	private MainUIController mainScene;
	private PictureFile pictureFile; 
	private Image image;
	private ImageView imageView;
	private Text pictureName;
	private PictureNode pictureNode = this;
	
	public BooleanProperty selected = new SimpleBooleanProperty();
	
	protected static ArrayList<File> selectedPictureFiles = new  ArrayList<>();
	protected static ArrayList<PictureNode> selectedPictures = new ArrayList<>();
	protected static ArrayList<PictureNode> cutedPictures = new ArrayList<>();
	
	public PictureNode(PictureFile pictureFile,MainUIController mainUIController) throws MalformedURLException {	
		this.pictureFile = pictureFile;
		this.mainScene = mainUIController;
		initData();
		addPictureNodeListener();
		new MyContextMenu(this,mainScene,true);
	}
	private void initData() throws MalformedURLException{
		
		this.setGraphicTextGap(10);
		this.setPadding(new Insets(1, 1, 1, 1));
		this.setContentDisplay(ContentDisplay.TOP);
		this.setPrefSize(110,110);
		
		this.image = new Image(pictureFile.getImageFile().toURI().toURL().toString(),100,100,true,true);
		this.imageView = new ImageView(image);
		this.pictureName = new Text(pictureFile.getImageName());
		this.setText(pictureName.getText());
		this.setGraphic(imageView);
		pictureNode.setId("pictureNode");
	}
	
	

	public Image getImage() {
		try {
			return image = new Image(pictureFile.getImageFile().toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return image;
		
	}
	public File getImageFile() {
		return this.pictureFile.getImageFile();
	}
	public PictureFile getPictureFile() {
		return pictureFile;
	}
	public String getURL() {
		try {
			return this.pictureFile.getImageFile().toURI().toURL().toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ImageView getImageView() {
		return this.imageView;
	}
	
	public static ArrayList<File> getSelectedPictureFiles() {
		return selectedPictureFiles;
	}
	public static ArrayList<PictureNode> getSelectedPictures() {
		return selectedPictures;
	}
	public static void setCutedPictures(ArrayList<PictureNode> cutedPictures) {
		PictureNode.cutedPictures = cutedPictures;
	}
	public static void addCutedPictures(PictureNode pNode){
		PictureNode.cutedPictures.add(pNode);
	}
	public static ArrayList<PictureNode> getCutedPictures() {
		return cutedPictures;
	}
	public static void clearCutedPictures() {
		cutedPictures.removeAll(cutedPictures);
	}
	
	
	public void setSelected(boolean value) {
		boolean istrue = selected.get();
		selected.set(value);
		if (selected.get() && !istrue)
			selectedPictures.add(this);
		else if (istrue && !selected.get())
			selectedPictures.remove(this);
		System.out.println(selectedPictures.size());
		mainScene.getTextTwo().setText("已选中 "+selectedPictures.size()+" 张图片");
	}
	
	public static void clearSelected() {
		for (PictureNode pNode : selectedPictures) {
			pNode.selected.set(false);
		}
		selectedPictures.removeAll(selectedPictures);
	}
	
	private void addPictureNodeListener() {
		selected.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(selected.get()) {
					pictureNode.setStyle("-fx-background-color:#a7a7a7;");
//					mainScene.getText().setText("");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}else {
					pictureNode.setStyle("-fx-background-color:transparent;");
//					System.out.println(selectedPictures.size()+"--");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}
			}
		});
		this.setOnMouseEntered((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:linear-gradient(to bottom,#3e4147 1%,  #a7a7a7 98%);");
//			    mainScene.getText().setText("");
			    
		});
		this.setOnMouseExited((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:transparent;");
			    
		});
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseEvenHandler(this,pictureFile));
	}
	
	public void openAction() {
//		mainScene.setList();
		new OpenAction();
	}

	
}