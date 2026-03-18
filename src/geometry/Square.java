package geometry;
import java.util.ArrayList;

public class Square extends Rectangle{
    public Square(double side, Point centre){
        super(side,side,centre);
    }

    public Square(double side,String colour,boolean filled,double orientation,ArrayList<Point> vertices){
        super(side,side,colour,filled,orientation,vertices);
    }

    public void setLength(double length){
        this.length = length;
        this.width = length;
    }

    public void setWidth(double width){
        this.length = width;
        this.width = width;
    }

    public String toString(){
        return "A Square with side length="+this.length
        + ", which is a subclass of " + super.toString();
    }
}
