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
import com.googlecode.lanterna.gui.component.TextBox;

import Control.customerControl;
import Control.ProductControl;
import Control.SQLConnection;
import Model.Customer;
import Model.Product;
import View.ModifyCustomer;

public class UpdDelProduct extends Window{

    private Button btnExit;
    private Table tblCli;
    private Panel pnlList;
    private Label lblName;
    private TextBox txtName;
    private Button btnEnter;
    
    private Component[] line=new Component[6];
    private static GUIScreen guiScreen;

    public UpdDelProduct(GUIScreen gs) {
        super("Modify Product");
              this.guiScreen=gs;
    init();   
    }

    private void init() {
    	
        setBorder(new Border.Standard());
        pnlList = new Panel(Panel.Orientation.HORISONTAL);
        lblName = new Label("Insert Name");
        txtName = new TextBox();
        pnlList.addComponent(lblName);
        pnlList.addComponent(txtName);
        addComponent(pnlList);
        btnEnter = new Button("Enter", new Action() {
        @Override
            public void doAction() {
                removeComponent(tblCli);
                removeComponent(btnExit);
                enter(txtName.getText());
            }
        }); 
        addComponent(btnEnter);
    }

    private void enter(String text) {

        Connection conn = SQLConnection.dbConnector(); 
        ProductControl ctrl=new ProductControl(conn); 
        
        tblCli = new Table(6, "Modify Product");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        line[0] = new Label("PID");
        line[1] = new Label("Name    ");
        line[2] = new Label("Amount   ");
        line[3] = new Label("Supplier  ");
        line[4] = new Label("Update");
        line[5] = new Label("Delete");
        tblCli.addRow(line);
        
        line[0] = new Label("----");
        line[1] = new Label("--------------");
        line[2] = new Label("--------------");
        line[3] = new Label("--------------");
        line[4] = new Label("--------");
        line[5] = new Label("--------");
        tblCli.addRow(line);      
        
        
        for (Product pro : ctrl.productsList(text)) {
        	
	        line[0] = new Label(pro.getPID());
	        line[1] = new Label(pro.getName());
	        line[2] = new Label(pro.getAmount());
	        line[3] = new Label(pro.getSupplier());
	        line[4] = new Button("Update", new Action() {	
									@Override
									public void doAction() {
										guiScreen.showWindow(new ModifyProduct(guiScreen,pro,"A"));            
									}
	        					});
	        line[5] = new Button("Delete", new Action() {
	
						            @Override
						            public void doAction() {
						            	guiScreen.showWindow(new ModifyProduct(guiScreen,pro,"E"));    
						            
						            }
	        					});
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
