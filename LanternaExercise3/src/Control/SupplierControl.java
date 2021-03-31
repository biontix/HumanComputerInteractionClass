package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Supplier;

public class SupplierControl {
	
	private Connection con = SQLConnection.dbConnector();       
    public SupplierControl(Connection con) {
        this.con = con;
    }

    
    public String createSupplier(Supplier supp){
    
    	String SQL = "INSERT INTO suppliers(SUID, name)"
            + " VALUES (?, ?)";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	        ps.setString(1, supp.getSUID());
	        ps.setString(2, supp.getName());
	        int check = ps.executeUpdate();
	        ps.close();
							
	        if(check>0){	        	
	        	return "Supplier Create";	        		        	
	        }else{
	        	return "Error: filed to create supplier";
	        }
	    
	    }catch(SQLException e){
	    	
	    	System.out.println(e.getErrorCode());
        	return e.getMessage();        
    	}

    }
    
    
    public String updateSupplier (Supplier supp){
    
	    String SQL = "UPDATE suppliers SET name=?" +
	    		" WHERE SUID='"+supp.getSUID()+"'";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	        ps.setString(1, supp.getName());
	        int i = ps.executeUpdate();		
	        
	        ps.close();
	        
	        if(i>0){
	        	return "Supplier Update with success";
	        }else{
	        	return "Error: Can not update Supplier";
	        }
	    
	    }catch(SQLException e){
	        return e.getMessage();
	    }
    }  
    
    
    public String deleteSupplier(Supplier supp){
    
    	String SQL = "DELETE FROM suppliers " +
    			" WHERE SUID =?";
	    try{
	    	
	        PreparedStatement ps= con.prepareStatement(SQL);
	
	        ps.setString(1, supp.getSUID());
	        ps.executeUpdate();									
	        ps.close();
	       
	        return "Supplier Deleted";	        
	    
	    }catch(SQLException e){
	        return e.getMessage();
	    }
    }
    
    
    public List<Supplier> suppliersList(String txt) {
    	
        String SQL="SELECT * FROM suppliers where name "
                + " like '%"+ txt +"%';";
        
        List<Supplier> suppLists = new ArrayList<Supplier>();
        
       try {
    	   
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery(); 
            if(rs !=null){
            	while(rs.next()){
            			Supplier supp =new Supplier();
            			supp.setSUID(rs.getString("SUID"));
            			supp.setName(rs.getString("name"));
            			suppLists.add(supp);
            	}
            }
            
            return suppLists;
            
        } catch (SQLException e) {
            return null;
        }
    }
    
    
    public String[]  supplierListByName() {
    	
        String SQL="SELECT * FROM suppliers order by name;";
        int numreg=NumberOfSupplier();
        String suppList[]= new String [numreg];
       
       try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery(); 
            int i=0;
            if(rs !=null){
            	while(rs.next()){
            		suppList[i]=(rs.getString("name"));
            		System.out.println("i : "+i+" name : "+suppList[i]);
            		i++;
            	}
            }
            return suppList;
        } catch (SQLException e) {
            return null;
        }
    }    

    public int NumberOfSupplier() {
        String SQL = "Select count(*) from suppliers";
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
