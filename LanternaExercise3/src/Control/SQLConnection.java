package Control;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {

	static Connection con = null;
	public static Connection dbConnector(){
		try{
			
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:warehouse.sqlite");
			return con;
			
		}catch(Exception e){
			System.out.println("Connection Error"+e.getMessage());
		}
		return con;
	}
}