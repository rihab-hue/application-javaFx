package models;

public class Logement {
	int id;
	String adrl;
	int superficie;
	int loyer;
	String type;
	String region;
	
	public Logement(){
		super();
	}
	public Logement(int id,String adrl,int superficie,int loyer,String type,String region) {
	 this.id= id;
	 this.superficie=superficie;
	 this.adrl=adrl;
	 this.loyer=loyer;
	 this.type=type;
	 this.region=region;
	 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdrl() {
		return adrl;
	}
	public void setAdrl(String adrl) {
		this.adrl = adrl;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	public int getLoyer() {
		return loyer;
	}
	public void setLoyer(int loyer) {
		this.loyer = loyer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
}
