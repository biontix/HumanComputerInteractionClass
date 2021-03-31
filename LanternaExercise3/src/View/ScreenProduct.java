package View;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Panel;


public class ScreenProduct extends Window {
	
	private Panel painel01;
	private Button btnCreate;
	private Button btnUpdDel;
	private Button btnList;
	private Button btnExit;
	private static GUIScreen ProductGui;
	 
	public ScreenProduct(GUIScreen gs) {
	    super("Products");
	    this.ProductGui=gs;
	    init();
	}
	
	private void init() {
	    
	    setBorder(new Border.Standard());
	    painel01 = new Panel(Panel.Orientation.VERTICAL);	    
	    btnCreate = new Button("Create Product", new Action() {
	    	@Override
	    	public void doAction() {
	    		ProductGui.showWindow(new CreateProduct(ProductGui));
	    	}
	    
	    });  
	    painel01.addComponent(btnCreate);
	
	
	    btnUpdDel = new Button("Update or Delete Products", new Action() {
		    @Override
		    public void doAction() {
		    	ProductGui.showWindow(new UpdDelProduct(ProductGui));
		    }	    
		});
		painel01.addComponent(btnUpdDel);
		
		
		btnList = new Button("Lists Products", new Action() {
			@Override
		    public void doAction() {
				ProductGui.showWindow(new ReadProducts(ProductGui));
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
