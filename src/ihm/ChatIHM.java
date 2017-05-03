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

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.MessageListModel;
import core.User;
import core.UserListModel;

public class ChatIHM extends JFrame{
		private ControllerIHM controller;
		private UserListModel userlist;
		private JList<User> listuser;
		private TextArea msg2send;

		
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
						targetselected();
					}
				});
				getContentPane().add(listuser);
			
				Box verticalBox = Box.createVerticalBox();
				verticalBox.setForeground(Color.DARK_GRAY);
				getContentPane().add(verticalBox);
				
				JList msglist = new JList();
				verticalBox.add(msglist);
				msglist.setSize(new Dimension(350, 100));
				msglist.setName("msglist");
				msglist.setSelectionMode(NORMAL);
				msglist.setPreferredSize(new Dimension(350, 280));
				
				this.msg2send = new TextArea();
				msg2send.setName("msg2send");
				msg2send.setPreferredSize(new Dimension(300, 50));
				verticalBox.add(msg2send);
				
				Box horizontalBox = Box.createHorizontalBox();
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
			}

			
		
		
		private void targetselected(){
			System.out.println("User selected " + this.listuser.getSelectedValue());
			System.out.println(this.listuser.getSelectedValuesList());
		
		}
		
		/**
		 * Send the text written in the text area
		 */
		private void actionsendtxt(){
			
			if (this.listuser.getSelectedValue() != null) {
				User user = (User)this.listuser.getSelectedValue();
				this.controller.actionsend(user,this.msg2send.getText() );
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
				this.controller.actionsendfile(user,filepath );
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
			this.controller.actiondisconnect();
		}
		
		
}
