package Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("8 Puzzle");
        window = primaryStage;
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void endWithCongrats(){

        Frontend.AlertBox.display("Congratulations","You Won!");
        //window.close();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
