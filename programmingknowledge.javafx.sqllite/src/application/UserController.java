package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class UserController {
    
    @FXML
    private Label userLbl;
       
    public void getUser(String user) {
        userLbl.setText(user);
    }
    
    public void signOut(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
              
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/application/Login.fxml").openStream());

            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();   
        } 
        catch (Exception e) {
            e.printStackTrace();
        }            
    }
    
}
