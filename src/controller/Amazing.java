package controller;

import java.awt.Desktop;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import action.OpenAction;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ChangeService;

public class Amazing implements Initializable {
	private final Desktop desktop = Desktop.getDesktop();
	private final FileChooser fileChooser = new FileChooser();
	// private Image image;
	private Stage stage;
	@FXML
	private ImageView imageview;
	@FXML
	private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
	@FXML
	private Slider slider;
	@FXML
	private TextArea textarea;
	@FXML
	private Button sure;
	@FXML
	private Button cancel;

	@FXML
	private void Change(ActionEvent event) {
		ChangeService.change=imageview;
		// App.setStage(stage);
		new OpenAction();

	}

	@FXML
	private AnchorPane backpane;

	@FXML
	private void Back(ActionEvent event) {
		backpane.setVisible(true);
		textarea.setStyle("-fx-background-color:  #2e2d2d;");
		sure.setDisable(false);
		cancel.setDisable(false);
	}

	@FXML
	private void Sure(ActionEvent event) {
		ChangeService.change=ChangeService.origin;
		// App.setStage(stage);
		new OpenAction();;
	}

	@FXML
	private void Cancel(ActionEvent event) {

		backpane.setVisible(false);
		sure.setDisable(true);
		cancel.setDisable(true);
	}

	@FXML
	Button ChangeButton;
	@FXML
	Button SaveButton;
	@FXML
	private Button test;

