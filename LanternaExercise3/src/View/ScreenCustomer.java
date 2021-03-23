package View;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Panel;


public class ScreenCustomer extends Window {
	
	private Panel painel01;
	private Button btnCreate;
	private Button btnUpdDel;
	private Button btnList;
	private Button btnExit;
	private static GUIScreen CustomerGui;
	 
	public ScreenCustomer(GUIScreen gs) {
	    super("Customers");
	    this.CustomerGui=gs;
	    init();
	}
	
	private void init() {
	    
	    setBorder(new Border.Standard());
	    painel01 = new Panel(Panel.Orientation.VERTICAL);	    
	    btnCreate = new Button("Create Customers", new Action() {
	    	@Override
	    	public void doAction() {
	    		CustomerGui.showWindow(new CreateCustomer(CustomerGui));
	    	}
	    
	    });  
	    painel01.addComponent(btnCreate);
	
	
	    btnUpdDel = new Button("Update or Delete Customer", new Action() {
		    @Override
		    public void doAction() {
		    	CustomerGui.showWindow(new UpdDelCustomer(CustomerGui));
		    }
	    
		});
		painel01.addComponent(btnUpdDel);
		
		
		btnList = new Button("Lists Customers", new Action() {
			@Override
		    public void doAction() {
		     
		    }
		    
		});
		painel01.addComponent(btnList);   
		
		
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
