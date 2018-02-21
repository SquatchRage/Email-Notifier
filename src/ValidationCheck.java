import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class ValidationCheck extends InputVerifier {

	public boolean verify( JComponent input) { 
        String                str; 

        int                    store; 
        boolean             	type; 
        JTextField            textField; 
         
        textField = (JTextField) input; 
        str = textField.getText().trim(); 
         
                 
        try 
        { 
 
        	store = Integer.parseInt(str); 
             
            if(store < 20 || store > 3600) 
            { 
                 
            	type = false; 
                 
            } 
 
            else  { 
                 
            	type = true; 
                  } 
             
        } 
             
        catch(NumberFormatException e)  { 
         	type = false; 
                    } 
             
         
        if(type == false){ 
     
            JOptionPane.showMessageDialog(null, "Integer values between 20-3600 only!"); 
         
        } 
         
         
        return type; 
         
    	}
	
	
    }
