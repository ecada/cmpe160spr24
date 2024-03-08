import java.awt.*;
import java.awt.event.KeyEvent;

public class FlappyBeginner {
    public static void main(String[] args) {
        double fallingConstant = 25;
        int vertSpeed = 0;
        int jumpSpeed = 30;
        int ballSize = 10;
        double x = 10, y = 200;
        double vx = 10;
        double dt = 0.1;

        int canvas_height = 600,canvas_width = 800;
        StdDraw.setCanvasSize(canvas_width, canvas_height);
        StdDraw.setXscale(0, canvas_width);
        StdDraw.setYscale(0, canvas_height);
        StdDraw.enableDoubleBuffering();
        double[][] rectangles = {
                {300, 160, 50, 160},
                {300, 490, 50, 110},
                {650, 415, 50, 185},
                {650, 85, 50, 85}
        };

        boolean gameRunning = true;
        while (gameRunning) {
            StdDraw.clear(StdDraw.WHITE);
            StdDraw.setPenColor(Color.MAGENTA);
            for (double[] rect : rectangles) {
                StdDraw.filledRectangle(rect[0], rect[1], rect[2], rect[3]);
            }

            x += vx * dt;
            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                vertSpeed = jumpSpeed;
            }
            y += vertSpeed * dt;
            vertSpeed -= fallingConstant * dt;

            StdDraw.setPenColor(Color.ORANGE);
            StdDraw.filledCircle(x, y, ballSize);
            if (x + ballSize > 750) {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 28));
                StdDraw.textLeft(50, 350, "Win!");
                StdDraw.show();
                break;
            }

            boolean hit = false;

            for (double[] rect : rectangles) {
                double closestX = Math.max(rect[0] - rect[2], Math.min(x, rect[0] + rect[2]));
                double closestY = Math.max(rect[1] - rect[3], Math.min(y, rect[1] + rect[3]));
                double distanceX = x - closestX;
                double distanceY = y - closestY;
                double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
                if (distanceSquared <= (ballSize * ballSize)) {
                    hit = true;
                    break;
                }
            }

            if (hit) {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 28));
                StdDraw.textLeft(50, 350, "Game Over!");
                StdDraw.show();
                break;
            }
            if (!(y + ballSize > 0 && y + ballSize < 600)) {
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 28));
                StdDraw.textLeft(50, 350, "Game Over!");
                StdDraw.show();
                break;
            }

            StdDraw.show();
            StdDraw.pause(15);
        }
    }
}
