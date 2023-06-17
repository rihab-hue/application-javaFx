package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionMysql {
	/*cn pour stocker la cox*/
	public static Connection cn =null;
	static String drivername="com.mysql.jdbc.Driver";
	/* pour établir la connexion à la base*/
    public static Connection connexionDB() {
    	try {
    		/*pour charger la classe du driver JDBC*/
    		Class.forName(drivername);
    		/* pour établir la connexion à la base*/
    		 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loyer","root","");
    		System.out.println("connexion reussite ");
    		return cn;
    		
    	}catch(ClassNotFoundException | SQLException e){
    		System.out.println("connexion echouee ");
    	   e.printStackTrace();  
    	    return null;
    	}

    }
}
