package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private double xOffset = 0.0D;
    private double yOffset = 0.0D;

    public Main() {
    }

    public void start(Stage stage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Click Clock");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
