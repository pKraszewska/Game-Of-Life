package Model;

public class Board {

    private int numberOfRows;

    private int numberOfColumns;

    private Cell[][] generation;

    public Board(int numberOfRows, int numberOfColumns) {

        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        generation = new Cell[numberOfRows][numberOfColumns];
        createBoard();
        findAllCellsNeighbours();
    }


    public void createBoard() {
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                generation[row][column] = new Cell();

            }
        }
    }

    private void findAllCellsNeighbours() {

        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfColumns; column++) {
                findCellsNeighbours(row, column);
            }
        }
    }

    private void findCellsNeighbours(int row, int column) {

        int[][] neighboursRelativeIndicesArray = new int[][]{{-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}};

        int indexInNeighboursArray = 0;
        int neighbourRow;
        int neighbourColumn;

        for (int[] indices : neighboursRelativeIndicesArray) {
            neighbourRow = row + indices[0];
            neighbourColumn = column + indices[1];

            if (neighbourRow == -1) {
                neighbourRow = numberOfRows - 1;
            } else if (neighbourRow == numberOfRows) {
                neighbourRow = 0;
            }
            if (neighbourColumn == -1) {
                neighbourColumn = numberOfColumns - 1;
            } else if (neighbourColumn == numberOfColumns) {
                neighbourColumn = 0;
            }

            getCell(row, column).setNeighbour(getCell(neighbourRow, neighbourColumn), indexInNeighboursArray);
            indexInNeighboursArray++;
        }
    }

    public Cell[][] getGeneration() {
        return generation;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public Cell getCell(int row, int column) {
        return generation[row][column];
    }
}