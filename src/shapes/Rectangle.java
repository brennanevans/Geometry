package shapes;
import java.util.ArrayList;

public class Rectangle extends Polygon{
    protected double width;
    protected double length;

    private static ArrayList<Point> calculatePoints(double width, double length, Point centre){
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(centre.x-width/2,centre.y+width/2));
        points.add(new Point(centre.x-width/2,centre.y-width/2));
        points.add(new Point(centre.x+width/2,centre.y-width/2));
        points.add(new Point(centre.x+width/2,centre.y+width/2));
        return points;
    }

    public Rectangle(double width, double length, Point centre){
        super(calculatePoints(width, length, centre));
        this.width = width;
        this.length = length;
    }

    public Rectangle(double width, double length, String colour, boolean filled, double orientation, ArrayList<Point> anticlockwiseVertices){
        super(colour,filled,orientation,anticlockwiseVertices);
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
