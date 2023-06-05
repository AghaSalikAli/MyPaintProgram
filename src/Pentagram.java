import java.awt.*;

public class Pentagram extends Shape {
    private Point location;
    private Point release;

    public Pentagram(Shape shape) {
        setTopleft(shape.getTopleft());
        setRelease(shape.getRelease());
        setColor(shape.getColor());
    }

    public Pentagram(Point location, Point release, Color cFill, Color cStroke) {
        setTopleft(location);
        setRelease(release);
        setfColor(cFill);
        setsColor(cStroke);
        type = "Pentagram";
    }

    public void draw(Graphics g) {
        int[] x = new int[10];
        int[] y = new int[10];

        int angle = 0;
        int angleOffset = 18; // Adjust this value to change the tilt angle

        int radius = calculateRadius();

        for (int i = 0; i < 10; i++) {
            int tempX;
            int tempY;

            if (i % 2 == 0) {
                tempX = getTopleft().x + (int) (radius * Math.cos(Math.toRadians(angle + angleOffset)));
                tempY = getTopleft().y - (int) (radius * Math.sin(Math.toRadians(angle + angleOffset)));
            } else {
                tempX = getTopleft().x + (int) ((radius / 2) * Math.cos(Math.toRadians(angle + angleOffset)));
                tempY = getTopleft().y - (int) ((radius / 2) * Math.sin(Math.toRadians(angle + angleOffset)));
            }

            angle += 36;

            x[i] = tempX;
            y[i] = tempY;
        }

        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(sColor);
        g2.setStroke(new BasicStroke(strokeThickness));
        g2.drawPolygon(new Polygon(x, y, 10));

        g2.setColor(fColor);
        g2.fill(new Polygon(x, y, 10));

    }

    private int calculateRadius() {
        int dx = getRelease().x - getTopleft().x;
        int dy = getRelease().y - getTopleft().y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
}