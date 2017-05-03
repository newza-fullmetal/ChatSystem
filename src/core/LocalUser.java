package core;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import user.MessageUser.typeConnect;

public class LocalUser extends User {
	
	/**
	 * Private constructor
	 * @param IP
	 * @param port
	 */
	private LocalUser(InetAddress IP, int port) 
	{super(IP, port);}
	
//	public LocalUser(String pseudo, InetAddress IP, int port, typeConnect etat) {
//		super(pseudo, IP, port, etat);
//	}
	
	
	
	/** Holder */
	private static class LocalUserHolder
	{		
		/** Instance unique non préinitialisée */
		private final static LocalUser instance = new LocalUser(getlocaladdress(), 2345);
	}
	
	/** Point d'accès pour l'instance unique du singleton */
	public static LocalUser getInstance()
	{	return LocalUserHolder.instance;
	}
	
	/**
	 * Set the Nickname of the local User
	 * @param ID : String; 
	 */
	public void setID(String ID){
		this.ID = ID;
	}
	
	/**
	 * Set the state to connected
	 */
	public void setConnected() {
		this.etat= typeConnect.CONNECTED;
	}
	
	/**
	 * Set the state to disconnected
	 */
	public void setDisconnected(){
		this.etat= typeConnect.DECONNECTED;
	}
	
	/**
	 * Get the local IP address
	 * @return LocalHost : InetAddress; 
	 */
	private static InetAddress getlocaladdress(){
		try {
			return InetAddress.getLocalHost();
		}
		catch (UnknownHostException e){
			JOptionPane.showMessageDialog(null,
				    "error getting the local IP address : " + e.getMessage(),
				    "error",
				    JOptionPane.ERROR_MESSAGE);
			return InetAddress.getLoopbackAddress();
		}
	}
	
}
