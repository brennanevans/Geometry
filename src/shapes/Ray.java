package shapes;

public class Ray extends Line{
    private Point start; 
    private boolean shotLeft;

    public Ray(Point start, Point point2){
        super(start,point2);

        if (point2.x < start.x){
            this.shotLeft = true;
        }
        
        this.start = start; 
    }

    public Ray(Point start, double gradient, boolean shotLeft){
        super(start, gradient);
        this.start = point1;
        this.shotLeft = shotLeft;
    }

    public Point getStart(){
        return this.start;
    }

    public boolean containsPoint(Point point){
        if (!super.containsPoint(point)){
            return false;
        }
        // System.out.println(this);
        // System.out.println(point);
        // System.out.println(" ");


        if (shotLeft){
            return point.x < this.start.x;
        } else{
            return point.x > this.start.x;
        }
    }

    public String toString(){
        String direction = this.shotLeft ? "left" : "right";
        return "A Ray with start point=" + point1 + ", gradient=" +gradient + 
        ", which has been shot " + direction + " and with colour=" + colour;
    } 
}
