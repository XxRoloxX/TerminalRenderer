public class Point implements Comparable<Point>{

    public Point(int x, int y){
        this.x = x;
        this.y=y;
    }
    public Point(){
        this.x = 0;
        this.y = 0;
    }
    public Point(Point otherPoint){
        x=otherPoint.x;
        y=otherPoint.y;
    }
    public void translate(Point p){
        this.x = this.x+p.x;
        this.y = this.y+p.y;
    }
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    double getDistance(Point p){
        return Math.sqrt(Math.pow(x - p.x,2) + Math.pow(y - p.y,2) );
    }

    public void multiply(double multiplicator){
        x = (int) (x*multiplicator);
        y = (int) (y*multiplicator);
    }
    /*
    public Point multiplyReturn(double multiplicator){
        Point result = new Point(this);
        result.multiply(multiplicator);
        return result;
    }
    public Point add(Point o){
        Point result = new Point(this);
        result.translate(o);
        return result;
    }
    */

    public double getArg(){
        double r = new Point(0,0).getDistance(this);
        //(double)x/r = cos(arg);
        double arg = Math.acos((double)x/r);

        return arg;

    }

    @Override
    public int compareTo(Point o) {

        double arg1 = getArg();
        double arg2 = o.getArg();
        Point centerPoint = new Point(0,0);

        if(y<0){
            arg1 = 2*Math.PI - arg1;
        }
        if(o.y<0){
            arg2 = 2*Math.PI - arg2;
        }

        if(arg1>arg2){
            return 1;
        }else if(arg1==arg2){
            return (int) (centerPoint.getDistance(this) - centerPoint.getDistance(o));
        }else{
            return -1;
        }


    }
    public String toString(){
        return "("+x+","+y+")";
    }
}
