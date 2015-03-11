package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.view.FramePanel;


public class Main extends Application {
	
	public static final String title = "Scantron Killer";
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
	
		Scene frame = new Scene(new FramePanel());
		primaryStage.setTitle(title);
		primaryStage.setScene(frame);
		primaryStage.show();
	}
	
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
