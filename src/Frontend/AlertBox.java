 package Frontend;

 import javafx.geometry.Pos;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.layout.VBox;
 import javafx.stage.Modality;
 import javafx.stage.Stage;

 public class AlertBox {
     private static Controller control=new Controller();
     public static void display(String title,String message){
         Stage window = new Stage();
         window.initModality(Modality.APPLICATION_MODAL); //focus on this window only
         window.setMinWidth(200);
         window.setMinHeight(150);
         Label label = new Label();
         label.setText(""+title+" "+message);
         Button closeButton = new Button("Close");
         closeButton.setOnAction(e -> {
             window.close();
         });
         VBox vBox = new VBox(5);
         vBox.getChildren().addAll(label,closeButton);
         vBox.setAlignment(Pos.CENTER);

         Scene scene = new Scene(vBox);
         window.setScene(scene);
         window.showAndWait();
     }
 }