import java.awt.Color;
public class ExponentialFunction extends Function {
    public ExponentialFunction(Color color) {
        super(color);
    }
    @Override
    public double evaluate(double x) {
        return Math.exp(x);
    }
}