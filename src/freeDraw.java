import java.awt.*;
import java.util.ArrayList;

public class freeDraw extends Shape{

    ArrayList<Point> points;
    int stroke;
    freeDraw(Point location, Color color)
    {
        points = new ArrayList<>();
        setTopleft(location);
        setColor(color);
    }

    freeDraw( )
    {
        points = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);

            // Calculate the distance between p1 and p2
            int distance = (int) p1.distance(p2);

            // Calculate the number of additional points needed
            int numPoints = distance / 1;  // Adjust this value to control the density

            // Calculate the x and y increments between each additional point
            double dx = (double) (p2.x - p1.x) / (numPoints + 1);
            double dy = (double) (p2.y - p1.y) / (numPoints + 1);

            // Draw the additional points as ovals
            for (int j = 1; j <= numPoints; j++) {
                int x = (int) (p1.x + dx * j);
                int y = (int) (p1.y + dy * j);
                g.fillOval(x, y, 2+stroke, 2+stroke);
            }

            // Draw the final point (p2)

        }
    }

    public void setStroke(int stroke){
            this.stroke = stroke;
    }
    }





