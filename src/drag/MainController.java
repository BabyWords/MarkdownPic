package drag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class MainController implements Initializable {

	@FXML
	private ImageView m_imageView;
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		m_imageView.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != m_imageView) {
					event.acceptTransferModes(TransferMode.ANY);
				}				
			}
		});
		
		m_imageView.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();
				List<File> files = dragboard.getFiles();
				String filePath="";
				if(files.size() > 0){
					try {
						filePath = files.get(0).getAbsolutePath();
						m_imageView.setImage(new Image(new FileInputStream(files.get(0))));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
