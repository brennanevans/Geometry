package geometry;
public class Shape {
    private String colour;
    private boolean filled;
    private Point centre;
    private double orientation;


    public Shape(){
        this.colour = "red";
        this.filled = true;
        this.centre = new Point(0,0);
        this.orientation = 0;
    }

    public Shape(String colour, boolean filled, Point centre, double orientation){
        this.colour = colour;
        this.filled = filled;
        this.centre = centre;
        this.orientation = orientation;
    }

    public String getColour(){
        return this.colour;
    }

    public boolean isFilled(){
        return this.filled;
    }

    public String toString(){
        String stringRepresentation = "A Shape with centre of " + centre
        + ", rotated " + orientation +" degrees clockwise" + ", colour of "
        + this.colour + " and is ";
        stringRepresentation += (this.filled) ? "filled" : "not filled.";

        return stringRepresentation;
    }

    public void setColour(String colour){
        this.colour = colour;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }

    public Point getCentre(){
        return centre;
    }

    public void setCentre(int x, int y){
        centre = new Point(x,y);
    }

    public double getOrientation(){
        return orientation;
    }

    public void rotate(double angle){
        rotate(angle,true);
    }

    public void rotate(double angle, boolean clockwise){
        if (clockwise){
            orientation += angle;
        } else{
            orientation -= angle;
        }
        double overflow = orientation%360;
        if (overflow < 0){
            orientation = 360 - overflow;
        } else{
            orientation = overflow;
        }
    }
}
