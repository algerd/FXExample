
package test2;

import java.io.IOException;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Главный контроллер главного fxml-документа.
 * Он имеет ссылку на вложенный контроллер includedCloseBtnController.
 * Имя ссылки вложенного контроллера в главном контроллере должна соответствовать правилу:
 *  fx:id элемента fx:include + Controller = includedCloseBtn + Controller
 */
public class MainController {
    
    @FXML
    private VBox root;
    
    @FXML 
	private Button addAnchorBtn;
    
    @FXML 
	private Button addAnchorBtn2;
    
    @FXML
    private AnchorPane newPane;
      
    private AnchorPane anchorPane;
    private AnchorPane anchorPane2;  
    private AnchorPaneController anchorPaneController;
    private AnchorPaneController2 anchorPaneController2;
    		
	//@FXML
    // вложенный контроллер
	//private CloseBtnController includedCloseBtnController;
	
	@FXML
	public void initialize() {
        try {
            loadAnchorPane();
            loadAnchorPane2();
        } catch (IOException ex) {
            ex.printStackTrace();
        }	
	}
       
    private void loadAnchorPane() throws IOException {
        URL fxmlUrl = this.getClass().getClassLoader().getResource("test2/anchorpane.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);     
		anchorPane = loader.<AnchorPane>load();
        anchorPaneController = loader.getController();
    }
    
     private void loadAnchorPane2() throws IOException {
        URL fxmlUrl = this.getClass().getClassLoader().getResource("test2/anchorpane2.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);     
		anchorPane2 = loader.<AnchorPane>load();
        anchorPaneController2 = loader.getController();
    }
    
    /**
     * Замена дефолтного узла через прямое присвоение ссылки нового узла.
     * Потому что родитель этого узла(VBox root) утрачивает ссылку ребёнка newPane в своей коллекции детей.
     * Поэтому надо вновь добавить заменённый узел родителю.
     *
     */
    @FXML 
    private void addAnchorPane() {
        changePane(anchorPane);
    }
    
    @FXML 
    private void addAnchorPane2() {
        changePane(anchorPane2);
    }
    
    private void changePane(AnchorPane pane) {
        ObservableList<Node> childrens = root.getChildren();
        // получить индекс заменяемого узла в родителе
        int index = childrens.indexOf(newPane);
        // Замена дефолтного узла через прямое присвоение ссылки нового узла
        newPane = pane;
        // добавление родителю
        childrens.set(index, newPane);
    }
    
    
}
