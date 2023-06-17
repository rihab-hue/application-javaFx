package models;

import java.sql.Date;

public class Locataire {
	
	int idl;
	String nomprenom; 
	Date datenaissance;
	String telephone;
	String cin;
	
	public Locataire() {
		super();
	
	}
	public Locataire (int idl, String nomprenom, Date datenaissance,String telephone,String cin ) {
		this.idl=idl;
		this.nomprenom=nomprenom ;
		this.datenaissance=datenaissance;
		this.telephone=telephone;
		this.cin=cin;
		
	}
	public int getIdl() {
		return idl;
	}
	public void setIdl(int idl) {
		this.idl = idl;
	}
	public String getNomprenom() {
		return nomprenom;
	}
	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}
	public Date getDatenaissance() {
		return datenaissance;
	}
	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}

	
	

}
