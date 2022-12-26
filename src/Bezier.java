
///quadratic Bezier curve
public class Bezier extends Primitive{

    Point[] points;

    public Bezier(Point[] points){

        this.points = points;

        this.position=GeometryUtils.getBoundingBoxAroundPoints(points)[0];
    }
    
    @Override
    public Point[] getBoundingBox() {

        return GeometryUtils.getBoundingBoxAroundPoints(points);
    }
    public void translate(Point o){
        super.translate(o);
        for(int i=0;i<points.length;i++){
            points[i].translate(o);
        }
    }

    Point bezierCurve(double t){
        return GeometryUtils.add(
                    GeometryUtils.add(
                            GeometryUtils.multiply(points[0],(Math.pow(1-t,3))),
                            GeometryUtils.multiply(points[1],3*t*Math.pow(1-t,2))
                    ),
                     GeometryUtils.add(
                             GeometryUtils.multiply(points[2],3*(1-t)*Math.pow(t,2)),
                             GeometryUtils.multiply(points[3],Math.pow(t,3))
                     )
        );
    }


    @Override
    public void draw() {

        Point pointOnCurve;

        for(double t=0;t<=1;t+=precision){

            pointOnCurve = bezierCurve(t);
            if(isInsideScene(pointOnCurve.getX(), pointOnCurve.getY())){
                sceneField[pointOnCurve.getY()][pointOnCurve.getX()]=charToDraw;
            }
        }

    }
}
