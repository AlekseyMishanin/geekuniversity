package javacoreadvanced.lesson4;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;

/**
 * Class App
 * @author Mishanin Aleksey
 * */
public class App extends Application{

    private double xOffset;
    private double yOffset;
    private Parent root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        //убираем стандартую рамку
        primaryStage.initStyle(StageStyle.UNDECORATED);
        scene = new Scene(root, 550, 575);
        primaryStage.setScene(scene);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
