import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

class RATriangle extends Shape {
    private int x1, y1, x2, y2, x3, y3;

    public RATriangle(Point location, Point release, Color cFill, Color cStroke) {
        setTopleft(location);
        setRelease(release);
        setfColor(cFill);
        setsColor(cStroke);
        type = "RightAngleTriangle";

        calculateVertices();
    }

    private void calculateVertices() {
        int width = Math.abs(getTopleft().x - getRelease().x);
        int height = Math.abs(getTopleft().y - getRelease().y);

        x1 = getTopleft().x;
        y1 = getRelease().y;
        x2 = getTopleft().x + width;
        y2 = getRelease().y;
        x3 = getTopleft().x;
        y3 = getTopleft().y - height;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Draw the stroke triangle
        g2d.setStroke(new BasicStroke(strokeThickness));
        g2d.setColor(sColor);
        int[] xPointsStroke = {x1, x2, x3, x1};
        int[] yPointsStroke = {y1, y2, y3, y1};
        g2d.drawPolygon(xPointsStroke, yPointsStroke, 4);

        // Draw the main triangle
        g2d.setColor(fColor);
        int[] xPointsFill = {x1, x2, x3};
        int[] yPointsFill = {y1, y2, y3};
        g2d.fillPolygon(xPointsFill, yPointsFill, 3);
    }
}
