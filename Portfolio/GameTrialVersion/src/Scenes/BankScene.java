package Scenes;

import Characters.PlayerClass;
import Items.Item;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BankScene extends MyScene{
	
	private GridPane gBank, gInventory;
	private Label lBank;
	private Button bExit;
	private AudioClip clip;
	private boolean outpost = false;
	
	public BankScene(Stage window, PlayerClass myClass) {
		super(window, myClass);
	}
	
	public BankScene(Stage window, PlayerClass myClass, boolean outpost) {
		super(window, myClass);
		this.outpost = outpost;
	}
	
	@Override
	public void loadScene() {
		clip = new AudioClip(this.getClass().getResource("/AudioFiles/Bank.wav").toString());
		clip.play(0.5);
		// TODO Auto-generated method stub
		lBank = new Label("You enter a bustling bank");
		bExit = new Button("Exit Bank");
		
		
		setRoot(new BorderPane());
		getRoot().setTop(lBank);
		getRoot().setBottom(bExit);
		
		setScene(new Scene(getRoot(), 1000, 500));
		
		populateInventory();
		populateBank();
		setFormatting("Bank", this);
		getWindow().setScene(getScene());
	}
	
	private void populateInventory() {
		gInventory = new GridPane();
		gInventory.setVgap(20);
		gInventory.setHgap(10);
		gInventory.setAlignment(Pos.CENTER_LEFT);
		
		for (int i = 0; i < getMyClass().getInventory().getLength(); i++) {
			Item item = getMyClass().getInventory().get(i);
			
			Label itemName = new Label(item.getName());
			Label itemAmount = new Label("" + item.getAmount());
			
			Button bDeposit = new Button("Deposit");
			
			setFormat(itemName);
			setFormat(bDeposit);
			itemName.setStyle("-fx-text-fill: white;");
			itemAmount.setStyle("-fx-text-fill: white;");
			itemAmount.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
			itemName.setTooltip(new Tooltip(item.getDescription()));
			
			gInventory.add(itemName, 0, i);
			gInventory.add(itemAmount, 1, i);
			gInventory.add(bDeposit, 2, i);
			
			bDeposit.setOnAction(e -> {
				if (!item.getName().equalsIgnoreCase("Gold") || item.getAmount() != 0) {
					getMyClass().getInventory().removeItem(new Item(item.getName(), 1));
					getMyClass().getBank().addItem(new Item(item.getName(), 1));
					populateInventory();
					populateBank();
				}
			});
		}
		getRoot().setLeft(gInventory);
	}
	
	private void populateBank() {
		gBank = new GridPane();
		gBank.setVgap(20);
		gBank.setHgap(10);
		gBank.setAlignment(Pos.CENTER_RIGHT);
		
		for (int i = 0; i < getMyClass().getBank().getLength(); i++) {
			Item item = getMyClass().getBank().get(i);
			
			Label itemName = new Label(item.getName());
			Label itemAmount = new Label("" + item.getAmount());
			
			Button bWithdraw = new Button("Withdraw");
			
			setFormat(itemName);
			setFormat(bWithdraw);
			itemName.setStyle("-fx-text-fill: white;");
			itemAmount.setStyle("-fx-text-fill: white;");
			itemAmount.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 19));
			itemName.setTooltip(new Tooltip(item.getDescription()));
			
			gBank.add(itemName, 0, i);
			gBank.add(itemAmount, 1, i);
			gBank.add(bWithdraw, 2, i);
			
			bWithdraw.setOnAction(e -> {
				if (!item.getName().equalsIgnoreCase("Gold") || item.getAmount() != 0) {
					getMyClass().getBank().removeItem(new Item(item.getName(), 1));
					getMyClass().getInventory().addItem(new Item(item.getName(), 1));
					populateInventory();
					populateBank();
				}
			});
		}
		getRoot().setRight(gBank);
	}
	
	@Override
	protected void formatControls() {
		// TODO Auto-generated method stub
		setFormat(lBank, "white");
		setFormat(bExit);
		
		lBank.setAlignment(Pos.TOP_CENTER);
		bExit.setAlignment(Pos.BOTTOM_CENTER);
		
		
		BorderPane.setAlignment(bExit, Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(lBank, Pos.TOP_CENTER);
	}

	@Override
	protected void buttonAddAction() {
		// TODO Auto-generated method stub
		bExit.setOnAction(e -> {
			if (clip.isPlaying()) {
				clip.stop();
			}
			if (outpost)
				new OutpostScene(getWindow(), getMyClass()).loadScene();
			else
				new TownScene(getWindow(), getMyClass()).loadScene();
		});
	}

	@Override
	protected void addTooltips() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void formatContainers() {
		// TODO Auto-generated method stub
		
	}

}
