package geometry;
import java.util.ArrayList;

public class Polygon extends Shape {
    private ArrayList<Point> anticlockwiseVertices;

    public Polygon(ArrayList<Point> anticlockwiseVertices){
        super();
        this.anticlockwiseVertices = anticlockwiseVertices;
        setCentre();
    }

    public Polygon(String colour, boolean filled, double orientation,ArrayList<Point> anticlockwiseVertices){
        super(colour, filled, new Point(0,0), orientation);
        this.anticlockwiseVertices = anticlockwiseVertices;
        setCentre();
    }

    public ArrayList<Point> getPoints(){
        return this.anticlockwiseVertices;
    }

    public ArrayList<Segment> getEdges(){
        ArrayList<Segment> edgeArray = new ArrayList<Segment>();

        int otherIndex;
        for (int i=0;i<anticlockwiseVertices.size();i++){
            if (i == anticlockwiseVertices.size()-1){
                otherIndex = 0;
            } else{
                otherIndex = i+1;
            }
            edgeArray.add(new Segment(anticlockwiseVertices.get(i), anticlockwiseVertices.get(otherIndex)));
        }
        return edgeArray;
    }

    public Point calculateCentroid(){
        // https://en.wikipedia.org/wiki/Centroid#Of_a_polygon
        double area = getArea();
        double edgeSummationX = 0;
        double edgeSummationY = 0;

        ArrayList<Segment> edgeArray = getEdges();

        for (Segment edge : edgeArray){
            double x1 = edge.getStart().x;
            double x2 = edge.getEnd().x;

            double y1 = edge.getStart().y;
            double y2 = edge.getEnd().y;

            edgeSummationX += (x1+x2)*((x1*y2)-(x2*y1));
            edgeSummationY += (y1+y2)*((x1*y2)-(x2*y1));
        }
        double centroidX = edgeSummationX/(6*area);
        double centroidY = edgeSummationY/(6*area);

        return new Point(centroidX,centroidY);
    }

    public Point calculateVertexCentroid(){
        // https://en.wikipedia.org/wiki/Centroid#Of_a_polygon
        double xSummation = 0;
        double ySummation = 0;
        int n = anticlockwiseVertices.size();

        for (Point point : anticlockwiseVertices){
            xSummation += point.x;
            ySummation += point.y;
        }
        
        double centroidX = (xSummation * 1/n);
        double centroidY = (ySummation * 1/n);
        return new Point(centroidX,centroidY);
    }

    public void setCentre(double x, double y){
        ArrayList<Point> newPoints = new ArrayList<Point>();
        for (Point point : anticlockwiseVertices){
            double xOffset = this.getCentre().x - point.x;
            double yOffset = this.getCentre().y - point.y;
            newPoints.add(new Point(x-xOffset,y-yOffset));
        }
        anticlockwiseVertices = newPoints;
    }

    private void setCentre(){
        Point centroid = calculateCentroid();
        super.setCentre(centroid.x,centroid.y);
    }

    public double getPerimeter(){
        double perimeter = 0;
        ArrayList<Segment> edgeArray = getEdges();
        for (Segment edge : edgeArray){
            perimeter += edge.getLength();
        }
        return perimeter;
    }

    public double getArea(){
        // https://en.wikipedia.org/wiki/Shoelace_formula#Trapezoid_formula

        double total = 0;
        ArrayList<Segment> edgeArray = getEdges();
        for (Segment edge : edgeArray){
            total+=0.5*((edge.getStart().y+edge.getEnd().y)*(edge.getStart().x-edge.getEnd().x));
        }
        return Math.abs(total);
    }

    private boolean rayContainsVertex(Ray ray){
        for (Point vertex : anticlockwiseVertices){
            if (ray.containsPoint(vertex)){
                return true;
            }
        }
        return false;
    }

    private Ray generateTestRay(Point point){
        // Shoot horizontal ray one way only (right)
        Ray testRay = new Ray(point,0,false);
        if (rayContainsVertex(testRay)){
            // If hits vertex shoot the other way
            testRay = new Ray(point,0,true);
            if (rayContainsVertex(testRay)){
                return null;
            }
        }
        return testRay;
    }

    public boolean contains(Point point){
        if (anticlockwiseVertices.contains(point)){
            return true;
        } 

        ArrayList<Segment> edgeArray = getEdges();
        int intersectionCounter = 0;

        for (Segment edge : edgeArray){
            if (edge.containsPoint(point)){
                return true;
            }
        }
        // If not filled and point not on edge, shape considered to not contain
        //  point
        if (!isFilled()){
            return false;
        } else{
            Ray testRay = generateTestRay(point);
            if (testRay == null){
                // Ray hit verticies in both directions so point inbetween 
                // vertices. If between two verticies must be inside shape for
                // simple polygons.
                return true;
            }
            
            for (Segment edge : edgeArray){
                intersectionCounter += testRay.intersects(edge) ? 1 : 0;
            }

            return (intersectionCounter != 0)&&(intersectionCounter%2!=0); 
        }
    }

    public double distance(Point point){
        if (this.contains(point)){
            return 0;
        }

        ArrayList<Segment> edgeArray = getEdges();
        double minimum = -1;

        for (Segment edge : edgeArray){
            double distance = edge.distanceTo(point);
            minimum = minimum == -1 ? distance : Math.min(distance, minimum);
        }
        
        return minimum;
    }

    public String toString(){
        return "A Polygon with vertices" + anticlockwiseVertices.toString() +
        ", which is a subclass of "+super.toString();
    }
}
