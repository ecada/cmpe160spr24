import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class MigrosDelivery {
    private final Map<Integer, Map<Integer, Double>> distancesMap;
    private final int migrosIdx;
    public record Result(Integer[] shortestRoute, double minDistance) {
    }
    public MigrosDelivery(Map<Integer, Map<Integer, Double>> distancesMap,int migrosIdx) {
        this.distancesMap = distancesMap;
        this.migrosIdx=migrosIdx;
    }
    public static Integer[] addElement(Integer[] arr, Integer element, int position)
    {
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        list.add(position , element);
        return list.toArray(arr);
    }
    public static void swapArrayElements(Integer[] array, int valAidx, int valBidx) {
        int tmp = array[valAidx];
        array[valAidx] = array[valBidx];
        array[valBidx] = tmp;
    }
    public static double computeDistanceBetweenLocations(double x_1, double y_1, double x_2, double y_2) {
        return Math.sqrt(Math.pow(y_2 - y_1,2) + Math.pow(x_2 - x_1,2));
    }
    public double computeTotalRouteDistance(Integer[] route) {
        double distance = 0;
        for (int i = 0; i < route.length-1; i++) {
            distance += distancesMap.get(route[i]).get(route[i+1]);
        }
        return distance;
    }
    private void findRoutes(List<Integer[]> permutationsArray,Integer[] permutation, int idx) {
        if (idx == 0) {
            permutation=addElement(permutation,migrosIdx,0);
            permutation = Arrays.copyOf(permutation, permutation.length + 1);
            permutation[permutation.length - 1] = permutation[0];
            permutationsArray.add(permutation);
            return;
        }
        for (int i = 0; i < idx; i++) {
            swapArrayElements(permutation, i, idx - 1);
            findRoutes(permutationsArray, permutation,idx - 1);
            swapArrayElements(permutation, i, idx - 1);
        }
    }


    public Result findShortestRoute(Integer[] Locations) {
        Integer[] shortestRoute = null;
        double minDistance = Double.MAX_VALUE;
        swapArrayElements(Locations,migrosIdx-1,0);
        Integer[] LocationsWithoutMigros = Arrays.copyOfRange(Locations, 1, Locations.length);
        List<Integer[]> routes = new ArrayList<>();
        findRoutes(routes, LocationsWithoutMigros, LocationsWithoutMigros.length);
        for (Integer[] route : routes) {
            double distance = computeTotalRouteDistance(route);
            if (distance < minDistance) {
                minDistance = distance;
                shortestRoute = route;
            }
        }
        return new Result(shortestRoute,minDistance);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<Location> locationList = new ArrayList<>();
        Map<Integer, Map<Integer, Double>> distancesLinkedHashMap = new LinkedHashMap<>();
        int locationIdx=1;
        int migrosIdx= 0;
        try {
            FileInputStream fis=new FileInputStream("input.txt");
            Scanner sc=new Scanner(fis);
            while(sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] lineSplit = line.split(",");
                String locationIdxx = Integer.toString(locationIdx);
                double x = Double.parseDouble(lineSplit[0]);
                double y = Double.parseDouble(lineSplit[1]);
                if (line.endsWith("Migros")){
                    migrosIdx=locationIdx;
                    locationList.add(new Location(Integer.parseInt(locationIdxx),x,  y,StdDraw.BOOK_LIGHT_BLUE));
                }else{
                    locationList.add(new Location(Integer.parseInt(locationIdxx),x,  y,StdDraw.LIGHT_GRAY));
                }
                locationIdx++;
            }
            sc.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        for (Location locationCurrent : locationList) {
            Map<Integer, Double> locationMap = new LinkedHashMap<>();
            distancesLinkedHashMap.put(locationCurrent.getidx(), locationMap);
            for (Location locationNext : locationList) {
                if (locationCurrent != locationNext) {
                    double distBetweenLocationNextLocation=computeDistanceBetweenLocations(locationNext.getX(), locationNext.getY(), locationCurrent.getX(), locationCurrent.getY());
                    locationMap.put(locationNext.getidx(), distBetweenLocationNextLocation);
                }
            }
        }
        MigrosDelivery MigrosDelivery = new MigrosDelivery(distancesLinkedHashMap,migrosIdx);
        Result result = MigrosDelivery.findShortestRoute(MigrosDelivery.distancesMap.keySet().toArray(new Integer[0]));
        Integer [] shortestRoute= result.shortestRoute();
        double minDistance= result.minDistance();
        System.out.println("Shortest Route: " + Arrays.toString(shortestRoute));
        System.out.println("Distance " + minDistance);

        //STDRAW
        int canvas_width = 800;
        int canvas_height = 800;
        StdDraw.setCanvasSize(canvas_width, canvas_height);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        double circle_radius = 0.02;
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont( new Font("Serif", Font.BOLD, 14) );
        StdDraw.textRight(0.95, 0.95, "Distance " + result.minDistance);
        StdDraw.textRight(0.95, 0.98, "Shortest Route: " + Arrays.toString(shortestRoute));
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.setPenRadius(0.005);
        double location_x= locationList.get(shortestRoute[0] - 1).getX();
        double location_y= locationList.get(shortestRoute[0] - 1).getY();
        for (int i = 1; i < shortestRoute.length; i++){
            double nextLocation_x= locationList.get(shortestRoute[i]-1).getX();
            double nextLocation_y= locationList.get(shortestRoute[i]-1).getY();
            StdDraw.line(location_x, location_y, nextLocation_x, nextLocation_y);
            location_x=nextLocation_x;
            location_y=nextLocation_y;
        }
        for (Location location: locationList){
            StdDraw.setPenColor(location.getColor());
            StdDraw.filledCircle(location.getX(), location.getY(), circle_radius);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setFont( new Font("Serif", Font.BOLD, 12));
            StdDraw.text(location.getX(), location.getY(), Integer.toString(location.getidx()));
        }
        StdDraw.show();
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);  // Duration in milliseconds
        System.out.println("Execution time: " + duration + " milliseconds");
    }

}