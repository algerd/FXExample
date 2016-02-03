
package address_book.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogManager {
    
    public static void showInfoDialog(String title, String text) {
        showDialog(AlertType.INFORMATION, title, text, "");      
    }
    
    public static void showErrorDialog(String title, String text) {
        showDialog(AlertType.ERROR, title, text, ""); 
    }
    
    public static void showWarningDialog(String title, String text) {
        showDialog(AlertType.WARNING, title, text, ""); 
    }
    
    public static void showDialog(AlertType alerTtype, String title, String contentText, String headerText) {
        if (alerTtype != AlertType.CONFIRMATION) {
            Alert alert = new Alert(alerTtype);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
        }
    }

}
