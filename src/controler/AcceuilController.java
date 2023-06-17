package controler;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AcceuilController implements Initializable {
	Connection cox ;
	/*effectuer requete */
	public PreparedStatement st;
	/* traitement resultat*/
	public ResultSet rs;
	 
	    @FXML
	    private TextField text_loyer;

	    @FXML
	    private TextField txt_region;
	    
	    @FXML
	    private TextField txt_superficie;

	    @FXML
	    private ImageView precedent;

	    @FXML
	    private ImageView suivant;
	    
	    @FXML
	    private Label nbr;
	      
	    @FXML
	    private TextField txt_adr;

 /* /*boutton precedent*/
	    @FXML
	    void showpreced(MouseEvent event) {
	    	String adr = txt_adr.getText();
	    	/* fixer 1 er logement */
	    	String sql3 ="select id from logement where adrl ='"+adr+"'";
	    	int position = 0;
	    	try {
	    		 st = cox.prepareStatement(sql3);
				 rs = st.executeQuery();
				 if(rs.next()) {
						 /*pour stoker idlogement*/
						 position=rs.getInt("id");
						}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	/*recuperer donner de logement  non louer  */
	    	
	    	String sql4 ="select superficie,nomRegion,loyer,adrl from logement,region where id not in(select logement from location)and region.idr=logement.region and id not in(select logement from location)and logement.id <'"+position+"'" ;
	    	int loyer = 0;
	    	int superficie =0;
	    	
	    	try {
	    		
				 st = cox.prepareStatement(sql4);
				 rs = st.executeQuery();
				if(rs.next()) {
					/* recuperer donner et affecter donner */ 
					loyer = rs.getInt("loyer");
					text_loyer.setText(Integer.toString(loyer));
				     superficie = rs.getInt("superficie");
					txt_superficie.setText(Integer.toString(superficie));
				    txt_region.setText(rs.getString("nomRegion"));
				    txt_adr.setText(rs.getString("adrl"));
				    
				
				}else { System.out.println("auccun");
				}
				 
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}

	    }
        /*boutton suivant*/
	    @FXML
	    void showsuiv(MouseEvent event) {
	    	String adr = txt_adr.getText();
	    	/* fixer 1 er logement */
	    	String sql3 ="select id from logement where adrl ='"+adr+"'";
	    	int position = 0;
	    	try {
	    		 st = cox.prepareStatement(sql3);
				 rs = st.executeQuery();
				 if(rs.next()) {
						 /*pour stoker idlogement*/
						 position=rs.getInt("id");
						}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	/* pour afficher logement disponible */
	    	
	    	String sql4 ="select superficie,nomRegion,loyer,adrl from logement,region where id not in(select logement from location)and region.idr=logement.region and id not in(select logement from location)and logement.id >'"+position+"'" ;
	    	int loyer = 0;
	    	int superficie =0;
	    	
	    	try {
	    		
				 st = cox.prepareStatement(sql4);
				 rs = st.executeQuery();
				if(rs.next()) {
					/* recuperer donner et affecter donner */ 
					loyer = rs.getInt("loyer");
					text_loyer.setText(Integer.toString(loyer));
				     superficie = rs.getInt("superficie");
					txt_superficie.setText(Integer.toString(superficie));
				    txt_region.setText(rs.getString("nomRegion"));
				    txt_adr.setText(rs.getString("adrl"));
				   
				
				}
				else { System.out.println("auccun");
				}
				 
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	    
	    
	    
	    /*afficher nbr de  logement disponible*/
	    @FXML
	   public void showlogement() {
	    	
	    	/* nomber logement dispo non louer*/
	    	String sql="select count(*) from logement where id not in (select logement from location)";
	    	int i = 0;
	    	try {
				 st = cox.prepareStatement(sql);
				 rs = st.executeQuery();
				if(rs.next()) {
					/*compteur incremente nbr logement*/
					i=rs.getInt(1);
				}
				nbr.setText(Integer.toString(i));	
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    	
	    	
	    	
	    	/* requete formulaire acceuil*/
	    	String sql2="select superficie,nomRegion,loyer,adrl from logement,region where region.idr=logement.region and id not in(select logement from location)";
	    	int loyer = 0;
	    	int superficie =0;
	    	
	    	try {
	    		
				 st = cox.prepareStatement(sql2);
				 rs = st.executeQuery();
				if(rs.next()) {
					/* recuperer donner et affecter donner */ 
					loyer = rs.getInt("loyer");
					text_loyer.setText(Integer.toString(loyer));
				     superficie = rs.getInt("superficie");
					txt_superficie.setText(Integer.toString(superficie));
				    txt_region.setText(rs.getString("nomRegion"));
				    txt_adr.setText(rs.getString("adrl"));
				   
				
				}else { 
					System.out.println("auccun");
				}
				
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
	    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*etablir cox*/
		 cox = ConnexionMysql.connexionDB();
		 /*appel fonction*/
		 showlogement();
		
	}

	
}
