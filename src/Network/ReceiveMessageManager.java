package Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import message.Message;
import core.LocalUser;

/**
 * Thread created to receive any new message in TCP
 * @author Julien Coustillas
 *
 */
public class ReceiveMessageManager extends Thread{
	
	private NetworkInteractionController controller;
	
	public ReceiveMessageManager (NetworkInteractionController control ){
		this.controller = control;
	}
	
	/**
	 * This thread can be stopped on the order of the Controller . 
	 */
	public void run(){
		System.out.println("Thread receptionMessage Start");
		while (!Thread.currentThread().isInterrupted()) {
			try{
				ServerSocket socketserver  ;
		        Socket socketduserveur ;
		        socketserver = new ServerSocket( LocalUser.getInstance().getPort());
	            socketduserveur = socketserver.accept(); 
				ObjectInputStream ois;
				InputStream is = socketduserveur.getInputStream();
				ois = new ObjectInputStream(is);
				Message msg = (Message)ois.readObject();
				if (socketduserveur.getInetAddress() != null) {
					this.controller.Newincomingmessage(msg, socketduserveur.getInetAddress());
				}
				//DEBUG
				 System.out.println("New incoming message :" + ((msg.getData()==null)?"C'est un fichier":msg.getData())); // null if file
				 socketserver.close();
	             socketduserveur.close();
			}
			
			catch(IOException e){
				JOptionPane.showMessageDialog(null,
					    "error while receving message: " + e.getMessage(),
					    "error",
					    JOptionPane.ERROR_MESSAGE);
			}
			catch(ClassNotFoundException e){
				JOptionPane.showMessageDialog(null,
					    "error while receving message: " + e.getMessage(),
					    "error",
					    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				
			}
		}
		
		
	}

}
