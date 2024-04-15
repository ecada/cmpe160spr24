import java.awt.Color;
public class TangentFunction extends Function {
    public TangentFunction(Color color) {
        super(color);
    }

    @Override
    public double evaluate(double x) {
        return Math.tan(x);
    }
}