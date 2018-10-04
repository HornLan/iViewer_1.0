package action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;

public class MainAction {
	public MainAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MainUI.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			ChangeService.stage.setScene(scene);
			ChangeService.stage.setTitle("iViewer0.3");
			ChangeService.stage.setResizable(false);
			ChangeService.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
