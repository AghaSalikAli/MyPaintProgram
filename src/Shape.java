import java.awt.*;
import java.util.Random;

public abstract class Shape {

    public Point [] coordinates;
    public Point center;
    private Color color;
    public Color fColor;
    public Color sColor;
    private int n;
    private Point topleft;
    private Point release;
    private Random random = new Random();
    public int strokeThickness = 5;


    int[] xPoints = new int[3];
    int[] yPoints = new int[3];

    String type;
    double side;





    Shape(){

    }


    void setTopleft(Point PTL) {
        topleft = new Point(PTL);
    }

    void setRelease(Point REL) {
        release = new Point(REL);
    }

    void setColor(Color Ccolor) {
        color = Ccolor;
    }
    /**
     *
     * @return returns the size of the circle
     */

    void setfColor(Color fColor){
        this.fColor = fColor;
    }

    void setsColor(Color sColor){
        this.sColor = sColor;
    }
    Point getTopleft(){
        return topleft;
    }

    Point getRelease(){
        return release;
    }

    void setCenter(Point c)
    {
        center = c;
    }
    public void setStrokeThickness(int strokeThickness){
        this.strokeThickness = strokeThickness;
    }

    Color getColor(){
        return color;
    }

    String getType(){
        return type;
    }

    public String toString() {
        if (type.equals("Rectangle") || type.equals("Circle")) {
            return type + " " + topleft.x + " " + topleft.y + " " + release.x + " " + release.y + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
        } else if (type.equals("Triangle")) {
            return type + " " + xPoints[0] + " " +  xPoints[1] + " " + xPoints[2] + " " + yPoints[0] + " " +  yPoints[1] + " " + yPoints[2]+ " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
        }
        else{
            return "Nothing here!";
        }
    }

    public Point getCenter() {
        return center;
    }

    abstract public void draw(Graphics g);


}
