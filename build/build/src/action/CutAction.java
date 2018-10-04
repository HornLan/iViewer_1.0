package action;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import model.PictureNode;

public class CutAction {

	public CutAction() {
		if(PictureNode.getSelectedPictures().size()<=0) {
			return;
		}
		if(PictureNode.getCutedPictures().size() > 0) {
			for(PictureNode pNode : PictureNode.getCutedPictures()) {
				pNode.getImageView().setEffect(null);
			}
			PictureNode.getCutedPictures().clear();
		}
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent clipboardContent = new ClipboardContent();
		clipboard.clear();
		for(PictureNode pNode : PictureNode.getSelectedPictures()) {
			PictureNode.getSelectedPictureFiles().add(pNode.getImageFile());
			pNode.getImageView().setEffect(new ColorAdjust(0, 0, 0.5, 0));//标志被剪切
			PictureNode.addCutedPictures(pNode);
		}
		clipboardContent.putFiles(PictureNode.getSelectedPictureFiles());
		clipboard.setContent(clipboardContent);
		clipboard = null;
		clipboardContent = null;
	}
}
