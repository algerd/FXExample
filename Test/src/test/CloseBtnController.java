
package test;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Контроллер вложенного fxml-документа.
 * Именован в соответствии с правилом:
 * fx:id корневого элемента + Controller = closeBtn + Controller = CloseBtnController
 */
public class CloseBtnController {
    
	@FXML
	private Button closeBtn;
	
	@FXML
	public void initialize() {
		//System.out.println("CloseBtnController.initialize()");
	}
    
    @FXML 
    private void showCloseMsg() {
        System.out.println("Close Button Message");
    }
}
