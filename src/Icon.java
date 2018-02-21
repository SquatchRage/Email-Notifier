

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Icon implements ActionListener {


	Timer timer;
	TrayIcon trayIcon;
	SystemTray tray;
	PopupMenu popupMenu;
	
	public Icon(){
		
		MenuItem menuItem;
		
		popupMenu = new PopupMenu();
		menuItem = new MenuItem("Kill Squatch");
		menuItem.setActionCommand("KILL SQUATCH");
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);
		
		menuItem = new MenuItem("Settings");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("SETTINGS");
		popupMenu.add(menuItem);
		
		menuItem = new MenuItem("Sound on");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("SOUND ON");
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
		try {
			Props.getProps();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Getting time from props an storing into an integer and creating timer for it. 
		int timeStore = Integer.parseInt(Props.time);
		timeStore *= 1000;
		System.out.println(timeStore + " millis between mail checks \n\n");
		timer = new Timer(timeStore, this);
		timer.setActionCommand("CHECK MAIL");
		timer.setRepeats(true);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd;
		
		cmd = e.getActionCommand();
		
		if(cmd.equals("CHECK MAIL")){
			try {
				Props.getProps();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 Mail.check(Props.server, SettingsDialog.protocolProvider, Props.user, Props.pass);
		
			 // checks mail count; if new mail play sound
			if(Mail.newMessageCount >= 1)
					JOptionPane.showMessageDialog(null, "New Mail!"); 
				//	Sound.playSound();  THIS PLAYS SOUND, BUT PLAYS IT AT EVERY SINGLE MAIL CHECK, EVEN THOUGH ITS IN A IF STATEMENT TO CHECK FOR NEW MAIL...
		}
		
		if(cmd.equals("SETTINGS"))
			try {
				Props.getProps();
		
				new SettingsDialog();

				timer.stop();
				
			
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		if(cmd.equals("SOUND ON"))
			JOptionPane.showMessageDialog(null, "Showing last message"); // SO MAKE A YES NO DIALOG HERE THAT WILL ENABLE SOUND
		
		else if (cmd.equals("KILL SQUATCH")){
			tray.remove(trayIcon);
			System.exit(0);
		}
	}
}
