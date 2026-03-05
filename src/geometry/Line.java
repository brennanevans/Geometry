package geometry;
public class Line {
    Point start;
    Point end;
    String colour;

    public Line(Point start, Point end){
        this.start = start;
        this.end = end;
        this.colour = "Black";
    }

    public String getColour(){
        return this.colour;
    }

    public Point getStart(){
        return this.start;
    }

    public Point getEnd(){
        return this.end;
    }

    public double getLength(){
        double x_diff = start.x-end.x;
        double y_diff = start.y-end.y;
        return Math.sqrt(Math.pow(x_diff,2)+Math.pow(y_diff,2));
    }

    public void setStart(Point start){
        this.start = start;
    }

    public void setEnd(Point end){
        this.end = end;
    }

    public double getGradient(){
        return (start.y-end.y)/(start.x-end.x);
    }

    public double getIntercept(){
        double gradient = getGradient();
        return start.y - (gradient * start.x);
    }

    private boolean pointOnSegment(double x, double y){
        double maxX = Math.max(this.start.x, this.end.x);
        double minX = Math.min(this.start.x, this.end.x);

        double maxY = Math.max(this.start.y, this.end.y);
        double minY = Math.min(this.start.y, this.end.y);

        return (x>minX)&&(x<maxX)&&(y>minY)&&(y<maxY);
    }

    public boolean containsPoint(Point point){
        return containsPoint(point.x,point.y);
    }

    private boolean containsPoint(double x, double y){
        double gradient = getGradient();
        double intercept = getIntercept();
        return y == (gradient*x) + intercept;
    }

    public boolean pointRayIntersection(Point point){
        if (!containsPoint(point)){
            return false;
        }
        if (this.end.x < this.start.x){
            //If shot left
            return point.x < this.start.x;
        } else{
            //If shot right
            return point.x > this.start.x;
        }
    }

    public boolean rayLineIntersection(Line ray){
        double gradient1 = this.getGradient();
        double intercept1 = this.getIntercept();

        double gradient2 = ray.getGradient();
        double intercept2 = ray.getIntercept();

        double intersectionX = (intercept2-intercept1)/(gradient1-gradient2);
        double intersectionY = (gradient1 * intersectionX) + intercept1;

        // If intersection point not part of shape edge
        if (!this.pointOnSegment(intersectionX, intersectionY)){
            return false;
        }

        if (ray.end.x < ray.start.x){
            //If shot left
            return intersectionX < ray.start.x;
        } else{
            //If shot right
            return intersectionX > ray.start.x;
        }
    }

    public boolean intersects(Line line){
        double gradient1 = this.getGradient();
        double intercept1 = this.getIntercept();

        double gradient2 = line.getGradient();
        double intercept2 = line.getIntercept();

        if (gradient1 == gradient2){
            return intercept1 == intercept2;
        } else{
            double intersectionX = (intercept2-intercept1)/(gradient1-gradient2);
            double intersectionY = (gradient1 * intersectionX) + intercept1;
            
            if (!line.pointOnSegment(intersectionX, intersectionY)){
                return false;
            }

            if (containsPoint(intersectionX,intersectionY)){
                return true;
            }
            return false;
        }
    }

    public double distanceTo(Point point){
        double a = this.getGradient();
        double b = -1.0;
        double c = this.getIntercept();

        // Finds point for perpendicular distance and, if it is not
        // part of the edge making up the shape, uses the end point of
        // the line (the vertex) as this is the next closest point.

        double x = (b*(b*point.x - a*point.y)-a*c)/(Math.pow(a, 2)+Math.pow(b, 2));
        double y = (a*(-b*point.x + a*point.y)-b*c)/(Math.pow(a, 2)+Math.pow(b, 2));

        if (this.pointOnSegment(x, y)){
            // Finds perpendicular distance from point to full line
            return Math.abs((a*point.x)+(b*point.y)+c)/(Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2)));
        }else{
            // Returns the closer of the two end points of the line
            if (Math.abs(x-this.start.x)<Math.abs(x-this.end.x)){
                x = this.start.x;
                y = this.start.y;
            } else{
                x = this.end.x;
                y = this.end.y;
            }

            return Math.sqrt(Math.pow(x-point.x, 2)+Math.pow(y-point.y, 2));
        }
    }

    public String toString(){
        return "A Line with colour=" + colour + ", with start point=" + start
        + " and end point=" +end;
    }
}
