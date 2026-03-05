package geometry;
public class Circle extends Shape{
    double radius;

    public Circle(){
        this.radius = 1.0;
    }

    public Circle(double radius){
        this.radius = radius;
    }

    public Circle(double radius, String colour, boolean filled,Point centre,double orientation){
        super(colour,filled,centre,orientation);
        this.radius = radius;
    }

    public double getRadius(){
        return this.radius;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI*radius*radius;
    }

    public double getPerimeter(){
        return Math.PI*radius*2;
    }

    public String toString(){
        return "A circle with radius="+radius+" which is a subclass of "
         + super.toString();
    }
    
}
    