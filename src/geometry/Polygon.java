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

    private Point calculateCentroid(){
        double area = getArea();
        double centroidX = 0;
        double centroidY = 0;

        for (int i=0;i<anticlockwiseVertices.size();i++){
            int otherIndex;
            if (i == anticlockwiseVertices.size()-1){
                otherIndex = 0;
            } else{
                otherIndex = i+1;
            }
        
            double y1 = anticlockwiseVertices.get(i).y;
            double y2 = anticlockwiseVertices.get(otherIndex).y;
            double x1 = anticlockwiseVertices.get(i).x;
            double x2 = anticlockwiseVertices.get(otherIndex).x;
            centroidX += (x1+x2)*((x1*y2)-(x2*y1));
            centroidY += (y1+y2)*((x1*y2)-(x2*y1));
        }

        int centroidXInt = (int) Math.round(centroidX/(6*area));
        int centroidYInt = (int) Math.round(centroidY/(6*area));
        return new Point(centroidXInt,centroidYInt);
    }

    public void setCentre(){
        Point centroid = calculateCentroid();
        super.setCentre((int)centroid.x,(int)centroid.y);
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

    public double getPerimeter(){
        double perimeter = 0;
        for (int i=0;i<anticlockwiseVertices.size();i++){
            int otherIndex;
            if (i == anticlockwiseVertices.size()-1){
                otherIndex = 0;
            } else{
                otherIndex = i+1;
            }

            double xDiff = anticlockwiseVertices.get(i).x - anticlockwiseVertices.get(otherIndex).x;
            double yDiff = anticlockwiseVertices.get(i).y - anticlockwiseVertices.get(otherIndex).y;
            double sideLength = Math.sqrt(Math.pow(xDiff, 2)+Math.pow(yDiff, 2));
            perimeter += sideLength;
        }
        return perimeter;
    }

    public double getArea(){
        double total = 0;
        for (int i=0;i<anticlockwiseVertices.size();i++){
            int otherIndex;
            if (i == anticlockwiseVertices.size()-1){
                otherIndex = 0;
            } else{
                otherIndex = i+1;
            }
            double y1 = anticlockwiseVertices.get(i).y;
            double y2 = anticlockwiseVertices.get(otherIndex).y;
            double x1 = anticlockwiseVertices.get(i).x;
            double x2 = anticlockwiseVertices.get(otherIndex).x;
            double trapezoidArea = 0.5*((y1+y2)*(x1-x2));
            total+=trapezoidArea;
        }
        return total;
    }

    public boolean contains(Point point){
        if (anticlockwiseVertices.contains(point)){
            return true;
        } 

        int intersectionCounter = 0;
        for (int i=0;i<anticlockwiseVertices.size();i++){
            int otherIndex;
            if (i == anticlockwiseVertices.size()-1){
                otherIndex = 0;
            } else{
                otherIndex = i+1;
            }
            Point point1 = anticlockwiseVertices.get(i);
            Point point2 = anticlockwiseVertices.get(otherIndex);
            Line currentLine = new Line(point1,point2);

            if (currentLine.containsPoint(point)){
                return true;
            }
                
            if (isFilled()){
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

                System.out.println(testRay);
                
                // If final ray hits odd number of lines it is inside
                // If ray was replaced will be specifically 1 for inside 0 for outside
                intersectionCounter += currentLine.rayLineIntersection(testRay) ? 1 : 0;
            }
        }
        // System.out.println(intersectionCounter);

        return (intersectionCounter != 0)&&(intersectionCounter%2!=0);
    }

    public double distance(Point point){
        if (this.contains(point)){
            return 0;
        }

        double minimum = -1;
        for (int i=0;i<anticlockwiseVertices.size();i++){
            int otherIndex;
            if (i == anticlockwiseVertices.size()-1){
                otherIndex = 0;
            } else{
                otherIndex = i+1;
            }
            Point point1 = anticlockwiseVertices.get(i);
            Point point2 = anticlockwiseVertices.get(otherIndex);
            Line currentLine = new Line(point1,point2);

            double distance = currentLine.distanceTo(point);
            minimum = minimum == -1 ? distance : Math.min(distance, minimum);
        }

        return minimum;
    }

    public String toString(){
        return "A Polygon with vertices" + anticlockwiseVertices.toString() +
        ", which is a subclass of "+super.toString();
    }
}
