import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MigrosDeliveryLab {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<ArrayList<Double>> locations = new ArrayList<>();
        int migrosIdx = -1;

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);
            int idx = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                ArrayList<Double> coordinates = new ArrayList<>();
                coordinates.add(Double.parseDouble(parts[0]));
                coordinates.add(Double.parseDouble(parts[1]));
                locations.add(coordinates);

                if (line.contains("Migros")) {
                    migrosIdx = idx;
                }
                idx++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
            return;
        }

        if (migrosIdx == -1) {
            System.out.println("Migros location not found.");
            return;
        }

        Integer[] initialRoute = new Integer[locations.size() - 1];
        int index = 0;
        for (int i = 0; i < locations.size(); i++) {
            if (i != migrosIdx) {
                initialRoute[index++] = i;
            }
        }

        ArrayList<Integer[]> permutations = new ArrayList<>();
        permute(permutations, initialRoute, 0);
        double minDistance = Double.MAX_VALUE;
        Integer[] bestRoute = null;
        for (Integer[] route : permutations) {
            double distance = calculateRouteDistance(route, locations, migrosIdx);
            if (distance < minDistance) {
                minDistance = distance;
                bestRoute = route;
            }
        }
        String bestRouteText=Arrays.toString(Arrays.stream(bestRoute).map(i -> i + 1).toArray(Integer[]::new));
        System.out.println("Shortest Route: Migros -> "+bestRouteText+" -> Migros" );
        System.out.println("Distance " + minDistance);
        //STDRAW---------------------------------------------------------------------------------------------------
        int canvasWidth = 800;
        int canvasHeight = 800;
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        double circleRadius = 0.02;
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("Serif", Font.BOLD, 14));
        StdDraw.textRight(0.95, 0.95, "Distance: " + String.format("%.2f", minDistance));
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.setPenRadius(0.005);
        ArrayList<Double> firstLocation = locations.get(migrosIdx);
        double locationX = firstLocation.get(0);
        double locationY = firstLocation.get(1);
        for (int idx : bestRoute) {
            ArrayList<Double> nextLocation = locations.get(idx);
            double nextLocationX = nextLocation.get(0);
            double nextLocationY = nextLocation.get(1);
            StdDraw.line(locationX, locationY, nextLocationX, nextLocationY);
            locationX = nextLocationX;
            locationY = nextLocationY;
        }


        StdDraw.line(locationX, locationY, firstLocation.get(0), firstLocation.get(1));

        for (int i = 0; i < locations.size(); i++) {
            ArrayList<Double> location = locations.get(i);
            if (i == migrosIdx) {
                StdDraw.setPenColor(StdDraw.RED);
            } else {
                StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            }
            StdDraw.filledCircle(location.get(0), location.get(1), circleRadius);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(location.get(0), location.get(1), Integer.toString(i + 1));
        }
        StdDraw.show();
        //STDRAW---------------------------------------------------------------------------------------------------
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Execution time: " + duration + " milliseconds");

    }

    //permute(permutations, initialRoute, 0);
    private static void permute(ArrayList<Integer[]> permutations, Integer[] arr, int k) {
        if (k == arr.length) {
            permutations.add(arr.clone());
        } else {
            for (int i = k; i < arr.length; i++) {
                Integer temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
                permute(permutations, arr, k + 1);
                temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
            }
        }
    }
    private static double calculateRouteDistance(Integer[] route, ArrayList<ArrayList<Double>> locations, int migrosIdx) {
        double distance = 0;
        int prevIdx = migrosIdx;
        for (int locationIdx : route) {
            distance += Math.sqrt(Math.pow(locations.get(locationIdx).get(0) - locations.get(prevIdx).get(0), 2) +
                    Math.pow(locations.get(locationIdx).get(1) - locations.get(prevIdx).get(1), 2));
            prevIdx = locationIdx;
        }
        distance += Math.sqrt(Math.pow(locations.get(migrosIdx).get(0) - locations.get(prevIdx).get(0), 2) +
                Math.pow(locations.get(migrosIdx).get(1) - locations.get(prevIdx).get(1), 2));
        return distance;
    }
}
