
public class Associates {
	private String firstName, lastName, associateID, DOB, SSN, email, phoneNum, password;

    public Associates(){
        firstName = "";
        lastName = "";
        associateID = "";
        DOB = "";
        SSN = "";
        email = "";
        phoneNum = "";
        password = "";
    }
    
    //patient constructor identifying the object
    public Associates(String firstName, String lastName, String associateID, String DOB, String SSN, String email, String phoneNum, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.associateID = associateID;
        this.DOB = DOB;
        this.SSN = SSN;
        this.email = email;
        this.phoneNum = phoneNum;
        this.password = password;
    }
    
    //setters and getters
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public void setID(String associateID) {
    	this.associateID = associateID;
    }
    
    public void setDOB(String DOB) {
    	this.DOB = DOB;
    }
    
    public void setSSN(String SSN) {
    	this.SSN = SSN;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setPhoneNum(String phoneNum) {
    	this.phoneNum = phoneNum;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }

    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getID(){
        return associateID;
    }
    
    public String getDOB(){
        return DOB;
    }
    
    public String getSSN(){
        return SSN;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPhoneNum(){
        return phoneNum;
    }
    
    public String getPassword(){
        return password;
    }
}
