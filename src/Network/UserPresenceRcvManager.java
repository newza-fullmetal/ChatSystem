package Network;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

import javax.swing.JOptionPane;

import user.MessageUser;

/**
 * Manager to receive the different message;
 * @author coustill
 *
 */
public class UserPresenceRcvManager extends Thread{
	
	private NetworkInteractionController controller;
	
	public UserPresenceRcvManager (NetworkInteractionController control ){
		this.controller = control;
	}
	
	public void run(){
		System.out.println("Thread reception Start");
		while (!Thread.currentThread().isInterrupted()) {
			byte[] recvBuf = new byte[5000];
		    DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
		    try{
		    	this.controller.G_MultiSocket.receive(packet);
		    	int byteCount = packet.getLength();
		        ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
		        ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
		        MessageUser msguser = (MessageUser)is.readObject();
		        System.out.println("new presence : " + msguser.getPseudo() + msguser.getEtat());
		        this.controller.userpresenceevent(msguser);
		        is.close();
		    }
		    catch (IOException e ){
		    	JOptionPane.showMessageDialog(null,
					    "error while receving packet: " + e.getMessage(),
					    "error",
					    JOptionPane.ERROR_MESSAGE);
		    } catch (ClassNotFoundException e) {
		    	JOptionPane.showMessageDialog(null,
					    "error while receving packet: " + e.getMessage(),
					    "error",
					    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		    
		}
	}
}
