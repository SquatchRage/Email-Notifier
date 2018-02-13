

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class AddIcon implements ActionListener {


	Timer timer;
	TrayIcon trayIcon;
	SystemTray tray;
	PopupMenu popupMenu;
	
	public AddIcon(){
		
		MenuItem menuItem;
		
		popupMenu = new PopupMenu();
		menuItem = new MenuItem("Kill Squatch");
		menuItem.setActionCommand("KILL SQUATCH");
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);
		
		menuItem = new MenuItem("Login Sceen");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("LOGIN SCREEN");
		popupMenu.add(menuItem);
		
		menuItem = new MenuItem("Sound Toggle");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("SOUND TOGGLE");
		popupMenu.add(menuItem);
		
		if(SystemTray.isSupported()){
			tray=SystemTray.getSystemTray();
			trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("image/pic.jpg"), "Email auto-reply.");
			trayIcon.setImageAutoSize(true);
			trayIcon.setPopupMenu(popupMenu);
			try{
				tray.add(trayIcon);
			}
			catch (AWTException e){
				System.out.println("TrayIcon could not be added");
			}
		}
		
		timer = new Timer(4000, this);
		timer.setActionCommand("UPDATE");
		timer.setRepeats(false);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd;
		
		cmd = e.getActionCommand();
		
		if(cmd.equals("UPDATE"))
			JOptionPane.showMessageDialog(null, "The timer just went off!"); // THIS NEEDS TO COORELATE TO NEW MAIL!
		
		if(cmd.equals("LOGIN SCREEN"))
			try {
				new LoginFrame();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		if(cmd.equals("SOUND TOGGLE"))
			JOptionPane.showMessageDialog(null, "Showing last message"); // SO MAKE A YES NO DIALOG HERE THAT WILL ENABLE SOUND
		
		else if (cmd.equals("KILL SQUATCH")){
			tray.remove(trayIcon);
			System.exit(0);
		}
	}
	
	
	
	
}