	@FXML
	private void Sepiatone(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  red;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");

		slider.setOpacity(0.5);
		slider.setValue(0.5);
		SepiaTone effect = new SepiaTone(0.5);
		imageview.setEffect(effect);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			effect.setLevel(new_val.doubleValue());
		});
	}

	@FXML
	private void Origin(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");

		slider.setOpacity(0.0);
		imageview.setEffect(null);

	}

	@FXML
	private void Overlay(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");

		slider.setOpacity(0.5);
		slider.setValue(0.5);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		imageview.setEffect(blend);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	@FXML
	private void Bloom(ActionEvent e) {

		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");
		slider.setOpacity(0.5);
		slider.setValue(0.5);
		Bloom bloom = new Bloom();
		imageview.setEffect(bloom);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			bloom.setThreshold(new_val.doubleValue());

		});
	}

	@FXML
	private void Mercury(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");
		slider.setOpacity(0.5);
		slider.setValue(0.5);
		ColorAdjust color = new ColorAdjust();
		color.setSaturation(-1.0);
		BoxBlur boxblur = new BoxBlur();
		boxblur.setWidth(5.0);
		boxblur.setIterations(1);
		color.setInput(boxblur);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		boxblur.setInput(blend);
		imageview.setEffect(color);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	@FXML
	private void Exclusion(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");
		slider.setOpacity(0.5);
		slider.setValue(0.5);
		Blend blend = new Blend();
		blend.setMode(BlendMode.EXCLUSION);
		blend.setOpacity(0.5);
		imageview.setEffect(blend);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());

		});
	}

	@FXML
	private void Arctic(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");
		slider.setOpacity(0.5);
		slider.setValue(0.5);
		ColorAdjust color = new ColorAdjust();
		color.setHue(-0.6);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		color.setInput(blend);
		imageview.setEffect(color);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	@FXML
	private void Denim(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");
		slider.setOpacity(0.5);
		slider.setValue(0.5);
		ColorAdjust color = new ColorAdjust();
		color.setSaturation(-1.0);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		color.setInput(blend);
		imageview.setEffect(color);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	@FXML
	private void Neo(ActionEvent e) {
		if (test != null) {
			test.setStyle("-fx-border-color:  #2e2d2d;-fx-background-color:  #2e2d2d;");
		}
		test = ((Button) e.getSource());
		((Button) e.getSource()).setStyle("-fx-border-color:  #42ff51;-fx-background-color:  #2e2d2d;");
		slider.setOpacity(0.5);
		slider.setValue(0.5);
		ColorAdjust color = new ColorAdjust();
		color.setHue(0.6);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		color.setInput(blend);
		imageview.setEffect(color);
		slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			blend.setOpacity(new_val.doubleValue());
		});
	}

	public void setImageViewImage(ImageView image) {
		image.setImage(ChangeService.change.getImage());
		image.setEffect(ChangeService.change.getEffect());
		image.setViewport(ChangeService.change.getViewport());
		image.setNodeOrientation(ChangeService.change.getNodeOrientation());
		image.setRotate(ChangeService.change.getRotate());
	}

	private void setImageViewEffect() {
		this.setImageViewImage(image1);
		this.setImageViewImage(image2);
		this.setImageViewImage(image3);
		this.setImageViewImage(image4);
		this.setImageViewImage(image5);
		this.setImageViewImage(image6);
		this.setImageViewImage(image7);
		this.setImageViewImage(image8);
		this.setImageViewImage(image9);
		image1.setEffect(null);
		Blend blend = new Blend();
		blend.setMode(BlendMode.OVERLAY);
		blend.setOpacity(0.5);
		image2.setEffect(blend);
		SepiaTone sep = new SepiaTone(0.5);
		image3.setEffect(sep);
		Bloom bloom = new Bloom(0.5);
		image4.setEffect(bloom);
		BoxBlur boxblur = new BoxBlur();
		boxblur.setWidth(5.0);
		boxblur.setIterations(1);
		ColorAdjust color1 = new ColorAdjust();
		color1.setSaturation(-1.0);
		color1.setInput(boxblur);
		boxblur.setInput(blend);
		image5.setEffect(color1);
		Blend blend2 = new Blend();
		blend2.setMode(BlendMode.EXCLUSION);
		blend2.setOpacity(0.5);
		image6.setEffect(blend2);
		ColorAdjust color2 = new ColorAdjust();
		color2.setHue(-0.6);
		color2.setInput(blend);
		image7.setEffect(color2);
		ColorAdjust color3 = new ColorAdjust();
		color3.setSaturation(-1.0);
		color3.setInput(blend);
		image8.setEffect(color3);
		ColorAdjust color4 = new ColorAdjust();
		color4.setHue(0.6);
		color4.setInput(blend);
		image9.setEffect(color4);
	}

	public void setImage() {
		imageview.setImage(ChangeService.change.getImage());
		imageview.setEffect(ChangeService.change.getEffect());
		imageview.setViewport(ChangeService.change.getViewport());
		imageview.setNodeOrientation(ChangeService.change.getNodeOrientation());
		imageview.setRotate(ChangeService.change.getRotate());
		setImageViewEffect();
		/*
		 * this.setImageViewImage(image1); this.setImageViewImage(image2);
		 * this.setImageViewImage(image3); this.setImageViewImage(image4);
		 * this.setImageViewImage(image5); this.setImageViewImage(image6);
		 * this.setImageViewImage(image7); this.setImageViewImage(image8);
		 * this.setImageViewImage(image9); image1.setEffect(null); Blend blend = new
		 * Blend(); blend.setMode(BlendMode.OVERLAY); blend.setOpacity(0.5);
		 * image2.setEffect(blend); SepiaTone sep = new SepiaTone(0.5);
		 * image3.setEffect(sep); Bloom bloom = new Bloom(0.5); image4.setEffect(bloom);
		 * BoxBlur boxblur = new BoxBlur(); boxblur.setWidth(5.0);
		 * boxblur.setIterations(1); ColorAdjust color1 = new ColorAdjust();
		 * color1.setSaturation(-1.0); color1.setInput(boxblur);
		 * boxblur.setInput(blend); image5.setEffect(color1); Blend blend2 = new
		 * Blend(); blend2.setMode(BlendMode.EXCLUSION); blend2.setOpacity(0.5);
		 * image6.setEffect(blend2); ColorAdjust color2 = new ColorAdjust();
		 * color2.setHue(-0.6); color2.setInput(blend); image7.setEffect(color2);
		 * ColorAdjust color3 = new ColorAdjust(); color3.setSaturation(-1.0);
		 * color3.setInput(blend); image8.setEffect(color3); ColorAdjust color4 = new
		 * ColorAdjust(); color4.setHue(0.6); color4.setInput(blend);
		 * image9.setEffect(color4);
		 */
	}

	@FXML
	private void Undo(ActionEvent e) {
		imageview.setImage(ChangeService.change.getImage());
	}

	@FXML
	private Region veil;
	@FXML
	private ProgressIndicator indicator;
	@FXML
	private Label savelabel;
	@FXML
	private AnchorPane Existpane;
	@FXML
	private BorderPane borderpane;

	@FXML
	private void Close(ActionEvent e) {
		Existpane.setVisible(false);
	}

	public class SaveTask extends Task<Integer> {

		@Override

		protected Integer call() throws Exception {

			for (int i = 0; i < 250; i++) {

				updateProgress(i, 250);

				Thread.sleep(5);

			}

			return 1;
		}

	}

	@FXML
	private void Copy(ActionEvent event) {
		File file = ChangeService.file;

		if (file.exists()) {
			Task<Integer> task = new SaveTask();

			veil.visibleProperty().bind(task.runningProperty());
			savelabel.visibleProperty().bind(task.runningProperty());
			indicator.visibleProperty().bind(task.runningProperty());
			new Thread(task).start();

			WritableImage image = imageview.snapshot(new SnapshotParameters(), null);
			String copyfilepath = null;

			String filename = file.getName();
			String fileParentPath = file.getParent();

			String name1 = filename.substring(0, filename.lastIndexOf("."));
			System.out.println(name1);
			int a = name1.lastIndexOf("(");
			int b = name1.lastIndexOf(")");
			if (a != -1 && b != -1) {
				String index = name1.substring(name1.lastIndexOf("(") + 1, name1.lastIndexOf(")"));
				if (index != "" && index != null) {
					int n = Integer.valueOf(index);
					n++;
					copyfilepath = fileParentPath + "\\" + name1 + "(" + n + ").jpg";
				}

			} else {
				copyfilepath = fileParentPath + "\\" + name1 + "(" + 1 + ").jpg";
			}
			System.out.println(copyfilepath);
			File files = new File(copyfilepath);
			System.out.println(file.getPath());
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", files);
			} catch (IOException e) {
				e.printStackTrace();
			}

			ChangeService.change=imageview;
		} else {
			Existpane.setVisible(true);
		}

	}

	@FXML
	private void Save(ActionEvent event) {

		File file = ChangeService.file;

		if (file.exists()) {
			Task<Integer> task = new SaveTask();

			veil.visibleProperty().bind(task.runningProperty());
			savelabel.visibleProperty().bind(task.runningProperty());
			indicator.visibleProperty().bind(task.runningProperty());
			new Thread(task).start();
			WritableImage image = imageview.snapshot(new SnapshotParameters(), null);

			try {
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
				ChangeService.change=imageview;
				ChangeService.change=imageview;
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Existpane.setVisible(true);
		}
	}

	@FXML
	private AnchorPane picturepane;

	@FXML
	private void Scroll(ScrollEvent e) {
		if (imageview.getBoundsInParent().getWidth() >= imageview.getFitWidth() * 2.5) {
			big.setDisable(true);
			big.setOpacity(0.6);
			if (e.getDeltaY() < 0) {
				scrollcount += (int) (e.getDeltaY() / 26);
				big.setDisable(false);
				big.setOpacity(1.0);
				imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
				imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
			}
		} else if (imageview.getBoundsInParent().getWidth() < slider.getWidth()) {
			small.setDisable(true);
			small.setOpacity(0.6);
			if (e.getDeltaY() > 0) {
				scrollcount += (int) (e.getDeltaY() / 26);
				small.setDisable(false);
				small.setOpacity(1.0);
				imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
				imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
			}
		} else {
			scrollcount += (int) (e.getDeltaY() / 26);
			imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
			imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
		}

	}

	private int count = 0;
	private int scrollcount = 0;
	@FXML
	private Button big, small;

	@FXML
	private void Big(ActionEvent e) {
		if (imageview.getBoundsInParent().getWidth() >= imageview.getFitWidth() * 2.5) {
			big.setDisable(true);
			big.setOpacity(0.6);
		} else {
			count++;
			small.setDisable(false);
			small.setOpacity(1.0);
			imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
			imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
		}

	}

	@FXML
	private void Small(ActionEvent e) {
		if (imageview.getBoundsInParent().getWidth() < slider.getWidth()) {
			small.setDisable(true);
			small.setOpacity(0.6);
		} else {
			count--;
			imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
			imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
			big.setDisable(false);
			big.setOpacity(1.0);

		}
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private AnchorPane leftpane, rightpane;
	@FXML
	private HBox toppane;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setImage();
		ChangeService.stage.widthProperty().addListener((a) -> {
			imageview.setScaleX(1.0);
			imageview.setScaleY(1.0);
		});
		ChangeService.stage.heightProperty().addListener((a) -> {
			imageview.setScaleX(1.0);
			imageview.setScaleY(1.0);
		});
		imageview.fitWidthProperty()
		.bind(ChangeService.stage.widthProperty().subtract(rightpane.widthProperty()).divide(4).multiply(3));
      imageview.fitHeightProperty()
		.bind(ChangeService.stage.heightProperty().subtract(toppane.heightProperty()).divide(4).multiply(3));

      slider.prefWidthProperty().bind(imageview.fitWidthProperty().divide(4).multiply(3));
      ChangeButton.setTooltip(new Tooltip("裁剪和旋转"));
	}
}
