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

import Control.ProductControl;
import Control.SQLConnection;
import Control.customerControl;
import Model.Customer;
import Model.Product;


public class ReadProducts extends Window{
	

    private Button btnExit;
    private Table tblCli;
    private Panel pnlList;
    private Component[] line = new Component[4];
    private static GUIScreen ListProductsGui;
    ProductControl productCtrl;

    public ReadProducts(GUIScreen gs) {
        super("List Products");
              this.ListProductsGui=gs;
    init();   
    }

    private void init() {
    	
    	Connection conn = SQLConnection.dbConnector(); 
    	productCtrl =new ProductControl(conn);
        
        setBorder(new Border.Standard());
        pnlList = new Panel(Panel.Orientation.HORISONTAL);
        tblCli = new Table(4, "Products List");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        line[0] = new Label("PID");
        line[1] = new Label("Name              ");
        line[2] = new Label("Amount           ");
        line[3] = new Label("Supplier           ");
        tblCli.addRow(line);
        line[0] = new Label("----");
        line[1] = new Label("------------------");
        line[2] = new Label("------------------");
        line[3] = new Label("------------------");
        tblCli.addRow(line);  
        
        for (Product pro : productCtrl.productsList("")) {
	        line[0] = new Label(pro.getPID());
	        line[1] = new Label(pro.getName());
	        line[2] = new Label(pro.getAmount());	  
	        line[3] = new Label(pro.getSupplier());
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
