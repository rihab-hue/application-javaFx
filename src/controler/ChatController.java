package controler;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ChatRemote;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.Message;

public class ChatController implements Initializable {
	ChatRemote x=null;
	String nom="Anonyme";
	 @FXML
	    private Button btn_send;

	    @FXML
	    private TextField txtsend;

	    @FXML
	    private TextArea txt_area;

	    @FXML
	    void send(MouseEvent event) throws RemoteException {
	    	
	         
			x.addMsg(new Message(nom,txtsend.getText()));
		
	    }
	    public void connect_client() {

	    
			try {
			
			 x= (ChatRemote) Naming.lookup("rmi://127.0.0.1/chatRemote");
			 
				System.out.println("client connecter");
			} catch (MalformedURLException e) {
				System.out.println("erreur url "+e.getMessage());
			} catch (RemoteException e) {
				System.out.println("erreur remote "+e.getMessage());
			} catch (NotBoundException e) {
				System.out.println("erreur serveur "+e.getMessage());
			}
			
			
			
		}
	    class Liremsg extends Thread{

			public void run()
			{
				while(true)
				{
					try {
						txt_area.setText("");
						ArrayList<Message> l=x.getMessg();
						for(int i=0;i<l.size();i++)
						{
							txt_area.appendText(l.get(i).getNom()+"\n" + ":" +l.get(i).getMsg()+"\n");
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			//connect_client();
			new Liremsg().start();
		}

}
