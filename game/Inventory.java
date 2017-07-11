package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import items.Item;

public class Inventory {
	
	private List<Item> inventory;
	private String standardPlayerText;
	private String invHasContendText;
	private String invEmptyText;
	private String invType;
	private int maxItemCapacity;
	private double maxWeight;
	private double currentWeight;
	
	

	public Inventory(int size, double weight, String type) {
		maxItemCapacity = size;
		invType = type;
		maxWeight = weight;
		inventory = new ArrayList<Item>(20);
		if(invType == "player"){
			invHasContendText = "You have these items in your backpack:";
			invEmptyText = "Your backpack is empty.";
		}else if(invType == "enemy"){
			invHasContendText = "The enemy had these items on his body:";
			invEmptyText = "The enemy had no useful items on him.";
		}
		else if(invType == "room"){
			invHasContendText = "You can see these items laying around in the area:";
			invEmptyText = "This room seems to be empty.";
		}
	}
	
	public String ShowContends(){
		String invContends = "";
		if(invType == "player"){
			invContends = "You are carying your dads old leather backpack." + "\n" + "You are carying (" + inventory.size() + " / " + maxItemCapacity + ") items and your backpack weighs (" + currentWeight + " / " + maxWeight + ") KG.\n";
		}
		if(inventory.size() == 0){
			invContends += invEmptyText;
		}else{
			invContends += invHasContendText;
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
	
	public boolean AddItem(Item item){
		if(inventory.size() < maxItemCapacity && (currentWeight + item.GetItemWheight() < maxWeight)){
			inventory.add(item);
			currentWeight += item.GetItemWheight();
			return true;
		}
		return false;
	}
	
	public void RemoveItem(Item item){
		Iterator<Item> iterator = inventory.iterator();
		while(iterator.hasNext()) {
			if(iterator.next() == item){
				iterator.remove();
				currentWeight -= item.GetItemWheight();
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
	
	public Item GetItemByName(String item){
		Iterator<Item> iterator = inventory.iterator();
		while(iterator.hasNext()) {
			Item invItem = iterator.next();
			if(invItem.GetItemName().equalsIgnoreCase(item)){
				return invItem;
			}
	    }
		return null;
	}
	
	public Item GetItemByIndex(int index){
		if(!(index > inventory.size() - 1)){
			return inventory.get(index);
		}
		return null;
	}
	
	public int GetSize(){
		return inventory.size();
	}

}
