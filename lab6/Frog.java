
import java.awt.*;
public class Frog extends Animal {
    private static int forgID=1;
    public Frog(int row, int col) {
        super(row, col, Color.GREEN,forgID);
        forgID++;

    }

    @Override
    protected void move() {
        int direction = randGen.nextInt(4);
        switch (direction) {
            case 0:
                if (row > 1) {
                    row-=2;
                }
                break;
            case 1:
                if (row < rows - 2) {
                    row+=2;
                }
                break;
            case 2:
                if (col > 1) {
                    col-=2;
                }
                break;
            case 3:
                if (col < cols - 2) {
                    col+=2;
                }
                break;
        }
    }

    @Override
    public String toString() {
        return "Frog"+animalID+"{" +
                "NUM_REWARDS=" + numRewards +
                '}';
    }
}
