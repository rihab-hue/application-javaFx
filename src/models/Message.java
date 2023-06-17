package models;
import java.io.Serializable;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 437193972099852740L;

String nom;
String msg;
Message(){}
public Message(String nom, String msg) {
	super();
	this.nom = nom;
	this.msg = msg;
	
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}

@Override
public String toString() {
	return "Message [nom=" + nom + ", msg=" + msg + "]";
}

}
