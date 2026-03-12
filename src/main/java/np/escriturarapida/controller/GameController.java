package np.escriturarapida.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import np.escriturarapida.model.GameModel;
import np.escriturarapida.model.IWordManager;
import np.escriturarapida.model.RandomWords;
import np.escriturarapida.view.stages.ResumeStage;

import java.io.IOException;

/**
 * Controls the main game logic and user interactions for the "Fast Typing" mini-project.
 * This class manages the game state, word validation, timer countdown, and feedback messages.
 * It connects the GUI components defined in FXML with the underlying game model.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Initialize the game with a random word and starting level.</li>
 *   <li>Handle user input validation via button click or Enter key.</li>
 *   <li>Manage the countdown timer and update the progress bar.</li>
 *   <li>Provide visual feedback for correct/incorrect answers.</li>
 *   <li>Control game progression, difficulty increase, and end-game summary.</li>
 * </ul>
 *
 * @author Natalia Andrea Parra Peña
 * @version 1.0
 */

public class GameController {

    /**
     * The game model containing the current state (level, score, time).
     */
    GameModel gM = new GameModel();

    /**
     * Word manager responsible for generating and validating random words.
     */
    private IWordManager wordManager;

    @FXML
    private TextField wordTextField;
    @FXML
    private Label wordLbl;
    @FXML
    private Label levelLbl;
    @FXML
    private Label scoreLbl;
    @FXML
    private Label lblChronometer;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label feedBackLbl;
    @FXML
    private Label addScoreLbl;;


    private Timeline tline;

    /**
     * Initializes the game controller after the FXML components are loaded.
     * Sets up the first random word, level, score, and starts the chronometer.
     */

    @FXML
    public void initialize() {
        wordManager= new RandomWords(gM);
        String text = wordTextField.getText();
        wordManager.generateWord();
        wordLbl.setText(wordManager.getCurrentWord());

        levelLbl.setText("NIVEL " + String.valueOf(gM.getCurrentLevel()).toUpperCase());
        scoreLbl.setText(String.valueOf(gM.getCurrentScore()));

        startChronometer(gM.levelSeconds(gM.getCurrentLevel()));
    }

    /**
     * Starts the countdown timer for the current level.
     *
     * @param levelSeconds the number of seconds allocated for the level
     */
    private void startChronometer(int levelSeconds) {
        gM.setTimeLeft(levelSeconds);
        gM.setTotalTime(levelSeconds);

        visualRefresh();

        tline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    try {
                        refreshTime();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
        );

        tline.setCycleCount(Timeline.INDEFINITE);
        tline.play();
    }

    /**
     * Refreshes the remaining time and updates the visual components.
     * Stops the timer and checks the word when time reaches zero.
     *
     * @throws IOException if validation fails due to input/output issues
     */
    private void refreshTime() throws IOException {
        gM.decreaseTime();
        visualRefresh();

        if (gM.getTimeLeft() <= 0) {
            tline.stop();
            gM.setTimeLeft(0);
            visualRefresh();
            checkWordEnd();
        }
    }

    /**
     * Updates the chronometer label and progress bar according to the remaining time.
     */
    private void visualRefresh() {
        lblChronometer.setText("00:" + String.format("%02d", gM.getTimeLeft()));

        double progress = (double) gM.getTimeLeft() / gM.getTotalTime();

        progressBar.setProgress(progress);
        changeColor(progress);
    }

    /**
     * Changes the progress bar and chronometer color depending on the remaining time.
     *
     * @param progress fraction of time left (0.0 to 1.0)
     */
    private void changeColor(double progress) {
        if (progress <= 0.25) {
            progressBar.setStyle("-fx-accent: red; -fx-border-color: red;");
            lblChronometer.setStyle("-fx-text-fill: red ;");
        } else if (progress <= 0.5) {
            progressBar.setStyle("-fx-accent: orange; -fx-border-color: orange;");
            lblChronometer.setStyle("-fx-text-fill: orange;");
        } else {
            progressBar.setStyle("-fx-accent: #00ff88; -fx-border-color: #00ff88; ");
            lblChronometer.setStyle("-fx-text-fill: #00ff88;");
        }


    }

