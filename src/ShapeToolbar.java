import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class ShapeToolbar {
    myButton RAtriangle;
    myButton Eqtriangle;
    myButton Rectangle;
    myButton Circle;
    myButton Hexagon;
    myButton Pentagram;

    public ShapeToolbar() {
        Circleicons();
        Rectangleicons();
        Hexagonicons();
        RAtriangleicons();
        EQtriangleicons();
        Pentagramicons();
    }

    private void Circleicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/circle_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/circle_pressed.png");
        Circle = new myButton(280, 0, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void Rectangleicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/rectangle_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/rectangle_pressed.png");
        Rectangle = new myButton(320, 0, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void Hexagonicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/hexagon_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/hexagon_pressed.png");
        Hexagon = new myButton(360, 0, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void RAtriangleicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/RAtriangle_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/RAtriangle_pressed.png");
        RAtriangle = new myButton(280, 50, 40, 40, button_dep.getImage(), button_pre.getImage());

    }

    private void EQtriangleicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/EQtriangle_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/EQtriangle_pressed.png");
        Eqtriangle = new myButton(320, 50, 40, 40, button_dep.getImage(), button_pre.getImage());
    }

    private void Pentagramicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/pentagram_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/pentagram_pressed.png");
        Pentagram = new myButton(360, 50, 40, 40, button_dep.getImage(), button_pre.getImage());
    }
    public void paint(Graphics g, ImageObserver observer) {
        Circle.paint(g, observer);

        Rectangle.paint(g, observer);
        Hexagon.paint(g, observer);
        RAtriangle.paint(g, observer);
        Eqtriangle.paint(g, observer);
        Pentagram.paint(g, observer);
        if (Circle.hovered){
            Circle.paintTooltip(g, observer, "Circle");
        }
        if (Rectangle.hovered){
            Rectangle.paintTooltip(g, observer, "Rectangle");
        }
        if (Hexagon.hovered){
            Hexagon.paintTooltip(g, observer, "Hexagon");
        }
        if (RAtriangle.hovered){
            RAtriangle.paintTooltip(g, observer, "Right Triangle");
        }
        if (Eqtriangle.hovered){
            Eqtriangle.paintTooltip(g, observer, "Equilateral Triangle");
        }
        if (Pentagram.hovered){
            Pentagram.paintTooltip(g, observer, "Pentagram");
        }

    }

    public void IsHovered(int x, int y){

        Circle.IsHovered(x, y);

        Rectangle.IsHovered(x, y);
        Hexagon.IsHovered(x, y);
        RAtriangle.IsHovered(x, y);
        Eqtriangle.IsHovered(x, y);
        Pentagram.IsHovered(x, y);
    }



    public void IsClicked(int x, int y) {
        Circle.IsClicked(x,y);
        if (Circle.IsPressed()) {
            Rectangle.reset();
            Hexagon.reset();
            RAtriangle.reset();
            Eqtriangle.reset();
            Pentagram.reset();
        }
        Rectangle.IsClicked(x,y);
        if (Rectangle.IsPressed()) {
            Circle.reset();
            Hexagon.reset();
            RAtriangle.reset();
            Eqtriangle.reset();
            Pentagram.reset();
        }
        Hexagon.IsClicked(x,y);
        if (Hexagon.IsPressed()) {
            Circle.reset();
            Rectangle.reset();
            RAtriangle.reset();
            Eqtriangle.reset();
            Pentagram.reset();
        }
        RAtriangle.IsClicked(x,y);
        if (RAtriangle.IsPressed()) {
            Circle.reset();
            Rectangle.reset();
            Hexagon.reset();
            Eqtriangle.reset();
            Pentagram.reset();
        }
        Eqtriangle.IsClicked(x,y);
        if (Eqtriangle.IsPressed()) {
            Circle.reset();
            Rectangle.reset();
            Hexagon.reset();
            RAtriangle.reset();
            Pentagram.reset();
        }
        Pentagram.IsClicked(x,y);
        if (Pentagram.IsPressed()) {
            Circle.reset();
            Rectangle.reset();
            Hexagon.reset();
            RAtriangle.reset();
            Eqtriangle.reset();
        }
    }

    public void resetAll () {
        Circle.reset();
        Rectangle.reset();
        Hexagon.reset();
        RAtriangle.reset();
        Eqtriangle.reset();
        Pentagram.reset();
    }

    public boolean isAnyPressed () {
        return Circle.IsPressed() || Rectangle.IsPressed() || Hexagon.IsPressed() ||
                RAtriangle.IsPressed() || Eqtriangle.IsPressed() || Pentagram.IsPressed();
        }

    }



