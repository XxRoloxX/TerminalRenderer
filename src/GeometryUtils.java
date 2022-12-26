public class GeometryUtils {

    final static double DEFAULT_PRECISION=0.5;
    final static char DEFAULT_CHAR = '#';
    public static double distanceBetweenPoints(Point p1, Point p2){
       return  p1.getDistance(p2);
    }

    public static double distanceBetweenCoordinates(int x1, int y1, int x2, int y2){
        Point p1 = new Point(x1,y1);
        Point p2 = new Point(x2,y2);
        return p1.getDistance(p2);
    }
    public static Point[] getBoundingBoxAroundPoints(Point[] points){
        int left = points[0].getX();
        int right = points[0].getX();
        int top = points[0].getY();
        int bottom = points[0].getY();

        for(int i=1;i<points.length;i++){
            if(points[i].getX()<left){
                left = points[i].getX();
            }
            if(points[i].getX()>right){
                right = points[i].getX();
            }
            if(points[i].getY()<top){
                top = points[i].getY();
            }
            if(points[i].getX()>bottom){
                bottom = points[i].getY();
            }

        }

        Point[] boundingBox = new Point[4];
        boundingBox[0] = new Point(left,top);
        boundingBox[1] = new Point(right,top);
        boundingBox[2] = new Point(left, bottom);
        boundingBox[3] = new Point(right, bottom);

        return boundingBox;
    }

    public static Point add(Point p1, Point p2){
        Point result = new Point(p1.getX()+ p2.getX(),p1.getY()+ p2.getY());
        return result;
    }
    public static Point multiply(Point p1, double multiplicator){
        Point result = new Point(p1);
        result.multiply(multiplicator);
        result.multiply(multiplicator);
        return result;
    }
    public static Point subtract(Point p1, Point p2){
        Point result = new Point(p2.getX()-p1.getX(), p2.getY() - p1.getY());
        return result;
    }
    public static double crossProduct(Point p1, Point p2){
        return p1.getX()*p2.getY() - p1.getY()*p2.getX();
    }


}
