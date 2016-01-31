
package fxexample.controllers;

import fxexample.interfaces.impls.CollectionAddressBook;
import fxexample.objects.Person;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainController {
    
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    
    @FXML
    private Button btnAdd;
        
    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;
    
    @FXML
    private TextField txtSearch; 
    
    @FXML
    private Button btnSearch;
    
    @FXML
    private TableView tableAddressBook;
    
    @FXML
    private TableColumn<Person, String> columnFIO;
    
    @FXML
    private TableColumn<Person, String> columnPhone;
       
    @FXML
    private Label labelCount;   
    
    
    @FXML
    private void initialize() {
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
    
        addressBookImpl.fillTestData();
        
        tableAddressBook.setItems(addressBookImpl.getPersonList());
        
        updateCountLabel();
    }
    
    private void updateCountLabel() {
        labelCount.setText("Количество записей: " + addressBookImpl.getPersonList().size());
    }
  
    public void showDialog(ActionEvent actionEvent) {

        try {            
            Parent root = FXMLLoader.load(getClass().getResource("/fxexample/fxml/edit.fxml"));
            
            Stage stage = new Stage();            
            stage.setTitle("Редактирование записи");
            stage.setMinHeight(150);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }  
    
}
