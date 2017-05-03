package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.LocalUser;

public class ConnectionIHM extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Label to signal if everything goes right or not
	 */
	private JLabel notification = new JLabel("");
	private JTextField IDText = new JTextField();
	ControllerIHM controller;
	private JButton okbut;
	
	
	public ConnectionIHM(ControllerIHM control) {
		setMinimumSize(new Dimension(300, 110));
		setType(Type.NORMAL);
		setTitle("Connection");
		this.controller = control;
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
			Box verticalBox = Box.createVerticalBox();
			panel.add(verticalBox);
				JLabel IDlabel = new JLabel("Enter your ID");
				verticalBox.add(IDlabel);
				verticalBox.add(this.IDText);
				verticalBox.add(this.notification);
				this.okbut = new JButton("Ok");
				this.okbut.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
				            //Execute when button is pressed
						 try {
						 	controller.setID(IDText.getText());
						 	notification.setText("Connection success");
							 	try { // little pause to display the message
							 	    Thread.sleep(1000);
							 	} catch(InterruptedException ex) {
							 	    Thread.currentThread().interrupt();
							 	}
							 	shut();
						 }
						 catch (NomNonValideException E) {
							 notification.setText( E.getMessage());
						 }
						 	

						 	
				        }
					 
				});
				verticalBox.add(this.okbut);
				
		
	}

	private void shut() {
		this.dispose();
		LocalUser.getInstance().setConnected();
		
	}
	
	
	
	

}
