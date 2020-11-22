package Items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Spell {
	
	private String name;
	private String attackDesc;
	private String description;
	private int damage = 0;
	private int hp = 0;
	private boolean immunity = false;
	private int manaCost = 0;
	private int damageBuff = 0;
	private int defenseBuff = 0;
	private String soundString;
	
	public Spell(String name) {
		this.name = name;
		findSpell();
	}
	
	private void findSpell() {
		BufferedReader reader = null;
		try {
			InputStream res = this.getClass().getResourceAsStream("/GameFiles/Spells.txt");
	    	reader = new BufferedReader(new InputStreamReader(res));
			while (true) {
				String line = reader.readLine();
				if (line.substring(0, line.indexOf('	')).equalsIgnoreCase(name)) {
					line = line.substring(line.indexOf('	') + 1);
					damage = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					hp = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					immunity = Boolean.parseBoolean(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					manaCost = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					damageBuff = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					defenseBuff = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					soundString = line.substring(0, line.indexOf('	'));
					line = line.substring(line.indexOf('	') + 1);
					description = line.substring(0, line.indexOf('	'));
					line = line.substring(line.indexOf('	') + 1);
					attackDesc = line;
					break;
				}
			}
			
			
		} catch (IOException e) {
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

	public int getDamage() {
		return damage;
	}

	public int getHp() {
		return hp;
	}

	public boolean getImmunity() {
		return immunity;
	}

	public int getManaCost() {
		return manaCost;
	}

	public String getAttackDesc() {
		return attackDesc;
	}

	public int getDamageBuff() {
		return damageBuff;
	}

	public int getDefenseBuff() {
		return defenseBuff;
	}

	public String getDescription() {
		return description;
	}
	
	public String getSoundString() {
		return soundString;
	}


	

}
