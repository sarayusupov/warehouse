import java.util.ArrayList;
import java.util.Map;

public class PO {

	private String POID;
	ArrayList<Order> orderList;
	private double price = 0;
	private boolean hasPayed = false;
	private boolean productionComplete = false;
	private String companyName;
	private String phoneNumber;
	
	public PO(String POID, ArrayList<Order> orderList, String companyName, String phoneNumber) {
		this.POID = POID;
		this.orderList = orderList;
		this.companyName = companyName;
		this.phoneNumber =  phoneNumber;
	}
	
	public String getPOID() {
		return this.POID;
	}
	
	
	public ArrayList<Order> getOrders() {
		return this.orderList;
	}
	
	public void setPrice(Map<String, Double> itemPrice) {
		for (Order order: this.orderList) {
			this.price += (order.getAmount() * itemPrice.get(order.getProductName()));
		}
	}
	
	public double getPrice() {
		return this.price;
	}
	public String getCompanyName() {
		return this.companyName;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setHasPayed(boolean hasPayed) {
		this.hasPayed = hasPayed;
	}
	
	public boolean getHasPayed() {
		return this.hasPayed;
	}
	
	public boolean getProductionComplete() {
		return this.productionComplete;
	}
	
	public void setProductionComplete(boolean complete) {
		this.productionComplete = complete;
	}
	
	
	public String getReceipt() {
		String receipt = "Your order receipt:\n";
		for (Order order: this.orderList) {
			receipt += order.getOrderDetails() + "\n";
		}
		receipt += "Company Name: " + this.companyName + "\n";
		receipt += "Phone Number: " + this.phoneNumber + "\n";
		receipt += "Price: " + Double.toString(this.price) + "\n";
		receipt += "Date: " + this.POID;
		return receipt;
	}
}
