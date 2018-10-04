package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import action.OpenAction;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.PictureNode;
import service.ChangeService;

public class PPT implements Initializable {
	private Timeline timeline;
//	private ArrayList<File> images;
//	private ArrayList<Image> list;
	private int count = 0;


	@FXML
	private ImageView imageview;
	@FXML
	private Button start, stop;

	@FXML
	private void Begin(ActionEvent e) {
		timeline.play();
	}

	@FXML
	private void Stop(ActionEvent e) {
		timeline.pause();
	}

	@FXML
	private void Press(MouseEvent e) {
		if (start.isVisible()) {
			start.setVisible(false);
			stop.setVisible(false);
		} else {
			start.setVisible(true);
			stop.setVisible(true);
		}
	}

	@FXML
	private void Back(ActionEvent e) {
		new OpenAction();
	}
//private void init() {
//	
//	imageview.setScaleX(1.5);
//	imageview.setScaleY(1.5);
//}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		imageview.setImage(ChangeService.change.getImage());
		imageview.setEffect(ChangeService.change.getEffect());
		imageview.setViewport(ChangeService.change.getViewport());
		imageview.setNodeOrientation(ChangeService.change.getNodeOrientation());
		imageview.setRotate(ChangeService.change.getRotate());
//		this.images = ChangeService.files;
		/*
		 * 下面这样做很傻，对同一个目录下的文件重复加载的内存， 按照以下做法，需要将一个file里的所有文件重复加载三次，
		 * 也就是说一个文件夹内有300M的图片，需要占用900M的内存 是不是傻
		 */
		// list = new ArrayList<Image>();
		// for (int i = 0; i < images.size(); i++) {
		// try {
		// list.add(new Image(images.get(i).toURI().toURL().toString()));
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// }
		// }
		timeline = new Timeline();

		timeline.setCycleCount(Timeline.INDEFINITE);

		KeyValue keyValue = new KeyValue(imageview.scaleXProperty(), 2);
		KeyValue keyValue2 = new KeyValue(imageview.scaleYProperty(), 2);
		Duration duration = Duration.seconds(5);

		EventHandler<ActionEvent> onFinished = (ActionEvent t) -> {
			if (count < ChangeService.files.size()) {
				try {
					imageview.setImage(new Image(ChangeService.files.get(count).toURI().toURL().toString()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

			} else if (count == ChangeService.files.size()) {

				count = 0;
				imageview.setScaleX(1.0);
				imageview.setScaleY(1.0);
				timeline.stop();
			}
			count++;
		};
		KeyFrame keyFrame1 = new KeyFrame(duration, onFinished, keyValue, keyValue2);

		timeline.getKeyFrames().add(keyFrame1);

	}

}
