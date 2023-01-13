import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class VideoToASCII implements Video {

    final static int DEFAULT_SLEEP_TIME=17;
    private ArrayList<ImageToASCII> frames;

    //private ArrayList<Item>itemFrames;

    //private Scene scene;
    int sleepTime;
    int videoWidth;
    int videoHeight;

    Point position;

    private ImageToASCII latestFrame;

    private Iterator<ImageToASCII> framesIterator;

    public VideoToASCII(){
        frames = new ArrayList<>();
        sleepTime=DEFAULT_SLEEP_TIME;
        latestFrame = new ImageToASCII();
        position = new Point(0,0);
    }

    public void setPosition(Point position){
        this.position = new Point(position);
    }
    public boolean setSleepTime(int newSleepTime){
        if(sleepTime<0){
            return false;
        }
        else{
            this.sleepTime=newSleepTime;
            return true;
        }
    }
    public int executeComannd(String command){

        try {
            Process p = new ProcessBuilder("/bin/sh","-c",command)
                    .redirectErrorStream(true)
                    .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .start();
            p.waitFor();
            System.out.println(p.exitValue() + p.info().toString());
            return p.exitValue();
        }catch(Exception e){
            System.out.println(e);
            return 1;
        }

    }

    public String drawString(String text, String charToDraw, int imageWidth, int imageHeight, int fontSize, int xPosition, int yPosition){

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString(text, xPosition, yPosition);

        StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < imageHeight; y++) {

            for (int x = 0; x < imageWidth; x++) {
                stringBuilder.append(image.getRGB(x, y) <= -1600000 ? " ": charToDraw);
                stringBuilder.append("  ");
            }
            stringBuilder.append("\n");

        }

        return stringBuilder.toString();

    }

    public void printCenteredString(String text, String charToDraw){
        ImageToASCII.clearScreen();
        System.out.print(drawString(text, charToDraw, videoWidth, videoHeight, (int) (videoHeight * 0.6), (int) (videoWidth * 0.2), (int) (videoHeight * 0.7)));
    }
    public void displayCountdown(int time) {
        Integer number;

        if(frames.size()>0) {
            for (int i = time; i >= 0; i--) {
                ImageToASCII.clearScreen();
                number = i;
                System.out.print(drawString(number.toString(), "#", videoWidth, videoHeight, (int)( videoHeight * 0.6), (int) (videoWidth * 0.4), (int) (videoHeight * 0.7)));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
            ImageToASCII.clearScreen();
        }

    }


    public boolean createFrames(String videoName){
        //String[] cmd={"ffmpeg","-i",videoName,"-vf","fps=30","/home/wieslaw/Video/out%d.png"};
        String cmd = "ffmpeg"+" -i "+videoName +" -vf " +" fps=30 "+"frames/out%d.png";
        if(executeComannd(cmd)!=0){
            return false;
        }else{
            return true;
        }

    }
    public boolean createDirectory(String path){
        File handler = new File(path);
        return handler.mkdirs();
    }
    public boolean deleteDirectory(String path){
        File handler = new File(path);
        return handler.delete();
    }
    public void emptyDirectory(String directory){
        String cmd = "rm " +directory+"*";
        executeComannd(cmd);
    }


    public void loadVideo(String videoName){
        String outputFileName = "frames/out";
        String name;
        boolean opened=true;
        int i=0;
        System.out.println(createDirectory("frames"));
        emptyDirectory("frames/");

        if(!createFrames(videoName)){
            System.out.println("Incorrect filepath");
            return;
        };

        while(opened){
            name=outputFileName+(i+1)+".png";
            frames.add(new ImageToASCII());
            opened = frames.get(i).openFile(name);
            ImageToASCII.clearScreen();
            System.out.println(name);
            //System.out.println(opened);
            if(opened){
                frames.get(i).setPosition(position);
                frames.get(i).createASCII();
            }
            i++;
        }
        frames.remove(frames.size()-1);
        videoWidth = frames.get(0).getWidth();
        videoHeight = frames.get(0).getHeight();

        emptyDirectory("frames/");
        deleteDirectory("frames");

        framesIterator=frames.iterator();


    }

    /*
    @Override
    public ArrayList<Item> getFrames() {
        ArrayList<Item> itemFrames= new ArrayList<>();
        itemFrames.addAll(frames);
        return itemFrames;
    }

    @Override
    public Iterator<Item> iterator() {
        ArrayList<Item> itemFrames= new ArrayList<>();
        itemFrames.addAll(frames);
        return itemFrames.iterator();
    }
*/
    /*
    public void printImages() {
        for(int i=0;i<frames.size();i++){
            ImageToASCII.clearScreen();
            frames.get(i).printASCIIImage();
            try {
                Thread.sleep(sleepTime);
            }catch(InterruptedException e){

            }
        }
    }

     */

    void setFrameDeph(int deph){
        for(Item frame: frames){
            frame.setDeph(deph);
        }
    }

    @Override
    public ItemInterface getNewFrame() {
       if(framesIterator.hasNext()){
            latestFrame.copyASCIIImage(framesIterator.next());
            //System.out.println("Next frame");
            return latestFrame;
       }else{
           framesIterator = frames.iterator();
           return latestFrame;
       }
    }
}
