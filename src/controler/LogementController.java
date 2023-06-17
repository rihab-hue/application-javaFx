package controler;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Locataire;
import models.Logement;


public class LogementController  implements Initializable{
	Connection cox ;
	/*effectuer requete */
	public PreparedStatement st;
	/* traitement resultat*/
	public ResultSet rs;

	@FXML
    private TextField txt_adr;

    @FXML
    private TextField txt_superficie;

    @FXML
    private TextField txt_loyer;

    @FXML
    private ComboBox<String> cb_region;

    @FXML
    private ComboBox<String> cb_type;

    
    @FXML
    private TableView<Logement> table_logement;
    @FXML
    private TableColumn<Logement, Integer> cin_id;

    @FXML
    private TableColumn<Logement, String> cin_adr;

    @FXML
    private TableColumn<Logement,Integer > cin_superficie;

    @FXML
    private TableColumn<Logement, Integer> cin_loyer;

    @FXML
    private TableColumn<Logement, String> cin_type;

    @FXML
    private TableColumn<Logement, String> cin_region;

    @FXML
    private Button btnadd;
    @FXML
    private TextField txt_search;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnmodifier;

    
    /*creation liste  listlog n7oto fhaa donner recuperer ml base */
    public ObservableList<Logement> listlog =FXCollections.observableArrayList();
    /*affichage donner ml base fi tableau*/
    public void showlogement() {
    	table_logement.getItems().clear();
    	String sql = "select id,adrl,superficie , loyer,type,region from logement,region,type where logement.region=region.idr and logement.type=type.idt";
    	try{	
			 st = cox.prepareStatement(sql);
			 rs = st.executeQuery();
			 /*while tou9eff kii youfww tout locataire 5tr kol mra yl9a w7da izidh f list*/
			while(rs.next()) {
				listlog.add(new Logement (rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6)));	
			}
			
   	}catch(SQLException e) {
   		e.printStackTrace();
   	}
    	/*chaque donnes recuperer placer dans la list data*/
    	cin_id.setCellValueFactory(new PropertyValueFactory<Logement, Integer>("id"));
    	cin_adr.setCellValueFactory(new PropertyValueFactory<Logement, String>("adrl"));
    	cin_superficie.setCellValueFactory(new PropertyValueFactory<Logement, Integer>("superficie"));
    	cin_loyer.setCellValueFactory(new PropertyValueFactory<Logement, Integer>("loyer"));
    	cin_region.setCellValueFactory(new PropertyValueFactory<Logement, String>("region"));
    	cin_type.setCellValueFactory(new PropertyValueFactory<Logement, String>("type"));
    	/* donner 7tinhom f list n7thom f table */
    	table_logement.setItems(listlog);	    	
    	}  
   
    
    
    /*combox type*/
    public void remplirType() {
    	String sql2="select nomType from type ";
    	List<String> types= new ArrayList<String>();
    	try {
    	st = cox.prepareStatement(sql2);
		 rs = st.executeQuery();
		 /*while tou9eff kii youfww tout type 5tr kol mra yl9a w7da izidh f list*/
		while(rs.next()) {
	       types.add(rs.getString("nomType"));		
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
    cb_type.setItems(FXCollections.observableArrayList(types));
    }  
    
 
    /*combox region*/
    public void remplirregion() {
    	String sql3="select nomRegion from region ";
    	List<String> types= new ArrayList<String>();
    	try {
    	st = cox.prepareStatement(sql3);
		 rs = st.executeQuery();
		 /*while tou9eff kii youfww tout type 5tr kol mra yl9a w7da izidh f list*/
		while(rs.next()) {
	       types.add(rs.getString("nomRegion"));		
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
    cb_region.setItems(FXCollections.observableArrayList(types));
    }  
    
    
      
    /*si click 3l ligne f tableau ythhro fi label*/
    @FXML
    void clicktable(MouseEvent event) {
    	Logement Logement =table_logement.getSelectionModel().getSelectedItem(); 
    	String sql4 = "select id,adrl,superficie , loyer,type,region from logement , region, type where  logement.region=region.idr and logement.type=type.idt and id=?";
    			 try {
              	   st=cox.prepareStatement(sql4);
              	   st.setInt(1, Logement.getId());
              	   rs=st.executeQuery();
              	   while(rs.next()) {
              		   int id=rs.getInt("id");
              		    txt_search.setText(String.valueOf(id));
  						txt_adr.setText(rs.getString("adrl"));
  						int superficie=rs.getInt("superficie");
  						txt_superficie.setText(String.valueOf(superficie));
  						int loyer=rs.getInt("loyer");
  						txt_loyer.setText(String.valueOf(loyer));
  			            cb_type.setValue(rs.getString("type"));
  					    cb_region.setValue(rs.getString("region"));     
              	   }
              	   
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}}
    
 
    /*chercher logement a traver id logement*/
    @FXML
    void searchlogement(MouseEvent event) {
    
    
    	String sql1 = "select id,adrl,superficie , loyer,type,region from logement , region, type where  logement.region=region.idr and logement.type=type.idt and id=?";
    			 try {
              	   st=cox.prepareStatement(sql1);
              	   st.setString(1, txt_search.getText());
              	   rs=st.executeQuery();
              	   while(rs.next()) {
              		   int id=rs.getInt("id");
              		    txt_search.setText(String.valueOf(id));
  						txt_adr.setText(rs.getString("adrl"));
  						int superficie=rs.getInt("superficie");
  						txt_superficie.setText(String.valueOf(superficie));
  						int loyer=rs.getInt("loyer");
  						txt_loyer.setText(String.valueOf(loyer));
  			            cb_type.setValue(rs.getString("type"));
  					    cb_region.setValue(rs.getString("region"));     
              	   }
              	   
  			} catch (SQLException e) {
  				e.printStackTrace();
  			}} 
    	
    	
    
    
    
    /*crude ajouter*/
    @FXML
    void addlogement(MouseEvent event) {
    	/*stocker donner  entrer par user dans des variables pour ajouter*/
    	 String adrl =txt_adr.getText();
         String superf =txt_superficie.getText();
         int superficie=Integer.parseInt(superf);
         String loy =txt_loyer.getText();
         int loyer=Integer.parseInt(loy);
         
         String typ =cb_type.getValue();
         String sql5="select idt from type where nomType='"+typ+"' ";
         int type=0;
         try {
        	 st=cox.prepareStatement(sql5);
        	 rs=st.executeQuery();
        	 if(rs.next()) {
        		 type=rs.getInt("idt");
        	 }
        	 
			
		} catch (SQLException e) {
				e.printStackTrace();
			}
         String reg =cb_region.getValue();
         String sql6="select idr from region where nomRegion='"+reg+"' ";
         int region=0;
         try {
        	 st=cox.prepareStatement(sql6);
        	 rs=st.executeQuery();
        	 if(rs.next()) {
        		 region=rs.getInt("idr");
        	 }
			
		} catch (SQLException e) {
				e.printStackTrace();
			}
         /*inserer dans la base*/
         String sql7="insert into logement(adrl,superficie,loyer,region,type) values(?,?,?,?,?)"; 
          try {
        	  st=cox.prepareStatement(sql7);
        	  st.setString(1, adrl);
        	  st.setInt(2, superficie);
        	  st.setInt(3, loyer);
        	  st.setInt(4, region);
        	  st.setInt(5, type);
        	  st.executeUpdate();
        	  showlogement();
        	  /*vider le chams apres l'ajout*/
        	  txt_adr.setText("");
				txt_superficie.setText("");
				txt_loyer.setText("");
				txt_search.setText("");
				cb_type.setValue("type"); 
				cb_region.setValue("region"); 
				/*alert pour confirmation d'ajout*/
				Alert alert = new Alert(AlertType.CONFIRMATION,"logement ajouter avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
         	 
		}  catch (SQLException e) {
			e.printStackTrace();
         
		}
    }
    /*crude modifier*/
    @FXML
    void modifylogement(MouseEvent event) {
      
    	/*stocker donner  entrer par user dans des variables pour ajouter*/
   	 String adrl =txt_adr.getText();
        String superf =txt_superficie.getText();
        int superficie=Integer.parseInt(superf);
        String loy =txt_loyer.getText();
        int loyer=Integer.parseInt(loy);
        
        String typ =cb_type.getValue();
        String sql5="select idt from type where nomType='"+typ+"' ";
        int type=0;
        try {
       	 st=cox.prepareStatement(sql5);
       	 rs=st.executeQuery();
       	 if(rs.next()) {
       		 type=rs.getInt("idt");
       	 }
       	 
			
		} catch (SQLException e) {
				e.printStackTrace();
			}
        String reg =cb_region.getValue();
        String sql6="select idr from region where nomRegion='"+reg+"' ";
        int region=0;
        try {
       	 st=cox.prepareStatement(sql6);
       	 rs=st.executeQuery();
       	 if(rs.next()) {
       		 region=rs.getInt("idr");
       	 }
			
		} catch (SQLException e) {
				e.printStackTrace();
			}
        /*inserer dans la base*/
        String sql8="update  logement set adrl=?,superficie=?,loyer=?,region=?,type=? where id='"+txt_search.getText()+"'"; 
         try {
       	  st=cox.prepareStatement(sql8);
       	  st.setString(1, adrl);
       	  st.setInt(2, superficie);
       	  st.setInt(3, loyer);
       	  st.setInt(4, region);
       	  st.setInt(5, type);
       	  st.executeUpdate();
       	  showlogement();
       	  /*vider le chams apres l'ajout*/
       	  txt_adr.setText("");
				txt_superficie.setText("");
				txt_loyer.setText("");
				txt_search.setText("");
				cb_type.setValue("type"); 
				cb_region.setValue("region"); 
				/*alert pour confirmation modification*/
				Alert alert = new Alert(AlertType.CONFIRMATION,"logement modifier avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
        	 
		}  catch (SQLException e) {
			e.printStackTrace();
        
		}
    	
    }
  /*crude supprimer*/
    @FXML
    void supplogement(MouseEvent event) {

    	 String Sql9 = "delete from logement where id='"+txt_search.getText()+"'";
       try {
    	   st=cox.prepareStatement(Sql9);
    	   st.executeUpdate();
    	   showlogement();
    	   txt_adr.setText("");
			txt_superficie.setText("");
			txt_loyer.setText("");
			txt_search.setText("");
			cb_type.setValue("type"); 
			cb_region.setValue("region"); 
			/*alert pour confirmation suppr*/
			Alert alert = new Alert(AlertType.CONFIRMATION,"logement supprimer avec succés",javafx.scene.control.ButtonType.OK);
			alert.showAndWait();
    	   

    	   
    	   
       }catch (Exception e) {
		// TODO: handle exception
	}
    
    
    }
    
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*etablir cox*/
		 cox = ConnexionMysql.connexionDB();
		 /*appel fonction*/
		 showlogement();
		 remplirType();
		 remplirregion();
		
	}
    
	
}
	

	
	

