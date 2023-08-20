import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientPanel extends JPanel implements ActionListener {

	public ArrayList<JTextField> texts;
	private JFrame frame;
	private StorageManager manager;
	private AccountManagement acc;
	
	public ClientPanel(StorageManager manager, AccountManagement acc) {
		this.manager = manager;
		this.acc = acc;
		this.setLayout(new GridLayout(8, 2));
		this.setBackground(Color.WHITE);
		texts = new ArrayList<JTextField>();
		this.frame = frame;
		this.initializeClientLabels();
	}
	
	private void initializeClientLabels() {
		JLabel label = new JLabel("Fill in all the info and press the button");
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 50));
		add(label);
		
		JButton button = new JButton("Submit client details");
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Arial", Font.PLAIN, 50));
		button.addActionListener(this);
		add(button);
		
		// Get name 
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBackground(Color.WHITE);
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		add(nameLabel);
		
		JTextField getName = new JTextField();
		getName.setEditable(true);
		getName.setFont(new Font("Arial", Font.PLAIN, 50));
		getName.setBackground(Color.WHITE);
		getName.setForeground(Color.BLACK);
		add(getName);
		texts.add(getName);
		
		// Get phone number
		JLabel phoneNumberLabel = new JLabel("Phone number:");
		phoneNumberLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		phoneNumberLabel.setBackground(Color.WHITE);
		phoneNumberLabel.setForeground(Color.BLACK);
		add(phoneNumberLabel);
		
		JTextField getPhoneNumber = new JTextField();
		getPhoneNumber.setFont(new Font("Arial", Font.PLAIN, 50));
		getPhoneNumber.setBackground(Color.WHITE);
		getPhoneNumber.setForeground(Color.BLACK);
		getPhoneNumber.setEditable(true);
		add(getPhoneNumber);
		texts.add(getPhoneNumber);
		
		// Get emailAddress
		JLabel emailLabel = new JLabel("Email Address:");
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		emailLabel.setBackground(Color.WHITE);
		emailLabel.setForeground(Color.BLACK);
		add(emailLabel);
		
		JTextField getEmail = new JTextField();
		getEmail.setFont(new Font("Arial", Font.PLAIN, 50));
		getEmail.setBackground(Color.WHITE);
		getEmail.setForeground(Color.BLACK);
		getEmail.setEditable(true);
		add(getEmail);
		texts.add(getEmail);
		
		// Get ID
		JLabel idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		idLabel.setBackground(Color.WHITE);
		idLabel.setForeground(Color.BLACK);
		add(idLabel);
		
		JTextField getId = new JTextField();
		getId.setFont(new Font("Arial", Font.PLAIN, 50));
		getId.setBackground(Color.WHITE);
		getId.setForeground(Color.BLACK);
		getId.setEditable(true);
		add(getId);
		texts.add(getId);
		
		// Get company name
		JLabel companyLabel = new JLabel("Company Name:");
		companyLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		companyLabel.setBackground(Color.WHITE);
		companyLabel.setForeground(Color.BLACK);
		add(companyLabel);
		
		JTextField getCompany = new JTextField();
		getCompany.setFont(new Font("Arial", Font.PLAIN, 50));
		getCompany.setBackground(Color.WHITE);
		getCompany.setForeground(Color.BLACK);
		getCompany.setEditable(true);
		add(getCompany);
		texts.add(getCompany);
		
		// Get Contact Name
		JLabel contactName = new JLabel("Contact Name:");
		contactName.setFont(new Font("Arial", Font.PLAIN, 50));
		contactName.setBackground(Color.WHITE);
		contactName.setForeground(Color.BLACK);
		add(contactName);
		
		JTextField getContact = new JTextField();
		getContact.setFont(new Font("Arial", Font.PLAIN, 50));
		getContact.setBackground(Color.WHITE);
		getContact.setForeground(Color.BLACK);
		getContact.setEditable(true);
		add(getContact);
		texts.add(getContact);
		
		// Get Address
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		addressLabel.setBackground(Color.WHITE);
		addressLabel.setForeground(Color.BLACK);
		add(addressLabel);
		
		JTextField getAddress = new JTextField();
		getAddress.setFont(new Font("Arial", Font.PLAIN, 50));
		getAddress.setBackground(Color.WHITE);
		getAddress.setForeground(Color.BLACK);
		getAddress.setEditable(true);
		add(getAddress);
		texts.add(getAddress);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JLabel message = new JLabel("Please fill all the details");
		message.setFont(new Font("Arial", Font.PLAIN, 50));
		message.setBackground(Color.WHITE);
		message.setForeground(Color.BLACK);
		if (e.getActionCommand().compareTo("Submit client details") == 0) {
			String[] info = new String[11];
			int i = 0;
			for (JTextField text: this.texts) {
				if (text.getText().compareTo("") == 0) {
					JOptionPane.showMessageDialog(this, message);
					return;
				}
				info[i++] = text.getText();
			}
			
			try {
				Double.parseDouble(info[1]);
				Double.parseDouble(info[3]);
			} catch (NumberFormatException nfe) {
				message.setText("Phone number or ID are not qualified numbers");
				JOptionPane.showMessageDialog(this, message);
				return;
			}
			
			Client client = new Client(info[0], info[1], info[2]
								, info[3],info[4],info[5], info[6]);
			this.manager.newClient(client);
			int approvalResult = manager.updateClientCreditApproval
									(client.getId(), acc.checkCreditStatus(client));
			
			if (approvalResult == 1) {
				message.setText("Your credit card has been approved");
			} else {
				message.setText("Your credit card has been denied");
			}
			JOptionPane.showMessageDialog(this, message);
		}
	}
}
