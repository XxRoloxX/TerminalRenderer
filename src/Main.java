import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void christmasCard(){
        Scene scene = new Scene(400,300);
        Item segment = new Segment(new Point(50,50),new Point(100,100));
        ///segment.setPrecision(2);
        Item circle = new Circle(false,new Point(200,30),30);

        Item snowmanHead = new Circle(false, new Point(300,70),20);
        Item snowmanBody = new Circle(false, new Point(290,110),30);
        Item snowmanLegs = new Circle(false, new Point(280,170),40);
        Item snowmanFace = new ComplexItem();
        snowmanFace.setDeph(1000);

        Point[] mouthPoints = new Point[4];
        mouthPoints[0] = new Point(305,90);
        mouthPoints[1] = new Point(315,100);
        mouthPoints[2] = new Point(325,100);
        mouthPoints[3] = new Point(335,90);
        Item snowmanMouthBezier = new Bezier(mouthPoints);
        snowmanMouthBezier.setPrecision(0.01);
        Item snowmanLeftEye = new Circle(false,new Point(305,75),5);
        Item snowmanRightEye = new Circle(false, new Point(315,75),5);
        Item snowmanNose = new Triangle(true, new Point(292,69), new Point(313,84), new Point(317,93));
        snowmanNose.setCharToDraw('?');
        snowmanNose.setDeph(1000);


        ((ComplexItem)snowmanFace).addItem(snowmanMouthBezier);
        ((ComplexItem)snowmanFace).addItem(snowmanLeftEye);
        ((ComplexItem)snowmanFace).addItem(snowmanRightEye);
        ((ComplexItem)snowmanFace).addItem(snowmanNose);

        Item hatBase = new Rect(new Point(290,65),true,60,10);
        //Item hatBase = new Rect(snowmanHead.getPosition().getX())
        Item hatTop = new Rect(new Point(305,35),true,30,30);
        hatBase.setCharToDraw('=');
        hatTop.setCharToDraw('=');




        Item text = new TextItem(new Point(10,200),"Wesołych świat xdd", 20);

        Item triangle = new Triangle(false, new Point(90,150), new Point(30,175),new Point(50,100));
        triangle.setPrecision(0.01);
    /*
        Item tri = new Triangle(false, new Point(40,40), new Point(100,200),new Point (300,450));
        tri.setPrecision(0.01);
    */

        scene.addItem(segment);
        scene.addItem(circle);
        scene.addItem(snowmanHead);
        scene.addItem(snowmanBody);
        scene.addItem(snowmanLegs);
        scene.addItem(snowmanFace);
        scene.addItem(text);
        scene.addItem(triangle);

        scene.addItem(hatBase);
        scene.addItem(hatTop);


        scene.draw();
    }
    public static void cubeTest()throws InterruptedException{
        Scene scene = new Scene(200,200);
        Item cube = new Cube(true, new Point(100,100),70);
        scene.addItem(cube);
        double rot=0.05;
        double sum =0.1;
        while(true){
            System.out.println(sum);
            ((Cube)cube).rotatePointsOnYAxis(rot);
            ((Cube)cube).rotatePointsOnZAxis(rot);
            ((Cube)cube).rotatePointsOnXAxis(rot);
            scene.clearScene();
            Scene.clearScreen();
            scene.draw();
            sum+=0.1;
            Thread.sleep(100);
        }

    }

    public static void tetrahedronTest() throws InterruptedException {

        //Tetrahedron tetrahedron = new Tetrahedron(true,new Point3D(50,60,-20), new Point3D(100,110,100), new Point3D(70,90,-50), new Point3D(150,110,70));
        Tetrahedron tetrahedron = new Tetrahedron(true,new Point3D(150,50,100), new Point3D(150,150,100), new Point3D(50,100,100), new Point3D(100,100,50));

        AnimatedScene scene = new AnimatedScene(300,200);
        SolidRotation tetrahedronAnimation = new SolidRotation(tetrahedron,5000);
        tetrahedronAnimation.setRotationAngles(0.1,0.1,0.1);
        scene.addVideo(tetrahedronAnimation);
        scene.drawFrames();




    }
    public static  void animationTest()throws InterruptedException{
        Cube cube3 = new Cube(true, new Point(150,50),30);
        Cube cube2 = new Cube(true, new Point(100,100),50);
        Cube cube1 = new Cube(true, new Point(50,50),30);
        Tetrahedron tetrahedron1 = new Tetrahedron(true,new Point3D(150,50,100), new Point3D(150,150,100), new Point3D(50,100,100), new Point3D(100,100,50) );


        AnimatedScene animatedScene = new AnimatedScene(300,200);

        SolidRotation cube1Animation = new SolidRotation(cube1,2);
        SolidRotation cube2Animation = new SolidRotation(cube2,2);
        SolidRotation cube3Animation = new SolidRotation(cube3,2);
        SolidRotation tetrahydron1Rotation = new SolidRotation(tetrahedron1,2);
        SolidTranslation cube4Animation = new SolidTranslation(cube3,2);
        SolidTranslation cube5Animation = new SolidTranslation(new Point(-1,1),cube2,2);
        SolidTranslation cube6Animation = new SolidTranslation(new Point(-1,-1),cube1,2);
        SolidTranslation tetrahydron1Translation = new SolidTranslation(new Point(1,1),tetrahedron1,2);




        cube1Animation.setRotationAngles(0.1,0.1,0.1);
        cube2Animation.setRotationAngles(-0.1,0.1,0.1);
        cube3Animation.setRotationAngles(-0.1,0.1,-0.1);
        tetrahydron1Rotation.setRotationAngles(0.1,0.1,0.1);

        VideoToASCII videoBackground = new VideoToASCII();
        videoBackground.loadVideo("/home/wieslaw/Videos/simplescreenrecorder-2022-12-27_16.57.50.mp4");


        animatedScene.addVideo(videoBackground);
        animatedScene.addVideo(cube1Animation);
       // animatedScene.addVideo(cube2Animation);
        animatedScene.addVideo(cube3Animation);
        animatedScene.addVideo(cube4Animation);
       // animatedScene.addVideo(cube5Animation);
        animatedScene.addVideo(cube6Animation);
        animatedScene.addVideo(tetrahydron1Translation);
        animatedScene.addVideo(tetrahydron1Rotation);



        animatedScene.drawFrames();

    }
    public static void main(String[] args) throws InterruptedException {

        animationTest();
        //christmasCard();
        //tetrahedronTest();
    }
}