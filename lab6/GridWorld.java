import java.awt.Color;
import java.util.Random;

public class GridWorld {
    private static final int rows = 10;
    private static final int cols = 10;
    private static final int cellSize = 40;
    private static final int numCats = 3;
    private static final int numDogs = 2;
    private static final int numFrogs= 4;
    private static final int numRewads = 5;
    private static int rewardLeft = 5;
    private static final Random randGen = new Random();
    private static Animal[] animals;
    private static Reward[] rewards;
    private static boolean[][] grid = new boolean[rows][cols];

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        setupGrid();
        setupAnimals();
        setupRewards();
        while (rewardLeft>0) {
            StdDraw.clear();
            // Draw grid
            StdDraw.setPenColor(Color.LIGHT_GRAY);
            StdDraw.setPenRadius(0.012);
            for (int i = 0; i <= rows; i++) {
                StdDraw.line(0, i * cellSize, cols * cellSize, i * cellSize);
            }
            for (int j = 0; j <= cols; j++) {
                StdDraw.line(j * cellSize, 0, j * cellSize, rows * cellSize);
            }

            // Draw rewards
            for (Reward reward : rewards) {
                reward.draw();
            }

            // Move and draw animals
            for (Animal animal : animals) {
                animal.move();
                animal.draw();
                checkForReward(animal);
            }
            StdDraw.show();
           StdDraw.pause(200);
        }
        for (Animal animal : animals) {
        System.out.println(animal);
        }
    }

    private static void setupGrid() {
        StdDraw.setCanvasSize(cols * cellSize, rows * cellSize);
        StdDraw.setXscale(0, cols * cellSize);
        StdDraw.setYscale(0, rows * cellSize);
    }

    private static void setupAnimals() {
        animals = new Animal[numCats + numDogs+numFrogs];
        for (int i = 0; i < numCats; i++) {
            animals[i] = new Cat(getrandGenomRow(), getrandGenomCol());
        }
        for (int i = numCats; i < numCats + numDogs; i++) {
            animals[i] = new Dog(getrandGenomRow(), getrandGenomCol());
        }
        for (int i = numCats+ numDogs; i < numCats + numDogs+ numFrogs; i++) {
            animals[i] = new Frog(getrandGenomRow(), getrandGenomCol());
        }
    }
    private static void setupRewards() {
        rewards = new Reward[numRewads];
        for (int i = 0; i < numRewads; i++) {
            int row =i*2 + randGen.nextInt(2);
            int col =i*2 +  randGen.nextInt(2);
            rewards[i] = new Reward(row, col,new Color(randGen.nextInt(256), randGen.nextInt(256), randGen.nextInt(256)));
            grid[row][col] = true;
        }
    }

    private static int getrandGenomRow() {
        return randGen.nextInt(rows);
    }

    private static int getrandGenomCol() {
        return randGen.nextInt(cols);
    }

    private static void checkForReward(Animal animal) {
        if (grid[animal.getRow()][animal.getCol()]) {
            for (Reward reward : rewards) {
                if (reward.getRow() == animal.getRow() && reward.getCol() == animal.getCol()) {
                    reward.collect();
                    grid[animal.getRow()][animal.getCol()] = false;
                    animal.numRewards++;
                    rewardLeft--;
                    break;
                }
            }
        }
    }
}

