package geometry;
import java.util.ArrayList;

public class Square extends Rectangle{
    public Square(){
        super();
    }

    public Square(double side){
        super(side,side);
    }

    public Square(double side,String colour,boolean filled,double orientation,ArrayList<Point> vertices){
        super(side,side,colour,filled,orientation,vertices);
    }

    public String toString(){
        return "A Square with side length="+this.length
        + ", which is a subclass of " + super.toString();
    }

    public void setLength(double length){
        this.length = length;
        this.width = length;
    }

    public void setWidth(double width){
        this.length = width;
        this.width = width;
    }
}
