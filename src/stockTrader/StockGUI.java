package stockTrader;

import java.text.DecimalFormat;

import javafx.application.Application;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StockGUI extends Application{
	
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
	
	Text name = new Text("NO STOCK LOADED");

	
	@Override
	public void start(Stage primaryStage){
		Stage window = primaryStage;
		window.setMinWidth(250);
		
		String[] symbolArray = {"FB", "GOOGL", "MSFT", "AAPL"};
		int numStocks = symbolArray.length;
		
		MenuItem[] stocksEdit;
		MenuItem[] stocksDelete;
		Button[] stocksView;
		
		stocksEdit = new MenuItem[numStocks];
		stocksDelete = new MenuItem[numStocks];
		stocksView = new Button[numStocks];
		
		Menu file = new Menu("File");

		Menu managePortfolio = new Menu("Portfolio Management");
		
		MenuItem addStock = new MenuItem("Add a Stock");
		addStock.setOnAction(e -> StockGUIActionHandlers.placeHolder("add"));
				
		Menu editStock = new Menu("Edit a Stock");
		
		for (int i = 0; i < numStocks; i++){	
			String tempSymbol = symbolArray[i]; 
			stocksEdit[i] = new MenuItem(symbolArray[i]);
			stocksEdit[i].setOnAction(e -> StockGUIActionHandlers.placeHolder(tempSymbol));
		}
		
		editStock.getItems().addAll(stocksEdit);
		
		Menu deleteStock = new Menu("Delete a Stock");
		
		for (int i = 0; i < numStocks; i++){	
			String tempSymbol = symbolArray[i]; 
			stocksDelete[i] = new MenuItem(symbolArray[i]);
			stocksDelete[i].setOnAction(e -> StockGUIActionHandlers.placeHolder(tempSymbol));
		}
		
		deleteStock.getItems().addAll(stocksDelete);
				
		managePortfolio.getItems().addAll(addStock, editStock, deleteStock);
				
		MenuItem exitApp = new MenuItem("Quit");
		exitApp.setOnAction(e -> CloseConfirm.display("Exit?", "Are you sure you would like to exit the program?"));
		
		file.getItems().addAll(managePortfolio, exitApp);
		
		Menu about = new Menu("About");
		MenuItem credits = new MenuItem("Credits");
		about.getItems().add(credits);
		credits.setOnAction(e -> AlertBox.display("About Stock Advisor", "Stock Advisor was created by Alessandra Shipman & Andrew Thomas"));
		
		VBox leftBar = new VBox(5);
		
		for (int i = 0; i < numStocks; i++){
			String tempSymbol = symbolArray[i];
			stocksView[i] = new Button(symbolArray[i]);
			stocksView[i].setOnAction(e -> displayStockInfo(tempSymbol));
		}
		
		leftBar.getChildren().addAll(stocksView);
		
		BorderPane bPane = new BorderPane();
		BorderPane aPane = new BorderPane();
		MenuBar toolbar = new MenuBar(file, about);
				
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
		
		GridPane gPane = new GridPane();
		gPane.setVgap(5);
		gPane.setHgap(5);
		
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
		
		name.setTextAlignment(TextAlignment.CENTER);
		name.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		
		aPane.setTop(name);
		aPane.setCenter(gPane);
		
		bPane.setTop(toolbar);
		bPane.setCenter(aPane);
		bPane.setLeft(leftBar);
		
		Scene scene = new Scene(bPane);
		window.setTitle("Stock Advisor");
		window.setScene(scene);
		window.show();
				
	}
	
	public void displayStockInfo(String symbol) {
		Stock tempStock = StockFetcher.getStock(symbol);
		
		name.setText(tempStock.getName());
		
		DecimalFormat money = new DecimalFormat("$ ###,###,###.00");
		DecimalFormat bigNumber = new DecimalFormat("###,###,###");
		
		tbsymbol.setText(tempStock.getSymbol());
		tbprice.setText("" + money.format(tempStock.getPrice()));
		tbvolume.setText("" + bigNumber.format(tempStock.getVolume()));
		tbpe.setText("" + tempStock.getPe());
		tbeps.setText("" + tempStock.getEps());
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