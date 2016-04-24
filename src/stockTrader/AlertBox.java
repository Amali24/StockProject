package stockTrader;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	public static void display(String title, String message){
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Text label = new Text();
		label.setText(message);
		label.setTextAlignment(TextAlignment.CENTER);
		
		Button okayButton = new Button("Okay");
		okayButton.setOnAction(e -> window.close());

		
		VBox vBox = new VBox(10);
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(okayButton);
		vBox.getChildren().addAll(label, hBox);
		vBox.setAlignment(Pos.CENTER);
		hBox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait();
	}
}
