import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

import Control.SQLConnection;
import View.MainScreen;

public class MainApp {
	
    public static Terminal terminal;
    public static Screen screen;
    public static GUIScreen guiScreen;
    

    public static void main(String[] args) {
		
    	/*
    	String SQL1 = "CREATE TABLE customers (CLID VARCHAR(26) PRIMARY KEY, name VARCHAR(26), surname VARCHAR(26));";
    	//String SQL1 = "DROP TABLE customers";
    	
    	final Connection con = SQLConnection.dbConnector();  
        PreparedStatement ps;
        
		try {
			
			ps = con.prepareStatement(SQL1);
	        ps.execute();									
	        ps.close();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	*/
    	
        terminal = TerminalFacade.createTerminal();
        screen = new Screen(terminal);
        guiScreen = new GUIScreen(screen);

        screen.startScreen();
        
        guiScreen.showWindow(new MainScreen(guiScreen));
        screen.stopScreen();
        
		    	
    }

}
