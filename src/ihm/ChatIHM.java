package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.LocalUser;
import core.User;
import core.UserListModel;
import message.Message;
import user.MessageUser.typeConnect;

public class ChatIHM extends JFrame{
		/**
	 * 
	 */
	private static final long serialVersionUID = 3177011309003249264L;
	
		private ControllerIHM controller;
		private UserListModel userlist;
		private JList<User> listuser;
		private TextArea msg2send;
		private JList<Message> msglist = new JList<>();
		private JButton btnDecoReco;
		
		public ChatIHM(ControllerIHM control, UserListModel userlist){
			this.controller = control;
			this.userlist = userlist;
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					actionquit();
				}
			});
		
			setTitle("Chat");
			setAlwaysOnTop(false);
			
			
			setSize(new Dimension(900, 600));
			getContentPane().setLayout(new GridLayout(1, 2, 0, 0));
			
			
			
				
				 this.listuser = new JList<User>(this.userlist.getlistmodel());
			     this.listuser.setCellRenderer(new label());
				listuser.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				listuser.setLayoutOrientation(JList.VERTICAL);
				
				listuser.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
							refreshlist(targetselected());
					}
				});
				getContentPane().add(listuser);
			
				Box verticalBox = Box.createVerticalBox();
				verticalBox.setForeground(Color.DARK_GRAY);
				getContentPane().add(verticalBox);
				
				verticalBox.add(this.msglist);
				msglist.setSize(new Dimension(350, 100));
				msglist.setName("msglist");
				msglist.setSelectionMode(NORMAL);
				msglist.setPreferredSize(new Dimension(350, 280));
				
				this.msg2send = new TextArea();
				msg2send.setName("msg2send");
				msg2send.setPreferredSize(new Dimension(300, 50));
				verticalBox.add(msg2send);
				
				Box horizontalBox = Box.createHorizontalBox();
				horizontalBox.setAlignmentY(Component.CENTER_ALIGNMENT);
				horizontalBox.setPreferredSize(new Dimension(0, 10));
				horizontalBox.setSize(new Dimension(0, 40));
				verticalBox.add(horizontalBox);
				
				JButton btnSend = new JButton("send");
				btnSend.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						actionsendtxt();
					}
				});
				horizontalBox.add(btnSend);
				
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalBox.add(horizontalStrut);
				
				JButton btnAddFile = new JButton("ADD File");
				btnAddFile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						 actionsendfile();
					}
				});
				horizontalBox.add(btnAddFile);
				
				this.btnDecoReco = new JButton("Disconnect");
				btnDecoReco.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (LocalUser.getInstance().getEtat() == typeConnect.CONNECTED){
							actiondisconnect();
						}
						else if(LocalUser.getInstance().getEtat() == typeConnect.DECONNECTED) {
							actionconnect();
							
						}	
					}
				});
				btnDecoReco.setAlignmentX(Component.CENTER_ALIGNMENT);
				verticalBox.add(btnDecoReco);
			}

			
		
		
		private List<User> targetselected(){
			System.out.println("User selected " + this.listuser.getSelectedValue());
			return this.listuser.getSelectedValuesList();
		}
		
		/**
		 * Send the text written in the text area
		 */
		private void actionsendtxt(){
			
			if (this.listuser.getSelectedValue() != null) {
				ArrayList<User> targets = new ArrayList<User>();
				targets.addAll(this.listuser.getSelectedValuesList());
				this.controller.actionsend(targets,this.msg2send.getText() );
			}
			else{
				JOptionPane.showMessageDialog(null,
					    "You didn't choose a user to send a message : " ,
					    "warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
		}
		/**
		 * open the panel to add a file to the chat
		 */
		private void actionsendfile(){
			String filepath;
			User user = (User)this.listuser.getSelectedValue();
			if (user != null) {
				filepath = this.controller.openSendfileIHM();
				ArrayList<User> targets = new ArrayList<User>();
				targets.addAll(this.listuser.getSelectedValuesList());
				this.controller.actionsendfile(targets,filepath );
			}
			else{
				JOptionPane.showMessageDialog(null,
					    "You didn't choose a user to send a message : " ,
					    "warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
		
		private void actionquit(){
			this.dispose();
			this.controller.actionquit();
		}
		private void actiondisconnect(){
			this.btnDecoReco.setText("connect");
			this.controller.actiondisconnect();
		}
		
		private void refreshlist(List<User> targetlist){
			if (!targetlist.isEmpty()){
				this.msglist = new JList<Message>(targetlist.get(0).getMsgList());
			}
		
		}
		private void actionconnect(){
			this.btnDecoReco.setText("Disconnect");
			this.controller.actionconnect();
		}
		
		
}
