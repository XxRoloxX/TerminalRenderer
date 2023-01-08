public class SolidRotation extends ObjectAnimation{

    private double xAxisRotation;
    private double yAxisRotation;
    private double zAxisRotation;

    public SolidRotation(Solid cube, int length){
        super(cube, length);
         xAxisRotation = 0;
         yAxisRotation=0;
         zAxisRotation=0;
    }
    public void setRotationAngles(double x, double y, double z){
        xAxisRotation=x;
        yAxisRotation=y;
        zAxisRotation=z;
    }

    protected void animationStep() {
        ((Solid)itemToAnimate).rotatePointsOnYAxis(xAxisRotation);
        ((Solid)itemToAnimate).rotatePointsOnXAxis(yAxisRotation);
        ((Solid)itemToAnimate).rotatePointsOnZAxis(zAxisRotation);
    }



}
