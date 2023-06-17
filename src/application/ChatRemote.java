package application;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import models.Message;

public interface ChatRemote extends Remote{
	
	public void addMsg(Message msg)  throws RemoteException;
	public ArrayList<Message> getMessg() throws RemoteException;

}
