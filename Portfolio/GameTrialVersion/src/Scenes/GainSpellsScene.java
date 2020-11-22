package Scenes;

import Characters.PlayerClass;
import Characters.PlayerClass.ClassType;
import Items.Spell;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class GainSpellsScene extends MyScene{
	
	private MyScene previousScene;
	private VBox bottomHalf;
	private Button bExit;
	private Animation animation;
	private Pane pOutput;
	private String sOutput;

	public GainSpellsScene(Stage window, PlayerClass myClass, MyScene previousScene) {
		super(window, myClass);
		this.previousScene = previousScene;
	}

	@Override
	public void loadScene() {
		getMyClass().setMaxHealth(getMyClass().getMaxHealth() + 10);
		getMyClass().setCurrentHealth(getMyClass().getMaxHealth());
		if (findSpell()) {
			Spell spell = newSpell();
			getMyClass().getSpells().add(spell);
			Image iBackpack = new Image("ItemSpellImages/" + spell.getName() + ".jpg");
			ImageView iVBackpack = new ImageView(iBackpack);
			iVBackpack.setFitHeight(500);
			iVBackpack.setFitWidth(500);
			iVBackpack.setPreserveRatio(true);
			
			sOutput = "You learned: " + spell.getName() + "!";
			sOutput += "\n" + spell.getDescription();
			pOutput = new Pane();
			bExit = new Button("Exit");
			bottomHalf = new VBox();
			bottomHalf.getChildren().addAll(pOutput, bExit);
			setRoot(new BorderPane());
			getRoot().setCenter(iVBackpack);
			getRoot().setBottom(bottomHalf);
			
			setScene(new Scene(getRoot(), 1000, 710));
			setFormatting(null, this);
			playSound(spell.getSoundString());
			pOutput.getChildren().add(animate(sOutput));
			
			if (gainAction()) {
				animation.setOnFinished(e -> {
					animation.setOnFinished(i ->{});
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					sOutput = "Due to your increasing experience and skill, you gained a bonus combat action!";
					getMyClass().setMaxActions(getMyClass().getMaxActions() + 1);
					getMyClass().setCurrentActions(getMyClass().getMaxActions());
					playSound("Slash");
					pOutput.getChildren().set(0, animate(sOutput));
				});
			}
			getWindow().setScene(getScene());
		}
		else if (gainAction()) {
			Image iBackpack = new Image("WeaponArmorImages/Iron Sword.jpg");
			ImageView iVBackpack = new ImageView(iBackpack);
			iVBackpack.setFitHeight(500);
			iVBackpack.setFitWidth(500);
			iVBackpack.setPreserveRatio(true);
			
			pOutput = new Pane();
			bExit = new Button("Exit");
			bottomHalf = new VBox();
			bottomHalf.getChildren().addAll(pOutput, bExit);
			setRoot(new BorderPane());
			getRoot().setBottom(bottomHalf);
			getRoot().setCenter(iVBackpack);
			setScene(new Scene(getRoot(), 1000, 710));
			setFormatting(null, this);
			sOutput = "Due to your increasing experience and skill, you gained a bonus combat action!";
			getMyClass().setMaxActions(getMyClass().getMaxActions() + 1);
			getMyClass().setCurrentActions(getMyClass().getMaxActions());
			playSound("Slash");
			pOutput.getChildren().add(animate(sOutput));
			getWindow().setScene(getScene());
			}
		else previousScene.loadScene();
	}
	
	private boolean gainAction() {
		switch (getMyClass().getClassType()) {
		case Mage: {
			if (getMyClass().getLevel() == 8)
				return true;
		}
		case Warrior: {
			if (getMyClass().getLevel() == 8 || getMyClass().getLevel() == 18)
				return true;
		}
		case Archer: {
			if (getMyClass().getLevel() == 8 || getMyClass().getLevel() == 18)
				return true;
		}
		case Assassin: {
			if (getMyClass().getLevel() == 8 || getMyClass().getLevel() == 18)
				return true;
		}
		
		}
		return false;
	}
	
	private boolean findSpell() {
		if (getMyClass().getLevel() == 3 || getMyClass().getLevel() == 8 || getMyClass().getLevel() == 13 
				|| getMyClass().getLevel() == 18 && getMyClass().getClassType() == ClassType.Mage)
			return true;
		else if (getMyClass().getLevel() == 3 || getMyClass().getLevel() == 8 || getMyClass().getLevel() == 13) 
			return true;
		else return false;
	}
	
	private Spell newSpell() {
		switch (getMyClass().getClassType()) {
		case Mage: {
			switch (getMyClass().getLevel()) {
			case 3: {
				return new Spell("Frost Shield");
			}
			case 8: {
				return new Spell("Blink");
			}
			case 13: {
				return new Spell("Flame Storm");
			}
			case 18: {
				return new Spell("Vitality");
			}
			}
		}
		case Warrior: {
			switch (getMyClass().getLevel()) {
			case 3: {
				return new Spell("Shield Wall");
			}
			case 8: {
				return new Spell("Flame Blade");
			}
			case 13: {
				return new Spell("Last Stand");
			}
			}
		}
		case Assassin: {
			switch (getMyClass().getLevel()) {
			case 3: {
				return new Spell("Hail of Blades");
			}
			case 8: {
				return new Spell("Invisibility");
			}
			case 13: {
				return new Spell("Backstab");
			}
			}
		}
		case Archer: {
			switch (getMyClass().getLevel()) {
			case 3: {
				return new Spell("Arrow Storm");
			}
			case 8: {
				return new Spell("Heavy Shot");
			}
			case 13: {
				return new Spell("Windwalk");
			}
			}
		}
		default: {
			return null;
		}
		}
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
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
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

}
