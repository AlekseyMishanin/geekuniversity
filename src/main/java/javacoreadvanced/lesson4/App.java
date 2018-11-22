package javacoreadvanced.lesson4;

import javacoreadvanced.lesson4.config.MyMouseConfig;
import javacoreadvanced.lesson4.service.MyEventHandlerDragged;
import javacoreadvanced.lesson4.service.MyEventHandlerPressed;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;

/**
 * Class App
 * @author Mishanin Aleksey
 * */
public class App extends Application{

    private Parent root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        //убираем стандартую рамку
        primaryStage.initStyle(StageStyle.UNDECORATED);
        scene = new Scene(root, 550, 575);
        primaryStage.setScene(scene);
        MyMouseConfig mouseconf = new MyMouseConfig(primaryStage);
        scene.setOnMousePressed(new MyEventHandlerPressed(mouseconf));
        scene.setOnMouseDragged(new MyEventHandlerDragged(mouseconf));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
