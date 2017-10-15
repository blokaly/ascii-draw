public interface Canvas {
    void drawLine(int x1, int y1, int x2, int y2);
    void drawRect(int x1, int y1, int x2, int y2);
    void fill(int x, int y, char color);
    void render();
}
