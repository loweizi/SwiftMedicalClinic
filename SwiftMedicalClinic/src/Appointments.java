
public class Appointments {
	private Patient patient;
	private Associates associate;
	private String weight, height, bodyTemp, bloodPressure;
	private String medication, prevHealth, immunization;
    private String findings, TBmedication, reccomendations;

    public Appointments(){
    	patient = new Patient();
    	associate = new Associates();
    	
    	weight = "";
    	height = "";
    	bodyTemp = "";
    	bloodPressure = "";
    	
    	medication = "";
    	prevHealth = "";
    	immunization = "";
    	
    	findings = "";
    	TBmedication = "";
    	reccomendations = "";
    }
    
    //patient constructor identifying the object
    public Appointments(Patient patient, Associates associate){
        this.patient = patient;
        this.associate = associate;
    }
    
    //setters and getters
    public void setPatient(Patient patient) {
    	this.patient = patient;
    }
    
    public void setAssociate(Associates associate) {
    	this.associate = associate;
    }
    
    public void setWeight(String weight) {
    	this.weight = weight;
    }
    
    public void setHeight(String height) {
    	this.height = height;
    }
    
    public void setBodyTemp(String bodyTemp) {
    	this.bodyTemp = bodyTemp;
    }
    
    public void setBloodPressure(String bloodPressure) {
    	this.bloodPressure = bloodPressure;
    }
    
    public void setMedication(String medication) {
    	this.medication = medication;
    }
    
    public void setPrevHealth(String prevHealth) {
    	this.prevHealth = prevHealth;
    }
    
    public void setImmunization(String immunization) {
    	this.immunization = immunization;
    }
    
    public void setFindings(String findings) {
    	this.findings = findings;
    }
    
    public void setTBMedication(String TBmedication) {
    	this.TBmedication = TBmedication;
    }
    
    public void setReccomendations(String reccomendations) {
    	this.reccomendations = reccomendations;
    }
    
    public Patient getPatient(){
        return patient;
    }
    
    public Associates getAssociate(){
        return associate;
    }
    
    public String getWeight(){
        return weight;
    }
    
    public String getHeight(){
        return height;
    }
    
    public String getBodyTemp(){
        return bodyTemp;
    }
    
    public String getBloodPressure(){
        return bloodPressure;
    }
    
    public String getMedication(){
        return medication;
    }
    
    public String getPrevHealth(){
        return prevHealth;
    }
    
    public String getImmunization(){
        return immunization;
    }
    
    public String getFindings(){
        return findings;
    }
    
    public String getTBMedication(){
        return TBmedication;
    }
    
    public String getReccomendations(){
        return reccomendations;
    }
   
   
}
