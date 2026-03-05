package geometry;

import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        ArrayList<Point> testVertices = new ArrayList<Point>();
        testVertices.add(new Point(5,9));
        testVertices.add(new Point(12,21));
        testVertices.add(new Point(20,12));
        Polygon testPolygon = new Polygon("red",true,0,testVertices);
        System.out.println(testPolygon.contains(new Point(8,18)));

    }

    // TODO: Refactor Line and Polygon so they arent a complete mess
    // Add pararameters/overload methods rather than defining new ones maybe
    // Decompose more stuff into multiple methods
}
