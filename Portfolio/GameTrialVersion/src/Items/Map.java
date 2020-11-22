package Items;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {
	
	//10, 45 == starting town coords
	private char[][] map;
	private int[] position;
	private char positionChar;
	
	public Map() {
		map = loadMap();
		position = loadPosition();
		positionChar = map[25][90];
		
	}
	
	public int[] loadPosition() {
		int[] playerPosition = new int[2];
 		for(int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == '@') {
					playerPosition[0] = i;
					playerPosition[1] = j;
					return playerPosition;
				}
			}
		}
 		return null;
	}
	
	public int move(String direction) {
		switch (direction) {
		case "Up": {
			if (map[position[0] - 1][position[1]] != 'X') {
				map[position[0]][position[1]] = positionChar;
				position[0] = --position[0];
				positionChar = map[position[0]][position[1]];
				map[position[0]][position[1]] = '@';
				map[25][90] = positionChar;
				return 1;
			}
			else return -1;
		}
		case "Down": {
			if (map[position[0] + 1][position[1]] != 'X') {
				map[position[0]][position[1]] = positionChar;
				position[0] = ++position[0];
				positionChar = map[position[0]][position[1]];
				map[position[0]][position[1]] = '@';
				map[25][90] = positionChar;
				return 1;
			}
			else return -1;
		}
		case "Left": {
			if (map[position[0]][position[1] - 1] != 'X') {
				map[position[0]][position[1]] = positionChar;
				position[1] = --position[1];
				positionChar = map[position[0]][position[1]];
				map[position[0]][position[1]] = '@';
				map[25][90] = positionChar;
				return 1;
			}
			else return -1;
		}
		case "Right": {
			if (map[position[0]][position[1] + 1] != 'X') {
				map[position[0]][position[1]] = positionChar;
				position[1] = ++position[1];
				positionChar = map[position[0]][position[1]];
				map[position[0]][position[1]] = '@';
				map[25][90] = positionChar;
				return 1;
			}
			else return -1;
		}
		}
		return -1;
	}
	
	public char[][] loadMap() {
		char[][] map = new char[26][95];
	    int lineNumber;
	    BufferedReader reader = null;
	    try {
	    	InputStream res = this.getClass().getResourceAsStream("/PlayerFiles/DefaultMap.txt");
	    	reader = new BufferedReader(new InputStreamReader(res));
	      for (lineNumber = 0; lineNumber < map.length; lineNumber++) {
	          reader.read(map[lineNumber]);
	        }
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	    finally {
	    	try {
	    		reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return map;
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public char getPositionChar() {
		return positionChar;
	}

	public void setPositionChar(char positionChar) {
		this.positionChar = positionChar;
	}

}
