import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
/**
 *
 * @author uakhan
 * This class creates a circle
 */
class Circle extends Shape
{




    Circle(Shape shape){
        setTopleft(shape.getTopleft());
        setRelease(shape.getRelease());
        setColor(shape.getColor());
    }
    Circle(Point location, Point release, Color cFill, Color cStroke)
    {

        setTopleft(location);
        setRelease(release);
        setfColor(cFill);
        setsColor(cStroke);
        type = "Circle";
    }


    public void draw(Graphics g) {
        g.setColor(sColor);

        // Calculate the bounds of the outer oval
        int x = Math.min(getTopleft().x, getRelease().x);
        int y = Math.min(getTopleft().y, getRelease().y);
        int width = Math.abs(getTopleft().x - getRelease().x);
        int height = Math.abs(getTopleft().y - getRelease().y);

        // Draw the outer oval
        g.fillOval(x, y, width, height);

        // Calculate the bounds of the inner oval for the stroke effect
        int innerX = x + strokeThickness;
        int innerY = y + strokeThickness;
        int innerWidth = width - (strokeThickness*2);
        int innerHeight = height - (strokeThickness*2);

        // Set the color to the background color to create the stroke effect
        g.setColor(fColor);

        // Draw the inner oval as the stroke
        g.fillOval(innerX, innerY, innerWidth, innerHeight);
    }



}