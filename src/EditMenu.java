import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class EditMenu {
    myButton Edit;
    myButton Undo;
    myButton Redo;


    public EditMenu () {
        Editicons();
        Undoicons();
        Redoicons();
    }

    private void Editicons () {
        ImageIcon button_dep = new ImageIcon("src/Pics/edit_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/edit_pressed.png");
        Edit = new myButton(120, 0, 120, 50, button_dep.getImage(), button_pre.getImage());
    }

    private void Undoicons () {
        ImageIcon button_dep = new ImageIcon("src/Pics/undo_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/undo_pressed.png");
        Undo = new myButton(120, 50, 120, 50, button_dep.getImage(), button_pre.getImage());
    }

    private void Redoicons () {
        ImageIcon button_dep = new ImageIcon("src/Pics/redo_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/redo_pressed.png");
        Redo = new myButton(120, 100, 120, 50, button_dep.getImage(), button_pre.getImage());
    }

    public void paint (Graphics g, ImageObserver observer) {
        Edit.paint(g, observer);

        if (Edit.IsPressed()) {
            Undo.paint(g, observer);
            Redo.paint(g,observer);

            if(Undo.hovered) {
                Undo.paintTooltip(g,observer,"Right Click");
            }

            if(Redo.hovered) {
                Redo.paintTooltip(g,observer,"Middle Mouse Button");
            }

            if (Undo.IsPressed()) {
                Undo.reset();
            }
            if (Redo.IsPressed()) {
                Redo.reset();
            }
        }
        else{
            Undo.reset();
            Redo.reset();
        }
    }

    public void IsClicked(int x, int y) {
        Edit.IsClicked(x, y);
        Redo.IsClicked(x, y);
        Undo.IsClicked(x, y);
    }

    public void IsHovered(int x, int y){

        Redo.IsHovered(x, y);
        Undo.IsHovered(x, y);

    }
}
