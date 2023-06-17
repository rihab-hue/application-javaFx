package controler;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MainController  implements Initializable{ 

	
	  @FXML
	    private Button btn_connecter;

	    @FXML
	    private Button btn_inscrire;
	    @FXML
	    private VBox Vbox;
	    @FXML
	    private Parent fxml;

	    @FXML
	    void openSignin() {
	    	try {
				Parent root = FXMLLoader.load(getClass().getResource("/interfaces/identifier.fxml"));
				Scene scene = new Scene(root);
				
			    Stage primaryStage = new Stage();
			    primaryStage.setTitle("location des logements");
			    primaryStage.getIcons().add(new Image("https://c8.alamy.com/compfr/2ekharx/location-appartement-rgb-couleur-icone-2ekharx.jpg")); 
				primaryStage.setScene(scene);	
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}

	    	

	    }
	    void openSignup() {
	    	
	    }

	   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	 
	}

}
