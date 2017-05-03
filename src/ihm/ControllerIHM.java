package ihm;

import java.io.File;

import javax.swing.JFileChooser;

import message.Message;
import Network.NetworkInteractionController;
import core.LocalUser;
import core.User;
import core.UserListModel;

public class ControllerIHM{
	
	private UserListModel userlist;
	private NetworkInteractionController netcontroller;
	public ControllerIHM(NetworkInteractionController controller) {	
		this.userlist = new UserListModel();
		this.netcontroller = controller;
	}

	/**
	 * Set the ID of the localuser in the model
	 * @param ID : String; Nickname of the user
	 * @throws NomNonValideException : Error in case of no valid name
	 */
	public void setID(String ID)  throws NomNonValideException{
		
		if (CheckID(ID)){
			LocalUser.getInstance().setID(ID);
			
		}
		else {
			throw new NomNonValideException("this username is not possible");
		}
		 	
	}
	
	/**
	 * 
	 * @param ID 
	 * @return
	 */
	private Boolean CheckID (String ID ) {
		return true;
	}
	
	/**
	 * Open the first frame where we choose our name
	 */
	public void Connection (){
		ConnectionIHM connectionframe = new ConnectionIHM(this); 
		connectionframe.pack();
		connectionframe.setAlwaysOnTop(true);
		connectionframe.setLocationRelativeTo(null);
		connectionframe.setVisible(true);
		
	}
	
	/**
	 * Open the mainwindow of the chat
	 * @param userlist: UserListModel; The moving userlist
	 */
	public void openchat(UserListModel userlist){
		ChatIHM chatframe = new ChatIHM(this, userlist);
//		chatframe.pack();
		chatframe.setLocationRelativeTo(null);
		chatframe.setVisible(true);
	}
	
	public String openSendfileIHM(){
		String filename ="";
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	       filename =  chooser.getCurrentDirectory()+"/"+chooser.getSelectedFile().getName();
	       
	    }
	    return filename;
	}
	
	/**
	 * Send the message via the network controller; @see NetworkInteractionController
	 * @param user : User; target of the message. 
	 * @param text2send : String; Text to send;
	 */
	public void actionsend(User user, String text2send){
		Message msg = new Message();
		msg.setData(text2send);
		msg.setTypeData(text2send);
		this.netcontroller.sendmessage(user, msg);
	}
	
	
	/**
	 * Send the file to the target via the network contoller; @see NetworkInteractionController
	 * @param user : User; target of the message. 
	 * @param path : String; Path of the file to send;
	 */
	public void actionsendfile(User user, String path){
		Message msg = new Message();
		File file2send = new File(path);
		msg.setFile(file2send);
		msg.setTypeData(file2send);
		this.netcontroller.sendmessage(user, msg);
		
	}
	
	public void actiondisconnect(){
		this.netcontroller.disconnect();
	}
	
}
