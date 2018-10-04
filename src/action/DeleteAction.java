package action;

import application.Main3;
import controller.MainUIController;
import model.MyAlert;
import model.PictureNode;

public class DeleteAction {
	MainUIController mainUIController;

	public DeleteAction(MainUIController mainUI) {
		mainUIController = mainUI;

		if(PictureNode.getSelectedPictures().size()<=0) {
			return;
		}
		if(PictureNode.getCutedPictures().size() > 0) {
			for(PictureNode pNode : PictureNode.getCutedPictures()) {
				pNode.getImageView().setEffect(null);
			}
			PictureNode.getCutedPictures().clear();
		}
		
		if(MyAlert.showAlert("是否删除选中的图片？", "", Main3.mainStage)) {
			for(PictureNode pNode : PictureNode.getSelectedPictures()) {
				mainUIController.getFlowPane().getChildren().remove(pNode);
				pNode.getImageFile().delete();
			}
			PictureNode.getSelectedPictureFiles().clear();
			
		}else {
			PictureNode.getSelectedPictureFiles().clear();
		}
		PictureNode.clearSelected();
	}
}
