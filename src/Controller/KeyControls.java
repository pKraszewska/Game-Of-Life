package Controller;

import Model.Cell;
import javafx.scene.Scene;


public class KeyControls {

    private final Scene SCENE;
    private GameLoop gameLoop;



    public KeyControls(GameLoop gameLoop, Scene scene) {
        this.gameLoop = gameLoop;
        this.SCENE = scene;
        setKeysControls(gameLoop);
    }

    private void setKeysControls(GameLoop gameLoop) {

        SCENE.setOnKeyTyped(e -> {
            if (e.getCharacter().equals("p")) {
                gameLoop.setGamePaused(!gameLoop.isGamePaused());
            } else if (e.getCharacter().equals("x")) {
                gameLoop.slowDown();
                System.out.println("pressed x");
            } else if (e.getCharacter().equals("e")) {
                gameLoop.speedUp();
                System.out.println("pressed e");
            } else if (e.getCharacter().equals("c")) {
                gameLoop.setGamePaused(!gameLoop.isGamePaused());

                System.out.println("pressed u");
            }
        });

    }

}
