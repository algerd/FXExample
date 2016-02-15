
package test;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
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
	private Button addCloseBtn;
    
    @FXML 
	private Button addOpenBtn;
    
    @FXML
    private AnchorPane newPane;
    
    private Button closeBtn;
    private Button openBtn;
    private CloseBtnController closeBtnController;
    private OpenBtnController openBtnController;
    		
	//@FXML
    // вложенный контроллер
	//private CloseBtnController includedCloseBtnController;
	
	@FXML
	public void initialize() {
        try {
            loadCloseBtn();
            loadOpenBtn();
        } catch (IOException ex) {
            ex.printStackTrace();
        }	
	}
    
    private void loadCloseBtn() throws IOException {
        URL fxmlUrl = this.getClass().getClassLoader().getResource("test/closebutton.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);  
		closeBtn = loader.<Button>load();
        closeBtnController = loader.getController();
    }
    
    private void loadOpenBtn() throws IOException {
        URL fxmlUrl = this.getClass().getClassLoader().getResource("test/openbutton.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlUrl);     
		openBtn = loader.<Button>load();
        openBtnController = loader.getController();
    }
    
    @FXML 
    private void addCloseBtn() {
        //System.out.println("Close");
        addChildToNewPane(closeBtn);
    }
    
    @FXML 
    private void addOpenBtn() {
        //System.out.println("Open");
        addChildToNewPane(openBtn);  
    }
    
    private void addChildToNewPane(Node node) {
        ObservableList<Node> childrens = newPane.getChildren();
        if (!childrens.contains(node)) {
            childrens.clear();
            newPane.getChildren().add(node);
        }
    }
    
    
}
