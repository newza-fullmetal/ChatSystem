package Network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import core.User;

public class SendMessage {
	
	protected ArrayList<User> targets;
	protected Object datatosend;
	protected NetworkInteractionController controller;
	
	SendMessage(ArrayList<User> users, Object data, NetworkInteractionController control ){
		this.targets=users;
		this.datatosend = data;
		this.controller = control;
	}
	SendMessage(User user,Object data,NetworkInteractionController control  ){
		this.targets = new ArrayList<User>();
		this.targets.add(user);
		this.datatosend = data;
		this.controller = control;
	}

	public void send(){
		ObjectOutputStream oos;
		OutputStream os;
		Socket socket; 
		try {
			for (User user : this.targets){
				socket = new Socket(user.getIP(), user.getPort());
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os);
				oos.writeObject(this.datatosend);
				oos.flush();
				oos.close();
				socket.close();
				System.out.println("Envoie de message vers : " + user.getIP() + user.getPort() + "\n" +
									"Contenu :" + this.datatosend);
			}
		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
