package core;

import java.net.InetAddress;

import message.Message;
import user.MessageUser.typeConnect;

/**
 * Class representing a User in the chat System 
 * @author coustill
 *
 */
public class User {
	/**
	 * name of the user
	 */
	protected String ID;
	
	/**
	 * IP address of the user
	 */
	private InetAddress IPaddress; 
	
	/**
	 * Statut of the user
	 */
	private String Statut;
	
	
	/**
	 * Le port du user
	 */
	private  int port;
	
	/**
	 * Enumeration qui indique l'état (Connecté ou deconnecté) du user
	 */
	protected typeConnect etat; 
	
	private MessageListModel msglist;
	
	public User(String pseudo, InetAddress iP, int port, typeConnect etat) {
		this.ID = pseudo;
		this.IPaddress = iP;
		this.port = port;
		this.etat=etat;
		msglist = new MessageListModel();
	}
	
	/**
	 * Constructor without ID
	 * @param iP : InetAddress
	 * @param port integer
	 */
	public User(InetAddress iP, int port) {
		this.IPaddress = iP;
		this.port = port;
	}
	
	public String getPseudo() {
		return this.ID;
	}


	public InetAddress getIP() {
		return this.IPaddress;
	}



	public int getPort() {
		return port;
	}


	public typeConnect getEtat() {
		return etat;
	}


	public String getStatut() {
		return this.Statut;
	}


	public void setStatut(String statut) {
		this.Statut = statut;
	}
	
	public String toString(){
		return this.ID +"   "+  this.etat;
	}
	
	/**
	 * Add a message in the historic
	 * @param msg : Message; @see Message;
	 */
	public void addmessage(Message msg){
		System.out.println("Message added for : " + this.ID);
		this.msglist.addmessage(msg);
	}
	

	
}
