import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInterfaceFrame extends JFrame implements ActionListener {

	private JPanel actionsButtonJPanel;
	public JPanel action;
	private Storage storage = Storage.getInstance(System.getProperty("user.dir") + "/Database");
	private StorageManager manager = new StorageManager(storage);
	private AccountManagement acc = new AccountManagement("ma", "asda", "asda", "asd");
	
	public UserInterfaceFrame() {
		super("UserInterface");
		
		actionsButtonJPanel = new JPanel();
		actionsButtonJPanel.setLayout(new GridLayout(1, 3));
		
		this.addButtons();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button_text = e.getActionCommand();
		
		if (action != null) {
			this.remove(action);
			revalidate();
		}
		switch (button_text) {
		case "Add New Client":
			action = new ClientPanel(this.manager, this.acc); 
			add(action, BorderLayout.CENTER);
			revalidate();
			break;
		case "Check PO Status":
			action = new StatusPanel(this.manager); 
			add(action, BorderLayout.CENTER);
			revalidate();
			break;
		
		case "New Product Order":
			action = new POPanel(this.manager); 
			add(action, BorderLayout.CENTER);
			revalidate();
			break;
		}
		
		
	}

	
	private void addButtons() {
		JButton b;

		b = new JButton("Add New Client");
		b.setFont(new Font("Arial", Font.PLAIN, 50));
		b.setBackground(Color.WHITE);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		actionsButtonJPanel.add(b);

		b = new JButton("Check PO Status");
		b.setFont(new Font("Arial", Font.PLAIN, 50));
		b.setBackground(Color.WHITE);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		actionsButtonJPanel.add(b);

		b = new JButton("New Product Order");
		b.setFont(new Font("Arial", Font.PLAIN, 50));
		b.setBackground(Color.WHITE);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		actionsButtonJPanel.add(b);

		add(actionsButtonJPanel, BorderLayout.SOUTH);
		
		
		JLabel instructionsLabel = new JLabel("Choose Action");
		instructionsLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		instructionsLabel.setBackground(Color.WHITE);
		instructionsLabel.setForeground(Color.BLACK);
		add(instructionsLabel, BorderLayout.NORTH);
	}
}
