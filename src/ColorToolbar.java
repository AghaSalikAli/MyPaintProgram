import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;




public class ColorToolbar extends JPanel {
    ColorButton Color1;
    ColorButton Color2;

    myButton Red;
    myButton Cyan;
    myButton Green;
    myButton Yellow;
    myButton Pink;
    myButton Blue;
    myButton Black;
    myButton White;
    myButton Gray;
    myButton Gradient;
    ImagePanel panel;

    public ColorToolbar() {
        Color1 = new ColorButton(420, 3, 60, 60, Color.black);
        Color1.SetPressed(true);
        Color2 = new ColorButton(483, 3, 60, 60, Color.gray);
        RedIcons();
        CyanIcons();
        GreenIcons();
        YellowIcons();
        PinkIcons();
        BlueIcons();
        BlackIcons();
        WhiteIcons();
        GrayIcons();
        GradientIcons();
    }

    private void RedIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/red_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/red_color.png");
        Red = new myButton(570, 5, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void CyanIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/cyan_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/cyan_color.png");
        Cyan = new myButton(610, 5, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void GreenIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/green_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/green_color.png");
        Green = new myButton(650, 5, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void YellowIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/yellow_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/yellow_color.png");
        Yellow = new myButton(570, 45, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void PinkIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/pink_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/pink_color.png");
        Pink = new myButton(610, 45, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void BlueIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/blue_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/blue_color.png");
        Blue = new myButton(650, 45, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void BlackIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/black_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/black_color.png");
        Black = new myButton(570, 85, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void WhiteIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/white_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/white_color.png");
        White = new myButton(610, 85, 40, 40, button_dep.getImage(), button_pre.getImage());
    }
    private void GrayIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/gray_color.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/gray_color.png");
        Gray = new myButton(650, 85, 40, 40, button_dep.getImage(), button_pre.getImage());
    }


    private void GradientIcons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/gradient_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/gradient_depressed.png");
        Gradient = new myButton(700, 5, 20, 20, button_dep.getImage(), button_pre.getImage());
    }

    public void paint(Graphics g, ImageObserver observer) {
        if ((panel!=null) && (panel.done)) {
            colorchange(panel.getChosenColor());
            panel.done = false;
        }
        Color1.paint(g);
        Color2.paint(g);
        Red.paint(g, observer);
        Cyan.paint(g, observer);
        Green.paint(g, observer);
        Yellow.paint(g, observer);
        Pink.paint(g, observer);
        Blue.paint(g, observer);
        Black.paint(g,observer);
        White.paint(g, observer);
        Gray.paint(g, observer);
        Gradient.paint(g, observer);

        if (Red.hovered) {
            Red.paintTooltip(g,observer,"RED, #FF0000");
        }

        if (Cyan.hovered) {
            Cyan.paintTooltip(g,observer,"CYAN, #00FFFF");
        }

        if (Green.hovered) {
            Green.paintTooltip(g,observer,"GREEN, #00FFFF");
        }

        if (Yellow.hovered) {
            Yellow.paintTooltip(g,observer,"YELLOW, #FFFF00");
        }

        if (Pink.hovered) {
            Pink.paintTooltip(g,observer,"PINK, #FFC0CB");
        }

        if (Blue.hovered) {
            Blue.paintTooltip(g,observer,"BLUE, #0000FF");
        }

        if (Black.hovered) {
            Black.paintTooltip(g,observer,"BLACK, #000000");
        }

        if (White.hovered) {
            White.paintTooltip(g,observer,"WHITE, #FFFFFF");
        }

        if (Gray.hovered) {
            Gray.paintTooltip(g,observer,"Gray, #808080");
        }

        if (Gradient.hovered) {
            Gradient.paintTooltip(g,observer,"Custom Colour");
        }

        if (Color1.hovered) {
            Color1.paintTooltip(g,observer, "FILL COLOR");
        }

        if (Color2.hovered)
            Color2.paintTooltip(g,observer, "STROKE COLOR");

    }

    public void IsClicked(int x, int y) {
        Color1.IsClicked(x, y);
        if (Color1.IsPressed())
            Color2.reset();

        Color2.IsClicked(x, y);
        if (Color2.IsPressed())
            Color1.reset();

        Red.IsClicked(x, y);
        if (Red.IsPressed()) {
            colorchange(Color.red);
            resetColors();
        }

        Cyan.IsClicked(x, y);
        if (Cyan.IsPressed()) {
            colorchange(Color.cyan);
            resetColors();
        }

        Green.IsClicked(x, y);
        if (Green.IsPressed()) {
            colorchange(Color.green);
            resetColors();
        }

        Yellow.IsClicked(x, y);
        if (Yellow.IsPressed()) {
            colorchange(Color.yellow);
            resetColors();
        }

        Pink.IsClicked(x, y);
        if (Pink.IsPressed()) {
            colorchange(Color.pink);
            resetColors();
        }

        Blue.IsClicked(x, y);
        if (Blue.IsPressed()) {
            colorchange(Color.blue);
            resetColors();
        }

        Black.IsClicked(x, y);
        if (Black.IsPressed()) {
            colorchange(Color.black);
            resetColors();
        }

        White.IsClicked(x, y);
        if (White.IsPressed()) {
            colorchange(Color.white);
            resetColors();
        }

        Gray.IsClicked(x, y);
        if (Gray.IsPressed()) {
            colorchange(Color.gray);
            resetColors();
        }

        Gradient.IsClicked(x, y);
        if(Gradient.IsPressed()) {
            openNewWindow();
            Gradient.reset();
        }
    }


    public void colorchange(Color color) {
        if (Color1.IsPressed())
            Color1.color = color;


        if (Color2.IsPressed())
            Color2.color = color;
    }

    public void resetColors() {
        Red.reset();
        Cyan.reset();
        Green.reset();
        Yellow.reset();
        Pink.reset();
        Blue.reset();
        Black.reset();
        White.reset();
        Gray.reset();
    }

    public void IsHovered(int x, int y){

        Red.IsHovered(x, y);
        Blue.IsHovered(x, y);
        Yellow.IsHovered(x, y);
        Green.IsHovered(x, y);
        Cyan.IsHovered(x, y);
        Pink.IsHovered(x, y);
        Black.IsHovered(x,y);
        White.IsHovered(x, y);
        Gray.IsHovered(x, y);
        Gradient.IsHovered(x,y);
        Color1.IsHovered(x,y);
        Color2.IsHovered(x,y);

    }

    public void openNewWindow() {
        JFrame newFrame = new JFrame();
        newFrame.setSize(500, 600);
        newFrame.setDefaultCloseOperation(newFrame.DISPOSE_ON_CLOSE);
        newFrame.getContentPane().add(panel=new ImagePanel());
        newFrame.setVisible(true);
        newFrame.setResizable(false);
        newFrame.addWindowListener(panel);
    }


}



 class ImagePanel extends JPanel implements MouseListener, MouseInputListener, MouseMotionListener, ActionListener, WindowListener {


    ImageIcon pencil = new ImageIcon("src/pics/pencil.png");
    Image p = pencil.getImage();
    int px;
    int py;
     Color ChosenColor = Color.white;
     final ImageIcon image;
     boolean done;


     public ImagePanel() {
         image = new ImageIcon("src/Pics/g_pressed.png");
         addMouseListener(this);
         addMouseMotionListener(this);
         done = false;
     }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(), 0, 0, null);
        g.setColor(Color.black);
        g.setFont(new Font("Comic Sans", Font.BOLD, 30));
        g.drawString("CHOSEN COLOR: " ,20, 540);
        g.setColor(Color.black);
        g.fillRect(300-2,510-2,45+4,45+4);
        g.setColor(ChosenColor);
        g.fillRect(300,510,45,45);
        if(py<500){
            g.drawImage(p, px-20, py-20, null);
        }
    }

     @Override
     public void mouseClicked(MouseEvent e) {
         int x = e.getX();
         int y = e.getY();
         if ((x<=500) && (y<=500)) {
         BufferedImage image1;
         try {
             image1 = ImageIO.read(new File("src/Pics/g_pressed.png"));
         } catch (IOException f) {
             throw new RuntimeException(f);
         }

        int rgb = image1.getRGB(x,y);
         setChosenColor(new Color(rgb));

         repaint();
         }
     }

     public void setChosenColor(Color chosenColor) {
         ChosenColor = chosenColor;
     }

     public Color getChosenColor() {
         return ChosenColor;
     }

     public void mousePressed(MouseEvent e) {

     }


     public void mouseReleased(MouseEvent e) {

     }


     public void mouseEntered(MouseEvent e) {

     }


     public void mouseExited(MouseEvent e) {

     }

     @Override
     public void actionPerformed(ActionEvent e) {
         Toolkit.getDefaultToolkit().sync();
         repaint();
     }

     @Override
     public void mouseDragged(MouseEvent e) {

     }

     @Override
     public void mouseMoved(MouseEvent e) {
         px = e.getX();
         py = e.getY();
         repaint();

     }

     @Override
     public void windowOpened(WindowEvent e) {

     }

     @Override
     public void windowClosing(WindowEvent e) {

     }

     @Override
     public void windowClosed(WindowEvent e) {
         done = true;
     }

     @Override
     public void windowIconified(WindowEvent e) {

     }

     @Override
     public void windowDeiconified(WindowEvent e) {

     }

     @Override
     public void windowActivated(WindowEvent e) {

     }

     @Override
     public void windowDeactivated(WindowEvent e) {

     }
 }