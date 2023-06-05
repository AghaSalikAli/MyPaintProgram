import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class DrawMenu {
    myButton pencil;
    myButton lowStroke;
    myButton medStroke;
    myButton largeStroke;

    public DrawMenu () {
        pencilicons();
        lowicons();
        medicons();
        largeicons();
    }

    private void pencilicons () {
        ImageIcon button_dep = new ImageIcon("src/Pics/pencil_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/pencil_pressed.png");
        pencil = new myButton(740, 0, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void lowicons () {
        ImageIcon button_dep = new ImageIcon("src/Pics/low_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/low_pressed.png");
        lowStroke = new myButton(780, 0, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void medicons () {
        ImageIcon button_dep = new ImageIcon("src/Pics/med_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/med_pressed.png");
        medStroke = new myButton(780, 40, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void largeicons () {
        ImageIcon button_dep = new ImageIcon("src/Pics/large_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/large_pressed.png");
        largeStroke = new myButton(780, 80, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    public void paint (Graphics g, ImageObserver observer) {
        pencil.paint(g, observer);
        lowStroke.paint(g,observer);
        medStroke.paint(g, observer);
        largeStroke.paint(g, observer);

        if (pencil.hovered){
            pencil.paintTooltip(g,observer,"Free Draw");
        }

        if (lowStroke.hovered) {
            lowStroke.paintTooltip(g,observer,"Light Stroke");
        }

        if (medStroke.hovered) {
            medStroke.paintTooltip(g,observer,"Medium Stroke");
        }

        if (largeStroke.hovered) {
            largeStroke.paintTooltip(g,observer,"Large Stroke");
        }
    }

    public void IsClicked(int x, int y) {
        pencil.IsClicked(x, y);
        lowStroke.IsClicked(x,y);
        if (lowStroke.IsPressed()) {
            medStroke.reset();
            largeStroke.reset();
        }

        medStroke.IsClicked(x,y);
        if (medStroke.IsPressed()) {
            lowStroke.reset();
            largeStroke.reset();
        }

        largeStroke.IsClicked(x,y);
        if (largeStroke.IsPressed()){
            lowStroke.reset();
            medStroke.reset();
        }
    }
    public void IsHovered(int x, int y){

        pencil.IsHovered(x, y);
        lowStroke.IsHovered(x, y);
        medStroke.IsHovered(x, y);
        largeStroke.IsHovered(x, y);

    }
}
