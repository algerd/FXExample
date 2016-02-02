
package fxexample.controllers;

import fxexample.interfaces.impls.CollectionAddressBook;
import fxexample.objects.Person;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainController implements Initializable {
    
    private CollectionAddressBook addressBookImpl = new CollectionAddressBook();
    private Stage mainStage;
    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    
    private ResourceBundle resourceBundle; 
    
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
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        columnFIO.setCellValueFactory(new PropertyValueFactory<Person, String>("fio"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));  
        initListeners();             
        fillData();
        initLoader();        
    }
    
    private void fillData() {
        addressBookImpl.fillTestData();
        tableAddressBook.setItems(addressBookImpl.getPersonList());
    }
    
    
    private void initListeners() {
        addressBookImpl.getPersonList().addListener(
            new ListChangeListener<Person>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends Person> c) {
                    updateCountLabel();
                }
            }      
        );
        
        tableAddressBook.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        // получить выбранную запись из таблицы
                        Person selectedPerson = (Person)tableAddressBook.getSelectionModel().getSelectedItem();
                        editDialogController.setPerson(selectedPerson);
                        showDialog();
                    }
                }             
            }          
        );            
    }
    
    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("/fxexample/fxml/edit.fxml"));
            fxmlLoader.setResources(ResourceBundle.getBundle("fxexample.bundles.Locale", new Locale("ru")));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();    
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
    
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
        
    private void updateCountLabel() {
        // запись взять из бандла локализации с ключом count
        labelCount.setText(resourceBundle.getString("count") + ": " + addressBookImpl.getPersonList().size());
    }
       
    public void actionButtonPressed(ActionEvent actionEvent) {
        // получить объект-источник события(нажатия)
        Object source = actionEvent.getSource();
        
        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }       
        Button clickedButton = (Button)source; 
                      
        switch (clickedButton.getId()) {
            case "btnAdd" :
                editDialogController.setPerson(new Person());
                showDialog();
                addressBookImpl.add(editDialogController.getPerson());
                break;    
                
            case "btnEdit" :
                // получить выбранную запись из таблицы
                Person editedPerson = (Person)tableAddressBook.getSelectionModel().getSelectedItem();
                editDialogController.setPerson(editedPerson);
                showDialog();
                
                //Требует доработки: надо добавить эктрактор в коллекцию для отслеживания изменений оббъектов в ней
                //см.  пример collections.ObservableListUpdateCallback
                // временное решение: т.к. наша коллекция отслеживает только удаление и добавление объектов - удалить и добавить объект
                addressBookImpl.delete(editedPerson);
                addressBookImpl.add(editedPerson);
                
                break;
                
            case "btnDelete" :
                // получить выбранную запись из таблицы
                Person deletedPerson = (Person)tableAddressBook.getSelectionModel().getSelectedItem();
                addressBookImpl.delete(deletedPerson);
                break;
        }
    }
      
    private void showDialog() {  
        // lazy initialization of editDialogStage
        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle(resourceBundle.getString("edit"));
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);                   
        }
        // для ожидания закрытия окна
        editDialogStage.showAndWait();      
    }  

    
    
}
