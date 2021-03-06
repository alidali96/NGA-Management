package Start;

import Database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Start extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/DBLoginView.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Ubuntu");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("NGA Management");
        stage.centerOnScreen();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(e-> DatabaseConnection.getInstance().closeConnection());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
