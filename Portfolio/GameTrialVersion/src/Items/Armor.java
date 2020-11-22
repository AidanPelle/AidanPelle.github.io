package Items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Armor {
	
	private String name;
	private int itemTier;
	private int defense;
	private String description;
	
	public Armor() {
		name = "Test Armor";
		defense = 1;
	}
	
	public Armor(String name) {
		this.name = name;
		findArmor();
	}
	
	private void findArmor() {
		BufferedReader reader = null;
		try {
			InputStream res = this.getClass().getResourceAsStream("/GameFiles/Armor.txt");
	    	reader = new BufferedReader(new InputStreamReader(res));
			while (true) {
				String line = reader.readLine();
				if (line.substring(0, line.indexOf('	')).equalsIgnoreCase(name)) {
					line = line.substring(line.indexOf('	') + 1);
					itemTier = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					defense = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					description = line;
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
	
	public void setItemTier(int itemTier) {
		this.itemTier = itemTier;
	}
	
	public int getItemTier() {
		return itemTier;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
