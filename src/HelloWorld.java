import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

//javafx 应用主要继承自Application类
public class HelloWorld extends Application {

    //所有程序的入口
    @Override
    //stage是javafx的顶级容器
    public void start(Stage primaryStage) {
//        Button btn = new Button();
        Text text = new Text();
        text.setText("请将图片拖拽到此处");
        ImageView imageView=new ImageView();
        imageView.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != imageView) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
            }
        });

        imageView.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                List<File> files = dragboard.getFiles();
                String filePath="";
                if(files.size() > 0){
                    try {
                        filePath = files.get(0).getAbsolutePath();
                        imageView.setImage(new Image(new FileInputStream(files.get(0))));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //StackPane->Scene->stage
        //stackPane是一个可调整大小的layout节点,还有很多中节点
        StackPane root = new StackPane();
        root.getChildren().add(text);
        root.getChildren().add(imageView);
        //scene是所有内容的容器
        Scene scene = new Scene(root, 250, 100);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}