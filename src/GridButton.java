import java.awt.*;
public class GridButton {
    public int x;
    public int y;
    private int width;
    private int height;
    private boolean pressed;
    public String text = "GRID";
    public int counter = 1;
    boolean hovered;




    public GridButton (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;


    }

    public void paint (Graphics g) {




        g.setFont(new Font("Comic Sans", Font.BOLD, 35));
        FontMetrics m = g.getFontMetrics();

        int s_width = m.stringWidth(text);
        int s_height = m.getAscent() - m.getDescent();
        g.setColor(new Color(235,184,64));
        g.fillRect(x,y,width,height);

        if (hovered){
            g.setColor(new Color(219,165,85));
            g.fillRect(x,y,width,height);

        }




        if (pressed) {



            g.setColor(new Color(207,129,12));
            g.fillRect(x,y,width,height);
            g.setColor(Color.BLACK);

            if (counter ==0){
                text = "GRID";
            }

            if (counter == 1){
                text = "2";
            }
            else if (counter == 2){
                text = "4";
            }
            else if (counter == 3){
                text = "8";
            }
            else if (counter == 4){
                text = "16";
            }
            else if (counter == 5){
                text = "32";
            }
            else if (counter == 6){

                text = "64";
            }
            else if(counter == 7){
                counter = 0;
                text = "GRID";
            }

            counter++;
            reset();

        }
        g.setColor(new Color(207, 129, 12));
        int lineThickness = 5;
        int lineLength = Math.min(width, height) - lineThickness + 3;
        int lineX = x + (width - lineLength) / 2;
        int lineY = y + (height - lineLength) / 2;


        g.fillRect(lineX, y + (height - lineThickness) / 2, lineLength, lineThickness);


        g.fillRect(x + (width - lineThickness) / 2, lineY, lineThickness, lineLength);

        g.setColor(Color.BLACK);
        g.drawString(text, x + width / 2 - s_width / 2, y + height / 2 + s_height / 2);







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

                pressed = false;
            }
        }


    public void IsHovered(int x, int y){

        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height){
            hovered = true;
        }
        else{
            hovered = false;
        }
    }


    public void reset(){
        pressed = false;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}