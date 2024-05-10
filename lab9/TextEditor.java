import java.awt.Font;

public class TextEditor {
    private static final double TEXT_X = 0.08; // X-coordinate for text
    private static final double TEXT_Y = 0.6; // Y-coordinate for text
    private static final double CHAR_WIDTH = 0.02;
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15); // Font for text

    private static LinkedList<Character> textList = new LinkedList<>(); // Linked list to store characters
    private static int cursorPosition = 0; // Cursor position in the text
    private static boolean cursorVisible = true; // Variable to toggle cursor visibility

    public static void main(String[] args) {
        StdDraw.setCanvasSize(600, 200);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        StdDraw.enableDoubleBuffering();

        StdDraw.setFont(TEXT_FONT);

        while (true) {
            StdDraw.clear();
            drawBorder();
            drawText();
            drawCursor();
            checkInput();

            // Show the updated canvas
            StdDraw.show();
            StdDraw.pause(20);
        }
    }

    private static void drawText() {
        double x = TEXT_X;
        Node<Character> current = textList.getHead(); // Get the head of the linked list
        StdDraw.setPenColor(StdDraw.BLACK); // Set color for text
        while (current != null) {
            StdDraw.text(x, TEXT_Y, String.valueOf(current.data));
            x += CHAR_WIDTH; // Adjust this value according to the font size
            current = current.next; // Move to the next node
        }
    }


    private static void drawCursor() {
        // Toggle cursor visibility every 0.5 seconds
        if (System.currentTimeMillis() % 1000 < 500) {
            cursorVisible = true;
        } else {
            cursorVisible = false;
        }

        if (cursorVisible) {
            double cursorX = TEXT_X + cursorPosition * CHAR_WIDTH; // Calculate cursor position based on character width
            double cursorY = TEXT_Y - 0.02; // Adjust this value according to the font size
            StdDraw.setPenColor(StdDraw.BLACK); // Set color for cursor
            StdDraw.line(cursorX, cursorY, cursorX, cursorY + 0.04); // Draw the cursor
        }
    }


    private static void checkInput() {
        if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            switch (key) {
                case '\b': // Backspace
                    if (cursorPosition > 0) {
                        textList.remove(cursorPosition - 1);
                        cursorPosition--;
                    }
                    break;
                case '\n': // Newline
                    break;
                default:
                    textList.insert(cursorPosition++, key);
                    break;
            }
        }
    }

    private static void drawBorder() {
        StdDraw.setPenColor(StdDraw.BLUE); // Set color for border
        StdDraw.rectangle(0.5, 0.5, 0.5, 0.45); // Draw a rectangle as a border
    }

}
