import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class ValidationCheck extends InputVerifier {

	   public boolean verify(JComponent input) {
	        String name = input.getName();

	        if(name.equals("timeField"))
	        {
	            String text = ((JTextField) input).getText().trim();
	           
	            if (text.equals("")){
	                JOptionPane.showMessageDialog(null, "True Value11");

	            	
	            	return false;
	            }
	            if (text.matches("[A-z]")){
	            	
	                JOptionPane.showMessageDialog(null, "True Value2");

	            	
	            	return false;
	            }
	            
	            if (!text.matches("2[0-9]|[1-9][0-9]|[1-9][0-9][0-9]|1[1-9][0-9][0-9]|2[1-9][0-9][0-9]|3[1-5][0-9][0-9]")){
	            	
	                JOptionPane.showMessageDialog(null, "Keep number in the 20-3600 range");

	            	
	            	return true;
	            }
	        }
	       
	      
	        return true;
	    }
}