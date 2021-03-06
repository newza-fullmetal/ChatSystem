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
	
	/**
	 * Deprecated 
	 */
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
		if (user != null){
			if(!this.userlist.isEmpty()){
				if(this.userlist.containsKey(user.getPseudo())){
					alreadyexist = true;
				}
			}
			
			if (!alreadyexist){
				System.out.println("NEW COME : " + user.getPseudo());
				this.userlist.put(user.getPseudo(),user);
				this.listmodeluser.addElement(user);
				System.out.println(this.listmodeluser);
	//			//DEBUG
	//			System.out.println("Table des users : " + this.listmodeluser);
				
			}
		}
		
	}
	
	
	
	/**
	 * Remove a user from the user list
	 * @param user : User; @see user
	 */
	public void removeuser(User user){
		if (user != null){
			
			if (!this.userlist.isEmpty() && this.userlist.containsKey(user.getPseudo())){
					User olduser = this.userlist.get(user.getPseudo());
					System.out.println("NEW LEAVE : " + olduser.getPseudo());
					this.userlist.remove(olduser.getPseudo(), olduser);
					this.listmodeluser.removeElement(olduser);
	//				//DEBUG
	//				System.out.println("Table des users : " + this.userlist);
					
				}
		}
		
	}
	
	/**
	 * Return the listmodel to allow other object to listen
	 * @return listmodeluser : DefaultListModel;
	 */
	public DefaultListModel<User> getlistmodel(){
		return this.listmodeluser;
	}
	
	/**
	 * Return the Hasmap containing all the users 
	 * @return userlist : Hashmap;
	 */
	public HashMap<String,User> getuserlist(){
		return this.userlist;
	}

}
