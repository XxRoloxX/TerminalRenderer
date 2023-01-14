public interface MixInSingleton{
         static Triangle getInstance(boolean filled, Point p1, Point p2, Point p3){
        Triangle triangle = Triangle.getInstance(filled, p1,p2,p3);
        return triangle;

    }

}