    /**
     * Validates the word entered by the player against the current target word.
     *
     * @throws IOException if validation fails due to input/output issues
     */
    private void validateWord() throws IOException {
        String text = wordTextField.getText();

        if (!wordManager.verifyWord(text)) {
            showfeedback(false);
            nextRound(false);
            return;
        }
        showfeedback(true);
        if (playerWon()) {
            endGame(true);
            return;
        }

        nextRound(true);

    }

    /**
     * Checks if the player has won by reaching the maximum level.
     *
     * @return true if the player reached level 45, false otherwise
     */
    private boolean playerWon(){
        return (gM.getCurrentLevel() >= 45);
    }

    /**
     * Ends the game and displays the summary stage.
     *
     * @param win true if the player won, false if lost
     * @throws IOException if stage creation fails
     */
    private void endGame(boolean win) throws IOException {
        if(win)
        {
            tline.stop();
            new ResumeStage(win, gM.getCurrentLevel(), gM.getCurrentScore());

            ((Stage) wordTextField.getScene().getWindow()).close();
        }
        else
        {

            tline.stop();
            new ResumeStage(win, gM.getCurrentLevel(), gM.getCurrentScore());
            ((Stage) wordTextField.getScene().getWindow()).close();
        }
    }

    /**
     * Prepares the next round by generating a new word and resetting the timer.
     *
     * @param okWord true if the previous word was correct, false otherwise
     */
    private void nextRound(boolean okWord){
        if(okWord){
            tline.stop();
            startChronometer(gM.levelSeconds(gM.getCurrentLevel()));
        }

        wordTextField.setText("");
        wordManager.generateWord();

        wordLbl.setText(wordManager.getCurrentWord());
        scoreLbl.setText(String.valueOf(gM.getCurrentScore()));
        levelLbl.setText("NIVEL  "+String.valueOf(gM.getCurrentLevel()));
    }

    /**
     * Validates the word when the timer reaches zero.
     *
     * @throws IOException if validation fails due to input/output issues
     */
    private void checkWordEnd() throws IOException {
        String text = wordTextField.getText();

        if (wordManager.verifyWord(text)) {
            showfeedback(true);
            startChronometer(gM.levelSeconds(gM.getCurrentLevel()));
            wordTextField.setText("");
            wordManager.generateWord();
            wordLbl.setText(wordManager.getCurrentWord());
            scoreLbl.setText(String.valueOf(gM.getCurrentScore()));
            levelLbl.setText("NIVEL " + String.valueOf(gM.getCurrentLevel()).toUpperCase());
        if(playerWon())
        {
            endGame(true);
            return;
        }
        } else {
            endGame(false);
        }

    }

    /**
     * Handles the validation event triggered by a button click.
     *
     * @param event the action event from the button
     * @throws IOException if validation fails
     */
    @FXML
    public void onHandleValidate(ActionEvent event) throws IOException {
        validateWord();
    }

    /**
     * Sets up the Enter key event handler for word validation.
     */
    @FXML
    public void setOnKeyPressed() {
        wordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        validateWord();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        });
    }

    /**
     * Displays feedback messages for correct or incorrect answers.
     *
     * @param correct true if the word was correct, false otherwise
     */
    private void showfeedback(boolean correct) {
        if (correct) {
            addScoreLbl.setText("➕"+String.valueOf(wordManager.getCurrentWord().length()));
            feedBackLbl.setText("✔ Correcto");
            feedBackLbl.setStyle("-fx-text-fill: #00ff88;");
        } else {
            feedBackLbl.setText("✖ Incorrecto");
            feedBackLbl.setStyle("-fx-text-fill: red;");
        }
        Timeline feedbackTimer = new Timeline(
                new KeyFrame(Duration.seconds(1.5), e -> feedBackLbl.setText(""))
        );
        Timeline scoretimer= new Timeline(
                new KeyFrame(Duration.seconds(1.0), e -> addScoreLbl.setText(""))
        );

        feedbackTimer.play();
        scoretimer.play();
    }
}

