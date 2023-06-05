import java.awt.*;

class Hexagon extends Shape {
    private int[] xPoints;
    private int[] yPoints;

    public Hexagon(Point location, Point release, Color cFill, Color cStroke) {
        setTopleft(location);
        setRelease(release);
        setfColor(cFill);
        setsColor(cStroke);
        type = "Hexagon";

        calculateVertices();
    }

    private void calculateVertices() {
        int x1 = getTopleft().x;
        int y1 = getTopleft().y;
        int x2 = getRelease().x;
        int y2 = getRelease().y;


        int side = Math.min(Math.abs(x2 - x1), Math.abs(y2 - y1));


        int centerX = (x1 + x2) / 2;
        int centerY = (y1 + y2) / 2;


        xPoints = new int[6];
        yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI / 6 * i;
            xPoints[i] = (int) (centerX + side / 2 * Math.cos(angle));
            yPoints[i] = (int) (centerY + side / 2 * Math.sin(angle));
        }
    }

    public void draw(Graphics g) {
        g.setColor(sColor);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(strokeThickness));
        g2d.drawPolygon(new Polygon(xPoints,yPoints,6));

        g2d.setColor(fColor);
        g2d.fillPolygon(xPoints, yPoints, 6);


    }
}
