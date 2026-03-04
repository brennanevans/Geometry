package shapes;
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

    private double getGradient(){
        return (start.y-end.y)/(start.x-end.x);
    }

    private double getIntercept(){
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
        if (!this.pointOnSegment(x, y)){
            return false;
        }

        double gradient = getGradient();
        double intercept = getIntercept();
        return y == (gradient*x) + intercept;
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

    public String toString(){
        return "A Line with colour=" + colour + ", with start point=" + start
        + " and end point=" +end;
    }
}
