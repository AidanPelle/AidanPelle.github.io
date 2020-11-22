package Scenes;

import java.util.ArrayList;

import Characters.Enemy;
import Characters.PlayerClass;
import Characters.PlayerClass.ClassType;
import Items.Inventory;
import Items.Item;
import Items.Spell;
import Items.Weapon.WeaponType;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FightScene extends MyScene{
	
	private Button bFlee, bEndTurn, bLoot, bEndCombat, bLevelUp;
	private Image iEnemy;
	private ImageView iVEnemy;
	private GridPane playerStats, enemyStats, playerBars, enemyBars;
	private String sOutput;
	private MyScene previousScene;
	private VBox vLeft, vRight, bottomHalf;
	private HBox hAttack, hFlee, hEndTurn, hOverall;
	private Pane pOutput;
	private Animation animation;
	private int damageBuff, defenseBuff;
	private boolean immunity, guaranteeCrit;
	private Label lPActions, lPHealth, lPMana, lEHealth;
	private ProgressBar pHealthBar, pManaBar, eHealthBar;
	private int xpGained = 0;
	
	public FightScene(Stage window, PlayerClass myClass, MyScene previousScene) {
		super(window, myClass);
		this.previousScene = previousScene;
	}
	
	@Override
	public void loadScene() {
		
		//create root
		setRoot(new BorderPane());
		if (getMyClass().getCurrentEnemy() == getMyClass().getEnemies().size()) {
			endCombat();
		} else loadRound();
		

		setScene(new Scene(getRoot(), 1000, 750));
		setFormatting(null, this);
		getWindow().setScene(getScene());
		
	}
	
	private void loadRound() {
		playSound(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getSoundString());
		//Output Box		
		sOutput = "You find yourself in combat with a " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getName() + "!";
		sOutput += "\n" + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getDescriptor();
		sOutput += "\nWhat do you want to do?";
			
		//Output Container
		pOutput = new Pane();
		pOutput.getChildren().add(animate(sOutput));
		
		
		//Loading Player Options
		loadPlayerAttacks();
		
		//Flee and End Turn Buttons
		bEndTurn = new Button("End Turn");
		hEndTurn = new HBox();
		hEndTurn.getChildren().add(bEndTurn);
		
		bFlee = new Button("Run Away!");
		hFlee = new HBox();
		hFlee.getChildren().add(bFlee);
		
		hOverall = new HBox();
		hOverall.getChildren().addAll(hEndTurn, hFlee);
		
		bottomHalf = new VBox();
		bottomHalf.getChildren().addAll(pOutput, hAttack, hOverall);
		getRoot().setBottom(bottomHalf);
				
				
		//Loading Center of Screen
		loadImage();
		getRoot().setCenter(iVEnemy);
				
		
		//Loading Left Side
		loadPlayer();
		getRoot().setLeft(vLeft);
		
		//Loading Right Side
		loadEnemy();
		getRoot().setRight(vRight);
		}
	
	private void loadImage() {
		iEnemy = new Image(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getImage());
		iVEnemy = new ImageView(iEnemy);
		
		iVEnemy.setFitHeight(450);
		iVEnemy.setFitWidth(450);
		iVEnemy.setPreserveRatio(true);
	}
	
	private void loadPlayerAttacks() {
		hAttack = new HBox();
		
		//Basic Melee Attack
		Button attack = new Button(getMyClass().getWeapon().getAttackDesc());
		setFormat(attack);
		attack.setTooltip(new Tooltip(getMyClass().getWeapon().getDescription()));
		attack.setOnAction(e -> {
			if (getMyClass().getCurrentActions() != 0) {
				playSound(getMyClass().getWeapon().getAttackDesc());
				int didCrit = dexRandom();
				if (guaranteeCrit) {
					didCrit = 2;
					guaranteeCrit = false;
				}
				int damage = (int) (getNotSpellDamage(getMyClass().getWeapon().getMeleeDamage() * didCrit) 
						* getDefense(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getArmor().getDefense()));
				if (damage >= getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP()) {
					getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).setCurrentHP(0);
				}
				else {
					getMyClass().getEnemies().get(getMyClass().getCurrentEnemy())
					.setCurrentHP(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP() - damage);
				}
				sOutput = "You cast " + getMyClass().getWeapon().getAttackDesc() + "!"
				+ "\nYou dealt " + damage;
				if (didCrit == 2) 
					sOutput += " crit damage!";
				else
					sOutput += " damage!";
				pOutput.getChildren().set(0, animate(sOutput));
				getMyClass().setCurrentActions(getMyClass().getCurrentActions() - 1);
				updateStats();
			}
		});
		hAttack.getChildren().add(attack);
		
		//Possible Ranged Attack if Archer
		if (getMyClass().getWeapon().getWeaponType().equals(WeaponType.Bow)) {
			Button rangedAttack = new Button("Fire Arrow");
			setFormat(rangedAttack);
			if (getMyClass().getInventory().findItem("Arrow") != null) {
				rangedAttack.setOnAction(e -> {
					if (getMyClass().getCurrentActions() != 0) {
						getMyClass().setCurrentActions(getMyClass().getCurrentActions() - 1);
						getMyClass().getInventory().removeItem(new Item("Arrow", 1));
						if (getMyClass().getInventory().findItem("Arrow") == null) rangedAttack.setDisable(true);
						playSound("Arrow");
						int didCrit = dexRandom();
						int damage = (int) (getNotSpellDamage(getMyClass().getWeapon().getRangedDamage() * didCrit) 
								* getDefense(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getArmor().getDefense()));
						if (damage >= getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP()) {
							getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).setCurrentHP(0);
						}
						else {
							getMyClass().getEnemies().get(getMyClass().getCurrentEnemy())
							.setCurrentHP(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP() - damage);
						}
						sOutput = "You cast " + rangedAttack.getText() + "!"
						+ "\nYou dealt " + damage;
						if (didCrit == 2)
							sOutput += " crit damage!";
						else
							sOutput += " damage!";
						pOutput.getChildren().set(0, animate(sOutput));
						updateStats();
					}
				});
			}
			else {
				rangedAttack.setDisable(true);
			}
			hAttack.getChildren().add(rangedAttack);
		}
		
		//Spell List
		for (Spell spell : getMyClass().getSpells()) {
			Button spellAttack = new Button(spell.getName());
			setFormat(spellAttack);
			if (spell.getManaCost() > getMyClass().getCurrentMana()) {
				spellAttack.setDisable(true);
			}
			else {
				spellAttack.setTooltip(new Tooltip(spell.getDescription()));
				spellAttack.setOnAction(e -> {
					if (getMyClass().getCurrentActions() != 0) {
						playSound(spell.getSoundString());
						getMyClass().setCurrentActions(getMyClass().getCurrentActions() - 1);
						getMyClass().setCurrentMana(getMyClass().getCurrentMana() - spell.getManaCost());
						
						if (spell.getManaCost() > getMyClass().getCurrentMana()) spellAttack.setDisable(true);
						if (spell.getDamage() != 0) {
							damageSpell(spell);
						} else if (spell.getHp() != 0) {
							hpSpell(spell);
						} else if (spell.getImmunity()) {
							immunitySpell(spell);
						} else if (spell.getDamageBuff() != 0) {
							damageBuffSpell(spell);
						} else if (spell.getDefenseBuff() != 0) {
							defenseBuffSpell(spell);
						}
					}
					refreshSpells();
				});
			}
			hAttack.getChildren().add(spellAttack);
		}
	}
	
	private void refreshSpells() {
		int i = 0;
		for (Spell spell : getMyClass().getSpells()) {
			Button spellAttack = new Button(spell.getName());
			setFormat(spellAttack);
			if (spell.getManaCost() > getMyClass().getCurrentMana()) {
				spellAttack.setDisable(true);
			}
			else {
				spellAttack.setTooltip(new Tooltip(spell.getDescription()));
				spellAttack.setOnAction(e -> {
					if (getMyClass().getCurrentActions() != 0) {
						playSound(spell.getSoundString());
						getMyClass().setCurrentActions(getMyClass().getCurrentActions() - 1);
						getMyClass().setCurrentMana(getMyClass().getCurrentMana() - spell.getManaCost());
						
						if (spell.getManaCost() > getMyClass().getCurrentMana()) spellAttack.setDisable(true);
						if (spell.getDamage() != 0) {
							damageSpell(spell);
						} else if (spell.getHp() != 0) {
							hpSpell(spell);
						} else if (spell.getImmunity()) {
							immunitySpell(spell);
						} else if (spell.getDamageBuff() != 0) {
							damageBuffSpell(spell);
						} else if (spell.getDefenseBuff() != 0) {
							defenseBuffSpell(spell);
						}
					}
					refreshSpells();
				});
			}
			if (getMyClass().getWeapon().getRangedDamage() == 0)
				hAttack.getChildren().set(1 + i++, spellAttack);
			else
				hAttack.getChildren().set(2 + i++, spellAttack);
		}
	}
	
	private void updateStats() {
		lPActions.setText("Actions: " + getMyClass().getCurrentActions());
		lPActions.setText("Actions: " + getMyClass().getCurrentActions());
		lPHealth.setText("HP: " + getMyClass().getCurrentHealth());
		lPMana.setText("Mana: " + getMyClass().getCurrentMana());
		lEHealth.setText("HP: " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP());
			
		pHealthBar.setProgress(((double) getMyClass().getCurrentHealth())/getMyClass().getMaxHealth());
		
		if (getMyClass().getCurrentMana() <= 0) {
			pManaBar.setProgress(-1);
		}
		else {
			pManaBar.setProgress(((double) getMyClass().getCurrentMana())/getMyClass().getMaxMana());
		}
		if (getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP() <= 0) {
			getMyClass().setCurrentExperience(getMyClass().getCurrentExperience() 
					+ getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getExperience());
			for (Item i : getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getLoot())
				getMyClass().getInventory().addItem(i);
			eHealthBar.setProgress(-1);
			getMyClass().setCurrentActions(getMyClass().getMaxActions());
			defenseBuff = 0;
			immunity = false;
			damageBuff = 0;
			animation.setOnFinished(e -> {
				animation.setOnFinished(i ->{});
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				sOutput = "You've defeated the " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getName() + "!";
				getMyClass().setCurrentEnemy(getMyClass().getCurrentEnemy() + 1);
				pOutput.getChildren().set(0, animate(sOutput));
				continueButton();
			});
		}
		else {
			eHealthBar.setProgress(((double) getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP()
					/getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getMaxHP()));
		}
	}
	
	private void endTurn() {
		final ArrayList<Spell> spellList =  getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getSpells();
		final Enemy enemy = getMyClass().getEnemies().get(getMyClass().getCurrentEnemy());
		if (spellList.size() != 0) {
			Spell spell = spellList.get((int )(Math.random() * spellList.size()));
			sOutput = "The " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getName() 
					+ " casts " + spell.getName() + "!\n";
			playSound(spell.getSoundString());
			if (!immunity) {
				if (dodge() ) {
					sOutput += spell.getAttackDesc() + "\nYou dodge the attack!";
					pOutput.getChildren().set(0, animate(sOutput));
				}
				else {
					int damage = (int) ((spell.getDamage() + enemy.getBonusDamage()) * getDefense(getMyClass().getDefense() + defenseBuff));
					getMyClass().setCurrentHealth(getMyClass().getCurrentHealth() - damage);
					sOutput += spell.getAttackDesc() + "\nYou take " + damage + " damage!";
					pOutput.getChildren().set(0, animate(sOutput));
					
				}
				getMyClass().setCurrentActions(getMyClass().getMaxActions());
				defenseBuff = 0;
				immunity = false;
				damageBuff = 0;
			}
			else {
				sOutput += spell.getAttackDesc() + "\nYou take 0 damage!";
				pOutput.getChildren().set(0, animate(sOutput));
				getMyClass().setCurrentActions(getMyClass().getMaxActions());
				defenseBuff = 0;
				immunity = false;
				damageBuff = 0;
			}
		}
		else {
			sOutput = "The " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getName() 
					+ " casts " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getWeapon().getAttackDesc() + "!\n";
			if (!getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getWeapon().getAttackDesc().equalsIgnoreCase("Nothing"))
				playSound(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getWeapon().getAttackDesc());
			if (!immunity) {
				if (dodge()) {
					sOutput += enemy.getWeapon().getAttackDesc() + "\nYou dodge the attack!";
					pOutput.getChildren().set(0, animate(sOutput));
				}
				else {
					int damage = (int) ((enemy.getBonusDamage() + enemy.getWeapon().getRelevantDamage()) * getDefense(getMyClass().getDefense() + defenseBuff));
					getMyClass().setCurrentHealth(getMyClass().getCurrentHealth() - damage);
					sOutput += enemy.getWeapon().getAttackDesc() + "\nYou take " + damage + " damage!";
					pOutput.getChildren().set(0, animate(sOutput));
				}
				getMyClass().setCurrentActions(getMyClass().getMaxActions());
				lPActions.setText("Actions: " + getMyClass().getCurrentActions());
				defenseBuff = 0;
				immunity = false;
				damageBuff = 0;
			}
			else {
				sOutput += enemy.getWeapon().getAttackDesc() + "\nYou take 0 damage!";
				pOutput.getChildren().set(0, animate(sOutput));
				getMyClass().setCurrentActions(getMyClass().getMaxActions());
				defenseBuff = 0;
				immunity = false;
				damageBuff = 0;
			}
		}
		updateStats();
		deathCheck();
	}
	
	private void defenseBuffSpell(Spell spell) {
		defenseBuff = (int) (spell.getDefenseBuff() * getMyClass().getWeapon().getSpellMultiplier()
				* (1 + (getMyClass().getIntelligence() - 10) / 20.0));
		sOutput = "You cast " + spell.getName() + "!\n" + spell.getAttackDesc() 
		+ "\nYou temporarily increase your defense by " + defenseBuff;
		pOutput.getChildren().set(0, animate(sOutput));
		updateStats();
	}
	
	private void damageBuffSpell(Spell spell) {
		if (spell.getName().equalsIgnoreCase("Backstab"))
			guaranteeCrit = true;
		damageBuff = (int) (spell.getDamageBuff() + getMyClass().getWeapon().getMeleeDamage()
				* (1 + (getMyClass().getIntelligence() - 10) / 20.0));
		sOutput = "You cast " + spell.getName() + "!\n" + spell.getAttackDesc() 
		+ "\nYou temporarily increase your damage by " + damageBuff;
		pOutput.getChildren().set(0, animate(sOutput));
		updateStats();
	}
	
	private void immunitySpell(Spell spell) {
		sOutput = "You cast " + spell.getName() + "!\n" + spell.getAttackDesc() 
		+ "\nYou temporarily cannot be hit";
		pOutput.getChildren().set(0, animate(sOutput));
		immunity = true;
		updateStats();
	}
	
	private void hpSpell(Spell spell) {
		int bonusHP = (int) (spell.getHp() * getMyClass().getWeapon().getSpellMultiplier() 
				* (1 + (getMyClass().getIntelligence() - 10) / 20.0));
		sOutput = "You cast " + spell.getName() + "!\n" + spell.getAttackDesc() 
		+ "\nYou temporarily increase your health by " + bonusHP;
		pOutput.getChildren().set(0, animate(sOutput));
		getMyClass().setCurrentHealth(getMyClass().getCurrentHealth() + bonusHP);
		updateStats();
	}
	
	private void damageSpell(Spell spell) {
		int didCrit = dexRandom();
		if (guaranteeCrit) {
			didCrit = 2;
			guaranteeCrit = false;
		}
		int damage = 0;
		if (spell.getName().equalsIgnoreCase("Hail of Blades") || spell.getName().equalsIgnoreCase("Arrow Storm"))
			damage = (int) (getSpellDamage((int) (getMyClass().getWeapon().getRelevantDamage() * didCrit * 1.5)) 
					* getDefense(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getArmor().getDefense()));
		else
			damage = (int) (getSpellDamage(spell.getDamage() * didCrit) 
				* getDefense(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getArmor().getDefense()));
		if (damage >= getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP()) {
			getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).setCurrentHP(0);
		}
		else {
			getMyClass().getEnemies().get(getMyClass().getCurrentEnemy())
			.setCurrentHP(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP() - damage);
		}
		sOutput = "You cast " + spell.getName() + "!\n" + spell.getAttackDesc() + "\nYou dealt " + damage;
		if (didCrit == 2) 
			sOutput += " crit damage!";
		else sOutput += " damage!";
		pOutput.getChildren().set(0, animate(sOutput));
		updateStats();
	}
	
	
	private void loadPlayer() {
		lPHealth = new Label("HP: " + getMyClass().getCurrentHealth());
		pHealthBar = getHealthBar();
		lPMana = new Label("Mana: " + getMyClass().getCurrentMana());
		pManaBar = getManaBar();
		Label lPLevel = new Label("Level: " + getMyClass().getLevel());
		Label lPWeapon = new Label("Weapon: " + getMyClass().getWeapon().getName());
		Label lPDamage = new Label("Damage: " + getMyClass().getWeapon().getRelevantDamage());
		Label lPArmor = new Label("Armor: " + getMyClass().getArmor().getName());
		Label lPDefense = new Label("Defense: " + getMyClass().getArmor().getDefense());
		lPActions = new Label("Actions: " + getMyClass().getCurrentActions());
		
		lPWeapon.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, getMyClass().getWeapon()).loadScene();});
		lPArmor.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), 
				this, getMyClass().getArmor()).loadScene();});
		setFormat(lPHealth);
		setFormat(lPMana);
		setFormat(lPLevel);
		setFormat(lPWeapon);
		setFormat(lPDamage);
		setFormat(lPArmor);
		setFormat(lPDefense);
		setFormat(lPActions);
		
		playerBars = new GridPane();
		playerBars.add(lPHealth, 0, 0, 2, 1);
		playerBars.add(pHealthBar, 0, 1, 2, 1);
		playerBars.add(lPMana, 0, 2, 2, 1);
		playerBars.add(pManaBar, 0, 3, 2, 1);
		
		playerBars.setVgap(10);
		playerBars.setHgap(10);
		
		playerStats = new GridPane();
		playerStats.add(lPLevel, 0, 2);
		playerStats.add(lPWeapon, 0, 3);
		playerStats.add(lPDamage, 0, 4);
		playerStats.add(lPArmor, 0, 5);
		playerStats.add(lPDefense, 0, 6);
		playerStats.add(lPActions, 0, 7);
		
		playerStats.setVgap(30);
		playerStats.setHgap(10);
		
		vLeft = new VBox();
		vLeft.getChildren().addAll(playerBars, playerStats);
		vLeft.setAlignment(Pos.CENTER);
	}
	
	private void loadEnemy() {
		lEHealth = new Label("HP: " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getCurrentHP());
		eHealthBar = getHealthBar(getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()));
		Label lELevel = new Label("Level: " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getLevel());
		Label lEWeapon = new Label("Weapon: " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getWeapon().getName());
		Label lEDamage = new Label("Damage: " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getWeapon().getRelevantDamage());
		Label lEArmor = new Label("Armor: " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getArmor().getName());
		Label lEDefense = new Label("Defense: " + getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getArmor().getDefense());
		
		lEWeapon.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), this, 
				getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getWeapon()).loadScene();});
		lEArmor.setOnMouseClicked(e -> {new ItemDescriptionScene(getWindow(), getMyClass(), this, 
				getMyClass().getEnemies().get(getMyClass().getCurrentEnemy()).getArmor()).loadScene();});
		
		setFormat(lEHealth);
		setFormat(lELevel);
		setFormat(lEWeapon);
		setFormat(lEDamage);
		setFormat(lEArmor);
		setFormat(lEDefense);
		
		enemyBars = new GridPane();
		enemyBars.add(lEHealth, 0, 0, 2, 1);
		enemyBars.add(eHealthBar, 0, 1, 2, 1);
		
		enemyBars.setVgap(10);
		enemyBars.setHgap(10);
		
		
		enemyStats = new GridPane();
		enemyStats.add(lELevel, 0, 3);
		enemyStats.add(lEWeapon, 0, 4);
		enemyStats.add(lEDamage, 0, 5);
		enemyStats.add(lEArmor, 0, 6);
		enemyStats.add(lEDefense, 0, 7);
		
		enemyStats.setVgap(30);
		enemyStats.setHgap(10);
		
		vRight = new VBox();
		vRight.getChildren().addAll(enemyBars, enemyStats);
		vRight.setAlignment(Pos.CENTER);
	}
	
	private void continueButton() {
		bLoot = new Button("Continue");
		setFormat(bLoot);
		bLoot.setOnAction(e -> {
			loadScene();
		});
		
		hAttack.getChildren().clear();
		hAttack.getChildren().add(bLoot);
	}
	
	private void endCombat() {
		
		//Create and load loot items, and xp gained
		Inventory loot = new Inventory();
		for (Enemy e : getMyClass().getEnemies()) {
			xpGained += e.getExperience();
			for (Item i : e.getLoot()) {
				loot.addItem(i);
			}
		}
		
		//Create Loot Box Image
				Image iBackpack = new Image("BackgroundFiles/Backpack.jpg");
				ImageView iVBackpack = new ImageView(iBackpack);
				iVBackpack.setFitHeight(500);
				iVBackpack.setFitWidth(500);
				iVBackpack.setPreserveRatio(true);
				
		//Loading loot string
		sOutput = "Congratulations! You've earned: ";
		if (loot.getLength() == 0) {
			sOutput += "Nothing!";
		}
		else {
			for (Item i: loot.getArray()) {
				sOutput += i.getAmount() + " " + i.getName() + "! ";
			}
		}
		pOutput.getChildren().set(0, animate(sOutput));
		
		sOutput = "You gained " + xpGained + " experience!";
		if (getMyClass().getCurrentExperience() >= getMyClass().getMaxExperience() && getMyClass().getLevel() < 20) {
			sOutput += "\nYou levelled up!";
			getMyClass().setCurrentExperience(getMyClass().getCurrentExperience() - getMyClass().getMaxExperience());
			getMyClass().setLevel(getMyClass().getLevel() + 1);
			getMyClass().setMaxExperience(getMyClass().getLevel() * 100);
			bLevelUp = new Button("Level Up");
			setFormat(bLevelUp);
			bLevelUp.setOnAction(e -> {
				if (getMyClass().getLevel() == 2 || getMyClass().getLevel() == 4 || getMyClass().getLevel() == 6 
						|| getMyClass().getLevel() == 8 || getMyClass().getLevel() == 10 || getMyClass().getLevel() == 12
						|| getMyClass().getLevel() == 14 || getMyClass().getLevel() == 16 || getMyClass().getLevel() == 18
						|| getMyClass().getLevel() == 20)
				new ChooseSkillPointsScene(getWindow(), getMyClass(), 1, previousScene).loadScene();
				else {
					new GainSpellsScene(getWindow(), getMyClass(), previousScene).loadScene();
				}
			});
			hAttack.getChildren().clear();
			hAttack.getChildren().add(bLevelUp);
		}
		else {
			bEndCombat = new Button("End Combat");
			setFormat(bEndCombat);
			bEndCombat.setOnAction(e -> {
				getMyClass().setCurrentActions(getMyClass().getMaxActions());
				getMyClass().getEnemies().clear();
				getMyClass().setCurrentEnemy(0);
				previousScene.loadScene();
			});
			hAttack.getChildren().clear();
			hAttack.getChildren().add(bEndCombat);
		}
		
		animation.setOnFinished(e -> {
			animation.setOnFinished( i ->{});
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			pOutput.getChildren().set(0, animate(sOutput));
		});
		bottomHalf.getChildren().clear();
		bottomHalf.getChildren().addAll(pOutput, hAttack);
		
		if (getMyClass().getMap().getPositionChar() == 'M' || getMyClass().getMap().getPositionChar() == 'E'
				|| getMyClass().getMap().getPositionChar() == 'W' || getMyClass().getMap().getPositionChar() == 'D')
			getMyClass().getMap().setPositionChar(' ');
		//Refresh the Scene
		setRoot(new BorderPane());
		getRoot().setCenter(iVBackpack);
		getRoot().setBottom(bottomHalf);
		
	}
	
	@Override
	protected void formatControls() {
		setFormat(bFlee);
		setFormat(bEndTurn);
	}

	@Override
	protected void formatContainers() {
		
		//hOverall.setPadding(new Insets(20));
		hEndTurn.setAlignment(Pos.BOTTOM_LEFT);
		hFlee.setAlignment(Pos.BOTTOM_RIGHT);
		hAttack.setAlignment(Pos.BOTTOM_CENTER);
		hAttack.setPadding(new Insets(20));
		hAttack.setSpacing(20);
		
		HBox.setHgrow(hEndTurn, Priority.ALWAYS);
		HBox.setHgrow(hFlee, Priority.ALWAYS);
		
		bottomHalf.setAlignment(Pos.CENTER);
		getRoot().setPadding(new Insets(20));
		
		pOutput.setMaxWidth(500);
		pOutput.setMaxHeight(100);
		pOutput.setMinHeight(100);
		pOutput.setMinWidth(500);
		pOutput.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		pOutput.setStyle("-fx-border-radius: 15px; -fx-border-color: black;");
	}

	@Override
	protected void buttonAddAction() {
		bFlee.setOnAction(e -> {
			getMyClass().getEnemies().clear();
			getMyClass().setCurrentEnemy(0);
			getMyClass().setCurrentActions(getMyClass().getMaxActions());
			previousScene.loadScene();
		});
		bEndTurn.setOnAction(e -> {
			endTurn();
		});
	}

	@Override
	protected void addTooltips() {
		
	}
	
	private Text animate(final String content) {
		Text text = new Text(10, 20, "");
		text.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
	    text.setWrappingWidth(500);
	    animation = new Transition() {
	        {
	            setCycleDuration(Duration.millis(30 * content.length()));
	        }
	        
	        @Override
	        protected void interpolate(double frac) {
	            final int length = content.length();
	            final int n = Math.round(length * (float) frac);
	            text.setText(content.substring(0, n));
	        }
	    
	    };
	    
	    animation.play();
	    return text;
	}
	
	private double getDefense(int defense) {
		if (getMyClass().getClassType() == ClassType.Warrior)
			return 0.75 * 50 / (50 + defense);
		else
			return (double) 50 / (50 + defense);
	}
	
	private int getNotSpellDamage(int damage) {
		return (int) ((damageBuff + damage) * (1 + (getMyClass().getStrength() - 10) / 10.0));
	}
	
	private int getSpellDamage(int damage) {
		return (int) ((damageBuff + damage) * (1 + (getMyClass().getIntelligence() - 10) / 20.0) 
				* (1 + (getMyClass().getStrength() - 10) / 10.0) * getMyClass().getWeapon().getSpellMultiplier()
				+ (getMyClass().getWeapon().getMeleeDamage()));
	}
	
	private boolean dodge() {
		if ((int) (Math.random() * getMyClass().getAgility()) >= 10) {
			return true;
		}
		else return false;
	}
	
	private int dexRandom() {
		if ((int) (Math.random() * getMyClass().getDexterity()) >= 10) {
			return 2;
		}
		else return 1;
	}

}
