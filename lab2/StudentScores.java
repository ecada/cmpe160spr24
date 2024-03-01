import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentScores {
    public static void main(String[] args) {
        File file = new File("students.txt");
        try {
            Scanner scanner = new Scanner(file);
            int numStudents = Integer.parseInt(scanner.nextLine());
            String[] names = new String[numStudents];
            double[] scores = new double[numStudents];
            double totalScore = 0;
            for (int i = 0; i < numStudents; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                names[i] = parts[0];
                scores[i] = Double.parseDouble(parts[1]);
                totalScore += scores[i];
            }
            scanner.close();
            double averageScore = totalScore / numStudents;
            StdDraw.setCanvasSize(800, 400);
            StdDraw.setXscale(-1, numStudents);
            StdDraw.setYscale(0, 110);
            StdDraw.setPenRadius(0.02);
            StdDraw.setFont( new Font("Helvetica Bold", Font.BOLD, 12));
            for (int i = 0; i < names.length; i++) {
                StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                StdDraw.filledRectangle(i, scores[i]/2, 0.33, scores[i]/2);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(i, 5, names[i]);
                StdDraw.text(i, scores[i] + 3, String.format("%.2f", scores[i]));
            }
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);

            StdDraw.line(-1, averageScore, names.length, averageScore);
            StdDraw.textRight(2, averageScore + 4, String.format("Average: %.2f", averageScore));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
