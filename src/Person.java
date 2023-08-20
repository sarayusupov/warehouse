
public class Person {
	protected String name;
	protected String phoneNumber;
	protected String emailAddress;
	protected String id;
	
	public Person(String name, String phoneNumber, String emailAddress, String id) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setEmailAddress(String email) {
		this.emailAddress = email;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return this.name + "," + this.phoneNumber + "," + this.emailAddress
				+ "," + this.id; 
	}
	
	public String getPersonDetails() {
		return "Name: " + this.name + "\nPhoneNumber: " + this.phoneNumber + 
				"\nEmailAddress: " + this.emailAddress + "\nId: " + this.id;
	}
}
