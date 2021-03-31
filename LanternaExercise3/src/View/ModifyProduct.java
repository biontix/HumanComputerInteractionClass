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
import Model.Product;


public class ModifyProduct extends Window{
	
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
    Product pro = new Product();
    private static GUIScreen guiScreen;
    ProductControl productCtrl;
    private String operation;

    public ModifyProduct(GUIScreen gS,Product pro, String op) {
        super("Modify Product");
        this.guiScreen = gS;
        this.pro= pro;
        operation=op;
        if(op.equals("A")){
        	Update();
        }else{
        	Delete();
        }
    }

    private void Update() {
              
        Connection conn = SQLConnection.dbConnector(); 
        productCtrl =new ProductControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Update Product  ");
        addComponent(label01);
        
        lblPID = new Label("PID : "+pro.getPID());        
        lblName = new Label("Product Name:");
        txtName = new TextBox();
        lblAmount = new Label("Amount:");
        txtAmount = new TextBox();
        lblSupplier = new Label("Product Supplier:");
        txtSupplier = new TextBox();
        addComponent(lblPID);
        addComponent(txtPID);
        addComponent(lblName);
        addComponent(txtName);
        addComponent(lblAmount);
        addComponent(txtAmount);
        addComponent(lblSupplier);
        addComponent(txtSupplier);
        
        btnSave = new Button("Update", new Action() {
            @Override
            public void doAction() {
                pro.setName(txtName.getText());
                pro.setAmount(txtAmount.getText());
                pro.setSupplier(txtSupplier.getText());
                MessageBox.showMessageBox(guiScreen, "Product", 
                        productCtrl.updateProduct(pro));
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
        productCtrl=new ProductControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Delete product");
        addComponent(label01);
        
        lblPID = new Label("PID : "+ pro.getPID());
        lblName = new Label("Product Name : "+ pro.getName());
        lblAmount = new Label("Amount : "+ pro.getAmount());
        lblSupplier = new Label("Supplier: "+ pro.getSupplier());
        addComponent(lblPID);
        addComponent(lblName);
        addComponent(lblAmount);
        addComponent(lblSupplier);
        btnSave = new Button("Delete", new Action() {
		            @Override
		            public void doAction() {
		                MessageBox.showMessageBox(guiScreen, "Product", 
		                        productCtrl.deleteProduct(pro));
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
