package Characters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Items.Armor;
import Items.Item;
import Items.Spell;
import Items.Weapon;

public class Enemy {
	
	private String name, descriptor, image;
	private int currentHP, maxHP;
	private Weapon weapon;
	private Armor armor;
	private ArrayList<Spell> spells;
	private ArrayList<Item> loot;
	private int level, experience;
	private String soundString;
	private int bonusDamage;
	
	public Enemy(String name) {
		this.name = name;
		findEnemy();
		currentHP = maxHP;
		image = "/EnemyImages/" + name + ".jpg";
	}
	
	private void findEnemy() {
		BufferedReader reader = null;
		try {
			InputStream res = this.getClass().getResourceAsStream("/GameFiles/Enemies.txt");
	    	reader = new BufferedReader(new InputStreamReader(res));
			while (true) {
				String line = reader.readLine();
				if (line.substring(0, line.indexOf('	')).contentEquals(name)) {
					line = line.substring(line.indexOf('	') + 1);
					maxHP = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					weapon = new Weapon(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					armor = new Armor(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					bonusDamage = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					experience = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					loot = getLoot(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					spells = getSpells(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					soundString = line.substring(0, line.indexOf('	'));
					line = line.substring(line.indexOf('	') + 1);
					descriptor = line;
					reader.close();
					break;
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private ArrayList<Item> getLoot(String line) {
		ArrayList<Item> items = new ArrayList<Item>();
		if (line.equalsIgnoreCase("None")) return items;
		else {
		while (line.indexOf('|') != -1) {
			line = line.substring(line.indexOf('|') + 1);
			String currentItem = line.substring(0, line.indexOf('|'));
			String itemName = line.substring(0, currentItem.indexOf(','));
			int itemAmount = Integer.parseInt(currentItem.substring(currentItem.lastIndexOf(',') + 1));
			line = line.substring(line.indexOf('|') + 1);
			items.add(new Item(itemName, itemAmount));
		}
		}
		return items;
	}
	
	private ArrayList<Spell> getSpells(String line) {
		ArrayList<Spell> spells = new ArrayList<Spell>();
		if (line.equalsIgnoreCase("None")) return spells;
		else {
		while (line.indexOf('|') != -1) {
			line = line.substring(line.indexOf('|') + 1);
			String name = line.substring(0, line.indexOf('|'));
			spells.add(new Spell(name));
			line = line.substring(line.indexOf('|') + 1);
		}
		}
		return spells;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public ArrayList<Item> getLoot() {
		return loot;
	}

	public void setLoot(ArrayList<Item> loot) {
		this.loot = loot;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Spell> spells) {
		this.spells = spells;
	}
	
	public boolean hasSpells() {
		if (spells.size() == 0)
			return true;
		else return false;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public String getSoundString() {
		return soundString;
	}
	
	public int getBonusDamage() {
		return bonusDamage;
	}

}
