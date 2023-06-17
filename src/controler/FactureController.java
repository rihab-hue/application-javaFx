package controler;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FactureController  implements Initializable {

	Connection cox ;
	/*effectuer requete */
	public PreparedStatement st;
	/* traitement resultat*/
	public ResultSet rs;
   

    @FXML
    private TextField txt_montant;

    @FXML
    private TextField txt_region;

    @FXML
    private TextField txt_locataire;

    @FXML
    private TextField txt_loyer;

    @FXML
    private TextField txt_adress;

    @FXML
    private TextField txt_type;

    @FXML
    private ComboBox<Integer> cb_logement;

    @FXML
    private ComboBox<String> cb_locataire;

    @FXML
    private TextField txt_forfaitaire;

    @FXML
    private ImageView txt_search;

   
    /*POUR remplir cobx locataire*/
    @FXML
    void remplirlocataire(MouseEvent event) {
    	String sql2= "select nomprenom from locataire,location where locataire.idl=location.locataire and location.logement='"+cb_logement.getValue()+"'";
    	
        List<String> ls2=new ArrayList<String>();
        try {
  		st=cox.prepareStatement(sql2);
  		rs=st.executeQuery();
  		while(rs.next()) {
  			ls2.add(rs.getString("nomprenom"));
  			
  		}
  	} catch(SQLException e) {
  		e.printStackTrace();
  	}
        cb_locataire.setItems(FXCollections.observableArrayList(ls2));

    }

    /*POUR remplir cobx logement*/
    @FXML
    void remplirlogement(MouseEvent event) {
      String sql1= "select DISTINCT logement  from location";
      List<Integer> ls=new ArrayList<Integer>();
      try {
		st=cox.prepareStatement(sql1);
		rs=st.executeQuery();
		while(rs.next()) {
			ls.add(rs.getInt("logement"));
			
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
      cb_logement.setItems(FXCollections.observableArrayList(ls));
      
    }
    
    /*bch ijib donner ml base wy3mell calcule*/
    @FXML
    void search(MouseEvent event) {
    	Integer id = cb_logement.getValue();
    	String Sql3="select adrl, loyer,nomType,forfaitaire,nomRegion from logement , type, region where logement.type=type.idt and logement.region=region.idr and logement.id='"+id+"'";
          int montant;
    	try {
        	  st=cox.prepareStatement(Sql3);
      		rs=st.executeQuery();
      		if(rs.next()) {
      			txt_adress.setText(rs.getString("adrl"));
      			int forf =rs.getInt("forfaitaire");
				txt_forfaitaire.setText(String.valueOf(forf));
				int loy =rs.getInt("loyer");
				txt_loyer.setText(String.valueOf(loy));
				txt_type.setText(rs.getString("nomType"));
				txt_region.setText(rs.getString("nomRegion"));
				txt_locataire.setText(cb_locataire.getValue());
				montant =forf+loy;
				txt_montant.setText(String.valueOf(montant));
				
      		}
		} catch(SQLException e) {
			e.printStackTrace();}
    }
    
    
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 cox = ConnexionMysql.connexionDB();
		
	}


}
