package Network;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import message.Message;
import user.MessageUser;
import user.MessageUser.typeConnect;
import core.LocalUser;
import core.User;
import core.UserListModel;


/**
 * Class handling all the interactions with the network
 * @author coustill
 *
 */

public class NetworkInteractionController {
	
	String Multicastaddr = "225.1.2.3";// Address reserved for the multicast
	MulticastSocket G_MultiSocket;
	InetAddress group;
	private UserPresenceSendManager presencemanager;
	private UserPresenceRcvManager msgrcptmanager;
	private UserListModel Userlist;
	private ReceiveMessageManager rcvmsgmanager;
	private SendMessage sendmessage;
	DatagramSocket G_UniSocket;
	
	public NetworkInteractionController (UserListModel list) {
		joinmultigroup();
		this.Userlist = list;
		try {
			this.G_UniSocket= new DatagramSocket(1234);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		
	}
	
	/**
	 * Create the socket and join the Multicast Group 
	 */
	private void joinmultigroup() {
		 try {
			 this.group = InetAddress.getByName(this.Multicastaddr);
			 try {
				 this.G_MultiSocket = new MulticastSocket(6789);
				 this.G_MultiSocket.joinGroup(this.group);
				 System.out.println("Connected to Multicast group");
			 }
			 catch (IOException e){
				 JOptionPane.showMessageDialog(null,
						    "error while joining multicast group : " + e.getMessage(),
						    "warning",
						    JOptionPane.WARNING_MESSAGE);
			 }
			 	
		 }
		 catch (UnknownHostException e) {
			 JOptionPane.showMessageDialog(null,
					    "error while joining multicast group : " + e.getMessage(),
					    "warning",
					    JOptionPane.WARNING_MESSAGE);
		 }
		
		 
	}
	
	
	
	/**
	 * Activate the auto send of presence message
	 */
	public void sendpresencemessage (){
		this.presencemanager = new UserPresenceSendManager(this);
		this.presencemanager.start();
		
	}
	/**
	 * Activate the listenning of the presence message
	 */
	public void rcvpresencemessage (){
		this.msgrcptmanager = new UserPresenceRcvManager(this);
		this.msgrcptmanager.start();
	}
	
	public void receivemessage(){
		this.rcvmsgmanager = new ReceiveMessageManager(this);
		this.rcvmsgmanager.start();
	}
	
	public void sendmessage(User user, Message msg)
	{
		this.sendmessage = new SendMessage(user, msg, this);
		this.sendmessage.send();
		
	}
	
	public void userpresenceevent(MessageUser msg){
		User user = new User(msg.getPseudo(), msg.getIP(), msg.getPort(), msg.getEtat());
		if (msg.getEtat() == typeConnect.CONNECTED || msg.getEtat() ==null ){
			
			this.Userlist.adduser(user);
		}
		else {
			
			this.Userlist.removeuser(user);
		}
	}
	
	/**
	 * Add a new message in the history of message of a user
	 * @param msg : Message;
	 * @param remoteIP : InetAddress;
	 */
	public void Newincomingmessage(Message msg, InetAddress remoteIP){
		if (!msg.isFile()){
			if (msg.getData() != null ){
				Set<String> cles = this.Userlist.getuserlist().keySet();
				Iterator<String> it = cles.iterator();
				while (it.hasNext()){
					Object cle = it.next();
					User user =  this.Userlist.getuserlist().get(cle); 
					if (user.getIP().getHostAddress() == remoteIP.getHostAddress()){
						user.addmessage(msg);
						user.displaymessage();
					}
				}
			}
		}
		else {
			try {
				JFileChooser chooser = new JFileChooser();
				FileOutputStream fos = new FileOutputStream(chooser.getCurrentDirectory()+"/"+msg.getFile().getName());
				System.out.println("file saved to" + chooser.getCurrentDirectory()+"/"+msg.getFile().getName());
				fos.write(msg.getContentFile());
				fos.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Function to disconnect when leaving 
	 * Set the Local user to disconnected and let time to send a message to the unicast group
	 * Kill the network thread and then the main Thread
	 */
	public void disconnect(){
		LocalUser.getInstance().setDisconnected();
		try { // Some time to send the message
			Thread.sleep(3000); 
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.exit(0);

	}
	
	
}