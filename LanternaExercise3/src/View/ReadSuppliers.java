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
import Control.SupplierControl;
import Control.customerControl;
import Model.Customer;
import Model.Supplier;


public class ReadSuppliers extends Window{
	

    private Button btnExit;
    private Table tblCli;
    private Panel pnlList;
    private Component[] line = new Component[3];
    private static GUIScreen ListSupplierGui;
    SupplierControl supplierCtrl;

    public ReadSuppliers(GUIScreen gs) {
        super("List Supplier");
              this.ListSupplierGui=gs;
    init();   
    }

    private void init() {
    	
    	Connection conn = SQLConnection.dbConnector(); 
        supplierCtrl =new SupplierControl(conn);
        
        setBorder(new Border.Standard());
        pnlList = new Panel(Panel.Orientation.HORISONTAL);
        tblCli = new Table(2, "Suppliers List");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        line[0] = new Label("SUID");
        line[1] = new Label("Name              ");
        tblCli.addRow(line);
        
        line[0] = new Label("----");
        line[1] = new Label("------------------");
        line[2] = new Label("------------------");
        tblCli.addRow(line);     
        
        for (Supplier supp : supplierCtrl.suppliersList("")) {
	        line[0] = new Label(supp.getSUID());
	        line[1] = new Label(supp.getName());        
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
