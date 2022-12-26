public class Main {
    public static void main(String[] args) {
        Scene scene = new Scene(500,500);
        Item circle = new Circle(false,new Point(0,0),100);
        circle.setDeph(100);
        circle.setCharToDraw('o');
        circle.translate(new Point(100,0));
        Item segment = new Segment(new Point(20,20), new Point(30,30));
        Item textItem = new TextItem("wesolych swiat xdd",new Point(50,30));
        Item rect = new Rect(new Point(70,70),false, 30, 30);
        Item tri = new Triangle(false, new Point(40,40), new Point(100,200),new Point (300,450));
        tri.setPrecision(0.01);
        Point[] bezierPoints = new Point[4];
        bezierPoints[0] = new Point(10,200);
        bezierPoints[1] = new Point(100,400);
        bezierPoints[2] = new Point(300,400);
        bezierPoints[3] = new Point(450,200);

        Item bezier = new Bezier(bezierPoints);
        bezier.translate(new Point(200,0));
        bezier.setPrecision(0.001);

        scene.addItem(circle);
        scene.addItem(segment);
        scene.addItem(textItem);
        scene.addItem(rect);
        scene.addItem(tri);
        scene.addItem(bezier);

        scene.draw();


    }
}