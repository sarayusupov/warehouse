import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class POPanel extends JPanel implements ActionListener {
	
	public ArrayList<JTextField> texts;
	public ArrayList<String> items;
	private JFrame frame;
	private JTextField idText;
	private StorageManager manager;
	
	public POPanel(StorageManager manager) {
		this.manager = manager;
		this.setLayout(new GridLayout(16, 2));
		this.setBackground(Color.WHITE);
		texts = new ArrayList<JTextField>();
		this.frame = frame;
		
		this.initializeProductLabels();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Order> orders = new ArrayList<Order>();
		int amount;
		JTextField text;
		JLabel message = new JLabel("Please order atleast one product");
		message.setFont(new Font("Arial", Font.PLAIN, 50));
		message.setBackground(Color.WHITE);
		message.setForeground(Color.BLACK);
		for (int i = 0; i < this.texts.size(); i++) {
			text =  this.texts.get(i);
			amount = Integer.parseInt(text.getText());
			if (amount > 0) {
				orders.add(new Order(this.items.get(i), amount));
			}
		}
		if (orders.size() == 0 || !this.manager.checkIfIDExists(this.idText.getText())) {
			if (orders.size() == 0) {
				message.setText("Please order atleast one product");
			} else {
				message.setText("Given ID doesn't exist");
			}
			JOptionPane.showMessageDialog(this, message);
		} else {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String poid = formatter.format(date);
			poid = poid.replaceAll(":", "-");
			String companyNameAndPhoneNumber = this.manager.getCompanyNameAndPhoneNumber(this.idText.getText());
			String companyName = companyNameAndPhoneNumber.split(",")[0];
			String phoneNumber = companyNameAndPhoneNumber.split(",")[1];
			PO po = new PO(poid, orders, companyName, phoneNumber);
			manager.insertPO(po);
			po.setPrice(manager.getItemsPrices(orders));
			JTextArea msg = new JTextArea();
			message.setText(Double.toString(po.getPrice()));
			JOptionPane.showMessageDialog(this, message);
			manager.updatePOPrice(po.getPOID(), po.getPrice());
			manager.updatePOStatus(poid, "Production complete");
			msg.setText(po.getReceipt());
			JScrollPane scrollPane = new JScrollPane(msg);
			JOptionPane.showMessageDialog(this, scrollPane);
		}
	}
	
	
	private void initializeProductLabels() {
		Map<String, Double> productPrices = this.manager.getItemsPrices();
		this.items = new ArrayList<String>();
		
		JLabel label = new JLabel("Fill in the amount and press the button");
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 50));
		add(label);
		
		JButton button = new JButton("Submit order");
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Arial", Font.PLAIN, 50));
		button.addActionListener(this);
		add(button);
		
		JTextField text;
		
		for (String productName: productPrices.keySet()) {
			label = new JLabel(productName);
			label.setBackground(Color.WHITE);
			label.setForeground(Color.BLACK);
			label.setFont(new Font("Arial", Font.PLAIN, 50));
			add(label);
			this.items.add(productName);
			text = new JTextField("0");
			text.setFont(new Font("Arial", Font.PLAIN, 50));
			text.setBackground(Color.WHITE);
			text.setForeground(Color.BLACK);
			text.setEditable(true);
			add(text);
			texts.add(text);
		}
		label = new JLabel("Client ID");
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 50));
		add(label);
		idText = new JTextField("Enter the ID");
		idText.setFont(new Font("Arial", Font.PLAIN, 50));
		idText.setBackground(Color.WHITE);
		idText.setForeground(Color.BLACK);
		idText.setEditable(true);
		add(idText);
		
		
	}
	
	
}
