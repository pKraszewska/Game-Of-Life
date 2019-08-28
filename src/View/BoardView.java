package View;

import Config.ViewConfig;
import Model.Board;
import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {

    private Board gameBoard;

    public BoardView(Board gameBoard) {
        this.setHgap(ViewConfig.CELL_GAP);
        this.setVgap(ViewConfig.CELL_GAP);
        this.gameBoard = gameBoard;
        createBoardView();
    }

    public void createBoardView() {

        this.getChildren().clear();

        int numberOfRows = gameBoard.getNumberOfRows();
        int numberOfColumns = gameBoard.getNumberOfColumns();


        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {

                CellView cellView = new CellView(gameBoard.getGeneration()[row][column]);

                GridPane.setColumnIndex(cellView, column);
                GridPane.setRowIndex(cellView, row);

                this.getChildren().addAll(cellView);
            }
        }
    }
}
