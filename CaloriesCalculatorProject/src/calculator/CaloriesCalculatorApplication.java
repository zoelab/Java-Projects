package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CaloriesCalculatorApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Create an FXMLLoader Instance
        Parent root = FXMLLoader.load(getClass().getResource("calories-calculator.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Calories Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
