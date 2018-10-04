package service;
import action.OpenAction;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.PictureFile;
import model.PictureNode;


public class MouseEvenHandler implements EventHandler<MouseEvent>{

	Node node;
	PictureFile pictureFile;
	public MouseEvenHandler(Node node,PictureFile pictureFile) {
		this.node = node;
		this.pictureFile = pictureFile;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(node instanceof PictureNode) {
//			System.out.println(event.getSource());
			if(event.isControlDown() == false) {//Control没有按下
				if(event.getButton()!=MouseButton.SECONDARY || !((PictureNode)node).selected.getValue())
					PictureNode.clearSelected();
				((PictureNode)node).setSelected(true);
				if(event.getClickCount()>=2 && event.getButton() == MouseButton.PRIMARY){
					//双击打开事件
					((PictureNode)node).setSelected(true);
					((PictureNode) node).openAction();
//					System.out.println(PictureNode.getSelectedPictures().size()+"~~~");
				}				
			}
			if(event.isControlDown() && event.getButton() == MouseButton.PRIMARY) {//Control按下
				((PictureNode) node).setSelected( !((PictureNode)node).selected.get() );
			}
		}
	}
}
