
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPMessage;

public class checkingMail {

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
		
		System.out.println("Number of folder" + store.getPersonalNamespaces().length);
		
		inboxFolder = store.getFolder("INBOX");
		inboxFolder.open(Folder.READ_WRITE);
		
		processFolder(inboxFolder);
		messageList = inboxFolder.getMessages();
		   System.out.println("messages.length---" + messageList.length);

		      for (Message message: messageList) {
		        processFolder(inboxFolder);
		      }

		      //close the store and folder objects
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
		System.out.println("New Messages!");
	else System.out.println("No New Messages!");
	
	System.out.println("Number of Messages:" + mailboxFolder.getMessageCount()+"\n");
	System.out.println("Number of new Messages: " + mailboxFolder.getNewMessageCount()+"\n");
	System.out.println("Number of unread messages: "+ mailboxFolder.getUnreadMessageCount()+"\n");
	
	messageCount = mailboxFolder.getMessageCount();
	newMessageCount = mailboxFolder.getNewMessageCount();
	
	System.out.println("===============================================================================\n");
	}
	
/*	static void processMessage(Message me){
		
		String[] headerList;
		
		try{
			
			System.out.println("From: "+me.getFrom()[0] + "\n\n");
			
			headerList = me.getHeader("to");
			if(headerList != null)
				System.out.println("To: " + headerList[0] + "\n \n");
			
			headerList = me.getHeader("date");
			if(headerList != null)
				System.out.println("Date Header: " + headerList[0] + "\n \n");
			
			System.out.println("Recieved Date is: " + me.getReceivedDate()+ "\n");
			System.out.println("Subject is: " + me.getSubject() + "\n");
			
			System.out.println("=======================================================================================");
		}
		catch (MessagingException m){
			System.out.println("MessagingExceptionFound.  Bad Messages?");
		}
	}
		
		
	static void showAllHeaders(IMAPMessage msg)throws MessagingException{
		
		Enumeration<String>  headerList;
		
		headerList = msg.getAllHeaderLines();
		
		while(headerList.hasMoreElements())
			System.out.println(">> " + headerList.nextElement());
	}
*/
}
