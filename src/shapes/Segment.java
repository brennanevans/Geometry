package shapes;

public class Segment extends Line{
    private Point start;
    private Point end;

    public Segment(Point start, Point end){
        super(start,end);
        this.start = start;
        this.end = end;
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

    public boolean containsPoint(Point point){
        if (!super.containsPoint(point)){
            return false;
        }

        double maxX = Math.max(this.start.x, this.end.x);
        double minX = Math.min(this.start.x, this.end.x);

        double maxY = Math.max(this.start.y, this.end.y);
        double minY = Math.min(this.start.y, this.end.y);

        return (point.x>minX)&&(point.x<maxX)&&(point.y>minY)&&(point.y<maxY);
    }

    public double distanceTo(Point point){
        // Implements Cartesian coordinates formula
        // https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line

        // Finds point for perpendicular distance and, if it is not
        // part of the edge making up the shape, uses the end point of
        // the line (the vertex) as this is the next closest point.

        Point perpendicularPoint = getPerpendicularPoint(point);

        if (this.containsPoint(perpendicularPoint)){
            // Finds perpendicular distance from point to full line
            return super.distanceTo(point);
        }else{
            // Returns the closer of the two end points of the line
            double x = perpendicularPoint.x;
            double y = perpendicularPoint.y;
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
        return "A Segment with start point=" + start + ", end point=" + end + ", with colour="+colour;
    }

}
