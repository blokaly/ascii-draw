public class CmdLineProcessor {
    private Canvas canvas;

    public void process(String[] cmd) {
        switch (cmd[0].toUpperCase()) {
            case "C":
                if (cmd.length != 3) {
                    throw new IllegalArgumentException("expecting 2 parameters for create canvas command");
                }
                createCmd(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                break;
            case "L":
                if (canvas == null) {
                    throw new IllegalStateException("canvas not created");
                }
                if (cmd.length != 5) {
                    throw new IllegalArgumentException("expecting 4 parameters for draw line command");
                }
                lineCmd(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
                break;
            case "R":
                if (canvas == null) {
                    throw new IllegalStateException("canvas not created");
                }
                if (cmd.length != 5) {
                    throw new IllegalArgumentException("expecting 4 parameters for draw rectangle command");
                }
                rectCmd(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]),Integer.parseInt(cmd[3]),Integer.parseInt(cmd[4]));
                break;
            case "B":
                if (canvas == null) {
                    throw new IllegalStateException("canvas not created");
                }
                if (cmd.length != 4) {
                    throw new IllegalArgumentException("expecting 3 parameters for draw rectangle command");
                }
                fillCmd(Integer.parseInt(cmd[1]),Integer.parseInt(cmd[2]), cmd[3].charAt(0));
                break;
            case "Q":
                System.exit(0);

        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void fillCmd(int x, int y, char color) {
        canvas.fill(x, y, color);
    }

    private void rectCmd(int x1, int y1, int x2, int y2) {
        canvas.drawRect(x1, y1, x2, y2);
    }

    private void lineCmd(int x1, int y1, int x2, int y2) {
        canvas.drawLine(x1, y1, x2, y2);
    }

    private void createCmd(int width, int height) {
        canvas = new CanvasImpl(width, height);
    }
}
