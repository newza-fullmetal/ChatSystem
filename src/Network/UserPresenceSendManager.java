package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;

import javax.swing.JOptionPane;

import user.MessageUser;
import core.LocalUser;

public class UserPresenceSendManager extends Thread{
	private MessageUser messagepresence;
	private NetworkInteractionController controller;
	
	public UserPresenceSendManager (NetworkInteractionController control) {
		this.controller = control;
	}
	
	public void run() {
		System.out.println("Thread presence start !");
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(2000); // 2 second to wait
				SendPresenceMessage();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
		
	private void SendPresenceMessage() {
		
		this.messagepresence = new MessageUser(LocalUser.getInstance().getPseudo(), LocalUser.getInstance().getIP(), LocalUser.getInstance().getPort(), LocalUser.getInstance().getEtat());
		
		ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bytearray);
			oos.writeObject(this.messagepresence);
			DatagramPacket hi = new DatagramPacket(bytearray.toByteArray(), bytearray.size(),
                    controller.group, 6789);
			controller.G_MultiSocket.send(hi);
			/**
			 * Debug
			 * System.out.println("MESSAGE SENT : " + this.messagepresence.toString());
			 */
		}
		catch (IOException e){
			JOptionPane.showMessageDialog(null,
				    "error while converting message to byte : " + e.getMessage(),
				    "warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	
	
	
}
