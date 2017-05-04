package core;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import message.Message;

public class MessageListModel implements ListModel<Message>{
	private ArrayList<Message> messagelist = new ArrayList<Message>();
	private ArrayList<ListDataListener> listlistener = new ArrayList<ListDataListener>();
	
	
	public void addmessage(Message msg){
		this.messagelist.add(msg);
		for (ListDataListener l : this.listlistener){
			l.contentsChanged(new ListDataEvent(msg, 0, 0, this.messagelist.indexOf(msg)));
		}
	}
	@Override
	public void addListDataListener(ListDataListener arg0) {
		this.listlistener.add(arg0);
		
	}
	@Override
	public Message getElementAt(int arg0) {
		return this.messagelist.get(arg0);
	}
	@Override
	public int getSize() {
		return this.messagelist.size();
	}
	@Override
	public void removeListDataListener(ListDataListener arg0) {
		this.listlistener.remove(arg0);
		
	}
	
	public String toString(){
		String historic ="";
		for (Message msg : this.messagelist) {
			historic += msg.toString();
			historic +="\n ";
		}
		return historic;
	}
}
