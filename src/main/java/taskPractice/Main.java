package taskPractice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import taskPractice.config.SpringConfig;
import taskPractice.loaderProvider.FXMLLoaderProvider;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        FXMLLoader loader = ctx.getBean(FXMLLoaderProvider.class).getLoader("/main.fxml");

        Parent root = loader.load();
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
