package application.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import application.Main;

public class FramePanel extends BorderPane {

	private String framePath = "view/frameLayout.fxml";

	public FramePanel() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(framePath));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch(IOException e) {e.printStackTrace();}
		
		this.setCenter(new HomePanel());
	}
}
