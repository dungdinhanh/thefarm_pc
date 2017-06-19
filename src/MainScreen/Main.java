package MainScreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.application.Application.launch;

/**
 * Created by Dung Dinh on 5/13/2017.
 */
public class Main extends Application {

    public static void main(String []args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("The Farm");
        Parent root =  FXMLLoader.load(getClass().getResource("/MainScreen/LogSign.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
       // primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

}
