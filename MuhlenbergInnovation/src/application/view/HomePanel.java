package application.view;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import org.apache.commons.io.FilenameUtils;

import application.Main;
import application.model.FileTreeItem;

public class HomePanel extends AnchorPane {

	private final String homePath = "view/homeLayout.fxml";

	@FXML
	private VBox vBox;
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TilePane tilePane;

	private File selectedFile;

	public HomePanel() {

		selectedFile = null;

		//load fxml file
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setRoot(this);
			loader.setController(this);
			loader.setLocation(Main.class.getResource(homePath));
			loader.load();
		} catch(IOException e) {e.printStackTrace();}

		setupTreeView();
		setupTilePane();
	}

	private void setupTreeView() {
		//setup treeview
		File appHome = new File(Main.class.getPackage() + "/tests");
		makeDefaults(appHome);

		FileTreeItem root = new FileTreeItem(appHome.getPath());
		root.setExpanded(true);
		root.getChildren();

		treeView.setRoot(root);

		treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {

				//expand node
				FileTreeItem clickedItem = (FileTreeItem) treeView.getSelectionModel().getSelectedItem();
				clickedItem.getChildren();

				//check for images to display
				if(!clickedItem.isExpanded()) {
					selectedFile = null;
					tilePane.getChildren().clear();
				}
				else {
					selectedFile = clickedItem.getFile();
					setupTilePane();
				}
				
			}
		});		
	}

	private void setupTilePane() {

		if(selectedFile!=null) {

			File[] images = selectedFile.listFiles(new ImageFileFilter());

			if(images!= null) {
				for(File image : images) {
					tilePane.getChildren().add(createImage(image));
				}
			}

		}

		selectedFile = null;
	}

	private ImageView createImage(File file) {

		try {
			Image image = new Image(new FileInputStream(file), 150, 0, true, true);
			ImageView view = new ImageView(image);

			return view;
		} catch (FileNotFoundException e) {e.printStackTrace();}

		return null;
	}

	private void makeDefaults(File appHome) {
		if(!appHome.exists())
			appHome.getParentFile().mkdirs();

		File class1 = new File(appHome.getPath() + "/Class 1");
		class1.getParentFile().mkdirs();

		File exam1 = new File(appHome.getPath() + "/Class 1/Exam 1");
		exam1.getParentFile().mkdirs();
	}

	private class ImageFileFilter implements FileFilter {

		@Override
		public boolean accept(File f) {
			String extension = FilenameUtils.getExtension(f.getPath());
			if(extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif"))
				return true;

			return false;
		}

	}
}
