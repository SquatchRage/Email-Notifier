
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Props {
	
	String getServer,  getUser,  getPass;

	
	public void setProps(String server, String user, String pass) throws IOException{
		
		Properties prop = new Properties();
		OutputStream os = new FileOutputStream("config.properties");
	
		prop.setProperty("Host", server);
		prop.setProperty("User", user);
		prop.setProperty("Password", pass);

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
		
		 LoginFrame.storeServerName = prop.getProperty("Host");
		 LoginFrame.storeUserName = prop.getProperty("User");
		 LoginFrame.storePassword = prop.getProperty("Password");
}




	public static void propsExist() throws FileNotFoundException{
		
		
		File propertiesFile = new File("config.properties");
		if(propertiesFile.exists()){
			  System.out.println("File existed");
			  getProps();
		  }else{
			  System.out.println("File not found!");
		  }

	  }
	
}
