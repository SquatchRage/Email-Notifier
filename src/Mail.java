
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.mail.imap.IMAPMessage;

public class Mail {

	static Session session;
	static Store store;
	static Folder inboxFolder;
	static String protocolProvider;
	static Message[] messageList = null;
	static Message me;
	static int messageCount, newMessageCount;
	
	public static void check(String host, String protocolProvider, String user,String pass){
	
	try{
	    Properties props = new Properties();
		session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		
		store = session.getStore(protocolProvider);
		store.connect(host, user, pass);
		
		inboxFolder = store.getFolder("INBOX");
		inboxFolder.open(Folder.READ_WRITE);
		
		processFolder(inboxFolder);
		messageList = inboxFolder.getMessages();
	
		      inboxFolder.close(false);
		      store.close();

		      } catch (NoSuchProviderException nspe) {
		    	  
		    	  System.out.println("No Such Email protocol Provider: " + protocolProvider);
		    	  
		      } catch (MessagingException me) {
		    	  System.out.println("Message Exception: ");

		         me.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
	
	}


	static void processFolder(Folder mailboxFolder) throws MessagingException{
	
	if(mailboxFolder.hasNewMessages())

	messageCount = mailboxFolder.getMessageCount();
	newMessageCount = mailboxFolder.getNewMessageCount();
	
	//System.out.println("===============================================================================\n");
	}
	
	

}
