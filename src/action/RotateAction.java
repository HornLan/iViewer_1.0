package action;

import javafx.scene.image.ImageView;

public class RotateAction {

	private ImageView selectedImage;
	public RotateAction(ImageView imageView) {
		selectedImage = imageView;
		selectedImage.setRotate((selectedImage.getRotate() + 90) % 360);
	}
}
