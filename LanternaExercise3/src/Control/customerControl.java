package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Customer;

public class customerControl {
	
	private Connection con = SQLConnection.dbConnector();       
    public customerControl(Connection con) {
        this.con = con;
    }

    
    public String createCustomer(Customer cust){
    
    	String SQL = "INSERT INTO customers(CLID, name, surname)"
            + " VALUES (?, ?, ?)";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	        ps.setString(1, cust.getCLID());
	        ps.setString(2, cust.getName());
	        ps.setString(3, cust.getSurname());
	        int check = ps.executeUpdate();
	        ps.close();
							
	        if(check>0){	        	
	        	return "Customer Create";	        		        	
	        }else{
	        	return "Error: filed to create customer";
	        }
	    
	    }catch(SQLException e){
	    	
	    	System.out.println(e.getErrorCode());
        	return e.getMessage();        
    	}

    }
    
    
    public String updateCustomer (Customer cust){
    
	    String SQL = "UPDATE Customers SET name=?, surname=?" +
	    		" WHERE CLID='"+cust.getCLID()+"'";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	        ps.setString(1, cust.getName());
	        ps.setString(2, cust.getSurname());
	        int i = ps.executeUpdate();		
	        
	        ps.close();
	        
	        if(i>0){
	        	return "Customer Update with success";
	        }else{
	        	return "Error: Can not update Customer";
	        }
	    
	    }catch(SQLException e){
	        return e.getMessage();
	    }
    }  
    
    
    public String deleteCustomer(Customer cust){
    
    	String SQL = "DELETE FROM customers " +
    			" WHERE CLID =?";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	
	        ps.setString(1, cust.getCLID());
	        ps.executeUpdate();									
	        ps.close();
	       
	        return "Customer Deleted";
	        
	    
	    }catch(SQLException e){
	        return e.getMessage();
	    }
    }
    
    
    public List<Customer> customersList(String txt) {
    	
        String SQL="SELECT * FROM customers where name "
                + " like '%"+ txt +"%';";
        
        List<Customer> custLists = new ArrayList<Customer>();
        
       try {
    	   
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery(); 
            if(rs !=null){
            	while(rs.next()){
            			Customer cust =new Customer();
            			cust.setCLID(rs.getString("CLID"));
            			cust.setName(rs.getString("name"));
            			cust.setSurname(rs.getString("surname"));
            			custLists.add(cust);
            	}
            }
            
            return custLists;
            
        } catch (SQLException e) {
            return null;
        }
    }
    
    
    public String[]  customerListByName() {
    	
        String SQL="SELECT * FROM customers order by name;";
        int numreg=NumberOfCustomer();
        String custList[]= new String [numreg];
       
       try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery(); 
            int i=0;
            if(rs !=null){
            	while(rs.next()){
            		custList[i]=(rs.getString("name"));
            		System.out.println("i : "+i+" name : "+custList[i]);
            		i++;
            	}
            }
            return custList;
        } catch (SQLException e) {
            return null;
        }
    }    

    public int NumberOfCustomer() {
        String SQL = "Select count(*) from customers";
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
