public class PixelsPrinter {

    public static void printLines(char[][] lines) {
        for (char[] line : lines) {
            printLine(line);
        }
    }

    public static void printLine(char[] line) {
        for (char c : line) {
            System.out.print(c);
        }
        System.out.println();
    }
}
