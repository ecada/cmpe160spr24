import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
public class CityPopulationLab {
    public static void main(String[] args) {
        int num_cities = 4;
        City[] cities = new City[num_cities];
        try (Scanner sc = new Scanner(new FileInputStream("city_input.txt"))) {
            for (int i = 0; i < num_cities; i++) {
                String line = sc.nextLine();
                String[] lineSplit = line.split(" ");
                cities[i] = new City(lineSplit[0], Double.parseDouble(lineSplit[1]),
                        Double.parseDouble(lineSplit[2]), Integer.parseInt(lineSplit[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        City[] closestCities = new City[2];
        City[] farthestCities = new City[2];
        double minDistance = Double.MAX_VALUE, maxDistance = Double.MIN_VALUE;
        for (int i = 0; i < cities.length - 1; i++) {
            for (int j = i + 1; j < cities.length; j++) {
                double distance = cities[i].distanceTo(cities[j]);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCities[0] = cities[i];
                    closestCities[1] = cities[j];
                }
                if (distance > maxDistance) {
                    maxDistance = distance;
                    farthestCities[0] = cities[i];
                    farthestCities[1] = cities[j];
                }
            }
        }
        visualizeCitiesDistances(cities, closestCities, farthestCities, minDistance, maxDistance);
    }
    private static void visualizeCitiesDistances(City[] cities, City[] closestCities, City[] farthestCities,
                                                 double minDistance, double maxDistance) {

        String maxDistanceStr= String.format("Max distance: %.3f ", maxDistance);
        String minDistanceStr= String.format("Min distance: %.3f ", minDistance);
        StdDraw.setCanvasSize(400, 400);
        StdDraw.setXscale(25, 55);
        StdDraw.setYscale(10, 40);
        StdDraw.clear();
        StdDraw.setFont(new Font("Helvetica Bold", Font.BOLD, 10));
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.line(farthestCities[0].getX(),
                farthestCities[0].getY(),farthestCities[1].getX(),farthestCities[1].getY());
        StdDraw.textRight((farthestCities[0].getX()+farthestCities[1].getX())/2-0.5,
                (farthestCities[0].getY()+farthestCities[1].getY())/2,maxDistanceStr );

        StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
        StdDraw.line(closestCities[0].getX(),
                closestCities[0].getY(),closestCities[1].getX(), closestCities[1].getY());
        StdDraw.textRight((closestCities[0].getX()+closestCities[1].getX())/2-0.5,
                (closestCities[0].getY()+closestCities[1].getY())/2+2, minDistanceStr);

        for(int i = 0; i<cities.length;i++){
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledCircle(cities[i].getX(),cities[i].getY(), 1.2*cities[i].getPopulation()/20000);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(cities[i].getX(),cities[i].getY(),cities[i].getName());
        }
        StdDraw.show();
    }
}
