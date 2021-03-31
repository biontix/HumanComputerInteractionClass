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
import Control.customerControl;
import Model.Customer;


public class CreateCustomer extends Window{
	
    private Panel painel01;
    private Button btnExit;
    private Button btnSave;
    private Label label01;
    private Label lblCLID;
    private TextBox txtCLID;
    private Label lblName;
    private TextBox txtName;
    private Label lblSurname;
    private TextBox txtSurname;
    
    Customer customer = new Customer();
    private static GUIScreen guiScreen;
    customerControl CustomerCtrl;

    public CreateCustomer(GUIScreen gS) {
        super("Create Customer Screen");
        this.guiScreen = gS;
        init();
    }

    private void init() {
              
        Connection conn = SQLConnection.dbConnector(); 
        CustomerCtrl = new customerControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Create Cusotmer");
        addComponent(label01);
        
        lblCLID = new Label("CLID :");
        txtCLID = new TextBox();
        lblName = new Label("Customer Name :");
        txtName = new TextBox();
        lblSurname = new Label("Customer Surname :");
        txtSurname = new TextBox();
        
        addComponent(lblCLID);
        addComponent(txtCLID);
        addComponent(lblName);
        addComponent(txtName);
        addComponent(lblSurname);
        addComponent(txtSurname);
        
        
        btnSave = new Button("Save", new Action() {
            @Override
            public void doAction() {
                customer.setName(txtName.getText());
                customer.setSurname(txtSurname.getText());
                customer.setCLID(txtCLID.getText());
                MessageBox.showMessageBox(guiScreen, "Customers", 
                        CustomerCtrl.createCustomer(customer));
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
