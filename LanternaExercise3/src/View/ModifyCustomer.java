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

import Control.customerControl;
import Control.SQLConnection;
import Model.Customer;

public class ModifyCustomer extends Window{
	
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
    Customer cust = new Customer();
    private static GUIScreen guiScreen;
    customerControl customerCtrl;
    private String operation;

    public ModifyCustomer(GUIScreen gS,Customer  cust, String op) {
        super("Modify Customer");
        this.guiScreen = gS;
        this.cust= cust;
        operation=op;
        if(op.equals("A")){
        	Update();
        }else{
        	Delete();
        }
    }

    private void Update() {
              
        Connection conn = SQLConnection.dbConnector(); 
        customerCtrl =new customerControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Update Customer  ");
        addComponent(label01);
        
        lblCLID = new Label("CLID : "+cust.getCLID());
        
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
        
        btnSave = new Button("Update", new Action() {
            @Override
            public void doAction() {
                cust.setName(txtName.getText());
                cust.setSurname(txtSurname.getText());
                MessageBox.showMessageBox(guiScreen, "Customer", 
                        customerCtrl.updateCustomer(cust));
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
        customerCtrl=new customerControl(conn);
        
        setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        label01 = new Label("Delete customer  ");
        addComponent(label01);
        
        lblCLID = new Label("CLID : "+ cust.getCLID());
        lblName = new Label("Customer Name : "+ cust.getName());
        lblSurname = new Label("Customer Surname : "+ cust.getSurname());
        addComponent(lblCLID);
        addComponent(lblName);
        addComponent(lblSurname);
        btnSave = new Button("Delete", new Action() {
		            @Override
		            public void doAction() {
		                MessageBox.showMessageBox(guiScreen, "Customer", 
		                        customerCtrl.deleteCustomer(cust));
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
