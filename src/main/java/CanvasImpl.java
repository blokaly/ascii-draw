import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CanvasImpl implements Canvas {

    private static final char EMPTY_CHAR = ' ';
    private static final char H_EDGE_CHAR = '-';
    private static final char V_EDGE_CHAR = '|';
    private static final char LINE_CHAR ='x';
    private final int width;
    private final int height;
    private final char[][] pixels;

    public CanvasImpl(int width, int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("canvas width and height must be positive value");
        }
        this.width = width+2;
        this.height = height+2;
        pixels = new char[this.height][this.width];
        init();
    }

    public CanvasImpl(char[][] pixels) {
        this.width = pixels[0].length;
        this.height = pixels.length;
        this.pixels = pixels;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        ensurePositive(x1, y1, x2, y2);
        ensureWithinWidth(x1, x2);
        ensureWithinHeight(y1, y2);
        if (x1 == x2) {
            fillVerticalLine(x1, y1, y2, LINE_CHAR);
        } else if (y1 == y2) {
            fillHorizontalLine(y1, x1, x2, LINE_CHAR);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void drawRect(int x1, int y1, int x2, int y2) {
        ensurePositive(x1, y1, x2, y2);
        ensureWithinWidth(x1, x2);
        ensureWithinHeight(y1, y2);
        ensureRightGreaterThanLeft(x1, x2);
        ensureRightGreaterThanLeft(y1, y2);
        fillHorizontalLine(y1, x1, x2, LINE_CHAR);
        fillHorizontalLine(y2, x1, x2, LINE_CHAR);
        fillVerticalLine(x1, y1+1, y2-1, LINE_CHAR);
        fillVerticalLine(x2, y1+1, y2-1, LINE_CHAR);
    }

    @Override
    public void fill(int x, int y, char color) {
        ensurePositive(x, y);
        ensureWithinWidth(x);
        ensureWithinHeight(y);
        Queue<Coordinate> queue = new LinkedList<Coordinate>();
        queue.add(new Coordinate(x, y));
        while (!queue.isEmpty()) {
            floodFill(queue, color);
        }
    }

    @Override
    public void render() {
        PixelsPrinter.printLines(pixels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CanvasImpl canvas = (CanvasImpl) o;
        return Arrays.deepEquals(pixels, canvas.pixels);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(pixels);
    }

    private void ensureWithinHeight(int... parameters) {
        for (int parameter : parameters) {
            if (parameter > this.height - 2) {
                throw new IllegalArgumentException("parameter out of canvas height: " + parameter);
            }
        }
    }

    private void ensureWithinWidth(int... parameters) {
        for (int parameter : parameters) {
            if (parameter > this.width - 2) {
                throw new IllegalArgumentException("parameter out of canvas width: " + parameter);
            }
        }
    }

    private void ensureRightGreaterThanLeft(int left, int right) {
        if (left >= right) {
            throw new IllegalArgumentException("coordinate positions are wrong: (" + left + ", " + right + ")");
        }
    }

    private void init() {
        for (char[] line : pixels) {
            Arrays.fill(line, EMPTY_CHAR);
        }
        fillHorizontalLine(0, 0, width-1, H_EDGE_CHAR);
        fillHorizontalLine(height-1, 0, width-1, H_EDGE_CHAR);
        fillVerticalLine(0, 1, height-2, V_EDGE_CHAR);
        fillVerticalLine(width-1, 1, height-2, V_EDGE_CHAR);
    }

    private void ensurePositive(int... parameters) {
        for (int parameter : parameters) {
            if (parameter <= 0) {
                throw new IllegalArgumentException("parameter must be positive number: " + parameter);
            }
        }
    }

    private void fillHorizontalLine(int y, int begin, int end, char p) {
        fillLine(y, begin, end, p, true);
    }

    private void fillVerticalLine(int x, int begin, int end, char p) {
        fillLine(x, begin, end, p, false);
    }

    private void fillLine(int fixed, int begin, int end, char p, boolean horizontal) {
        if (begin > end) {
            // swap begin and end
            begin = begin ^ end;
            end = begin ^ end;
            begin = begin ^ end;
        }
        for(int i=begin; i<=end; i++) {
            if (horizontal) {
                pixels[fixed][i] = p;
            } else {
                pixels[i][fixed] = p;
            }

        }
    }

    private void floodFill(Queue<Coordinate> queue, char color) {
        Coordinate node = queue.remove();
        int x = node.getX();
        int y = node.getY();
        char currentColor = pixels[y][x];
        if (color == currentColor) {
            return;
        }
        int west  = x;
        int east  = west;
        while (west>1 && pixels[y][west-1]==currentColor) {
            --west;
        }

        while (east<this.width && pixels[y][east+1]==currentColor) {
            ++east;
        }

        for(int i=west; i<=east; i++) {
            pixels[y][i] = color;
            if ( pixels[y -1][i]==currentColor) {
                queue.add(new Coordinate(i, y - 1));
            }
            if ( pixels[y +1][i]==currentColor) {
                queue.add(new Coordinate(i, y + 1));
            }
        }
    }
}
