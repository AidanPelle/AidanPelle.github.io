package Characters;

import java.util.ArrayList;

import Items.Armor;
import Items.Inventory;
import Items.Map;
import Items.Spell;
import Items.Weapon;

public class PlayerClass {
	
	private int strength, dexterity, intelligence, agility, defense;
	private String sRace, sName;
	private Weapon weapon;
	private Armor armor;
	private ArrayList<Spell> spells;
	private Map map;
	private int currentWater, maxWater, currentHealth, maxHealth, currentMana,
	maxMana, level, currentExperience, maxExperience;
	private Inventory inventory;
	private Inventory bank;
	private ArrayList<Enemy> enemies;
	private int currentEnemy, maxActions, currentActions;
	private Quest[] quests;
	private ClassType classType;
	
	public PlayerClass() {
		this.inventory = new Inventory();

	}
	
	public PlayerClass(ClassType classType, String sRace, String sName, Map map, Weapon weapon, Armor armor) {
		strength = 10;
		dexterity = 10;
		intelligence = 10;
		agility = 10;
		defense = 10;
		this.classType = classType;
		this.sRace = sRace;
		this.sName = sName;
		this.map = map;
		maxWater = 25;
		currentWater = maxWater;
		maxHealth = 10;
		currentHealth = maxHealth;
		maxMana = 100;
		currentMana = maxMana;
		level = 1;
		currentExperience = 0;
		maxExperience = level * 100;
		maxActions = 2;
		currentActions = maxActions;
		this.weapon = weapon;
		this.armor = armor;
		inventory = new Inventory();
		bank = new Inventory();
		spells = new ArrayList<Spell>();
		enemies = new ArrayList<Enemy>();
		currentEnemy = 0;
	}
	
	public PlayerClass(int[] stats, ClassType classType, String sRace, String sName, Map map, 
			int maxWater, int maxHealth, int maxMana, int level, int currentExperience,
			Weapon weapon, Armor armor, int maxActions, Inventory items, Inventory bank, ArrayList<Spell> spells) {
		strength = stats[0];
		dexterity = stats[1];
		intelligence = stats[2];
		agility = stats[3];
		defense = stats[4];
		this.classType = classType;
		this.sRace = sRace;
		this.sName = sName;
		this.map = map;
		this.maxWater = maxWater;
		currentWater = this.maxWater;
		this.maxHealth = maxHealth;
		currentHealth = this.maxHealth;
		this.maxMana = maxMana;
		currentMana = this.maxMana;
		this.level = level;
		this.currentExperience = currentExperience;
		this.maxExperience = level * 100;
		this.weapon = weapon;
		this.armor = armor;
		this.inventory = items;
		this.bank = bank;
		this.spells = spells;
		this.maxActions = maxActions;
		currentActions = maxActions;
		enemies = new ArrayList<Enemy>();
		currentEnemy = 0;
	}
	
	public enum ClassType {
		Mage, Warrior, Assassin, Archer;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	public String getsRace() {
		return sRace;
	}

	public void setsRace(String sRace) {
		this.sRace = sRace;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
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

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<Spell> spells) {
		this.spells = spells;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public int getCurrentWater() {
		return currentWater;
	}

	public void setCurrentWater(int currentWater) {
		this.currentWater = currentWater;
	}

	public int getMaxWater() {
		return maxWater;
	}

	public void setMaxWater(int maxWater) {
		this.maxWater = maxWater;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentMana() {
		return currentMana;
	}

	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurrentExperience() {
		return currentExperience;
	}

	public void setCurrentExperience(int currentExperience) {
		this.currentExperience = currentExperience;
	}

	public int getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(int maxExperience) {
		this.maxExperience = maxExperience;
	}

	public Inventory getBank() {
		return bank;
	}

	public void setBank(Inventory bank) {
		this.bank = bank;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public int getCurrentEnemy() {
		return currentEnemy;
	}

	public void setCurrentEnemy(int currentEnemy) {
		this.currentEnemy = currentEnemy;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public void setMaxActions(int maxActions) {
		this.maxActions = maxActions;
	}

	public int getCurrentActions() {
		return currentActions;
	}

	public void setCurrentActions(int currentActions) {
		this.currentActions = currentActions;
	}

	public void setInventory(Inventory items) {
		this.inventory = items;
	}

	public Quest[] getQuests() {
		return quests;
	}

	public void setQuests(Quest[] quests) {
		this.quests = quests;
	}

}
