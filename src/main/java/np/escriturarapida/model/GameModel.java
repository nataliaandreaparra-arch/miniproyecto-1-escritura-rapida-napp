package np.escriturarapida.model;

import java.util.Random;
/**
 * Represents the core game state for "Fast Typing".
 * This class stores and manages the player's level, score, and time tracking.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Track current level and score.</li>
 *   <li>Manage total time and remaining time per level.</li>
 *   <li>Provide difficulty scaling by reducing available seconds as levels increase.</li>
 * </ul>
 *
 * @author Natalia Andrea Parra Peña
 * @version 1.0
 */

public class GameModel {

    private int currentLevel = 0;
    private int currentScore = 0;
    private int totalTime= 0;
    private int timeLeft= 0;

    /** Sets the remaining time for the current level. */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /** Sets the total time allocated for the current level. */
    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    /** Decreases the remaining time by one second. */
    public void decreaseTime(){
        timeLeft--;
    }

    /** @return total time allocated for the current level */
    public int getTotalTime() {
        return totalTime;
    }

    /** @return remaining time for the current level */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Calculates the number of seconds available for a given level.
     * Difficulty increases by reducing time every 5 levels.
     *
     * @param lev the level number
     * @return seconds allocated for the level, or -1 if invalid
     */
    public int levelSeconds(int lev){
        if(lev<= 5) return 20;
        if(lev <= 10) return 18;
        if(lev <= 15) return 16;
        if(lev <= 20) return 14;
        if(lev <= 25) return 12;
        if(lev <= 30) return 10;
        if(lev <= 35) return 8;
        if(lev <= 40) return 6;
        if(lev <= 45) return 4;

        return -1;
    }

    /** @return the current level number */
    public int getCurrentLevel(){
        return currentLevel;
    }

    /** @return the current score */
    public int getCurrentScore(){
        return currentScore;
    }

    /** Increments the current level by one. */
    public void setCurrentLevel() {
        currentLevel++;
    }

    /**
     * Increases the score by the given amount.
     *
     * @param currentScore points to add
     */
    public void setCurrentScore(int currentScore) {
        this.currentScore+= currentScore;
    }
}
