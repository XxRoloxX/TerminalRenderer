public class BoundingBoxDecorator extends ItemDecorator{
    public BoundingBoxDecorator(ItemInterface decoratedItem){
        super(decoratedItem);
    }
    @Override
    public void draw(){
        decoratedItem.draw();

        Point[] boundingBox = decoratedItem.getBoundingBox();
        Item rect = new Rect(boundingBox[0],false,boundingBox[1].getX()-boundingBox[0].getX(),boundingBox[2].getY()-boundingBox[0].getY());
        rect.sceneField = decoratedItem.getSceneField();
        rect.sceneHeight = decoratedItem.getSceneField().length;
        rect.sceneWidth = decoratedItem.getSceneField()[0].length;
        rect.draw();
    }
}
