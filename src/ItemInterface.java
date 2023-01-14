public interface ItemInterface {
    void setScene(char[][] sceneField, int sceneHeight, int sceneWidth);

    int compareTo(Item o);

    Point getPosition();
    void setPosition(Point p);
    void translate(Point p);
    Point[] getBoundingBox();
    void draw();

     double getPrecision();

     void setPrecision(double precision);

     char getCharToDraw();

    void setCharToDraw(char charToDraw);

    boolean isInsideScene(int x, int y);

    int getDeph();

    void setDeph(int deph);

}
