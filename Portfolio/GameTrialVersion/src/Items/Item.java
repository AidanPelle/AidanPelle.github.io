package Items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Item {
	
	private String name;
	private int amount;
	private int price;
	private String description;
	
	public Item(String name) {
		this.name = name;
		findItem();
	}
	public Item(String name, int amount) {
		this.name = name;
		findItem();
		this.amount = amount;
	}
	
	private void findItem() {
		BufferedReader reader = null;
		try {
			InputStream res = this.getClass().getResourceAsStream("/GameFiles/Items.txt");
	    	reader = new BufferedReader(new InputStreamReader(res));
			while (true) {
				String line = reader.readLine();
				if (line.substring(0, line.indexOf('	')).equalsIgnoreCase(name)) {
					line = line.substring(line.indexOf('	') + 1);
					price = Integer.parseInt(line.substring(0, line.indexOf('	')));
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
	
	protected void addAmount(int amount) {
		this.amount += amount;
	}
	
	protected void removeAmount(int amount) {
		this.amount -= amount;
	}
	
	protected int compareTo(Item i) {
		return this.getName().compareTo(i.getName());
	}
	
	protected boolean equals(Item i) {
		if (name.equalsIgnoreCase(i.getName()))
			return true;
		else return false;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

