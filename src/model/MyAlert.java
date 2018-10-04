package model;

import java.util.Optional;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class MyAlert {

	 public static boolean showAlert(String p_header,String p_message,Window stage){
//       按钮部分可以使用预设的也可以像这样自己 new 一个
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION,p_message,new ButtonType("取消", ButtonBar.ButtonData.NO),
               new ButtonType("确定", ButtonBar.ButtonData.YES));
//       设置窗口的标题
       alert.setTitle("注意窗口");
       alert.setHeaderText(p_header);
//       设置对话框的 icon 图标，参数是主窗口的 stage
       alert.initOwner(stage);
//       showAndWait() 将在对话框消失以前不会执行之后的代码
       Optional<ButtonType> buttonType = alert.showAndWait();
//       根据点击结果返回
       if(buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
           return true;
       }
       else {
           return false;
       }
   }
}
