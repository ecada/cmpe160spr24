import java.awt.*;
class CosineFunction extends Function {
    public CosineFunction(Color color) {
        super(color);
    }

    @Override
    public double evaluate(double x) {
        return Math.cos(x);
    }
}