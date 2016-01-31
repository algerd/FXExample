
package fxexample.start;

import fxexample.interfaces.impls.CollectionAddressBook;
import fxexample.objects.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Alex
 */
public class Main extends Application {
     public static void main(String[] args) {
        launch(args);
    }
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxexample/fxml/main.fxml"));
             
        primaryStage.setTitle("");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        
        testData();
    }

    private void testData() {
        CollectionAddressBook addressBook = new CollectionAddressBook();
        addressBook.fillTestData();
        addressBook.print();
    }
   
    
    
}
