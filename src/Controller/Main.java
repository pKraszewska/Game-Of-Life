package Controller;

import Model.Board;
import Model.Cell;
import View.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Board initialBoard = new Board(Config.ViewConfig.NUMBER_OF_ROWS, Config.ViewConfig.NUMBER_OF_COLUMNS);
        BoardView boardView = new BoardView(initialBoard);
        Game game = new Game(initialBoard);
        Scene scene = new Scene(boardView, Config.ViewConfig.WIDTH, Config.ViewConfig.HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Of Life");
        primaryStage.show();

        GameLoop gameLoop = new GameLoop(game, scene);
        new KeyControls(gameLoop, scene);
        gameLoop.updateGame();

    }
}