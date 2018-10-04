package action;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enlarge_Small_Action {

	private static int changeNum = 1;

	public static void enlarge(ImageView imageView) {
		changeNum +=1;
		Image image = imageView.getImage();
		imageView.setFitWidth(800*(changeNum*0.1+1));
		imageView.setFitHeight(800*(changeNum*0.1+1));
		imageView.setPreserveRatio(true);
	}
	public static void small(ImageView imageView) {
		changeNum -=1;
		Image image = imageView.getImage();
		imageView.setFitWidth(800*(changeNum*0.1+1));
		imageView.setFitHeight(800*(changeNum*0.1+1));
		imageView.setPreserveRatio(true);
	}
	
}
