package tests;


import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Network.NetworkInteractionController;
import core.LocalUser;
import core.User;
import core.UserListModel;
import message.Message;

import org.junit.Assert;
/**
 * Class to test all the different features of the network controller, we will use some features to test the others
 * @author Julien
 *
 */
public class NetworkInteractionControllerTest {
	
	private static NetworkInteractionController netController;
	private static UserListModel userlist;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("------------------------");
        System.out.println("Tests Sur la classe NetworkInteractionController");
        System.out.println("------------------------");
		userlist = new UserListModel();
		netController = new NetworkInteractionController(userlist);
		LocalUser.getInstance().setID("localuser");
		LocalUser.getInstance().setConnected();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		LocalUser.getInstance().setID("localuser");
		LocalUser.getInstance().setConnected();
		netController.rcvpresencemessage(); // Activate the receive presence manager
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendpresencemessage() {
		System.out.println("------------------------");
        System.out.println("Test réception de message de présence");
        System.out.println("------------------------");
		netController.sendpresencemessage();
		try{
			Thread.sleep(4000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(userlist.getlistmodel());
		Assert.assertEquals("localuser", userlist.getlistmodel().get(0).getPseudo());
		System.out.println("Test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test activation de deux messages de présence");
        System.out.println("------------------------");
        netController.sendpresencemessage();
		try{
			Thread.sleep(3500);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(userlist.getlistmodel());
		Assert.assertEquals("deux messages de présences", 1 , userlist.getlistmodel().getSize());
		System.out.println("Test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test message de présence utilisateur null");
        System.out.println("------------------------");
        LocalUser.getInstance().setID(null);
        
		Assert.assertEquals("présence d'un user null", 1 , userlist.getlistmodel().getSize());
		System.out.println("Test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test message de déconnexion");
        System.out.println("------------------------");
        LocalUser.getInstance().setID("localuser");
        LocalUser.getInstance().setDisconnected();;
        try{
			Thread.sleep(3500);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
    	Assert.assertEquals("User non déconnecté", 0, userlist.getlistmodel().getSize());
		System.out.println("Test OK !");
		LocalUser.getInstance().setID("localuser");
		LocalUser.getInstance().setConnected();
		try{
			Thread.sleep(3500);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	

	@Test
	public void testsendmessage() {
		ArrayList<User> listuser = new ArrayList<User>();
		listuser.add(userlist.getlistmodel().get(0));
		netController.receivemessage();
		System.out.println("------------------------");
        System.out.println("Test envoie de message pour un utilisateur null");
        System.out.println("------------------------");
        Message msg = new Message();
        msg.setData("coucou");
		netController.sendmessage(null, msg);
		
		System.out.println("------------------------");
        System.out.println("Test envoie de message null");
        System.out.println("------------------------");
		netController.sendmessage(listuser, null);
		Assert.assertEquals("msg null", 0, userlist.getuserlist().get("localuser").getMsgList().getSize());
		System.out.println("Test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test envoie de 100 messages coucou différents");
        System.out.println("------------------------");
		
		for (int i=0;i<100; i++){
			msg.setData("coucou"+i);
			netController.sendmessage(listuser, msg);
			try{
				Thread.sleep(100);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
			Assert.assertTrue("msg = "+msg.getData(), msg.getData().equals(userlist.getuserlist().get("localuser").getMsgList().getElementAt(i).toString()));		
		}
		System.out.println("Test OK !");
		
		
		
		
	}
/**
 * Theses functions are already tested with the others tests before
 */
//	@Test
//	public void testUserpresenceevent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testNewincomingmessage() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testDisconnect() {
		netController.sendpresencemessage();
		System.out.println("------------------------");
        System.out.println("Test de déconnexion");
        System.out.println("------------------------");
        netController.disconnect();
        Assert.assertEquals("User non déconnecté", 0, userlist.getlistmodel().getSize());
		System.out.println("Test OK !");
	}

}
