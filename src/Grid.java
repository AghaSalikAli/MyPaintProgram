import java.awt.*;

public class Grid {


    GridButton gb = new GridButton(850, 10, 100, 100);


    public void paint(Graphics g) {
        gb.paint(g);
    }

    public void IsClicked(int x, int y) {
        gb.IsClicked(x, y);
    }


    public void IsHovered(int x, int y) {
        gb.IsHovered(x, y);
    }

    public void drawGrid(Graphics g) {
        int x = 0;
        int y = 125;
        g.setColor(Color.gray);

        Graphics2D g2d = (Graphics2D) g;

        // Draw the stroke triangle
        g2d.setStroke(new BasicStroke(1));
        if (gb.text.equals("2")) {
            while (x < 975) {
                g.drawLine(x, y, x, 725);
                x += 4;
            }
            x = 0;
            while (y < 725) {
                g.drawLine(x, y, 975, y);
                y += 4;
            }
        } else if (gb.text.equals("4")) {
            while (x < 975) {
                g.drawLine(x, y, x, 725);
                x += 8;
            }
            x = 0;
            while (y < 725) {
                g.drawLine(x, y, 975, y);
                y += 8;
            }
        } else if (gb.text.equals("8")) {
            while (x < 975) {
                g.drawLine(x, y, x, 725);
                x += 16;
            }
            x = 0;
            while (y < 725) {
                g.drawLine(x, y, 975, y);
                y += 16;
            }
        } else if (gb.text.equals("16")) {
            while (x < 975) {
                g.drawLine(x, y, x, 725);
                x += 32;
            }
            x = 0;
            while (y < 725) {
                g.drawLine(x, y, 975, y);
                y += 32;
            }
        } else if (gb.text.equals("32")) {
            while (x < 975) {
                g.drawLine(x, y, x, 725);
                x += 64;
            }
            x = 0;
            while (y < 725) {
                g.drawLine(x, y, 975, y);
                y += 64;
            }
        } else if (gb.text.equals("64")) {
            while (x < 975) {
                g.drawLine(x, y, x, 725);
                x += 128;
            }
            x = 0;
            while (y < 725) {
                g.drawLine(x, y, 975, y);
                y += 128;
            }
        } else if (gb.text.equals("128")) {
            while (x < 975) {
                g.drawLine(x, y, x, 725);
                x += 256;
            }
            x = 0;
            while (y < 725) {
                g.drawLine(x, y, 975, y);
                y += 256;
            }
        }
    }
}