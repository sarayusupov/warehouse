import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class StorageManager {
	private Storage storage;
	
	public StorageManager(Storage storage) {
		this.storage = storage;
	}
	// Add a new client to the data base success=1 failed=0
	
	public int newClient(Client client) {
		return this.storage.insertClient(client);
	}
	
	public boolean checkIfIDExists(String id) {
		return this.storage.checkIfIDExists(id);
	}
	
	public String getCompanyNameAndPhoneNumber(String id) {
		return this.storage.getCompanyNameAndPhoneNumber(id);
	}
	// update client if credit approved

	public int updateClientCreditApproval(String id, boolean approval) {
		 if (this.storage.updateClientCreditApproval(id, approval) == 1) {
			 if (approval) {
				 return 1;
			 }
		 }
		 return 0;
	}
	
	public int insertPO(PO po) {
		return this.storage.insertPO(po);
	}
	
	public int updatePOStatus(String POID, String status) {
		return this.storage.updatePOStatus(POID, status);
	}
	
	
	public Map<String, Double> getItemsPrices() {
		return this.storage.getProductPrices();
	}
	
	
	public Map<String, Double> getItemsPrices(ArrayList<Order> items) {
		ArrayList<String> productNames = new ArrayList<String>();
		for (Order item: items) {
			productNames.add(item.getProductName());
		}
		Map<String, Double> productPrices = this.storage.getProductPrices();
		Map<String, Double> prices = new HashMap<String, Double>();
		
		for (String name: productNames) {
			prices.put(name, productPrices.get(name));
		}
		return prices;
	}
	
	public int updatePOPrice(String POID, double price) {
		return this.storage.updatePOPrice(POID, price);
	}
	
	public int updatePOPayment(String POID, boolean hasPayed) {
		return this.storage.updatePOPayment(POID, hasPayed);
	}
	
	public String getPOStatus(String POID) {
		return this.storage.getPOStatus(POID);
	}
}
