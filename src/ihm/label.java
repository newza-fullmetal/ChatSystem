package ihm;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import core.User;


public class label extends JLabel 
    implements ListCellRenderer<User> {

  /**
	 * 
	 */
	private static final long serialVersionUID = -8848756140503755710L;
Color selectCouleur = Color.RED;
  public  label(){

  }
  public Component getListCellRendererComponent(JList<?extends User> list, 
       User value, // valeur à afficher
       int index, // indice d'item
       boolean isSelected, // l'item est-il sélectionné
       boolean cellHasFocus) // La liste a-t-elle le focus
  {
     String s = value.toString();
     if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(selectCouleur);
        setText(s+"  ");
     }else{
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(s);
     }
     setEnabled(list.isEnabled());
     setFont(list.getFont());
     setOpaque(true);
     return this;
  }

}


