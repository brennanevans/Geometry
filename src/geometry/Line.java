package geometry;
public class Line {
    Point point1;
    String colour;
    double gradient;

    public Line(Point point1, Point point2){
        this.point1 = point1;
        this.colour = "Black";
        this.gradient = (point1.y-point2.y)/(point1.x-point2.x);
        if (this.gradient == -0.0){
            gradient = 0.0;
        }
    }

    public Line(Point point1, double gradient){
        this.point1 = point1;
        this.gradient = gradient;
        this.colour = "Black";
    }

    public String getColour(){
        return this.colour;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public double getIntercept(){
        return point1.y - (gradient * point1.x);
    }

    public boolean containsPoint(Point point){
        return containsPoint(point.x,point.y);
    }

    private boolean containsPoint(double x, double y){
        double intercept = getIntercept();
        return y == (gradient*x) + intercept;
    }

    public Point getIntersectionPoint(Line line){
        double gradient1 = this.gradient;
        double intercept1 = this.getIntercept();

        double gradient2 = line.gradient;
        double intercept2 = line.getIntercept();

        if (gradient1 == gradient2){
            if (intercept1 == intercept2){
                return null;
            }
        }

        double intersectionX = (intercept2-intercept1)/(gradient1-gradient2);
        double intersectionY = (gradient1 * intersectionX) + intercept1;

        return new Point(intersectionX,intersectionY);
    }

    public boolean intersects(Line line){
        Point intersectionPoint = getIntersectionPoint(line);
        if (intersectionPoint == null){
            return false;
        } 
        if (line instanceof Ray || line instanceof Segment){
            if (!line.containsPoint(intersectionPoint)){
                return false;
            }
        }
        if (this instanceof Ray || this instanceof Segment){
            if (!this.containsPoint(intersectionPoint)){
                return false;
            }
        }

        return true;
    }

    protected Point getPerpendicularPoint(Point point){
        double a = this.gradient;
        double b = -1.0;
        double c = this.getIntercept();

        double x = (b*(b*point.x - a*point.y)-a*c)/(Math.pow(a, 2)+Math.pow(b, 2));
        double y = (a*(-b*point.x + a*point.y)-b*c)/(Math.pow(a, 2)+Math.pow(b, 2));

        return new Point(x,y);
    }

    public double distanceTo(Point point){
        // Implements Cartesian coordinates formula
        // https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line

        if (containsPoint(point)){
            return 0;
        }

        Point perpendicularPoint = getPerpendicularPoint(point);

        return Math.sqrt(Math.pow(perpendicularPoint.x-point.x, 2)+Math.pow(perpendicularPoint.y-point.y, 2));
    }

    public String toString(){
        return "A Line containing point=" + point1
        + ", with gradient=" +gradient + ",with colour="+colour;
    }
}
