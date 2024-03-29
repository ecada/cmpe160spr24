import java.awt.*;
import java.util.Random;

public abstract class Animal {
    protected int row;
    protected int col;

    protected final int animalID;
    protected static final int rows = 10;
    protected static final int cellSize = 40;
    protected static final int cols = 10;
    protected Random randGen = new Random();

    protected Color color;

    protected int numRewards = 0;

    protected Animal(int row, int col, Color color, int animalID) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.animalID= animalID;
        }

    protected int getRow() {
        return row;
        }

    protected int getCol() {
        return col;
        }

    protected abstract void move();

    protected void draw() {
        StdDraw.setPenColor(color);
        StdDraw.text((col + 0.5) * cellSize, (rows - row - 0.5) * cellSize, Integer.toString(animalID));
        StdDraw.circle((col + 0.5) * cellSize, (rows - row - 0.5) * cellSize, cellSize * 0.4);
    }

}

