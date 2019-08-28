package Model;

import Controller.Game;

import java.util.Observable;
import java.util.Observer;

public class Cell extends Observable implements Observer {

    private CellState actualCellState;
    private CellState nextGenerationCellState;

    private Cell[] neighbours = new Cell[8];

    private Game game;


    public Cell() {
        actualCellState = CellState.DEAD;
        nextGenerationCellState = CellState.DEAD;
    }

    public void setFutureCellState() {

        boolean cellIsAlive = actualCellState.equals(CellState.ALIVE);

        int aliveNeighboursCount = 0;

        for (Cell neighbourCell : neighbours) {
            if (neighbourCell.actualCellState.equals(CellState.ALIVE)) {
                aliveNeighboursCount++;
            }
        }

        if ((cellIsAlive && (aliveNeighboursCount == 2 || aliveNeighboursCount == 3))) {
            nextGenerationCellState = CellState.ALIVE;
        } else if (!cellIsAlive && aliveNeighboursCount == 3) {
            nextGenerationCellState = CellState.ALIVE;
        } else {
            nextGenerationCellState = CellState.DEAD;
        }
    }

    public boolean stateChanges() {
        return !(actualCellState.equals(nextGenerationCellState));
    }

    public synchronized void notifyAllCellsObserving() {
        setChanged();
        notifyObservers();
        clearChanged();

    }

    public void changeActualState() {
        if (actualCellState == CellState.DEAD) {
            actualCellState = CellState.ALIVE;
        } else {
            actualCellState = CellState.DEAD;
        }
    }

    public void bringToLife() {
        changeActualState();
        game.getCellsToBeCheckedInThisGeneration().add(this);
        for (Cell clickedCellNeighbour : neighbours) {
            game.getCellsToBeCheckedInThisGeneration().add(clickedCellNeighbour);
        }
    }

//    public Cell[] getNeighbours() {
//        return neighbours;
//    }

    public void setNeighbour(Cell neighbour, int index) {
        neighbours[index] = neighbour;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void update(Observable observedCell, Object arg) {
        addCellToToBeCheckedList(this);
        removeFromObserversList(observedCell, this);
    }

    private void removeFromObserversList(Observable o, Cell cell) {

        o.deleteObserver(this);
    }

    private void addCellToToBeCheckedList(Cell cell) {

        game.getCellsToBeCheckedInNextGeneration().add(cell);
    }


    public void makeAllNeighboursObserveThis() {

        for (Cell neighbour : neighbours) {
            this.addObserver(neighbour);
        }
    }

    public CellState getActualCellState() {
        return actualCellState;
    }

    public void setActualCellState(CellState actualCellState) {
        this.actualCellState = actualCellState;
    }

    public CellState getNextGenerationCellState() {
        return nextGenerationCellState;
    }

    public void setNextGenerationCellState(CellState nextGenerationCellState) {
        this.nextGenerationCellState = nextGenerationCellState;
    }
}