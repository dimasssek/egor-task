package ru.egor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.egor.property.PropertyContainer;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        var propertyContainer = new PropertyContainer("app.properties");
        propertyContainer.loadProperties();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}