public class City {
    private String name;
    private double x;
    private double y;

    private int population;
    public City(String name, double x, double y, int population) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.population = population;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public String getName() {
        return name;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getPopulation() {
        return population;
    }
    public double distanceTo(City other){
        double dx= this.x-other.x;
        double dy= this.y-other.y;
        return Math.sqrt(dx*dx+dy*dy);
    }
    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", population=" + population +
                '}';
    }
}
