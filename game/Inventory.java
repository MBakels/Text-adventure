package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Items.Item;

public class Inventory {
	
	private List<Item> inventory;
	private String invContendText;
	private String invEmptyText;
	private String invType;

	public Inventory(int size, String type) {
		inventory = new ArrayList<Item>();
		invType = type;
		if(type == "player"){
			invContendText = "You have these items in your backpack:";
			invEmptyText = "Your backpack is empty.";
		}else if(type == "enemy"){
			invContendText = "The enemy had these items on his body:";
			invEmptyText = "The enemy had no useful items on him.";
		}
		else if(type == "room"){
			invContendText = "You can see these items laying around in the area:";
			invEmptyText = "This room seems to be empty.";
		}
	}
	
	public String ShowContends(){
		String invContends;
		if(inventory.size() == 0){
			invContends = invEmptyText;
		}else{
			invContends = invContendText;
		}
	    Iterator<Item> iterator = inventory.iterator();
		while(iterator.hasNext()) {
			Item item = (Item)iterator.next();
			invContends += "\n" + item.GetItemName();
			if(invType == "room"){
				invContends += "	(" + ((int)item.GetItemInRoomLocation().x + 1) + ", " + ((int)item.GetItemInRoomLocation().y + 1) + ")";
			}
	    }
		return invContends;
	}
	
	public void AddItem(Item item){
		inventory.add(item);
	}
	
	public void RemoveItem(String item){
		Iterator<Item> iterator = inventory.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().GetItemName().equalsIgnoreCase(item)){
				iterator.remove();
				return;
			}
	    }
	}
	
	public boolean Contains(String item){
		Iterator<Item> iterator = inventory.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().GetItemName().equalsIgnoreCase(item)){
				return true;
			}
	    }
		return false;
	}
	
	public Item GetItem(String item){
		Iterator<Item> iterator = inventory.iterator();
		while(iterator.hasNext()) {
			Item invItem = iterator.next();
			if(invItem.GetItemName().equalsIgnoreCase(item)){
				return invItem;
			}
	    }
		return null;
	}

}
