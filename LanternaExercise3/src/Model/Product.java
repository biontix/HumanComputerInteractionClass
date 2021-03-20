package Model;

public class Product {
	
    private String PID;
    private String name;
    private String amount;
	  private String supplier;
    
    public String getPID() {
		  return PID;
	  }
	  public void setPID(String pID) {
		  PID = pID;
	  }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}  

}
