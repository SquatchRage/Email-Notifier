
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Props {
	
	static String server; 
	static String user; 
	static String pass;
	static String time;

	
	public void setProps(String server, String user, String pass, String time) throws IOException{
		
		Properties prop = new Properties();
		OutputStream os = new FileOutputStream("config.properties");
	
		prop.setProperty("Host", server);
		prop.setProperty("User", user);
		prop.setProperty("Password", pass);
		prop.setProperty("checkTime", time);


		prop.store(os, null);
		
	}
	


    
public static void getProps() throws FileNotFoundException{
		
		Properties prop = new Properties();
		InputStream is = new FileInputStream("config.properties");
		
	try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	server = prop.getProperty("Host");
	user = prop.getProperty("User");
	pass = prop.getProperty("Password");
	time = prop.getProperty("checkTime");
	
}



// check whether there is a property file or not.  If there is, it'll access the config file and run the Icon class, if not it'l create a settings dialog
	public static void propsExist() throws FileNotFoundException{
		
		
		File propertiesFile = new File("config.properties");
		if(propertiesFile.exists()){
			
			new Icon();

		}else{
			  System.out.println("File not found!");
			  new SettingsDialog();
			  

		  }

	  }
	
}
