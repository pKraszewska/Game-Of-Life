package Config;

import java.awt.*;

public class ViewConfig {

    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static final double WIDTH = SCREEN_WIDTH * 0.5 ;
    public static final double HEIGHT = SCREEN_HEIGHT * 0.5;

    public static final int NUMBER_OF_ROWS = 40;
    public static final int NUMBER_OF_COLUMNS = 70;

    public static final double CELL_WIDTH = WIDTH / NUMBER_OF_COLUMNS;
    public static final double CELL_HEIGHT = HEIGHT / NUMBER_OF_ROWS;

    public static final double CELL_GAP = 0.5;



}
