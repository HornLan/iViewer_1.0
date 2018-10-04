package action;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import controller.MainUIController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.PictureFile;
import model.PictureNode;
import service.ChangeService;

public class Previous_next_Action {

	private static int page = 0;
	private static Image image;
	private boolean Previous_or_next = true;

//	public Previous_next_Action(ImageView imageView, boolean Previous_or_next) {
//
//		this.Previous_or_next = Previous_or_next;
//		this.imageView = imageView;
//		iniData();
//		change();
//	}

	public static void changePicture(ImageView imageView,Boolean Previous_or_next) {
	    if(Previous_or_next) {
	    	page++;
	    }
	    if(!Previous_or_next) {
	    	page--;
	    }
		if (page < 0) {
			String text = "这是第一张图片";
			Button button = new Button(text);
			Pane root = new Pane(button);
			Scene scene = new Scene(root);
			scene.getStylesheets().add("view/iVCSS.css");
			Stage Stage = null;
			Stage = new Stage();
			Stage.setTitle("提示");
			Stage.setScene(scene);
			Stage.show();
			page++;
			return;
		}
		if (page > ChangeService.files.size() - 1) {
			String text = "这是最后一张图片";
			Button button = new Button(text);
			Pane root = new Pane(button);
			Scene scene = new Scene(root);
			scene.getStylesheets().add("view/iVCSS.css");
			Stage Stage = null;
			Stage = new Stage();
			Stage.setTitle("提示");
			Stage.setScene(scene);
			Stage.show();
			page--;
			return;
		}
		File file = ChangeService.files.get(page);
		try {
			image = new Image(file.toURI().toURL().toString());
			imageView.setImage(image);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
