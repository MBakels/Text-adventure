package game;

public class Player {
	
	private int maxHealth;
	private int health;
	private Vector2 location;
	private Room currentRoom;
	private Inventory inventory;

	public Player() {
		maxHealth = 100;
		health = maxHealth;
		location = new Vector2(10, 10);
		inventory = new Inventory(20, 20.00, "player");
	}
	
	public boolean IsAlive(){
		if(health > 0){
			return true;
		}
		return false;
	}
	
	public int GetMaxHealth(){
		return maxHealth;
	}
	
	public int GetHealth(){
		return health;
	}
	
	public void Damage(int amount){
		health -= amount;
	}
	
	public void Heal(int amount){
		health += amount;
	}
	
	public void SetLocation(Vector2 newLocation){
		location = newLocation;
	}
	
	public Vector2 GetLocation(){
		return location;
	}
	
	public void SetRoom(Room room){
		currentRoom = room;
	}
	
	public Room GetRoom(){
		return currentRoom;
	}
	
	public Inventory GetInventory(){
		return inventory;
	}
	
}
