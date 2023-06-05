import java.awt.*;
import java.awt.image.ImageObserver;

public class myButton
{
	public int x;
	public int y;
	private int width;
	private int height;
	private Image image_depressed;
	private Image image_pressed;
	private Image current_image;
	private boolean pressed;
	public boolean hovered;
	public int hoverX;
	public int hoverY;

	public myButton(int x, int y, int width, int height, Image i_depressed, Image i_pressed)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		image_depressed = i_depressed.getScaledInstance(width,height,Image.SCALE_FAST);
		image_pressed = i_pressed.getScaledInstance(width,height,Image.SCALE_FAST);
		current_image = image_depressed;
	}

	public Image GetImage()
	{
		return current_image;
	}
	public void paint (Graphics g, ImageObserver observer) {
		g.drawImage(current_image,x,y,observer);
	}

	public void paintTooltip(Graphics g, ImageObserver observer, String text){
		if (hovered){
			if(hoverX!=0){
				g.setFont(new Font("Comic Sans", Font.BOLD, 13));
				FontMetrics m = g.getFontMetrics();
				int s_width = m.stringWidth(text);
				g.setColor(new Color(250, 169, 7, 220));
				g.fillRect(hoverX, hoverY+30, s_width+5, 20);
				g.setColor(new Color(24, 17, 2, 255));
				g.drawString(text, hoverX+(s_width+5)/2 - s_width/2, hoverY+45);}
		}

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
		if (current_image==image_depressed) {
			if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
				pressed = true;
				current_image = image_pressed;
			}
		}

		else if(current_image == image_pressed) {
			if((x > this.x && x < this.x + width && y > this.y && y < this.y + height)) {
				pressed = false;
				current_image = image_depressed;
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
		current_image = image_depressed;
	}


}