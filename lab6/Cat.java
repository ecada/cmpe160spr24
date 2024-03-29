
import java.awt.*;
public class Cat extends Animal {
    private static int catID=1;
    public Cat(int row, int col) {
        super(row, col, Color.RED, catID);
        catID++;
    }

    @Override
    protected void move() {
        int direction = randGen.nextInt(4);
        switch (direction) {
            case 0:
                if (row > 1) {
                    row-=1;
                }
                break;
            case 1:
                if (row < rows - 2) {
                    row+=1;
                }
                break;
            case 2:
                if (col > 1) {
                    col-=1;
                }
                break;
            case 3:
                if (col < cols - 2) {
                    col+=1;
                }
                break;
        }
    }

    @Override
    public String toString() {
        return "Cat"+animalID+"{" +
                "NUM_REWARDS=" + numRewards +
                '}';
    }

}
