package Characters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Items.Item;

public class Quest {
	
	private String name;
	private boolean isCompleted;
	private int xpReward;
	private int goldReward;
	private Item requiredItem;
	private String request;
	private String response;
	
	public Quest(String name, int saveFile) {
		this.name = name;
		findQuest(saveFile);
	}
	
	private void findQuest(int saveFile) {
		BufferedReader reader = null;
		String url;
		if (saveFile == 0) {
			url = "/PlayerFiles/BlankQuestSave.txt";
			InputStream res = this.getClass().getResourceAsStream(url);
	    	reader = new BufferedReader(new InputStreamReader(res));
		}
		else {
			url = "QuestSave" + saveFile + ".txt";
			File file = new File(url);
			FileReader fReader;
			try {
				fReader = new FileReader(file);
				reader = new BufferedReader(fReader);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			while (true) {
				String line = reader.readLine();
				if (line.substring(0, line.indexOf('	')).contentEquals(name)) {
					line = line.substring(line.indexOf('	') + 1);
					isCompleted = Boolean.parseBoolean(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					xpReward = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					goldReward = Integer.parseInt(line.substring(0, line.indexOf('	')));
					line = line.substring(line.indexOf('	') + 1);
					String name = line.substring(0, line.indexOf(','));
					int integer = Integer.parseInt(line.substring(line.indexOf(',') + 1, line.indexOf('	')));
					requiredItem = new Item(name, integer);
					line = line.substring(line.indexOf('	') + 1);
					request = line.substring(0, line.indexOf('	'));
					line = line.substring(line.indexOf('	') + 1);
					response = line;
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
	
	public String getRequest() {
		return request;
	}
	
	public String getResponse() {
		return response;
	}

	public String getName() {
		return name;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public int getXpReward() {
		return xpReward;
	}

	public int getGoldReward() {
		return goldReward;
	}

	public Item getRequiredItem() {
		return requiredItem;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
