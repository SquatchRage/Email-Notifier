
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
import java.sql.Time;
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
 
	JButton saveButton;
	JButton cancelButton;
	
	static String storePassword;
	static String storeUserName;
	static String storeServerName;
	static String getTime;
	static int storeTime;
	JCheckBox storeSound;
	JPanel labelPanel;
	JPanel fieldPanel;
	JPanel buttonPanel;
	JPanel togglePanel;
	Props p = new Props();
	static String protocolProvider = "imaps";
	Timer timer;
	Container cp;

 
public SettingsDialog() throws FileNotFoundException{
	// This line code checks whether there is already a properties file, if there is, it does not display one, if it there isnt, it display dialog. 
	  //Props.propsExist();
	
	  
	  
	 //creation of buttons, labels, textfields, lists and panels. 
	  saveButton = new JButton("Login");
	  saveButton.addActionListener(this);
	  saveButton.setActionCommand("Login");
	  saveButton.isDefaultButton();
	 
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
     timeField.setInputVerifier(new Verification());
     
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

		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
	 cp = getContentPane();
	 cp.add(fieldPanel, BorderLayout.WEST);
	 cp.add(buttonPanel, BorderLayout.SOUTH);
	 

		setSize(500, 300);
		setVisible(true);

 }
 
 @Override
 public void actionPerformed(ActionEvent AE) 
 {     
	 
	 Verification verify = new Verification();

	 
	 if(AE.getActionCommand().equals("Login")){
		 
		 
	     storePassword = passwordField.getText().trim();
	     storeUserName = nameField.getText().trim();
		 storeServerName = serverField.getText().trim();
		 getTime = timeField.getText().trim();
		 storeTime = Integer.parseInt(getTime); 
			 
		 if (verify.verify(timeField)){
             JOptionPane.showMessageDialog(null, "True Value");
         }
         else JOptionPane.showMessageDialog(null, "False Value");
     
		
	
		 //store properties here
		 
			try {
				p.setProps(storeServerName, storeUserName, storePassword, getTime);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 checkingMail.check(storeServerName, protocolProvider, storeUserName, storePassword);
			 JOptionPane.showMessageDialog(null, "You Have " +  checkingMail.newMessageCount + " New Messages! ");
			 new AddIcon();
//------------------------------------------------------------------------------------------------------------------------------------			 
			 // This code hides the settings dialog 1 second after all mail has been read and user clicks on optionPane
			 Timer timer = new Timer(1000, new ActionListener() { 
		            public void actionPerformed(ActionEvent e) {
		                setVisible(false);
		                dispose();
		            }
		        });
		        timer.start();
		        setVisible(true);
//------------------------------------------------------------------------------------------------------------------------------------			 
	 
	 }
	 
	 if(AE.getActionCommand().equals("Cancel")){
		 
		 System.exit(0);
	 }
	 
	 
 	}
}// EOC