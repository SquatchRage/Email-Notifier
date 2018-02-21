
/*2/1/18
Jonathan Rogers*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


public class SettingsDialog extends JDialog implements ActionListener, DocumentListener 
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
	JButton button;
	
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
	int count = 0;
	Container cp;
	

 
public SettingsDialog() throws FileNotFoundException{

	 //creation of buttons, labels, textfields, lists and panels. 
	  saveButton = new JButton("Save");
	  saveButton.addActionListener(this);
	  saveButton.setActionCommand("Save");
	  saveButton.isDefaultButton();
	  saveButton.setEnabled(false);
	  
	 
	 cancelButton = new JButton("Cancel");
	 cancelButton.addActionListener(this);
	 cancelButton.setActionCommand("Cancel");
	 
	 serverLabel = new JLabel("Server:");
	 nameLabel = new JLabel("Username:");
	 passwordLabel = new JLabel("Password:");
	 timeLabel = new JLabel("Time between checks:");
	 playSoundLabel = new JLabel("Sound?");

	 passwordField = new JTextField(20);
	 nameField = new JTextField(20);
	 serverField = new JTextField(20);
	 
	 serverField.setText(Props.server); 
     nameField.setText(Props.user);
     passwordField.setText(Props.pass);
     
     System.out.println(Props.pass);

     timeField = new JTextField(5);
     timeField.setName("timeField");
     timeField.setInputVerifier(new ValidationCheck());

     storeSound = new JCheckBox();

     //puts focus in serverField
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
		setTitle("Mail Notifier");
		setLocationRelativeTo(null);
		setVisible(true);
		
		
//----------------- Document listener that disables Save Button if textfields empty ------------------		
		serverField.getDocument().addDocumentListener(new DocumentListener(){
			  public void changedUpdate(DocumentEvent e){checkLength();}
			  public void removeUpdate(DocumentEvent e){checkLength();}
			  public void insertUpdate(DocumentEvent e){checkLength();}
			});
		
		nameField.getDocument().addDocumentListener(new DocumentListener(){
			  public void changedUpdate(DocumentEvent e){checkLength();}
			  public void removeUpdate(DocumentEvent e){checkLength();}
			  public void insertUpdate(DocumentEvent e){checkLength();}
			});
		
		passwordField.getDocument().addDocumentListener(new DocumentListener(){
			  public void changedUpdate(DocumentEvent e){checkLength();}
			  public void removeUpdate(DocumentEvent e){checkLength();}
			  public void insertUpdate(DocumentEvent e){checkLength();}
			});
		
		timeField.getDocument().addDocumentListener(new DocumentListener(){
			  public void changedUpdate(DocumentEvent e){checkLength();}
			  public void removeUpdate(DocumentEvent e){checkLength();}
			  public void insertUpdate(DocumentEvent e){checkLength();}
			});

 }


public void checkLength(){
	  saveButton.setEnabled(serverField.getDocument().getLength() > 0);
	  saveButton.setEnabled(nameField.getDocument().getLength() > 0);
	  saveButton.setEnabled(passwordField.getDocument().getLength() > 0);
	  saveButton.setEnabled(timeField.getDocument().getLength() > 0);


	}

//----------------------------------------------------------------------------------------------------------------
 @Override
 public void actionPerformed(ActionEvent AE) 
 {     
	 
	 if(AE.getActionCommand().equals("Save")){
		 
	     storePassword = passwordField.getText().trim();
	     storeUserName = nameField.getText().trim();
		 storeServerName = serverField.getText().trim();
		 getTime = timeField.getText().trim();
		 storeTime = Integer.parseInt(getTime); 

		 //store properties here
		 
			try {
				p.setProps(storeServerName, storeUserName, storePassword, getTime);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Does initial mail check, then calls the Icon class, where a timer re-checks mail at given time. 
			 Mail.check(storeServerName, protocolProvider, storeUserName, storePassword);
			 JOptionPane.showMessageDialog(null, "You Have " +  Mail.newMessageCount + " New Messages! ");
			 new Icon();
		 
		 
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
	 count++;
	 }
	 
	 if(AE.getActionCommand().equals("Cancel")){
		 
		 System.exit(0);
	 }
	 
	 
 	}// End of Action


@Override
public void changedUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void insertUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
}


@Override
public void removeUpdate(DocumentEvent e) {
	// TODO Auto-generated method stub
	
}

 public  void close(){
	 
 }

}// EOC