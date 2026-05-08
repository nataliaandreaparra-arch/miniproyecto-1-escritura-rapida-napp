package np.escriturarapida.view.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import np.escriturarapida.view.ICreateStage;

import java.io.IOException;

/**
 * Represents the main game window for "Fast Typing".
 * Loads the game view from FXML and configures the stage properties.
 */
public class GameStage extends Stage implements ICreateStage {

    /**
     * Creates and displays the game stage.
     *
     * @throws IOException if the FXML resource cannot be loaded
     */
    public GameStage() throws IOException {
        FXMLLoader loader = loadFXMLLoader("/np/escriturarapida/fxml/escrituraRapida-view.fxml");
        Parent root= loader.getRoot();
        Scene scene = new Scene(root);
        setScene(scene);
        setResizable(false);
        setTitle("ESCRITURA RÁPIDA");
        getIcons().add(new Image(
                String.valueOf(getClass().getResource("/np/escriturarapida/icons/favicon.png"))
        ));
        show();
    }
}
