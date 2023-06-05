import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

class Rectangle extends Shape {



    Rectangle(Point location, Point release, Color cFill, Color cStroke) {
        setTopleft(location);
        setRelease(release);
        setfColor(cFill);
        setsColor(cStroke);
        type = "Rectangle";
        strokeThickness = 5; // Default stroke thickness
    }

    public void draw(Graphics g) {
        int x = Math.min(getTopleft().x, getRelease().x);
        int y = Math.min(getTopleft().y, getRelease().y);
        int width = Math.abs(getTopleft().x - getRelease().x);
        int height = Math.abs(getTopleft().y - getRelease().y);

        // Draw the stroke effect
        g.setColor(sColor);
        g.fillRect(x - strokeThickness, y - strokeThickness, width + 2 * strokeThickness, height + 2 * strokeThickness);

        g.setColor(fColor);
        g.fillRect(x, y, width, height);
    }
}
