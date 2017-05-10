package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("../views/root.fxml"));
        Parent login = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        primaryStage.setTitle("Attendance Monitor");
        primaryStage.setHeight(600);
        primaryStage.setWidth(1000);
        primaryStage.setResizable(false);
        root.setCenter(login);
        Scene s = new Scene(root);
        s.getStylesheets().add("views/stylesheet.css");
        primaryStage.setScene(s);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../views/icons/icon.png")));
        primaryStage.show();
        //Exit with System.exit on window close to close any spare windows
        primaryStage.setOnCloseRequest(e-> {
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
