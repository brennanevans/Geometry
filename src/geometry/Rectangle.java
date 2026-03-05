package geometry;
import java.util.ArrayList;

public class Rectangle extends Polygon{
    double width;
    double length;

    public Rectangle(){
        this.width = 1.0;
        this.length = 1.0;
    }

    public Rectangle(double width, double length){
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, String colour, boolean filled, double orientation,ArrayList<Point> vertices){
        super(colour,filled,orientation,vertices);
        this.width = width;
        this.length = length;
    }

    public double getWidth(){
        return this.width;
    }

    public double getLength(){
        return this.length;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public void setLength(double length){
        this.length = length;
    }

    public double getArea(){
        return this.width*this.length;
    }

    public double getPerimeter(){
        return (this.width+this.length)*2;
    }

    public String toString(){
        return "A Rectangle with width="+this.width+" and length="+this.length+
        ", which is a subclass of "+super.toString();
    }

}
