module locationlogement {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires java.rmi;
	requires java.desktop;
	exports controler;
	
	
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens controler to javafx.fxml;
	opens models to javafx.base;
	exports application to java.rmi;

}
