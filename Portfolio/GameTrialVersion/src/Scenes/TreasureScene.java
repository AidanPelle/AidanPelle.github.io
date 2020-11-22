package Scenes;

import Characters.PlayerClass;
import Items.Item;
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

public class TreasureScene extends MyScene{
	
	private VBox bottomHalf;
	private Button bExit;
	private Animation animation;
	private Pane pOutput;
	private String sOutput;

	public TreasureScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}

	@Override
	public void loadScene() {
		Image iBackpack = new Image("BackgroundFiles/Backpack.jpg");
		ImageView iVBackpack = new ImageView(iBackpack);
		iVBackpack.setFitHeight(500);
		iVBackpack.setFitWidth(500);
		iVBackpack.setPreserveRatio(true);
		
		sOutput = "You found the treasure!";
		sOutput += "\nYou got: 10 Gold, 5 Bat Ear, 5 Vial of Venom!";
		getMyClass().getInventory().addItem(new Item("Gold", 10));
		getMyClass().getInventory().addItem(new Item("Bat Ear", 5));
		getMyClass().getInventory().addItem(new Item("Vial of Venom", 5));
		pOutput = new Pane();
		bExit = new Button("Exit");
		bottomHalf = new VBox();
		bottomHalf.getChildren().addAll(pOutput, bExit);
		setRoot(new BorderPane());
		getRoot().setCenter(iVBackpack);
		getRoot().setBottom(bottomHalf);
		
		setScene(new Scene(getRoot(), 1000, 710));
		setFormatting(null, this);
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
			getMyClass().getMap().setPositionChar(' ');
			new TravellingMenuScene(getWindow(), getMyClass()).loadScene();
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
