package View;

import java.sql.Connection;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Table;

import Control.SQLConnection;
import Control.customerControl;
import Model.Customer;


public class ReadCustomers extends Window{
	

    private Button btnExit;
    private Table tblCli;
    private Panel pnlList;
    private Component[] line = new Component[3];
    private static GUIScreen ListConstumerGui;
    customerControl customerCtrl;

    public ReadCustomers(GUIScreen gs) {
        super("List Costumer");
              this.ListConstumerGui=gs;
    init();   
    }

    private void init() {
    	
    	Connection conn = SQLConnection.dbConnector(); 
        customerCtrl =new customerControl(conn);
        
        setBorder(new Border.Standard());
        pnlList = new Panel(Panel.Orientation.HORISONTAL);
        tblCli = new Table(3, "Customers List");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        line[0] = new Label("CLID");
        line[1] = new Label("Name              ");
        line[2] = new Label("Surname           ");
        tblCli.addRow(line);
        line[0] = new Label("----");
        line[1] = new Label("------------------");
        line[2] = new Label("------------------");
        tblCli.addRow(line);      
        for (Customer cust : customerCtrl.customersList("")) {
	        line[0] = new Label(cust.getCLID());
	        line[1] = new Label(cust.getName());
	        line[2] = new Label(cust.getSurname());	        
	        tblCli.addRow(line);
        }  
        addComponent(tblCli);
        
        btnExit = new Button("Exit", new Action() {
        @Override
            public void doAction() {
                close();
            }
        });      
     addComponent(btnExit);
    }

}
