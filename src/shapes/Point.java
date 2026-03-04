package shapes;
public class Point {
    double x;
    double y;
    String colour;

    public Point(){
        this.x = 0.0;
        this.y = 0.0;
        this.colour = "Black";
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
        this.colour = "Black";
    }

    public Point(double x, double y){
        this.x = (double) Math.round(x);
        this.y = (double) Math.round(y);
        this.colour = "Black";
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
        return "A Point with x="+x+", y="+y+" and colour of " + this.colour;
    }
}
