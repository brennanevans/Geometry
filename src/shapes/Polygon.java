package shapes;
import java.util.ArrayList;

public class Polygon extends Shape {
    private ArrayList<Point> anticlockwiseVertices;
    public static void main(String[] args) {
        ArrayList<Point> testVertices = new ArrayList<Point>();
        testVertices.add(new Point(5,9));
        testVertices.add(new Point(12,21));
        testVertices.add(new Point(20,12));
        Polygon testPolygon = new Polygon("red",true,0,testVertices);
        System.out.println(testPolygon.contains(new Point(15,11)));
    }

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
                Line testLine = new Line(new Point(0,0), point);
                intersectionCounter += currentLine.intersects(testLine) ? 1 : 0;
                // Adds additional 1 for cases where line crosses shape vertices
                // as this is technically passing through 2 lines else
                // points outside the shape whose line (0,0) --> point crosses
                // a vertex will be considered inside as only counted one line.
            
                // This causes vertices to not be counted as part of the shape
                // so explicitly tested at top of method.
                intersectionCounter += testLine.containsPoint(anticlockwiseVertices.get(i)) ? 1 : 0;
            }
        }

        return (intersectionCounter != 0)&&(intersectionCounter%2!=0);
    }

    public String toString(){
        return "A Polygon with vertices" + anticlockwiseVertices.toString() +
        ", which is a subclass of "+super.toString();
    }
}
