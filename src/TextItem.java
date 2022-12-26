public class TextItem extends Item {

    String text;

    public TextItem(){
        text="";
    }
    public TextItem(String text, Point position){
        super(position);
        this.text = text;
    }

    @Override
    public Point[] getBoundingBox() {

        Point[] boundingBox = new Point[4];
        boundingBox[0] = new Point(position.getX(),position.getY());
        boundingBox[1] = new Point(position.getX()+text.length(), position.getY());
        boundingBox[2] = new Point(position.getX(),position.getY());
        boundingBox[3] = new Point(position.getX()+text.length(), position.getY());

        return boundingBox;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void draw() {
        for(int i=0;i<text.length();i++){
            sceneField[position.getY()][position.getX()+i]=text.charAt(i);
        }
    }
}
