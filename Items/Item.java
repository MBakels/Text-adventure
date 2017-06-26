package Items;

import game.Vector2;

public class Item {
	
	private String itemName;
	Vector2 itemInRoomLocation;

	public Item(String name) {
		this.itemName = name;
		itemInRoomLocation = new Vector2(0, 0);
	}
	
	public String GetItemName(){
		return itemName;
	}
	
	public void GetItemDescription(){
		
	}
	
	public void SetItemInRoomLocation(int x, int y){
		itemInRoomLocation.setTo(x, y);
	}
	
	public Vector2 GetItemInRoomLocation(){
		return itemInRoomLocation;
	}

}
