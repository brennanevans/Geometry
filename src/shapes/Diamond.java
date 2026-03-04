package shapes;
import java.util.ArrayList;

public class Diamond extends Polygon{
    public Diamond(){
        super();
    }

    public Diamond(ArrayList<Point> AnticlockwiseVertices){
        super(AnticlockwiseVertices);
    }

    public Diamond(String colour, boolean filled, double orientation,ArrayList<Point> AnticlockwiseVertices){
        super(colour,filled,orientation,AnticlockwiseVertices);
    }

    public String toString(){
        return "A Diamond which is a subclass of "+super.toString();
    }
}
