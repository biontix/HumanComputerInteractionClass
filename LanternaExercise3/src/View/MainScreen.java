package View;


import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;


public class MainScreen extends Window {
    
	 private Panel painel01;
	 private Label label01;
	 private Button btnCustomers;
	 private Button btnSuppliers;
	 private Button btnProducts;
	 private Button btnExit;
	 private static GUIScreen MainGui;
 
 
 
    public MainScreen(GUIScreen gs) {
        super("Control System Warehouse");
        this.MainGui=gs;
        init();
    }

    private void init() {
    	
    	setBorder(new Border.Standard());
        painel01 = new Panel(Panel.Orientation.HORISONTAL);
        btnCustomers = new Button("Customers", new Action() {
            @Override
            public void doAction() {
              MainGui.showWindow(new ScreenCustomer(MainGui));
            }
            
        });
        painel01.addComponent(btnCustomers);
        
        btnSuppliers = new Button("Suppliers", new Action() {
            @Override
            public void doAction() {
            	MainGui.showWindow(new ScreenSupplier(MainGui));
            }
            
        });
        painel01.addComponent(btnSuppliers);
        
        btnProducts = new Button("Products", new Action() {
            @Override
            public void doAction() {
            	MainGui.showWindow(new ScreenProduct(MainGui));
            }
            
        });
        painel01.addComponent(btnProducts);
        
        btnExit = new Button("Exit", new Action() {
        @Override
            public void doAction() {
                close();
            }
        });      
        painel01.addComponent(btnExit);
        
        addComponent(painel01);       
        
    }    
    
}
