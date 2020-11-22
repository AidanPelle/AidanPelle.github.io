package Items;

public class Inventory {
	
	private Item[] inventory;
	
	public Inventory() {
		inventory = new Item[0];
	}
	
	public Item findItem(String name) {
		if (search(new Item(name)) == -1) {
			return null;
		}
		else
			return inventory[search(new Item(name))];
	}
	
	public void addItem(Item i) {
		try {
			if (search(i) != -1) {
				inventory[search(i)].addAmount(i.getAmount());
			}
			else {
				Item[] newInventory = new Item[inventory.length + 1];
				for (int j = 0; j < inventory.length; j++) {
					newInventory[j] = inventory[j];
				}
				newInventory[newInventory.length - 1] = i;
				inventory = newInventory;
			}
			
			sortInventory();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeItem(Item i) {
		try {
			int searchInt = search(i);
			Item searchItem = inventory[searchInt];
			
			if (searchItem.getAmount() > i.getAmount()) {
				searchItem.removeAmount(i.getAmount());
			}
			else if (searchItem.getName().equalsIgnoreCase("Gold")){
				searchItem.setAmount(0);
			}
			else {
				deleteItem(searchInt);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Item[] getArray() {
		return inventory;
	}
	
	public Item get(int i) {
		return inventory[i];
	}
	
	public int getLength() {
		return inventory.length;
	}
	
	private void deleteItem(int i) {
		
		Item[] newInven = new Item[inventory.length - 1];
			
		inventory[i] = new Item("X");
		int j = 0;
		for (int k = 0; k < newInven.length; k++) {
			if (!inventory[k].equals(new Item("X"))) {
				newInven[k] = inventory[j++];
			}
			else {
				newInven[k] = inventory[++j];
				j++;
			}
		}
		inventory = newInven;
	}
	
	private int search(Item i) {
		
		
		int firstIndex = 0;
	    int lastIndex = inventory.length - 1;

	    // termination condition (element isn't present)
	    while(firstIndex <= lastIndex) {
	        int middleIndex = (firstIndex + lastIndex) / 2;
	        // if the middle element is our goal element, return its index
	        if (inventory[middleIndex].equals(i)) {
	            return middleIndex;
	        }

	        // if the middle element is smaller
	        // point our index to the middle+1, taking the first half out of consideration
	        else if (inventory[middleIndex].compareTo(i) < 0)
	            firstIndex = middleIndex + 1;

	        // if the middle element is bigger
	        // point our index to the middle-1, taking the second half out of consideration
	        else if (inventory[middleIndex].compareTo(i) > 0)
	            lastIndex = middleIndex - 1;

	    }
	    return -1;
	}
	
	private void sortInventory() {
	        int n = inventory.length; 
	        for (int i = 1; i < n; ++i) { 
	            Item key = inventory[i]; 
	            int j = i - 1; 
	  
	            /* Move elements of arr[0..i-1], that are 
	               greater than key, to one position ahead 
	               of their current position */
	            while (j >= 0 && inventory[j].compareTo(key) > 0) { 
	                inventory[j + 1] = inventory[j]; 
	                j = j - 1; 
	            } 
	            inventory[j + 1] = key; 
	        }
	}

}