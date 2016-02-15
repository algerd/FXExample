
package test;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Контроллер вложенного fxml-документа.
 * Именован в соответствии с правилом:
 * fx:id корневого элемента + Controller = openBtn + Controller = OpenBtnController
 */
public class OpenBtnController {
	@FXML
	private Button openBtn;
	
	@FXML
	public void initialize() {
		//System.out.println("OpenBtnController.initialize()");
	}
    
    @FXML 
    private void showOpenMsg() {
        System.out.println("Open Button Message");
    }
      
}
