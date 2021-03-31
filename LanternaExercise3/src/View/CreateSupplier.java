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
import Control.customerControl;
import Model.Customer;
import Model.Supplier;


public class CreateSupplier extends Window{
	
    private Panel painel01;
    private Button btnExit;
    private Button btnSave;
    private Label label01;
    private Label lblSUID;
    private TextBox txtSUID;
    private Label lblName;
    private TextBox txtName;
    
    Supplier supplier = new Supplier();
    private static GUIScreen guiScreen;
    SupplierControl supplierCtrl;

    public CreateSupplier(GUIScreen gS) {
        super("Create Supplier Screen");
        this.guiScreen = gS;
        init();
    }

    private void init() {
              
        Connection conn = SQLConnection.dbConnector(); 
        supplierCtrl = new SupplierControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Create Cusotmer");
        addComponent(label01);
        
        lblSUID = new Label("SUID :");
        txtSUID = new TextBox();
        lblName = new Label("Supplier Name :");
        txtName = new TextBox();
                
        addComponent(lblSUID);
        addComponent(txtSUID);
        addComponent(lblName);
        addComponent(txtName);
        
        
        btnSave = new Button("Save", new Action() {
            @Override
            public void doAction() {
                supplier.setName(txtName.getText());
                supplier.setSUID(txtSUID.getText());
                MessageBox.showMessageBox(guiScreen, "Suppliers", 
                        supplierCtrl.createSupplier(supplier));
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
