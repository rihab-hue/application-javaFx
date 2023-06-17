package controler;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import models.Locataire;


public class LocataireController implements Initializable  {
	Connection cox ;
	/*effectuer requete */
	public PreparedStatement st;
	/* traitement resultat*/
	public ResultSet rs;
	
	
	 @FXML
	    private TextField txt_nom;

	    @FXML
	    private TextField txt_tele;

	    @FXML
	    private TableColumn<Locataire, Integer> cin_id;
	    
	    @FXML
	    private TableView<Locataire> table_locataire;

	    @FXML
	    private TableColumn<Locataire, String> cin_nomcomplet;

	    @FXML
	    private TableColumn<Locataire, String> cin_cin;

	    @FXML
	    private TableColumn<Locataire, Date> cin_datenaissance;

	    @FXML
	    private TableColumn<Locataire, String> cin_telephone;

	    @FXML
	    private TextField txt_cin;
	    @FXML
	    private TextField txt_search;

	    @FXML
	    private Button btn_ajouter;

	    @FXML
	    private Button btn_supprimer;

	    @FXML
	    private Button btn_modifier;

	    @FXML
	    private DatePicker datepicker;
	    /*creation liste de locataire dans base donner*/
	     public ObservableList <Locataire> data =FXCollections.observableArrayList();
	     
