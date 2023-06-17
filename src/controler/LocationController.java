package controler;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LocationController  implements Initializable  {
	Connection cox ;
	/*effectuer requete */
	public PreparedStatement st;
	/* traitement resultat*/
	public ResultSet rs;
	
	
	@FXML
    private Button bntadd;

    @FXML
    private TextField txt_searchlogement;

    @FXML
    private TextField txt_searchlocataire;

    @FXML
    private TextField txt_region;
    @FXML
    private TextField txt_cin;

    @FXML
    private TextField txt_nom;
 
    @FXML
    private TextField txt_type;

    @FXML
    private TextField txt_loyer;

    @FXML
    private TextField txt_adress;

    @FXML
    private TextField txt_telephone;

    @FXML
    private DatePicker txt_debut;

    @FXML
    private DatePicker txt_fin;

    
    /*pour chercher locatair par son cin*/
    @FXML
    void searchlocataire(MouseEvent event) {
    	String sql1 = "select nomprenom,cin,telephone from locataire where cin='"+txt_searchlocataire.getText()+"'";
        int m=0;/*pour tester existance locataire*/
        try {
     	   st=cox.prepareStatement(sql1);
	       rs=st.executeQuery();
	       if(rs.next()) {
       		            txt_cin.setText(rs.getString("cin"));
						txt_nom.setText(rs.getString("nomprenom"));
						txt_telephone.setText(rs.getString("telephone"));
						txt_searchlocataire.setText("");
					     m=1; 
	             }
	             } catch (SQLException e) {
			e.printStackTrace();
		}
        if(m==0) {
     	   Alert alert = new Alert(AlertType.ERROR," aucun locataire   avec cin= "+txt_searchlocataire.getText()+"",javafx.scene.control.ButtonType.OK);
     	   alert.showAndWait();}}
    	
    	
    /*pour chercher logement par son id*/
    @FXML
    void searchlogement(MouseEvent event) {
    	String sql2 = "select adrl, loyer,type,region from logement , region, type where  logement.region=region.idr and logement.type=type.idt and logement.id='"+txt_searchlogement.getText()+"'";
    	 int m=0;
    	try {
     	   st=cox.prepareStatement(sql2);
     	   rs=st.executeQuery();
     	   if(rs.next()) {
					txt_adress.setText(rs.getString("adrl"));
					int loyer=rs.getInt("loyer");
					txt_loyer.setText(String.valueOf(loyer));
		            txt_type.setText(rs.getString("type"));
				    txt_region.setText(rs.getString("region"));     
				    txt_searchlogement.setText("");
     	            m=1;
     	   }
     	   
		} catch (SQLException e) {
			e.printStackTrace();
		}if(m==0) {
	     	   Alert alert = new Alert(AlertType.ERROR," aucun logement avec identifiant = '"+txt_searchlogement.getText()+"'",javafx.scene.control.ButtonType.OK);
	     	   alert.showAndWait();
	        }
    	} 
    
    
    
     /*pour tester w9et eno logement disponible ou non 
      * t3mell test ben el w9ett eli bch t3mlou ajouter wl w9t eli f base  */
    public Boolean isBetween(java.sql.Date my_date, java.sql.Date my_debut ,java.sql.Date my_fin) {
    	
		return (my_date.equals(my_debut)||my_date.after(my_debut))&& (my_date.equals(my_fin)||my_date.before(my_fin));
		} 
    
/*ajouter location par temps*/    
    @FXML
    void addlocation(MouseEvent event) {
    	/*stocker id locataire choisir  fi variable  pour ajouter dans location*/
    	String sql5 = "select idl from locataire where cin='"+txt_cin.getText()+"' ";
    	int locataire=0;
    	try {
			 st=cox.prepareStatement(sql5);
			 rs=st.executeQuery();
			  if(rs.next()) {
				  locataire=rs.getInt("idl");
			  }
		} catch (SQLException e) {
			e.printStackTrace();}
      
    	/*stocker id logement  choisir fi variable  pour ajouter dans location*/
    	String sql6 = "select id from logement where adrl='"+txt_adress.getText()+"'";
        int logement=0;
        try {
        	st=cox.prepareStatement(sql6);
			 rs=st.executeQuery();
			  if(rs.next()) {
				  logement=rs.getInt("id");
		} }catch (SQLException e) {
			e.printStackTrace();}
       
        
        /*stoker date eli d5lo user f des variable*/
         java.util.Date datedd= Date.from(txt_debut.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
         java.sql.Date dateDebut = new java.sql.Date(datedd.getTime());
         java.util.Date datedff= Date.from(txt_fin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
         java.sql.Date dateFin = new java.sql.Date(datedff.getTime());
         
         /*pour tester logement choisir pour ajouter location disponible ou non selon date eli d5lo*/
         String sql7 = "select datedebut , datefin from location where logement ='"+logement+"'";
         boolean fin = false;
         boolean debut = false;
         java.sql.Date dated = null;
         java.sql.Date datef = null;
         Date d = null;
         Date f = null;
         
        try {
        	 st=cox.prepareStatement(sql7);
			 rs=st.executeQuery();
			 while(rs.next()) {
				 /*n3mlo test ben eli d5lto weli f base*/
				 dated=rs.getDate("datedebut");
				 datef= rs.getDate("datefin");
				 if(isBetween(dateFin, dated, datef)==true) {
					 fin = true;
				 }
				 if(isBetween(dateDebut, dated, datef)==true) {
					 debut = true; 
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();} 
		
        /*ken w9ett hw bido t9olo occuper si non n3mlo insertion b date eli 7tito*/
        if(fin==true || debut ==true) {
        	 Alert alert = new Alert(AlertType.WARNING," ce logement est occuper pandant cette duree entre "+dated+" et "+datef+" ",ButtonType.OK);
      	     alert.showAndWait();
        }else {
        	String sql8="insert into location(logement,locataire,dateDebut,dateFin) values(?,?,?,?)";
        	try {
        		 st=cox.prepareStatement(sql8);
           	  st.setInt(1, logement);
           	  st.setInt(2, locataire);
           	  st.setDate(3, dateDebut);
           	  st.setDate(4, dateFin);
           	  st.executeUpdate();
           	  txt_adress.setText("");
           	  txt_cin.setText("");
           	  txt_loyer.setText("");
			  txt_nom.setText("");
			  txt_region.setText("");
			  txt_telephone.setText("");
			  txt_type.setText("");
			  this.txt_debut.setValue(null);
			  this.txt_fin.setValue(null);
			/*alert pour confirmation d'ajout*/
			  Alert alert = new Alert(AlertType.CONFIRMATION,"le logement ajouter avec succés",ButtonType.OK);
			  alert.showAndWait();
			} catch (SQLException e) {
				e.printStackTrace();} 	
        }
           
    }
   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*etablir cox*/
		 cox = ConnexionMysql.connexionDB();
		
	}

}
