package np.escriturarapida.model;

/**
 * Defines the contract for word management in the game.
 * Implementations must provide word generation, validation, and retrieval.
 */
public interface IWordManager {

    /** Generates a new random word for the player. */
    void generateWord();

    /**
     * Verifies if the player's input matches the current word.
     *
     * @param wordText the word entered by the player
     * @return true if the word matches, false otherwise
     */
    boolean verifyWord(String wordText);

    /** @return the current word to be typed */
    String getCurrentWord();
}
