public class Main {
    public static void main(String[] args) {
        Scene scene = new Scene(100,100);
        Item circle = new Circle(false,new Point(70,70),10);
        circle.setDeph(100);
        circle.setCharToDraw('o');
        Item segment = new Segment(new Point(20,20), new Point(30,30));
        Item textItem = new TextItem("wesolych swiat xdd",new Point(50,30));
        Item rect = new Rect(new Point(70,70),false, 30, 30);
        Item tri = new Triangle(true, new Point(40,40), new Point(50,50),new Point (50,60));

        scene.addItem(circle);
        scene.addItem(segment);
        scene.addItem(textItem);
        scene.addItem(rect);
        scene.addItem(tri);

        scene.draw();


    }
}