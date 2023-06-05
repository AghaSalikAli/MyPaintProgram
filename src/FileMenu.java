import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileMenu {

    myButton File;
    myButton New;
    myButton Save;
    myButton Open;
    JFrame frame = new JFrame();
    public static FileChooser fileChooser = new FileChooser();

    public FileMenu() {
        Fileicons();
        Newicons();
        Saveicons();
        Openicons();
    }

    private void Fileicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/file_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/file_pressed.png");
        File = new myButton(0, 0, 120, 50, button_dep.getImage(), button_pre.getImage());
    }

    private void Newicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/new_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/new_pressed.png");
        New = new myButton(0, 50, 120, 50, button_dep.getImage(), button_pre.getImage());
    }

    private void Saveicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/save_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/save_pressed.png");
        Save = new myButton(0, 100, 120, 50, button_dep.getImage(), button_pre.getImage());
    }

    private void Openicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/open_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/open_pressed.png");
        Open = new myButton(0, 150, 120, 50, button_dep.getImage(), button_pre.getImage());
    }

    public void paint(Graphics g, ImageObserver observer) {
        File.paint(g, observer);


        if (File.IsPressed()) {
            New.paint(g, observer);
            Save.paint(g, observer);
            Open.paint(g, observer);

            if (New.hovered) {
                New.paintTooltip(g, observer, "Refresh");
            }

            if (Open.hovered) {
                Open.paintTooltip(g, observer, "Choose File");
            }

            if (Save.hovered) {
                Save.paintTooltip(g, observer, "Save Current State");
            }

            if (New.IsPressed()) {
                New.reset();
            }
            if (Save.IsPressed()) {
                fileChooser.addFile();
                Save.reset();
            }

            if (Open.IsPressed()) {
                Open.reset();
                openNewcanvas();
            }
        } else {
            New.reset();
            Open.reset();
            Save.reset();
        }

        if (fileChooser.filesPressed) {
            frame.setVisible(false);
            frame.dispose();
        }

    }

    public void IsClicked(int x, int y) {
        File.IsClicked(x, y);
        New.IsClicked(x, y);
        Save.IsClicked(x, y);
        Open.IsClicked(x, y);
        
    }

    public void openNewcanvas() {
        fileChooser.filesPressed=false;
        frame.add(fileChooser);
        frame.setTitle("File Chooser");
        frame.setLocation(450, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void IsHovered(int x, int y) {

        New.IsHovered(x, y);
        Save.IsHovered(x, y);
        Open.IsHovered(x, y);

    }
}
