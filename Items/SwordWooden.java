package items;

import game.Player;

public class SwordWooden extends Equipment {

	public SwordWooden() {
		SetProporties("Wooden sword", "A flimsy wooden sword, you dont think this garbage can cut anything.", 2.0);
		System.out.println("wooden sword created");
	}

	@Override
	public String Use(Player player) {
		
		return "You equipt a wooden sword.";
	}

}
