package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 */
public class LoginController implements Initializable {
    
    private LoginModel loginModel = new LoginModel();
    
    @FXML
    private Label isConnected;
    
    @FXML
    private TextField txtUsername;
            
    @FXML
    private TextField txtPassword;        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");
        }
    }  
    
    public void login(ActionEvent event) {
        try {
            if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())) {
                isConnected.setText("username and password is correct");
                
                ((Node)event.getSource()).getScene().getWindow().hide();
                
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("/application/User.fxml").openStream());
                UserController userController = loader.getController();
                userController.getUser(txtUsername.getText());
                
                Stage primaryStage = new Stage();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();              
            } 
            else {
                isConnected.setText("username and password is not correct");
            }
        } catch (SQLException ex) {
            isConnected.setText("username and password is not correct");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
