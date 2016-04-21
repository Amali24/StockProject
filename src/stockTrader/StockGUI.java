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
		MenuItem[] stocksHeld;
		stocksHeld = new MenuItem[numStocks];
		Menu file = new Menu("File");
			Menu managePortfolio = new Menu("Portfolio Management");
				MenuItem addStock = new MenuItem("Add a Stock");
					addStock.setOnAction(e -> placeHolder());
				Menu editStock = new Menu("Edit a Stock");
				for (int i = 0; i < symbolArray.length; i++){	
					stocksHeld[i] = new MenuItem(symbolArray[i]);
					stocksHeld[i].setOnAction(e -> placeHolder());
				}
					editStock.getItems().addAll(stocksHeld);
				Menu deleteStock = new Menu("Delete a Stock");
				
				managePortfolio.getItems().addAll(addStock, editStock, deleteStock);
				
		MenuItem exitApp = new MenuItem("Quit");
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
	
	public void placeHolder() {
		System.out.println("Place Holder Invoked");
	}

	public static void main(String[] args){
		launch();
	}

}
