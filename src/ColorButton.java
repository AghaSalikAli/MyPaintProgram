import java.awt.*;
import java.awt.image.ImageObserver;

public class ColorButton {
    public int x;
    public int y;
    private int width;
    private int height;
    public Color color;
    private boolean pressed;
    public boolean hovered;
    public int hoverX;
    public int hoverY;


    public ColorButton (int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void paint (Graphics g) {
        if (pressed) {
            g.setColor(Color.MAGENTA);
            g.fillRect(x-2,y-2,width+4,height+4);
        }
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    public Boolean IsPressed()
    {
        return pressed;
    }

    public void SetPressed(boolean pressed)
    {
        this.pressed = pressed;
    }


    public void IsClicked(int x, int y)
    {
        if (!pressed) {
            if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
                pressed = true;
            }
        }

        else {
            if((x > this.x && x < this.x + width && y > this.y && y < this.y + height)) {
                pressed = false;
            }
        }
    }

    public void IsHovered(int x, int y){

        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height){
            hovered = true;
            hoverX = x;
            hoverY = y;
        }
        else{
            hovered = false;
            hoverY = 0;
            hoverX = 0;
        }
    }

    public void reset(){
        pressed = false;
    }

    public Color getColor() {
        return color;
    }

    public void paintTooltip(Graphics g, ImageObserver observer, String text){
        if (hovered){
            if(hoverX!=0){
                g.setFont(new Font("Comic Sans", Font.BOLD, 13));
                FontMetrics m = g.getFontMetrics();
                String text1 = convertColorToHex(getColor());
                if (color==Color.pink)
                    text1 = "#FFC0CB";
                String ftext = text + " " + text1;
                int s_width = m.stringWidth(ftext);
                g.setColor(new Color(250, 169, 7, 220));
                g.fillRect(hoverX, hoverY+30, s_width+5, 20);
                g.setColor(new Color(24, 17, 2, 255));
                g.drawString(ftext, hoverX+(s_width+5)/2 - s_width/2, hoverY+45);}
        }

    }

    public static String convertColorToHex(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}

