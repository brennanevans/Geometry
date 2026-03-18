package geometry;
public class Point {
    double x;
    double y;
    String colour;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
        this.colour = "Black";
    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
        this.colour = "Black";
    }

    public Point(double x, double y, String colour){
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    public boolean equals(Object object){
        if (!(object instanceof Point)){
            return false;
        }else{
            Point otherPoint = (Point) object;
            return (this.x==otherPoint.x)&&(this.y==otherPoint.y);
        }
    }

    public String getColour(){
        return this.colour;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public String toString(){
        return "A Point with coordinates ("+x+","+y+") and colour=" + this.colour;
    }
}
