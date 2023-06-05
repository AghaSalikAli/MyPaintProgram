import java.awt.*;
import java.util.ArrayList;

public class LayerButton {
    public int x;
    public int y;
    private int width;
    private int height;
    private boolean pressed;
    private boolean hovered;
    public String text;
    public ArrayList<Shape> shapes;
    public Queue redoQueue;

    public LayerButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        shapes = new ArrayList<>();
        redoQueue = new Queue();
    }

    public void paint(Graphics g) {

        if (pressed) {
            g.setColor(Color.BLACK);

            g.fillRect(x - 2, y - 2, width + 4, height + 4);
            g.setColor(new Color(235,184,64));
            g.fillRect(x,y,width,height);
        } else if (hovered) {
            g.setColor(new Color(232, 169, 38));
            g.fillRect(x, y, width, height);
        }
        else{
            g.setColor(new Color(235,184,64));
            g.fillRect(x,y,width,height);
        }


        g.setColor(Color.black);
        g.setFont(new Font("Comic Sans", Font.BOLD, 20));
        FontMetrics m = g.getFontMetrics();
        int s_width = m.stringWidth(text);
        int s_height = m.getAscent() - m.getDescent();
        g.drawString(text, x + width / 2 - s_width / 2, y + height / 2 + s_height / 2);
    }

    public Boolean IsPressed() {
        return pressed;
    }

    public void SetPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void IsClicked(int x, int y) {
        if (!pressed) {
            if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
                pressed = true;
            }
        } else {
            if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
                pressed = false;
            }
        }
    }

    public void IsHovered(int x, int y) {
        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            hovered = true;
        } else {
            hovered = false;
        }
    }

    public void reset() {
        pressed = false;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}