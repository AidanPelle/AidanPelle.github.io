package Scenes;

import Characters.PlayerClass;
import Characters.PlayerClass.ClassType;
import Items.Armor;
import Items.Item;
import Items.Spell;
import Items.Weapon;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ItemDescriptionScene extends MyScene{
	
	private MyScene previousScene;
	private VBox bottomHalf;
	private Button bExit;
	private Animation animation;
	private Pane pOutput;
	private String sOutput;
	private SceneType sceneType;
	private Weapon weapon;
	private Armor armor;
	private Item item;
	private Spell spell;
	Image iBackpack;
	ImageView iVBackpack;

	public ItemDescriptionScene(Stage window, PlayerClass myClass, MyScene previousScene, Weapon weapon) {
		super(window, myClass);
		this.previousScene = previousScene;
		this.weapon = weapon;
		sceneType = SceneType.Weapon;
	}
	
	public ItemDescriptionScene(Stage window, PlayerClass myClass, MyScene previousScene, Armor armor) {
		super(window, myClass);
		this.previousScene = previousScene;
		this.armor = armor;
		sceneType = SceneType.Armor;
	}
	
	public ItemDescriptionScene(Stage window, PlayerClass myClass, MyScene previousScene, Item item) {
		super(window, myClass);
		this.previousScene = previousScene;
		this.item = item;
		sceneType = SceneType.Item;
	}
	
	public ItemDescriptionScene(Stage window, PlayerClass myClass, MyScene previousScene, Spell spell) {
		super(window, myClass);
		this.previousScene = previousScene;
		this.spell = spell;
		sceneType = SceneType.Spell;
	}
	
	private enum SceneType{
		Weapon, Armor, Item, Spell;
	}

	@Override
	public void loadScene() {
		
		switch (sceneType) {
		case Weapon: {
			iBackpack = new Image("WeaponArmorImages/" + weapon.getName() + ".jpg");
			sOutput = "Name: " + weapon.getName() + "		Type: " + weapon.getWeaponType() + "		Tier: " + weapon.getItemTier();
			sOutput += "\nMelee: " + weapon.getMeleeDamage();
			if (weapon.getRangedDamage() != 0) sOutput += "	Ranged: " + weapon.getRangedDamage();
			else if (getMyClass().getClassType() == ClassType.Mage) sOutput += "		Spell Multiplier: " + weapon.getSpellMultiplier();
			sOutput += "\n" + weapon.getDescription();
			break;
		}
		case Armor: {
			iBackpack = new Image("WeaponArmorImages/" + armor.getName() + ".jpg");
			sOutput = "Name: " + armor.getName() + "		Tier: " + armor.getItemTier();
			sOutput += "\nDefense: " + armor.getDefense();
			sOutput += "\n" + armor.getDescription();
			break;
		}
		case Item: {
			iBackpack = new Image("ItemSpellImages/" + item.getName() + ".jpg");
			sOutput = "Name: " + item.getName() + "		Price: " + item.getPrice();
			sOutput += "\n" + item.getDescription();
			break;
		}
		case Spell: {
			iBackpack = new Image("ItemSpellImages/" + spell.getName() + ".jpg");
			sOutput = "Name: " + spell.getName();
			if (spell.getDamage() != 0) sOutput += "		Damage: " + spell.getDamage();
			else if (spell.getHp() != 0) sOutput += "		Bonus HP: " + spell.getHp();
			else if (spell.getImmunity()) sOutput += "		Immunity: " + spell.getImmunity();
			else if (spell.getDamageBuff() != 0) sOutput += "		Damage Buff: " + spell.getDamageBuff();
			else if (spell.getDefenseBuff() != 0) sOutput += "		Defense Buff: " + spell.getDefenseBuff();
			sOutput += "\nMana Cost: " + spell.getManaCost();
			sOutput += "\n" + spell.getDescription();
			break;
		}
		}
		
		iVBackpack = new ImageView(iBackpack);
		iVBackpack.setFitHeight(500);
		iVBackpack.setFitWidth(500);
		iVBackpack.setPreserveRatio(true);
		
		pOutput = new Pane();
		bExit = new Button("Exit");
		bottomHalf = new VBox();
		bottomHalf.getChildren().addAll(pOutput, bExit);
		setRoot(new BorderPane());
		getRoot().setCenter(iVBackpack);
		getRoot().setBottom(bottomHalf);
		
		setScene(new Scene(getRoot(), 1000, 710));
		setFormatting(null, null);
		pOutput.getChildren().add(animate(sOutput));
		getWindow().setScene(getScene());
	}

	@Override
	protected void formatControls() {
		setFormat(bExit);
	}

	@Override
	protected void formatContainers() {
		
		bottomHalf.setAlignment(Pos.CENTER);
		bottomHalf.setSpacing(20);
		getRoot().setPadding(new Insets(20));
		
		pOutput.setPrefWidth(500);
		pOutput.setMaxWidth(500);
		pOutput.setMaxHeight(100);
		pOutput.setMinHeight(100);
		pOutput.setMinWidth(500);
		pOutput.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		pOutput.setStyle("-fx-border-radius: 15px; -fx-border-color: black;");
	}

	@Override
	protected void buttonAddAction() {
		bExit.setOnAction(e -> {
			previousScene.loadScene();
		});
		bExit.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.B) {
				previousScene.loadScene();
			}
		});
		getRoot().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				getRoot().setOnKeyPressed(i -> {
					if (i.getCode() == KeyCode.ESCAPE) {
						System.exit(0);
					}
				});
			}
		});
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}
	
	private Text animate(final String content) {
		Text text = new Text(10, 20, "");
		text.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
	    text.setWrappingWidth(490);
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

}
