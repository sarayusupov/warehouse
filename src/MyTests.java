import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class MyTests {
	Storage storage = Storage.getInstance(System.getProperty("user.dir") + "/TestDatabase");
	StorageManager manager = new StorageManager(storage);
	AccountManagement acc = new AccountManagement("ma", "asda", "asda", "asd");
	
	@Test
	public void insertClientTest() {
		Client client = new Client("Joseph", "054", "asd@asdf", "320",
				"her", "asda", "asda");
		manager.newClient(client);
		File clientFile = new File(System.getProperty("user.dir") + "/TestDatabase/clients.csv");
		assertEquals(true, clientFile.exists(), "Should return true for correct client insertion");
	}
	@Test
	public void createPOTest() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String poid = formatter.format(date);
		poid = poid.replaceAll(":", "-");
		Order order = new Order("25 mm plastic ceiling spacer", 3);
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(order);
		PO po = new PO(poid, orders, "320", "054");
		manager.insertPO(po);
		assertNotEquals("Should return status of the order", "The product order does not exist", manager.getPOStatus(poid));
	}
}
