package application.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.activation.MimetypesFileTypeMap;

import application.Main;

public class FileTreeItem extends TreeItem<String>{

	private boolean hasLoadedChildren = false;
	private File root;

	private Image collapsed, expanded, folder;

	public FileTreeItem(String filePath) {
		super(filePath);
		this.root = new File(filePath);

		String value = filePath.substring(filePath.lastIndexOf(File.separator)+1);
		if(value.length() == 0)
			value = filePath;

		this.setValue(value);

		generateImages();
	}

	@Override
	public ObservableList<TreeItem<String>> getChildren() {
		if(!hasLoadedChildren) {
			loadChildren();
		}
		return super.getChildren();
	}

	public void loadChildren() {
		this.hasLoadedChildren = true;

		if(!root.isDirectory())
			return;

		File[] roots = root.listFiles();
		if(roots!=null) {
			for(File child : roots) {
				FileTreeItem node = new FileTreeItem(child.getPath());
				
				if(node.getFile().isDirectory())
					node.setGraphic(new ImageView(collapsed));

				try {
					String fileType =Files.probeContentType(Paths.get(node.getFile().toURI()));
					if(fileType!= null)
						System.out.println(fileType);
				} catch (IOException e) {e.printStackTrace();}
				
				super.getChildren().add(node);
			}
		}
	}

	private void generateImages() {
		collapsed = new Image(Main.class.getResourceAsStream("/res/collapsed.png"));
		expanded = new Image(Main.class.getResourceAsStream("/res/expanded.png"));
		folder = new Image(Main.class.getResourceAsStream("/res/folder.png"));

	}

	public File getFile() {
		return this.root;
	}
}
