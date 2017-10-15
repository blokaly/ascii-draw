import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("### Ascii Draw ###");
        Scanner scanner = new Scanner(System.in);
        CmdLineProcessor lineProcessor = new CmdLineProcessor();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] cmd = line.split("\\s+");
            try {
                lineProcessor.process(cmd);
                Canvas canvas = lineProcessor.getCanvas();
                if (canvas != null) {
                    canvas.render();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
