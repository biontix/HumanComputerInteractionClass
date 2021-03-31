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

import Control.SQLConnection;
import Control.SupplierControl;
import Model.Supplier;

public class UpdDelSupplier extends Window{

    private Button btnExit;
    private Table tblCli;
    private Panel pnlList;
    private Label lblName;
    private TextBox txtName;
    private Button btnEnter;
    
    private Component[] line=new Component[4];
    private static GUIScreen guiScreen;

    public UpdDelSupplier(GUIScreen gs) {
        super("Modify Customer");
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
        SupplierControl ctrl=new SupplierControl(conn); 
        
        tblCli = new Table(4, "Modify Supplier");
        tblCli.setColumnPaddingSize(1);
        tblCli.removeAllRows();
        
        line[0] = new Label("SUID");
        line[1] = new Label("Name      ");
        line[2] = new Label("Update");
        line[3] = new Label("Delete");
        tblCli.addRow(line);
        
        line[0] = new Label("----");
        line[1] = new Label("--------------");
        line[2] = new Label("--------------");
        line[3] = new Label("--------");
        tblCli.addRow(line);      
        
        
        for (Supplier supp : ctrl.suppliersList(text)) {
        	
	        line[0] = new Label(supp.getSUID());
	        line[1] = new Label(supp.getName());
	        line[2] = new Button("Update", new Action() {	
									@Override
									public void doAction() {
										guiScreen.showWindow(new ModifySupplier(guiScreen,supp,"A"));            
									}
	        					});
	        line[3] = new Button("Delete", new Action() {
	
						            @Override
						            public void doAction() {
						            	guiScreen.showWindow(new ModifySupplier(guiScreen,supp,"E"));    
						            
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
