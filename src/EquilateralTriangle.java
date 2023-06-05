import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

class EquilateralTriangle extends Shape {
    private int x1, y1, x2, y2, x3, y3;

    public EquilateralTriangle(Point location, Point release, Color cFill, Color cStroke) {
        setTopleft(location);
        setRelease(release);
        setfColor(cFill);
        setsColor(cStroke);
        type = "EquilateralTriangle";
        calculateVertices();
    }

    private void calculateVertices() {
        int width = Math.abs(getTopleft().x - getRelease().x);
        int height = Math.abs(getTopleft().y - getRelease().y);

        int midX = getTopleft().x + width / 2;
        int midY = getTopleft().y + height / 2;

        int sideLength = Math.min(width, height);

        double triangleHeight = Math.sqrt(3) * sideLength / 2.0;

        x1 = midX;
        y1 = midY - (int) (triangleHeight / 2);
        x2 = midX - sideLength / 2;
        y2 = midY + (int) (triangleHeight / 2);
        x3 = midX + sideLength / 2;
        y3 = midY + (int) (triangleHeight / 2);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(strokeThickness));

        // Draw the stroke triangle
        g2d.setColor(sColor);
        int[] xPointsStroke = {x1, x2, x3, x1};
        int[] yPointsStroke = {y1, y2, y3, y1};
        g2d.drawPolygon(xPointsStroke, yPointsStroke, 3);


        g2d.setColor(fColor);
        int[] xPointsFill = {x1, x2, x3};
        int[] yPointsFill = {y1, y2, y3};
        g2d.fillPolygon(xPointsFill, yPointsFill, 3);
    }
}