	     /* pour recuper donner de la base donner*/
	    public void showlocataire() {
		    	/*bch kol mra t3ml ajout my3wdech yaffichie champs recuperer ml base , table y3ml mise jour a chaque fois d'ajout*/
		    	table_locataire.getItems().clear();
		    	String sql = "select * from locataire";
		    	try{	
					 st = cox.prepareStatement(sql);
					 rs = st.executeQuery();
					 /*while tou9eff kii youfww tout locataire 5tr kol mra yl9a w7da izidh f list*/
					while(rs.next()) {
						data.add(new Locataire(rs.getInt("idl"),rs.getString("nomprenom"),rs.getDate("datanaissance"),rs.getString("telephone"),rs.getString("cin")));	
					}
					
		    	}catch(SQLException e) {
		    		e.printStackTrace();
		    	}
		    	/*chaque donnes recuperer placer dans la list data*/
		    	cin_id.setCellValueFactory(new PropertyValueFactory<Locataire, Integer>("idl"));
		    	cin_cin.setCellValueFactory(new PropertyValueFactory<Locataire, String>("cin"));
		    	cin_telephone.setCellValueFactory(new PropertyValueFactory<Locataire, String>("telephone"));
		    	cin_nomcomplet.setCellValueFactory(new PropertyValueFactory<Locataire, String>("nomprenom"));
		    	cin_datenaissance.setCellValueFactory(new PropertyValueFactory<Locataire, Date>("datenaissance"));
		    	/* donner 7tinhom f list n7thom f table */
		    	table_locataire.setItems(data);	    	}
		    
		    
		    /*pour chercher locatair par son cin*/
		    @FXML
		void searchlocataire(MouseEvent event) {
	           String sql6 = "select nomprenom,cin,datanaissance,telephone from locataire where cin='"+txt_search.getText()+"'";
	           int m=0;
	           try {
	        	   st=cox.prepareStatement(sql6);
		             rs=st.executeQuery();
		             if(rs.next()) {
	          		        txt_cin.setText(rs.getString("cin"));
							txt_nom.setText(rs.getString("nomprenom"));
							txt_tele.setText(rs.getString("telephone"));
	          	            Date date = rs.getDate("datanaissance");
	          	            datepicker.setValue(date.toLocalDate());
	          	            m=1; 
	          	        
		             }
		             } catch (SQLException e) {
				e.printStackTrace();
			}
	           if(m==0) {
	        	   Alert alert = new Alert(AlertType.ERROR," aucun locataire   avec cin= "+txt_search.getText()+"",javafx.scene.control.ButtonType.OK);
	        	   alert.showAndWait();
	           }
		    }
		   
		    
	        /*si clique 3l ligne f table bch t3ml supp ou modifier*/
		    @FXML
	    void tablelocatairclick(MouseEvent event) {
	               Locataire locataire =table_locataire.getSelectionModel().getSelectedItem(); 
	               String sql5="select * from locataire where idl=?";
	               try {
	            	   st=cox.prepareStatement(sql5);
	            	   st.setInt(1, locataire.getIdl());
	            	   rs=st.executeQuery();
	            	   if(rs.next()) {
	            		   txt_cin.setText(rs.getString("cin"));
							txt_nom.setText(rs.getString("nomprenom"));
							txt_tele.setText(rs.getString("telephone"));
	            	        Date date = rs.getDate("datanaissance");
	            	        datepicker.setValue(date.toLocalDate());
	            	        txt_search.setText(rs.getString("cin"));
	            	        
	            	   }   
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		    
      /*crude ajouter*/
	    @FXML
	    void addlocataire(MouseEvent event) {
	    	
            String nomprenom =txt_nom.getText();
            String telephone =txt_tele.getText();
            String cin =txt_cin.getText();
            /* requete insertion dans bd*/
            String sql2="insert into locataire(nomprenom,datanaissance,telephone,cin) values(?,?,?,?)"; 
            /*test si user ecrir champs vide*/
            if(!nomprenom.equals("")&& !telephone.equals("") && !cin.equals("")&& !datepicker.getValue().equals(null)) {
            	try {
            		st=cox.prepareStatement(sql2);
            		/*kol champs ? attribuer sa valeur saisir*/
            		
            		st.setString(1, nomprenom);
            		
            		java.util.Date date = java.util.Date.from(datepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					Date sqlDate= new Date(date.getTime());
					st.setDate(2,sqlDate);
					st.setString(3,telephone);
					st.setString(4,cin);
					/*executer requete*/
					st.execute();
					/* apres ajout retourn champs vide*/
					txt_cin.setText("");
					txt_nom.setText("");
					txt_tele.setText("");
					datepicker.setValue(null);
					/*alert pour confirmation d'ajout*/
					Alert alert = new Alert(AlertType.CONFIRMATION,"locataire ajouter avec succés",javafx.scene.control.ButtonType.OK);
					alert.showAndWait();
					showlocataire();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} 
            	
            	}else {
            		Alert alert = new Alert(AlertType.WARNING,"veuillez remplir tous les champs",javafx.scene.control.ButtonType.OK);
					alert.showAndWait();
            	}
            }
	    	

	    
	    /*crude modifier*/
	    @FXML
	    void modifylocataire(MouseEvent event) {
	         
	    	 String nomprenom =txt_nom.getText();
	            String telephone =txt_tele.getText();
	            String cin =txt_cin.getText();
	            String sql3 = "update locataire set nomprenom=?, datanaissance=? ,telephone=?, cin=? where cin='"+txt_search.getText()+"'";
	            
	            if(!nomprenom.equals("")&& !telephone.equals("") && !cin.equals("")&& !datepicker.getValue().equals(null)) {
	            	try {
	            		st=cox.prepareStatement(sql3);
	            		/*kol champs ? attribuer sa valeur saisir*/
	            		st.setString(1, nomprenom);
	            		
	            		java.util.Date date = java.util.Date.from(datepicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
						Date sqlDate= new Date(date.getTime());
						st.setDate(2,sqlDate);
						st.setString(3,telephone);
						st.setString(4,cin); 
						/*executer requete*/
						st.execute();
						/* apres ajout retourn champs vide*/
						txt_cin.setText("");
						txt_nom.setText("");
						txt_tele.setText("");
						datepicker.setValue(null);
						/*alert pour confirmation d'ajout*/
						Alert alert = new Alert(AlertType.CONFIRMATION,"locataire modifier avec succés",javafx.scene.control.ButtonType.OK);
						alert.showAndWait();
						showlocataire();
						
					    }catch (SQLException e) {
						e.printStackTrace();} 
	            	}else {
	            		Alert alert = new Alert(AlertType.WARNING,"veuillez remplir tous les champs",javafx.scene.control.ButtonType.OK);
						alert.showAndWait();
	            	}
	            }
		    	
	           
	    /*crude aupprimer jouter*/
	    @FXML
	    
	    void supplocataire(MouseEvent event) {
	    	try {

	            String Sql4 = "delete from locataire where cin='"+txt_search.getText()+"'";
	            st=cox.prepareStatement(Sql4);
	             st.executeUpdate();
	             txt_cin.setText("");
	 			txt_nom.setText("");
	 			txt_tele.setText("");
	 			datepicker.setValue(null);
	 			Alert alert = new Alert(AlertType.CONFIRMATION,"locataire supprimer  avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showlocataire();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
           
	    }
	    
	    
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*etablir cox*/
		 cox = ConnexionMysql.connexionDB();
		 /*appel fonction*/
		 showlocataire();
		
	}

}
