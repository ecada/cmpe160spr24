import java.awt.*;

public class Reward {
    private final int row;
    private final int col;
    private final Color color;
    protected static final int rows = 10;
    protected static final int  cellSize = 40;
    private boolean collected = false;

    public Reward(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void collect() {
        collected = true;
    }

    public void draw() {
        if (!collected) {
            StdDraw.setPenColor(color);
            StdDraw.filledRectangle((col + 0.5) * cellSize, (rows - row - 0.5) * cellSize, cellSize * 0.3,cellSize * 0.3);
        }
    }
}
