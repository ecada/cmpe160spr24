import java.awt.*;

class SineFunction extends Function {
    public SineFunction(Color color) {
        super(color);
    }

    @Override
    public double evaluate(double x) {
        return Math.sin(x);
    }
}
