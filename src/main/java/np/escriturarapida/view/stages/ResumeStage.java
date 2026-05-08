package np.escriturarapida.view.stages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import np.escriturarapida.controller.ResumeController;
import np.escriturarapida.view.ICreateStage;

import java.io.IOException;

/**
 * Represents the summary window shown at the end of the game.
 * Displays the player's performance and options to restart or exit.
 */
public class ResumeStage extends Stage implements ICreateStage {

    private ResumeController controller;

    /**
     * Creates and displays the summary stage.
     *
     * @param win true if the player won, false otherwise
     * @param currentLevel the final level reached
     * @param currentScore the final score achieved
     * @throws IOException if the FXML resource cannot be loaded
     */
    public ResumeStage(boolean win, int currentLevel, int currentScore) throws IOException {
        FXMLLoader loader = loadFXMLLoader("/np/escriturarapida/fxml/resumenEscrituraRapida-view.fxml");
        Parent root = loader.getRoot();
        controller= loader.getController();
        controller.configureResume(win,currentLevel,currentScore);
        Scene scene = new Scene(root);
        setScene(scene);
        setResizable(false);
        setTitle("RESUMEN FINAL ESCRITURA RÁPIDA");
        getIcons().add(new Image(
                String.valueOf(getClass().getResource("/np/escriturarapida/icons/favicon.png"))
        ));
        show();
    }
    /** @return the controller associated with this stage */
    public ResumeController getController() {
        return controller;
    }
}
