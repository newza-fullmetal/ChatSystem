package tests;


import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import core.User;
import core.UserListModel;
import user.MessageUser.typeConnect;

public class UserListModelTest {
	private static UserListModel userlist;
	private static int expectedsize;
	private static User user1;
	private static User user2;
	private static DefaultListModel<User> modellist;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("------------------------");
        System.out.println("Tests Sur la classe UserListModel");
        System.out.println("------------------------");
		userlist = new UserListModel();
		expectedsize =2; //we will add 2 users for the tests
		user1 = new User("user1", InetAddress.getLocalHost(), 6666, typeConnect.CONNECTED);
		user2 = new User("user2", InetAddress.getLocalHost(), 6666, typeConnect.CONNECTED);
		modellist = new DefaultListModel<>();
		modellist.addElement(user1);
		modellist.addElement(user2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userlist.adduser(user1);
		userlist.adduser(user2); // we add both users
	}

	@After
	public void tearDown() throws Exception {
		userlist.removeuser(user1);
		userlist.removeuser(user2); // we reset the model
	}

	@Test
	public void testAdduser() {
	 	System.out.println("------------------------");
        System.out.println("Test ajout de User");
        System.out.println("------------------------");
        
		Assert.assertEquals(expectedsize, userlist.getlistmodel().getSize());
		
		System.out.println("test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test ajout de User existant");
        System.out.println("------------------------");
		userlist.adduser(user1);
		Assert.assertEquals("ajout d'un user existant",expectedsize, userlist.getlistmodel().getSize());
		System.out.println("test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test ajout de User null");
        System.out.println("------------------------");
		userlist.adduser(null);
		Assert.assertEquals("ajout d'un user null",expectedsize, userlist.getlistmodel().getSize());
		System.out.println("test OK !");
	}

	@Test
	public void testRemoveuser() {
		
		User user3 = null;
		try {
			user3 = new User("user3", InetAddress.getLocalHost(), 6666, typeConnect.CONNECTED);
			userlist.adduser(user3);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("------------------------");
        System.out.println("Test retrait de User existant");
        System.out.println("------------------------");
		userlist.removeuser(user3);
		Assert.assertEquals("retrait d'un user existant",expectedsize, userlist.getlistmodel().getSize());
		System.out.println("test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test retrait de User inexistant");
        System.out.println("------------------------");
		userlist.removeuser(user3);
		Assert.assertEquals("retrait d'un user non existant",expectedsize, userlist.getlistmodel().getSize());
		System.out.println("test OK !");
		
		System.out.println("------------------------");
        System.out.println("Test retrait de User null");
        System.out.println("------------------------");
		userlist.removeuser(null);
		Assert.assertEquals("retrait d'un user null",expectedsize, userlist.getlistmodel().getSize());
		System.out.println("test OK !");
	}

	@Test
	public void testGetlistmodel() {
		System.out.println("------------------------");
        System.out.println("Test de retour de listes");
        System.out.println("------------------------");

        System.out.println(userlist.getlistmodel());
		System.out.println(modellist);
		Assert.assertTrue("Listes différentes",modellist.equals(userlist.getlistmodel())); //pourquoi ?
		System.out.println("test OK !");
	}

	// Test of the method of Hashmap object
//	@Test
//	public void testGetuserlist() {
//		fail("Not yet implemented");
//	}

}
