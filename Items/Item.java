package items;

import game.Player;
import game.Vector2;

public abstract class Item {
	
	private String itemName;
	private Vector2 itemInRoomLocation;
	private String itemDescription;
	private double itemWeight;

	public Item() {
		itemInRoomLocation = new Vector2(0, 0);
	}
	
	protected void SetProporties(String name, String description, double weight){
		this.itemName = name;
		this.itemDescription = description;
		this.itemWeight = weight;
	}
	
	public String GetItemName(){
		return itemName;
	}
	
	public String GetItemDescription(){
		return itemDescription;
	}
	
	public double GetItemWheight(){
		return itemWeight;
	}
	
	public void SetItemInRoomLocation(int x, int y){
		itemInRoomLocation.setTo(x, y);
	}
	
	public Vector2 GetItemInRoomLocation(){
		return itemInRoomLocation;
	}
	
	public abstract String Use(Player player);

}
