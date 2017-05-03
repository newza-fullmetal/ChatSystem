package testInterface;

import user.MessageUser;

/**
 * 
 * à vous de remplir tous les methodes et écrire Junit test pour cette classe.
 * Cette classe est la seule qu'on va tester
 *
 */
public class chatSystem {

    private String pseudo;
    
    public chatSystem(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * 
     * @return info messageUser qu'on va diffuser
     */
    public MessageUser connect(){
        return null;
    }
    /**
     * 
     * @return messageUser qu'on va diffuser lorsqu'on déconnecte
     */
    public MessageUser deconnect(){
    return null;
    }
    /**
     * 
     * @param user représente une messageUser qu'on recoit
     * @return 0 si déjà existé et on ajoute pas dans la model
     *            1 sinon
     */
    public int addUser(MessageUser user){
        return 0;
    }
    
    public void removeUser(MessageUser user){
        
    }
    
}

