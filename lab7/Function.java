import java.awt.Color;

public abstract class Function {
    protected Color color;
    public  static int funcCounter=0;

    public Function(Color color) {
        this.color = color;
        funcCounter++;
    }

    public Function() {
        this(Color.magenta);
    }

    public abstract double evaluate(double x);

    public void plot(double xMin, double xMax, double width) {
        StdDraw.setPenColor(color);
        double dx = (xMax - xMin) / width;
        double x = xMin;
        double y = evaluate(x);
        while (x < xMax) {
            double nextX = x + dx;
            double nextY = evaluate(nextX);
            StdDraw.line(x, y, nextX, nextY);
            x = nextX;
            y = nextY;
            StdDraw.show();
        }

    }

}
