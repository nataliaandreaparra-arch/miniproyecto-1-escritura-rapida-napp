package np.escriturarapida.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import np.escriturarapida.model.GameModel;
import np.escriturarapida.view.stages.GameStage;
import java.io.IOException;

/**
 * Controls the summary screen displayed at the end of the game.
 * Provides feedback to the player about their performance and options to restart or exit.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Display win/lose messages with corresponding styles and icons.</li>
 *   <li>Show final level and score statistics.</li>
 *   <li>Handle restart and exit actions.</li>
 * </ul>
 */

public class ResumeController {

    GameModel gM = new GameModel();

    @FXML
    private Label tittleLbl;
    @FXML
    private ImageView iconImage;
    @FXML
    private Label playAgainLbl;
    @FXML
    private Label resumGameLbl;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label levelLbl;
    @FXML
    private Label scoreLbl;
    @FXML
    private ImageView exitIconImg;

    /**
     * Configures the summary screen based on the game result.
     *
     * @param win true if the player won, false otherwise
     * @param level the final level reached
     * @param score the final score achieved
     */
    public void configureResume(boolean win, int level, int score) {

        if(win){
            tittleLbl.setText("¡GANASTE EL JUEGO!");
            resumGameLbl.setText("Superaste todos los niveles del juego");
            levelLbl.setText("Nivel alcanzado: " + String.valueOf(level));
            scoreLbl.setText("Puntuación final: " + String.valueOf(score));


            anchorPane.setStyle("-fx-background-color: #003300; ");
            playAgainLbl.setText("REINTENTAR");

            iconImage.setImage(new Image("/np/escrituraRapida/icons/winicon.png"));
            exitIconImg.setImage(new Image("/np/escrituraRapida/icons/exiticon.png"));
        }
        else{
            tittleLbl.setText("¡PERDISTE!");
            resumGameLbl.setText("No escribiste la palabra a tiempo");
            levelLbl.setText("Nivel alcanzado: " + String.valueOf(level));
            scoreLbl.setText("Puntuación final: " + String.valueOf(score));

            anchorPane.setStyle("-fx-background-color: #550000;");
            playAgainLbl.setText("REINTENTAR");

            iconImage.setImage(new Image("/np/escrituraRapida/icons/gameOverIcon.png"));
            exitIconImg.setImage(new Image("/np/escrituraRapida/icons/exiticon.png"));

        }

    }

    /** Handles the restart action, launching a new game stage. */
    @FXML
    public void onHandleRestart() throws IOException {
        new GameStage();

        Stage stage = (Stage) tittleLbl.getScene().getWindow();
        stage.close();

    }

    /** Handles the exit action, closing the current stage. */
    @FXML
    void onHandleExit(ActionEvent event) {
        Stage stage = (Stage) tittleLbl.getScene().getWindow();
        stage.close();
    }




}

