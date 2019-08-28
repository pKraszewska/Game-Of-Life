//package View;
//
//import Model.Cell;
//import Model.CellState;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//
//class CellView extends Rectangle {
//
//    CellView(Cell cell) {
//
//        double SIZE = 9;
//
//        this.setHeight(SIZE);
//        this.setWidth(SIZE);
//
//        this.updateCellColor(cell);
//
//        this.setOnMouseClicked(lambda -> {
//            cell.changeActualState();
//            updateCellColor(cell);
//        });
//    }
//
//    void updateCellColor(Cell cell) {
//        this.setFill((cell.getActualCellState().equals(CellState.ALIVE)) ? Color.WHITE : Color.CADETBLUE);
//    }
//}

package View;

import Config.ViewConfig;
import Model.Cell;
import Model.CellState;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class CellView extends Rectangle {

    CellView(Cell cell) {

        this.setHeight(ViewConfig.CELL_HEIGHT);
        this.setWidth(ViewConfig.CELL_WIDTH);

        this.updateCellColor(cell);

        this.setOnMouseClicked(lambda -> {
            cell.bringToLife();
            updateCellColor(cell);
        });
    }

    void updateCellColor(Cell cell) {
        this.setFill((cell.getActualCellState().equals(CellState.ALIVE)) ? Color.GREENYELLOW : Color.BLACK);
    }
}