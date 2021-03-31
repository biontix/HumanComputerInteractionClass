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

import Control.ProductControl;
import Control.SQLConnection;
import Control.SupplierControl;
import Control.customerControl;
import Model.Customer;
import Model.Product;


public class CreateProduct extends Window{
	
    private Panel painel01;
    private Button btnExit;
    private Button btnSave;
    private Label label01;
    private Label lblPID;
    private TextBox txtPID;
    private Label lblName;
    private TextBox txtName;
    private Label lblAmount;
    private TextBox txtAmount;
    private Label lblSupplier;
    private TextBox txtSupplier;

    
    Product product = new Product();
    private static GUIScreen guiScreen;
    ProductControl productCtrl;

    public CreateProduct(GUIScreen gS) {
        super("Create Customer Screen");
        this.guiScreen = gS;
        init();
    }

    private void init() {
              
        Connection conn = SQLConnection.dbConnector(); 
        productCtrl = new ProductControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Create Product");
        addComponent(label01);
        
        lblPID = new Label("PID :");
        txtPID = new TextBox();
        lblName = new Label("Product Name :");
        txtName = new TextBox();
        lblAmount = new Label("Amount :");
        txtAmount = new TextBox();
        lblSupplier = new Label("Supplier Name :");
        txtSupplier = new TextBox();
        
        addComponent(lblPID);
        addComponent(txtPID);
        addComponent(lblName);
        addComponent(txtName);
        addComponent(lblAmount);
        addComponent(txtAmount);
        addComponent(lblSupplier);
        addComponent(txtSupplier);
        
        
        btnSave = new Button("Save", new Action() {
            @Override
            public void doAction() {
            	product.setSupplier(txtSupplier.getText());
            	product.setName(txtName.getText());
            	product.setAmount(txtAmount.getText());
            	product.setPID(txtPID.getText());
                MessageBox.showMessageBox(guiScreen, "Customers", 
                        productCtrl.createProduct(product));
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
