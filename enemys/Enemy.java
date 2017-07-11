package enemys;

import game.Inventory;
import game.Vector2;

public class Enemy {
	
	private int maxHealth;
	private int health;
	private Vector2 location;
	private Inventory inventory;

	public Enemy(Vector2 location) {
		maxHealth = 100;
		health = maxHealth;
		this.location = location;
		inventory = new Inventory(20, 20.00, "enemy");
	}
	
	public void UpdateAI(int ticks){
		System.out.println(ticks);
		/*while(ticks > 0){
			
			ticks--;
		}*/
	}
	
	public boolean IsAlive(){
		if(health <= 0){
			return false;
		}
		return true;
	}
	
	public Vector2 GetLocation(){
		return location;
	}

}
