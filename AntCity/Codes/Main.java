import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AntForm.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setResizable(false);

        String css = this.getClass().getResource("AntFormCSS.css").toExternalForm();
        scene.getStylesheets().add(css);

        Image icon = new Image("Ant_icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Ant Form");

        stage.setScene(scene);
        stage.show();
    }
}
