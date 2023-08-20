
public class Order {
	private String productName;
	private int amount;
	
	public Order(String productName, int amount) {
		this.productName = productName;
		this.amount = amount;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public String toString() {
		return this.productName + "," + Integer.toString(this.amount);
	}
	
	public String getOrderDetails() {
		return "Product Name: " + this.productName + 
				", Amount: " + Integer.toString(this.amount);
	}
}
