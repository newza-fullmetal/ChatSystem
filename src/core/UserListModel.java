package core;

import java.util.HashMap;

import javax.swing.DefaultListModel;
/**
 * List that represent all the users on the network
 * @author coustill
 *
 */
public class UserListModel {
	private HashMap<String,User> userlist = new HashMap<String,User>();
	private DefaultListModel<User> listmodeluser = new DefaultListModel<User>();
	
//	private void updateevent(ListDataEvent ev){ 
//		for (ListDataListener l : this.listlistener){
//			l.contentsChanged(ev);
//			System.out.println(ev);
//		}
//		
//		
//	}
	/**
	 * Add user to the list
	 * @param user : User; @see User.
	 */
	public void adduser(User user){
		
		boolean alreadyexist =false;
		
		
			if(this.userlist.containsKey(user.getPseudo())){
				alreadyexist = true;
			}
		
		if (!alreadyexist){
			this.userlist.put(user.getPseudo(),user);
			this.listmodeluser.addElement(user);
			System.out.println(this.listmodeluser);
			
		}
		
	}
	
	
	
	/**
	 * Remove a user from the user list
	 * @param user : User; @see user
	 */
	public void removeuser(User user){
		if (this.userlist.containsValue(user)){
				this.userlist.remove(user);
				this.listmodeluser.removeElement(user);
			}
		
	}
	
	
	public DefaultListModel<User> getlistmodel(){
		return this.listmodeluser;
	}
	
	public HashMap<String,User> getuserlist(){
		return this.userlist;
	}

}
