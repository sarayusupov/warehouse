import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StatusPanel extends JPanel implements ActionListener {
	
	private JFrame frame;
	private StorageManager manager;
	public ArrayList<JTextField> texts;

	
	public StatusPanel(StorageManager manager) {
		this.manager = manager;
		this.setLayout(new GridLayout(2, 2));
		this.setBackground(Color.WHITE);
		texts = new ArrayList<JTextField>();
		this.initializeButtons();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea message = new JTextArea(this.manager.getPOStatus(this.texts.get(0).getText()));
		message.setFont(new Font("Arial", Font.PLAIN, 50));
		message.setBackground(Color.WHITE);
		message.setForeground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(message);
		JOptionPane.showMessageDialog(this, scrollPane);
	}
	
	private void initializeButtons() {
		JLabel label = new JLabel("Write the product id and press the button");
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 50));
		add(label);
		
		JButton button = new JButton("Submit id ");
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Arial", Font.PLAIN, 50));
		button.addActionListener(this);
		add(button);
		
		// Get name 
		JLabel nameLabel = new JLabel("Product Order ID:");
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
	}

}
