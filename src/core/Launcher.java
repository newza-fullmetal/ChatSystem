package core; 

import ihm.ControllerIHM;


import user.MessageUser.typeConnect;
import Network.NetworkInteractionController;

public class Launcher {

	public static void main(String[] args) {
		
		UserListModel userlist = new UserListModel();
		NetworkInteractionController netController = new NetworkInteractionController(userlist);
		
		ControllerIHM controller = new ControllerIHM(netController);
		
		controller.Connection();
		while (LocalUser.getInstance().getEtat() != typeConnect.CONNECTED){
			// Wait for user connection
			System.out.println("");
		}
		System.out.println("test fin de connexion");
		
		controller.openchat(userlist);

		
		
		netController.rcvpresencemessage();
		netController.sendpresencemessage();
		netController.receivemessage();
		
		
		
		/**
		 * TEST ENVOI MESSAGE
		 */
//		Message msgtest = new Message();
//		msgtest.setData("ceci est un message test");
//		
//		try {
//			Thread.sleep(4000); 
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//		}
//		netController.sendmessage(LocalUser.getInstance(), msgtest);
//		
//		/**
//		 * TEST ENVOIE FICHIER
//		 */
//		
//		Message msgfiletest = new Message();
//		File file = new File(controller.openSendfileIHM());
//		msgfiletest.setFile(file);
//		msgfiletest.setTypeData(file);
//		netController.sendmessage(LocalUser.getInstance(), msgfiletest);
//		
		
		
		
		
		
		
		
		
		
		

		
	}
}
