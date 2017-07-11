package items;

import game.Player;

public class HealthPotion extends Potion {
	
	private int potionStrength = 20;

	public HealthPotion() {
		SetProporties("Health potion", "A health potion, you can use it to heal yourself " + potionStrength + " health.", 0.5);
		System.out.println("potion created");
	}

	@Override
	public String Use(Player player) {
		if(player.GetHealth() < player.GetMaxHealth()){
			if(player.GetHealth() + potionStrength > player.GetMaxHealth()){
				int healAmount = player.GetMaxHealth() - player.GetHealth();
				player.Heal(healAmount);
				return "You used a health potion and healed " + healAmount + " lives.";
			}
			player.Heal(potionStrength);
			return "You used a health potion and healed " + potionStrength + " lives.";
		}else{
			return "Your health is full, you cant use a health potion.";
		}
	}

}
