package controler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class IdentifierController  implements Initializable {
	Connection cox ;
	/*effectuer requete */
	public PreparedStatement st;
	/* traitement resultat*/
	public ResultSet rs;
	
	
	
	@FXML
    private AnchorPane vb;
	@FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private Button connecter;
    @FXML
    private Parent fxml;
    

    @FXML
    void openHome(ActionEvent event)  {
    	String nom = txt_username.getText();
    	String pass = txt_password.getText();
    	/* defenir requete simple*/
    	String sql = "select username,password from admin";
    	/*execute requete*/
    	try {
    		st=cox.prepareStatement(sql);
    		rs=st.executeQuery();
    		/*parcours resultat*/
    		if(rs.next()) {
    			if(nom.equals(rs.getString("username"))&& pass.equals(rs.getString("password"))) {
    	    		System.out.println("bien");
    	    		//sortie ml page login
    	    	    vb.getScene().getWindow().hide();
    	    	    Stage home = new Stage();
    	    	    try {
    	                //ouverture home page
    	    	    	fxml = FXMLLoader.load(getClass().getResource("/interfaces/home.fxml"));
    	    	    	Scene scene = new Scene(fxml);
    	    	    	home.setScene(scene);
    	    	    	home.show();
    	    	} catch(IOException e) {
    				e.printStackTrace();
    			}
    	    	    
    	    	}else {
    	    		/*alert pour message error*/
    	    		Alert alert = new Alert (AlertType.ERROR,"nom utilisateur ou mot de passe incorrect:",javafx.scene.control.ButtonType.OK);
    	    	    alert.showAndWait();
    	    	}
    	    	
    		}
    		}catch(SQLException e1) {
    			e1.printStackTrace(); 
    		}
    	
    	
    	
    	
    	
    	
    	//test nom et mot passe
    }
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*etablir cox*/
		cox =ConnexionMysql.connexionDB();
		
	}

	
	
}
