package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Customer;
import Model.Product;

public class ProductControl {
	
	private Connection con = SQLConnection.dbConnector();   
	
    public ProductControl(Connection con) {
        this.con = con;
    }

    
    public String createProduct(Product pro){
    
    	String SQL = "INSERT INTO inventory(PID, name, amount, supplier)"
            + " VALUES (?, ?, ?, ?)";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	        ps.setString(1, pro.getPID());
	        ps.setString(2, pro.getName());
	        ps.setString(3, pro.getAmount());
	        ps.setString(4, pro.getSupplier());
	        int check = ps.executeUpdate();
	        ps.close();
							
	        if(check>0){	        	
	        	return "Product Create";	        		        	
	        }else{
	        	return "Error: filed to create product";
	        }
	    
	    }catch(SQLException e){
	    	
	    	System.out.println(e.getErrorCode());
        	return e.getMessage();        
    	}

    }
    
    
    public String updateProduct (Product pro){
    
	    String SQL = "UPDATE inventory SET name=?, amount=?, supplier=?" +
	    		" WHERE PID='"+pro.getPID()+"'";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	        ps.setString(1, pro.getName());
	        ps.setString(2, pro.getAmount());
	        ps.setString(3, pro.getSupplier());
	        int i = ps.executeUpdate();		
	        
	        ps.close();
	        
	        if(i>0){
	        	return "Product Update with success";
	        }else{
	        	return "Error: Can not update Product";
	        }
	    
	    }catch(SQLException e){
	        return e.getMessage();
	    }
    }  
    
    
    public String deleteProduct(Product pro){
    
    	String SQL = "DELETE FROM inventory " +
    			" WHERE PID =?";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	
	        ps.setString(1, pro.getPID());
	        ps.executeUpdate();									
	        ps.close();
	       
	        return "Product Deleted";
	        
	    
	    }catch(SQLException e){
	        return e.getMessage();
	    }
    }
    
    
    public List<Product> productsList(String txt) {
    	
        String SQL="SELECT * FROM inventory where name "
                + " like '%"+ txt +"%';";
        
        List<Product> proLists = new ArrayList<Product>();
        
       try {
    	   
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery(); 
            if(rs !=null){
            	while(rs.next()){
            			Product pro =new Product();
            			pro.setPID(rs.getString("PID"));
            			pro.setName(rs.getString("name"));
            			pro.setAmount(rs.getString("amount"));
            			pro.setSupplier(rs.getString("supplier"));
            			proLists.add(pro);
            	}
            }
            
            return proLists;
            
        } catch (SQLException e) {
            return null;
        }
    }
    
    
    public String[]  productListByName() {
    	
        String SQL="SELECT * FROM inventory order by name;";
        int numreg=NumberOfProducts();
        String proList[]= new String [numreg];
       
       try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery(); 
            int i=0;
            if(rs !=null){
            	while(rs.next()){
            		proList[i]=(rs.getString("name"));
            		System.out.println("i : "+i+" name : "+proList[i]);
            		i++;
            	}
            }
            return proList;
        } catch (SQLException e) {
            return null;
        }
    }    

    public int NumberOfProducts() {
        String SQL = "Select count(*) from inventory";
        int numt = 0;
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    numt = rs.getInt(1);
                }
            }
            return numt;
        } catch (SQLException e) {
            System.out.println("" + e);
            return 0;
        }
    }    

}
