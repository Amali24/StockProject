package stockTrader;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CloseConfirm{
	
	public static void display(String title, String message){
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		
		Button okayButton = new Button("Confirm");
		okayButton.setOnAction(e -> exitApp());
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> window.close());
		
		VBox vBox = new VBox(10);
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(okayButton, cancelButton);
		vBox.getChildren().addAll(label, hBox);
		vBox.setAlignment(Pos.CENTER);
		hBox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private static void exitApp() {
		System.exit(0);
	}
}
