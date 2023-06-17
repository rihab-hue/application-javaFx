package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable{
	@FXML
    private Parent fxml;
	@FXML
    private AnchorPane root;
	 /*integrer interface acceul*/
	 @FXML
	    void accueil(MouseEvent event){
		 
		 try {
             //ouverture acceueil page
 	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/accueil.fxml"));
 	    	root.getChildren().removeAll();
 	    	root.getChildren().setAll(fxml);
 	    	
		 	} catch(IOException e) {
			e.printStackTrace();
		 	}
	 }
		
	 
	 /*integrer interface facture*/
	    @FXML
	    void facture(MouseEvent event) {
	    	 try {
	             //ouverture acceueil page
	 	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/Facture.fxml"));
	 	    	root.getChildren().removeAll();
	 	    	root.getChildren().setAll(fxml);
	 	    	
			 	} catch(IOException e) {
				e.printStackTrace();
			 	}
	    }

	    /*integrer interface locataire*/
	    @FXML
	    void locataire(MouseEvent event) {
	    	 try {
	             //ouverture acceueil page
	 	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/locataire.fxml"));
	 	    	root.getChildren().removeAll();
	 	    	root.getChildren().setAll(fxml);
	 	    	
			 	} catch(IOException e) {
				e.printStackTrace();
			 	}
	    }

	    
	    /*integrer interface location*/
	    @FXML
	    void location(MouseEvent event) {
	    	 try {
	             //ouverture acceueil page
	 	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/location.fxml"));
	 	    	root.getChildren().removeAll();
	 	    	root.getChildren().setAll(fxml);
	 	    	
			 	} catch(IOException e) {
				e.printStackTrace();
			 	}
	    }

	    
	    /*integrer interface logement*/
	    @FXML
	    void logement(MouseEvent event) {
	    	 try {
	             //ouverture acceueil page
	 	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/logement.fxml"));
	 	    	root.getChildren().removeAll();
	 	    	root.getChildren().setAll(fxml);
	 	    	
			 	} catch(IOException e) {
				e.printStackTrace();
			 	}
	    }
	    	 
	    	 @FXML
	 	    void chat(MouseEvent event) {
	 	    	 try {
	 	             //ouvertur chat page
	 	 	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/chat.fxml"));
	 	 	    	root.getChildren().removeAll();
	 	 	    	root.getChildren().setAll(fxml);
	 	 	    	
	 			 	} catch(IOException e) {
	 				e.printStackTrace();
	 			 	}
	    }

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
            //ouverture acceueil page
	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/accueil.fxml"));
	    	/*pour vider intterface home*/
	    	root.getChildren().removeAll();
	    	 /*remplir tout ficher fxml*/
	    	root.getChildren().setAll(fxml);
	    	
		 	} catch(IOException e) {
			e.printStackTrace();
		 	}
		
	}

}
