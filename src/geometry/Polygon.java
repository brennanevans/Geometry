package geometry;
import java.util.ArrayList;

public class Polygon extends Shape {
    private ArrayList<Point> anticlockwiseVertices;
    public Polygon(){
        super();
        this.anticlockwiseVertices = new ArrayList<Point>();
    }

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

    private ArrayList<Line> getEdges(){
        ArrayList<Line> edgeArray = new ArrayList<Line>();

        int connectedVertexIndex;
        for (int i=0;i<anticlockwiseVertices.size();i++){
            if (i == anticlockwiseVertices.size()-1){
                connectedVertexIndex = 0;
            } else{
                connectedVertexIndex = i+1;
            }

            Point vertex1 = anticlockwiseVertices.get(i);
            Point vertex2 = anticlockwiseVertices.get(connectedVertexIndex);
            edgeArray.add(new Line(vertex1,vertex2));
        }
        return edgeArray;
    }

    public Point calculateCentroid(){
        // https://en.wikipedia.org/wiki/Centroid#Of_a_polygon
        double area = getArea();
        double edgeSummationX = 0;
        double edgeSummationY = 0;

        ArrayList<Line> edgeArray = getEdges();

        for (Line edge : edgeArray){
            double x1 = edge.start.x;
            double x2 = edge.end.x;

            double y1 = edge.start.y;
            double y2 = edge.end.y;

            edgeSummationX += (x1+x2)*((x1*y2)-(x2*y1));
            edgeSummationY += (y1+y2)*((x1*y2)-(x2*y1));
        }
        int centroidX = (int) Math.round(edgeSummationX/(6*area));
        int centroidY = (int) Math.round(edgeSummationY/(6*area));

        return new Point(centroidX,centroidY);
    }

    public Point calculateVertexCentroid(){
        // https://en.wikipedia.org/wiki/Centroid#Of_a_polygon
        double xSummation = 0;
        double ySummation = 0;
        int n = anticlockwiseVertices.size();

        for (int i=0;i<anticlockwiseVertices.size();i++){
            xSummation += anticlockwiseVertices.get(i).x;
            ySummation += anticlockwiseVertices.get(i).y;
        }
        
        int centroidX = (int) (xSummation * 1/n);
        int centroidY = (int) (ySummation * 1/n);
        return new Point(centroidX,centroidY);
    }

    public void setCentre(int x, int y){
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
        super.setCentre((int)centroid.x,(int)centroid.y);
    }

    public double getPerimeter(){
        double perimeter = 0;
        ArrayList<Line> edgeArray = getEdges();
        for (Line edge : edgeArray){
            perimeter += edge.getLength();
        }
        return perimeter;
    }

    public double getArea(){
        // https://en.wikipedia.org/wiki/Shoelace_formula#Trapezoid_formula

        double total = 0;
        ArrayList<Line> edgeArray = getEdges();

        for (Line edge : edgeArray){
            total+=0.5*((edge.start.y+edge.end.y)*(edge.start.x-edge.end.x));
        }
        return total;
    }

    public boolean contains(Point point){
        if (anticlockwiseVertices.contains(point)){
            return true;
        } 

        ArrayList<Line> edgeArray = getEdges();
        int intersectionCounter = 0;

        for (Line edge : edgeArray){
            if (edge.containsPoint(point)){
                return true;
            }
        }
        // If not filled and point not on edge, shape considered to not contain
        //  point
        if (!isFilled()){
            return false;
        } else{
           // Shoot horizontal ray one way only (right)
            Line testRay = new Line(point,new Point(point.x+1,point.y));

            // If hits vertex shoot the other way
            // Other way can't hit vertex else the point is on an edge
            // because the shapes are simple --> not convex
            for (Point possibleParallelPoint : anticlockwiseVertices){
                if (testRay.pointRayIntersection(possibleParallelPoint)){
                    testRay = new Line(point,new Point(point.x-1,point.y));
                }
            }
            
            for (Line edge : edgeArray){
                intersectionCounter += edge.rayLineIntersection(testRay) ? 1 : 0;
            }

            return (intersectionCounter != 0)&&(intersectionCounter%2!=0); 
        }
    }

    public double distance(Point point){
        if (this.contains(point)){
            return 0;
        }

        ArrayList<Line> edgeArray = getEdges();
        double minimum = -1;

        for (Line edge : edgeArray){
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
