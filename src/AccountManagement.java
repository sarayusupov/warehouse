import java.util.Random;

public class AccountManagement extends Person{

	public AccountManagement(String name, String phoneNumber, String emailAddress, String id) {
		super(name, phoneNumber, emailAddress, id);
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkCreditStatus(Client c) {
		Random rd = new Random(); 
		return rd.nextBoolean();
	}

}
