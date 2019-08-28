
package Controller;

import Model.Board;
import View.BoardView;
import javafx.scene.Scene;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GameLoop {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(5);
    private Game game;
    private Scene scene;
    private boolean isGamePaused;
    private int currentPeriod;         //period of time in scheduledExecutorService, impacts to animation speed
    private int changedPeriod;
    private int periodDiff = 100;    //when slowing down or speeding up
    private int delay;
    private ScheduledFuture<?> gameUpdating;

    public GameLoop(Game game, Scene scene) {
        this.game = game;
        this.scene = scene;
        isGamePaused = true;
        final int INITIAL_PERIOD = 100;
        currentPeriod = INITIAL_PERIOD;
        delay = currentPeriod;
    }

    boolean isGamePaused() {
        return isGamePaused;
    }

    void setGamePaused(boolean gamePaused) {
        isGamePaused = gamePaused;
    }

    private void changePeriod(int diff) {
        if (currentPeriod + diff > 0) {
            changedPeriod = currentPeriod + diff;
        }
    }

    void speedUp() {
        changePeriod(-periodDiff);
        actualizeGameTempo();
    }

    void slowDown() {
        changePeriod(periodDiff);
        actualizeGameTempo();
    }

    public void updateGame() {

        final Runnable gameUpdate = new Runnable() {
            public void run() {
                if (isGamePaused) {
                    return;
                }
                synchronized (this) {
                    Board nextGeneration = game.playGame();
                    scene.setRoot(new BoardView(nextGeneration));
                }
            }
        };
        gameUpdating = scheduler.scheduleAtFixedRate(gameUpdate, delay, currentPeriod, MILLISECONDS);
    }

    private void actualizeGameTempo() {
        if (changedPeriod != currentPeriod) {
            gameUpdating.cancel(true);
            updateGame();
            currentPeriod = changedPeriod;
        }
    }

}