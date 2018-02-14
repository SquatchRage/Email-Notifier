import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class Verification extends InputVerifier {
	  public boolean verify(JComponent input) {
		    JTextField tf = (JTextField) input;
		    String inputField = tf.getText();
		    int converted = Integer.parseInt(inputField); 
		   
		    if(inputField.isEmpty()) return false;
		    if(converted < 20 || converted > 3600) return false;
		    
		    return true;
		  }
}
