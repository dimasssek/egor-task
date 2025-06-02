package ru.egor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.egor.db.DerbyDataSourceProvider;
import ru.egor.property.PropertyContainer;
import ru.egor.repository.DerbyPaymentsDatabaseRepository;
import ru.egor.service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
        var propertyContainer = new PropertyContainer("app.properties");
        propertyContainer.loadProperties();
        var dataProvider = new DerbyDataSourceProvider(propertyContainer);
        var repository = new DerbyPaymentsDatabaseRepository(dataProvider.getDataSource());
        var service = new PaymentService(repository);

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