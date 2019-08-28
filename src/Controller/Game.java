package Controller;

import Model.Board;
import Model.Cell;
import Model.CellState;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private Board currentGeneration;

    private int numberOfRows;
    private int numberOfColumns;

    private Set<Cell> cellsToBeCheckedInThisGeneration = new HashSet<>();
    private Set<Cell> cellsToBeCheckedInNextGeneration = new HashSet<>();

    public Game(Board currentGeneration) {
        this.currentGeneration = currentGeneration;
        numberOfRows = this.currentGeneration.getNumberOfRows();
        numberOfColumns = this.currentGeneration.getNumberOfColumns();
        attachGameToAllCells();
    }

    public Set<Cell> getCellsToBeCheckedInThisGeneration() {
        return cellsToBeCheckedInThisGeneration;
    }

    public Set<Cell> getCellsToBeCheckedInNextGeneration() {
        return cellsToBeCheckedInNextGeneration;
    }

    public Board playGame() {

        notifyAllNeighboursIfNeeded();
        changeStateOfAllCheckedCells();
        cloneList();

        return currentGeneration;
    }

    private void cloneList() {
        cellsToBeCheckedInThisGeneration.clear();
        cellsToBeCheckedInThisGeneration.addAll(cellsToBeCheckedInNextGeneration);
        cellsToBeCheckedInNextGeneration.clear();
    }

    private void changeStateOfAllCheckedCells() {
        for (Cell cellToBeChecked : cellsToBeCheckedInThisGeneration) {
            cellToBeChecked.setActualCellState(cellToBeChecked.getNextGenerationCellState());
            cellToBeChecked.setNextGenerationCellState(CellState.DEAD);

        }
    }

    private void notifyAllNeighboursIfNeeded() {
        for (Cell cellToBeChecked : cellsToBeCheckedInThisGeneration) {
            cellToBeChecked.setFutureCellState();
            if (cellToBeChecked.stateChanges()) {
                cellToBeChecked.makeAllNeighboursObserveThis();
                cellToBeChecked.notifyAllCellsObserving();
            }
        }
    }

    private void attachGameToAllCells() {
        for (Cell[] cells : currentGeneration.getGeneration()) {
            for (Cell cell : cells) {
                cell.setGame(this);
            }
        }
    }


}