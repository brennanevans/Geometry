package geometry;
import java.util.ArrayList;

public class Triangle extends Polygon{
    public Triangle(ArrayList<Point> AnticlockwiseVertices){
        super(AnticlockwiseVertices);
    }

    public Triangle(String colour, boolean filled, double orientation,ArrayList<Point> AnticlockwiseVertices){
        super(colour, filled,orientation,AnticlockwiseVertices);
    }

    public String toString(){
        return "A Triangle which is a subclass of "+super.toString();
    }
}
