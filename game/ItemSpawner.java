package game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import items.HealthPotion;
import items.Item;
import items.SwordWooden;

public class ItemSpawner {
	
	private HashMap<Double, String> itemList;

	public ItemSpawner() {
		itemList = new HashMap<Double, String>();
		
		itemList.put(0.5, "items.HealthPotion");
		itemList.put(1.0, "items.SwordWooden");
	}
	
	public Item GetRandomItem(){
		Set set = itemList.entrySet();
	    Iterator iterator = set.iterator();
	    double randomChance = Math.random();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			if((double)mentry.getKey() >= randomChance){
				
				Class randomObject = null;
				try {
					randomObject = Class.forName((String)mentry.getValue());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
				Constructor constructor = null;
				try {
					constructor = randomObject.getConstructor();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				
				Item randomItem = null;
				try {
					randomItem = (Item)constructor.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				return randomItem;
			}
	    }
		return null;
	}

}
