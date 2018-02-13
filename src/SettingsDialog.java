
/*2/1/18
Jonathan Rogers*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;

public class SettingsDialog extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 8417262671424751407L;
	JLabel serverLabel;
	JLabel nameLabel;
	JLabel passwordLabel;
	JLabel playSoundLabel;
	JLabel timeLabel;
 
	JTextField nameField;
	JTextField passwordField;
	JTextField serverField;
	JTextField timeField;
 
	JButton loginButton;
	JButton cancelButton;
	
	static String storePassword;
	static String storeUserName;
	static String storeServerName;
	String setPassword, setUserName, setServerName;
	int storeTime;
	JCheckBox storeSound;
	JPanel labelPanel;
	JPanel fieldPanel;
	JPanel buttonPanel;
	JPanel togglePanel;
	Props p = new Props();
	String protocolProvider = "imaps";
	Container cp;

 
public SettingsDialog() throws FileNotFoundException{
	
	Props.propsExist();
	 //creation of buttons, labels, textfields, lists and panels. 
	 loginButton = new JButton("Login");
	 loginButton.addActionListener(this);
	 loginButton.setActionCommand("Login");
	 
	 cancelButton = new JButton("Cancel");
	 cancelButton.addActionListener(this);
	 cancelButton.setActionCommand("Cancel");
	 
	 serverLabel = new JLabel("Server:");
	 nameLabel = new JLabel("Username:");
	 passwordLabel = new JLabel("Password:");
	 timeLabel = new JLabel("Time between checks:");
	 playSoundLabel = new JLabel("Sound?");

	 serverField = new JTextField(20);
	 serverField.setText(storeServerName); 
	 
     nameField = new JTextField(20);
     nameField.setText(storeUserName);
     
     passwordField = new JTextField(20);
     passwordField.setText(storePassword);
     
     timeField = new JTextField(5);
     
     storeSound = new JCheckBox();

     addWindowListener( new WindowAdapter() {
    	    public void windowOpened( WindowEvent e ){
    	        serverField.requestFocus();
    	    }
    	}); 
     
	 fieldPanel = new JPanel(new GridBagLayout()); 
	 buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	 
	 GridBagConstraints gbc = new GridBagConstraints();
	 
		gbc.insets = new Insets(4, 4, 4, 4);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		fieldPanel.add(serverLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		fieldPanel.add(serverField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		fieldPanel.add(nameLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		fieldPanel.add(nameField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		fieldPanel.add(passwordLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1;
		fieldPanel.add(passwordField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		fieldPanel.add(timeLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1;
		fieldPanel.add(timeField, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.weightx = 1;
		fieldPanel.add(playSoundLabel, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.weightx = 1;
		fieldPanel.add(storeSound, gbc);

		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		
	 cp = getContentPane();
	 cp.add(fieldPanel, BorderLayout.WEST);
	 cp.add(buttonPanel, BorderLayout.SOUTH);
	 

 }
 
 @Override
 public void actionPerformed(ActionEvent AE) 
 {     
	 if(AE.getActionCommand().equals("Login")){
		 
	     storePassword = passwordField.getText().trim();
	     storeUserName = nameField.getText().trim();
		 storeServerName = serverField.getText().trim();
		//storeTime = Integer.parseInt(timeField.getText());

		 //store properties here
		 
			try {
				p.setProps(storeServerName, storeUserName, storePassword);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 checkingMail.check(storeServerName, protocolProvider, storeUserName, storePassword);
			 JOptionPane.showMessageDialog(null, "You Have " +  checkingMail.newMessageCount + " New Messages! ");
			 new AddIcon();
			 Timer timer = new Timer(1000, new ActionListener() { // 10 sec
		            public void actionPerformed(ActionEvent e) {
		                setVisible(false);
		                dispose();
		            }
		        });

		        timer.start();

		        setVisible(true);
			 
	 
	 }
	 
	 if(AE.getActionCommand().equals("Cancel")){
		 
		 System.exit(0);
	 }
	 
 }

 
}