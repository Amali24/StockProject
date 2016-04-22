package stockTrader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StockGUI extends Application{

	@Override
	public void start(Stage primaryStage){
		String[] symbolArray = {"test 1", "test 2"};
		int numStocks = symbolArray.length;
		MenuItem[] stocksEdit;
		MenuItem[] stocksDelete;
		stocksEdit = new MenuItem[numStocks];
		stocksDelete = new MenuItem[numStocks];
		Menu file = new Menu("File");
			
		Menu managePortfolio = new Menu("Portfolio Management");
		
		MenuItem addStock = new MenuItem("Add a Stock");
		addStock.setOnAction(e -> placeHolder("add"));
				
		Menu editStock = new Menu("Edit a Stock");
		for (int i = 0; i < symbolArray.length; i++){	
			String tempSymbol = symbolArray[i]; 
			stocksEdit[i] = new MenuItem(symbolArray[i]);
			stocksEdit[i].setOnAction(e -> placeHolder(tempSymbol));
		}
		editStock.getItems().addAll(stocksEdit);
		
		Menu deleteStock = new Menu("Delete a Stock");
		for (int i = 0; i < symbolArray.length; i++){	
			String tempSymbol = symbolArray[i]; 
			stocksDelete[i] = new MenuItem(symbolArray[i]);
			stocksDelete[i].setOnAction(e -> placeHolder(tempSymbol));
		}
		deleteStock.getItems().addAll(stocksDelete);
				
		managePortfolio.getItems().addAll(addStock, editStock, deleteStock);
				
		MenuItem exitApp = new MenuItem("Quit");
		exitApp.setOnAction(e -> CloseConfirm.display("Exit?", "Are you sure you would like to exit the program?"));
		
		file.getItems().addAll(managePortfolio, exitApp);
		
		Menu edit = new Menu("Edit");
		Menu help = new Menu("Help");
		BorderPane bPane = new BorderPane();
		AnchorPane aPane = new AnchorPane();
		MenuBar toolbar = new MenuBar(file, edit, help);
		
		bPane.setTop(toolbar);
		bPane.setCenter(aPane);
		
		Scene scene = new Scene(bPane);
		primaryStage.setTitle("Stock Advisor");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public static void exitApp() {
		System.out.println("Exit Method Invoked");
	}

	public void placeHolder(String symbol) {
		System.out.println("Place Holder Invoked: " + symbol);
	}

	public static void main(String[] args){
		launch();
	}

}
