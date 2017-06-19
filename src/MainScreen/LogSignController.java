package MainScreen;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.event.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;
import logic.*;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by Dung Dinh on 5/13/2017.
 */
public class LogSignController {
        @FXML
        private AnchorPane parentContainer;

        @FXML
        private MaterialDesignIconView iconClose;

        @FXML
        private HBox menusHolder;

        @FXML
        private TextField userName;

        @FXML
        private PasswordField password;

        @FXML
        private TextField logUserName;

        @FXML
        private PasswordField logPassword;

        @FXML
        private PasswordField repeatPassword;

        @FXML
        private TextField email;

        @FXML
        private TextField repeatEmail;

        @FXML
        private CheckBox confirmBox;

        @FXML
        void hideStage(MouseEvent event) {
            Platform.exit();
        }


        boolean checkSignUp() {
            if (repeatEmail.getText() == null || repeatPassword.getText() == null || email.getText() == null
                    || userName.getText() == null || password.getText() == null || !confirmBox.isSelected()) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("The Farm Message");
                alert.setContentText("You need to fill in all blanks");
                alert.setHeaderText("Fulfill Error");
                alert.showAndWait();
                return false;

            }
            else if( !(repeatPassword.getText().equals(password.getText())) || !(repeatEmail.getText().equals(email.getText()))){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("The Farm message");
                alert.setHeaderText("Confirm error");
                alert.setContentText("Your confirmed email or password is wrong");
                alert.showAndWait();
                return false;
            }
            else
                return true;
        }

        @FXML
        void setLogIn(ActionEvent event) throws Exception {
            try {
                Var newVar = new Var();
                newVar.setUserName(logUserName.getText());
                HashMap<String, String> a = new HashMap<>();
                a.put(Var.KEY_USER, logUserName.getText());
                a.put(Var.KEY_PASSWORD, logPassword.getText());
                Scene currentScene = email.getScene();
                currentScene.setCursor(Cursor.WAIT);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("The Farm Message");
                alert.setHeaderText("Connecting to server");
                alert.setContentText("Checking your account. Wait in few minute");
                alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
                alert.show();
                JSONObject json = new jsonDeal().loadJSON(Var.METHOD_LOG_IN, a);
                System.out.println(json.toString());
                boolean value = (boolean) json.get("login");
                alert.close();
                currentScene.setCursor(Cursor.DEFAULT);
                if (value == true) {
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("MainBoard.fxml"));
                    Scene scene = new Scene(root);
                    Stage window = new Stage(StageStyle.UNDECORATED);

                    window.setTitle("The Farm");
                    window.setScene(scene);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();
                } else {
                    alert.setTitle("Log in message");
                    alert.setHeaderText("Log in failure");
                    alert.setContentText("Your password or username is wrong");
                    alert.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
                    alert.showAndWait();
                }
            }catch (TimeoutException t){
                System.out.println("time out request\n");
            }
        }

        @FXML
        void setSignUp(ActionEvent event) throws Exception {
            if(!checkSignUp())return;
            jsonDeal newAction = new jsonDeal();
            HashMap<String, String> request = new HashMap<>();
            request.put(Var.KEY_USER, userName.getText());
            request.put(Var.KEY_PASSWORD, password.getText());
            JSONObject json = newAction.loadJSON(Var.METHOD_SIGN_UP, request);
            boolean value = (boolean) json.get("register");
            if(value == true)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Sign Up SUCCESSFULLY");

                alert.setContentText("You have just sign up with the user name:" + userName.getText() +"\n" +
                "password: " + password.getText());
                alert.showAndWait();
                userName.clear();
                password.clear();
                repeatEmail.clear();
                repeatPassword.clear();
                email.clear();

            }
            else
            {
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Server message");
                alert.setHeaderText("Sign Up Fail");
                alert.setContentText("You failed to create your farm");
                alert.showAndWait();
            }

        }



}
