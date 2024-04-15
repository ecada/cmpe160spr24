import java.awt.Color;
public class FunctionPlotter {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double X_MIN = -Math.PI *2;
    private static final double X_MAX = Math.PI *2;
    private static final double Y_MIN = -6;
    private static final double Y_MAX = 6;

    public static void main(String[] args) {
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(X_MIN, X_MAX);
        StdDraw.setYscale(Y_MIN, Y_MAX);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(X_MIN, 0, X_MAX, 0);
        StdDraw.line( 0,Y_MIN,0, Y_MAX);

        Function[] functions = new Function[4];
        functions[0] = new SineFunction(Color.BLUE);
        functions[1] = new CosineFunction(Color.RED);
        functions[2] = new TangentFunction(Color.GREEN);
        functions[3] = new ExponentialFunction(Color.ORANGE);
        for (Function f : functions) {
            f.plot(X_MIN,X_MAX,WIDTH);

        }
    }
}
