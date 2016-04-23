package stockTrader;

import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StockGUI extends Application{
	
	// Text Fields for Displaying Stock Info
	TextField tbsymbol = new TextField();
	TextField tbprice = new TextField();
	TextField tbvolume = new TextField();
	TextField tbpe = new TextField();
	TextField tbeps = new TextField();	
	TextField tbweek52low = new TextField();
	TextField tbweek52high = new TextField();
	TextField tbdaylow = new TextField();	
	TextField tbdayhigh = new TextField();  
	TextField tbmovingav50day = new TextField();
	TextField tbmarketcap = new TextField();	  
	TextField tbshortRatio = new TextField();
	TextField tbpreviousClose = new TextField(); 
	TextField tbopen = new TextField();		 
	TextField tbexchange = new TextField();	  
	TextField tbmovingav200Day = new TextField();
	

	
	// Text Object to display name of stock, defaults to "No Stock Loaded"
	Text name = new Text("No Stock Loaded");

	
	@Override
	public void start(Stage primaryStage){
		Stage window = primaryStage; // "re-name" primaryStage for ease of use
		window.setMinWidth(730); // Establish minimum width of stage for even spacing throughout
		
		String[] symbolArray = {"FB", "GOOGL", "MSFT", "AAPL"}; // Holds symbols of held stocks
		
		int numStocks = symbolArray.length; // number of stocks
		
		// Menu and Button Array to Dynamically Create Menus and Buttons
		MenuItem[] stocksDelete;
		Button[] stocksView;
		stocksDelete = new MenuItem[numStocks];
		stocksView = new Button[numStocks];
		
		// File Menu
		Menu file = new Menu("File");
		// Portfolio Management sub-menu
		Menu managePortfolio = new Menu("Portfolio Management");
		// Add Stock menuItem for adding new stock to portfolio
		MenuItem addStock = new MenuItem("Add a Stock");
		addStock.setOnAction(e -> addStock());
		
		// Delete Stock sub-menu (dynamically lists currently added stocks
		Menu deleteStock = new Menu("Delete a Stock");
		
		// Create Dynamic Menu
		for (int i = 0; i < numStocks; i++){	
			String tempSymbol = symbolArray[i]; 
			stocksDelete[i] = new MenuItem(symbolArray[i]);
			stocksDelete[i].setOnAction(e -> StockGUIActionHandlers.placeHolder(tempSymbol));
		}
		// Add dynamic list of stocks to delete stock menu
		deleteStock.getItems().addAll(stocksDelete);
		
		// Add all sub menus to portfolio menu
		managePortfolio.getItems().addAll(addStock, deleteStock);
		
		// Add exit command
		MenuItem exitApp = new MenuItem("Quit");
		//Display confirmation box if exit clicked
		exitApp.setOnAction(e -> CloseConfirm.display("Exit?", "Are you sure you would like to exit the program?"));
		//Add all to File menu
		file.getItems().addAll(managePortfolio, exitApp);
		
		// Create about menu with menu item "credits"
		Menu about = new Menu("About");
		MenuItem credits = new MenuItem("Credits");
		about.getItems().add(credits);
		// Display Credits
		credits.setOnAction(e -> AlertBox.display("About Stock Advisor", "Stock Advisor was created by Alessandra Shipman & Andrew Thomas"));
		
		// Create VBox or stock buttons
		VBox leftBar = new VBox(5);
		
		// Dyanmically create button for each stock in array
		for (int i = 0; i < numStocks; i++){
			String tempSymbol = symbolArray[i];
			stocksView[i] = new Button(symbolArray[i]);
			stocksView[i].setMaxWidth(66);
			stocksView[i].setMinWidth(66);
			stocksView[i].setOnAction(e -> displayStockInfo(tempSymbol));
		}
		
		// Add dynamic buttons to leftBar
		leftBar.getChildren().addAll(stocksView);
		
		// Create two border panes
		BorderPane bPane = new BorderPane();
		BorderPane aPane = new BorderPane();
		
		// MenuBar holds previously created menus
		MenuBar toolbar = new MenuBar(file, about);
		
		
		// Make text boxes uneditable
		tbsymbol.setEditable(false);
		tbprice.setEditable(false);
		tbvolume.setEditable(false);
		tbpe.setEditable(false);
		tbeps.setEditable(false);
		tbweek52low.setEditable(false);
		tbweek52high.setEditable(false);
		tbdaylow.setEditable(false);
		tbdayhigh.setEditable(false);
		tbmovingav50day.setEditable(false);
		tbmarketcap.setEditable(false);
		tbshortRatio.setEditable(false);
		tbpreviousClose.setEditable(false);
		tbopen.setEditable(false);
		tbexchange.setEditable(false);
		tbmovingav200Day.setEditable(false);
		
		// Create a label for each data field
		Label symbol = new Label("Symbol: ");
		Label price = new Label("Price: ");
		Label volume = new Label("Volume: ");
		Label pe = new Label("Price/Earnings Ratio: ");
		Label eps = new Label("Earnings per Share: ");	
		Label week52low = new Label("52 Week Low: ");
		Label week52high = new Label("52 Week High: ");
		Label daylow = new Label("Daily Low: ");	
		Label dayhigh = new Label("Daily High: ");  
		Label movingav50day = new Label("Moving 50 Day Average: ");
		Label marketcap = new Label("Market Cap: ");	  
		Label shortRatio = new Label("Short Ratio: ");
		Label previousClose = new Label("Previous Close: "); 
		Label open = new Label("Previous Open: ");		 
		Label exchange = new Label("Exchange: ");	  
		Label movingav200Day = new Label("Moving 200 Day Average: ");
		
		// Create Grid Pane for labels and class level textFields
		GridPane gPane = new GridPane();
		gPane.setVgap(5);
		gPane.setHgap(5);
		
		// Add all labels and text fields to grid pane
		gPane.add(symbol, 0, 0);
		gPane.add(exchange, 0, 1);
		gPane.add(price, 0, 2);
		gPane.add(volume, 0, 3);
		gPane.add(daylow, 0, 4);
		gPane.add(dayhigh, 0, 5);
		gPane.add(open, 0, 6);
		gPane.add(previousClose, 0, 7);
		
		gPane.add(tbsymbol, 1, 0);
		gPane.add(tbexchange, 1, 1);
		gPane.add(tbprice, 1, 2);
		gPane.add(tbvolume, 1, 3);
		gPane.add(tbdaylow, 1, 4);
		gPane.add(tbdayhigh, 1, 5);
		gPane.add(tbopen, 1, 6);
		gPane.add(tbpreviousClose, 1, 7);
		
		gPane.add(pe, 2, 0);
		gPane.add(eps, 2, 1);
		gPane.add(marketcap, 2, 2);
		gPane.add(shortRatio, 2, 3);
		gPane.add(week52low, 2, 4);
		gPane.add(week52high, 2, 5);
		gPane.add(movingav50day, 2, 6);
		gPane.add(movingav200Day, 2, 7);
		
		gPane.add(tbpe, 3, 0);
		gPane.add(tbeps, 3, 1);
		gPane.add(tbmarketcap, 3, 2);
		gPane.add(tbshortRatio, 3, 3);
		gPane.add(tbweek52low, 3, 4);
		gPane.add(tbweek52high, 3, 5);
		gPane.add(tbmovingav50day, 3, 6);
		gPane.add(tbmovingav200Day, 3, 7);
		
		// Set Font parameters for title
		name.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		
		//Position name
		aPane.setTop(name);
		BorderPane.setAlignment(name, Pos.CENTER);
		// Position grid pane with labels and fields
		aPane.setCenter(gPane);
		
		// Position toolbar and other panes
		bPane.setTop(toolbar);
		bPane.setCenter(aPane);
		bPane.setLeft(leftBar);
		
		
		// Set some margins
		BorderPane.setMargin(leftBar, new Insets(25, 10, 10, 10));
		
		
		// Display scene
		Scene scene = new Scene(bPane);
		window.setTitle("Stock Advisor");
		window.setScene(scene);
		window.show();
						
	}
	
	private void addStock() {
		// TODO finish this method
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add a Stock");
		window.setMinWidth(250);
		
		Label symbol = new Label("Symbol: ");
		TextField symbolTB = new TextField();
		
		String symbolIn = symbolTB.getText(); 
		
		Button okay = new Button("Add");
		okay.setOnAction(e -> addToStockArray(symbolIn));
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> window.close());
		
		GridPane pane = new GridPane();
		
		pane.add(symbol, 0, 0);
		pane.add(symbolTB, 1, 0);
		pane.add(okay, 0, 1);
		pane.add(cancel, 1, 1);
		
		Scene scene = new Scene(pane);
		window.setScene(scene);
		window.showAndWait();
		
	}

	private void addToStockArray(String symbolIn) {
		//TODO write this shit
	}

	public void displayStockInfo(String symbol) {
		// Get stock info for given symbol
		Stock tempStock = StockFetcher.getStock(symbol);
		
		// Display name at top of window
		name.setText(tempStock.getName());
		
		// Decimal formats, one for money, one for other large numbers (volume)
		DecimalFormat money = new DecimalFormat("$ ###,###,###.00");
		DecimalFormat bigNumber = new DecimalFormat("###,###,###");
		
		// Set all text fields with applicable format
		tbsymbol.setText(tempStock.getSymbol());
		tbprice.setText("" + money.format(tempStock.getPrice()));
		tbvolume.setText("" + bigNumber.format(tempStock.getVolume()));
		tbpe.setText("" + tempStock.getPe());
		tbeps.setText("" + money.format(tempStock.getEps()));
		tbweek52low.setText("" + money.format(tempStock.getWeek52low()));
		tbweek52high.setText("" + money.format(tempStock.getWeek52high()));
		tbdaylow.setText("" + money.format(tempStock.getDaylow()));
		tbdayhigh.setText("" + money.format(tempStock.getDayhigh()));
		tbmovingav50day.setText("" + money.format(tempStock.getMovingav50day()));
		tbmarketcap.setText(tempStock.getMarketcap());
		tbshortRatio.setText("" + tempStock.getShortRatio());
		tbpreviousClose.setText("" + money.format(tempStock.getPreviousClose()));
		tbopen.setText("" + money.format(tempStock.getOpen()));
		tbexchange.setText(tempStock.getExchange());
		tbmovingav200Day.setText("" + money.format(tempStock.getMovingav200Day()));
		

	}
	
	public static void main(String[] args){
		launch();
	}	
}