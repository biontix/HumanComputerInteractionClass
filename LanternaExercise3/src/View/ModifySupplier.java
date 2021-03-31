package View;

import java.sql.Connection;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;

import Control.SQLConnection;
import Control.SupplierControl;
import Model.Supplier;

public class ModifySupplier extends Window{
	
	private Panel painel01;
    private Button btnExit;
    private Button btnSave;
    private Label label01;
    private Label lblSUID;
    private TextBox txtSUID;
    private Label lblName;
    private TextBox txtName;
    Supplier supp = new Supplier();
    private static GUIScreen guiScreen;
    SupplierControl supplierCtrl;
    private String operation;

    public ModifySupplier(GUIScreen gS,Supplier  supp, String op) {
        super("Modify Supplier");
        this.guiScreen = gS;
        this.supp= supp;
        operation=op;
        if(op.equals("A")){
        	Update();
        }else{
        	Delete();
        }
    }

    private void Update() {
              
        Connection conn = SQLConnection.dbConnector(); 
        supplierCtrl =new SupplierControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Update Customer  ");
        addComponent(label01);
        
        lblSUID = new Label("SUID : "+supp.getSUID());
        
        lblName = new Label("Supplier Name :");
        txtName = new TextBox();
        addComponent(lblSUID);
        addComponent(txtSUID);
        addComponent(lblName);
        addComponent(txtName);
        
        btnSave = new Button("Update", new Action() {
            @Override
            public void doAction() {
                supp.setName(txtName.getText());
                MessageBox.showMessageBox(guiScreen, "Customer", 
                        supplierCtrl.updateSupplier(supp));
                close();
            }

        });
        addComponent(btnSave);
        
        btnExit = new Button("Exit", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        addComponent(btnExit);

    }

    private void Delete() {

        Connection conn = SQLConnection.dbConnector(); 
        supplierCtrl=new SupplierControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Delete supplier  ");
        addComponent(label01);
        
        lblSUID = new Label("SUID : "+ supp.getSUID());
        lblName = new Label("Customer Name : "+ supp.getName());
        addComponent(lblSUID);
        addComponent(lblName);
        btnSave = new Button("Delete", new Action() {
		            @Override
		            public void doAction() {
		                MessageBox.showMessageBox(guiScreen, "Customer", 
		                        supplierCtrl.deleteSupplier(supp));
		                close();
		            }

        });
        addComponent(btnSave);
        
        btnExit = new Button("Exit", new Action() {
		            @Override
		            public void doAction() {
		                close();
		            }
        });
        addComponent(btnExit);

    }


}
