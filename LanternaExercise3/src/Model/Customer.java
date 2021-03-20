package Model;

public class Customer {
	
    private String CLID;
    private String name;
    private String surname;
    

    public String getCLID() {
        return CLID;
    }

    public void setCLID(String CLID) {
        this.CLID = CLID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
