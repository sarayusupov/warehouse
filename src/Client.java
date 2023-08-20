
public class Client extends Person{

	private String companyName;
	private String contactName;
	private String address;
	private boolean creditApproved;
	
	
	public Client(String name, String phoneNumber, String emailAddress,
				String id, String companyName, String contactName, String address) {
		super(name, phoneNumber, emailAddress, id);
		this.companyName = companyName;
		this.contactName = contactName;
		this.address = address;
	}
	
	public String toString() {
		return super.toString() + "," + this.companyName 
						+ "," + this.contactName + "," + this.address; 
	}
 
}
