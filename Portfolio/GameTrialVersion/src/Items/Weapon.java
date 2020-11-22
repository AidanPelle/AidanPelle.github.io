package Items;

import java.io.*;

public class Weapon {
	
	private String name;
	private WeaponType weaponType;
	private int itemTier;
	private int meleeDamage;
	private int rangedDamage;
	private int spellMultiplier;
	private String description;
	private String attackDesc;
	
	public Weapon() {
		name = "Test Weapon";
		description = "Test Decription";
		weaponType = WeaponType.Sword;
		meleeDamage = 1;
		itemTier = 0;
	}
	
	public Weapon(String name) {
		this.name = name;
		findWeapon();
	}
	
	public enum DamageType {
		Melee, Ranged, Multiplier
	}
	
	public enum WeaponType {
		None, Fists, Staff, Sword, Bow, Dagger
	}
	
	void findWeapon() {
		BufferedReader reader = null;
		try {
			InputStream res = this.getClass().getResourceAsStream("/GameFiles/Weapons.txt");
	    	reader = new BufferedReader(new InputStreamReader(res));
			while (true) {
				String line = reader.readLine();
				if (line.substring(0, line.indexOf('	')).equalsIgnoreCase(name)) {
					line = line.substring(line.indexOf('	') + 1);
					weaponType = WeaponType.valueOf(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					itemTier = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					meleeDamage = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					rangedDamage = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					spellMultiplier = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					description = line.substring(0, line.indexOf('	'));
					line = line.substring(line.indexOf('	') + 1);
					attackDesc = line;
					break;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public int getMeleeDamage() {
		return meleeDamage;
	}

	public void setMeleeDamage(int meleeDamage) {
		this.meleeDamage = meleeDamage;
	}

	public int getRangedDamage() {
		return rangedDamage;
	}

	public void setRangedDamage(int rangedDamage) {
		this.rangedDamage = rangedDamage;
	}

	public int getSpellMultiplier() {
		return spellMultiplier;
	}

	public void setSpellMultiplier(int spellMultiplier) {
		this.spellMultiplier = spellMultiplier;
	}
	
	public int getRelevantDamage() {
		if (rangedDamage != 0)
			return rangedDamage;
		else
			return meleeDamage;
	}

	public void setItemTier(int itemTier) {
		this.itemTier = itemTier;
	}
	
	public int getItemTier() {
		return itemTier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttackDesc() {
		return attackDesc;
	}

	public void setAttackDesc(String attackDesc) {
		this.attackDesc = attackDesc;
	}
}