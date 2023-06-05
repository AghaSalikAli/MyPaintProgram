import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;

public class LayerToolbar {

    myButton up;
    myButton down;
    myButton add;
    myButton remove;

    LayerButton layer1 = new LayerButton(1000, 52, 400,36,"layer 1");

    ArrayList<LayerButton> layerslist = new ArrayList<>();
    int lnum = 2;
    String ln = "";
    int y = 40;
    int currentlyPressedLayerIndex = 0;

    private ImageIcon layerHeading = new ImageIcon("src/Pics/layersHeading.png");



    public LayerToolbar() {
        upicons();
        downicons();
        removeicons();
        addicons();
        layer1.SetPressed(true);
        layerslist.add(layer1);
    }
    private void upicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/up_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/up_pressed.png");
        up = new myButton(1300, 600, 100, 100, button_dep.getImage(), button_pre.getImage());
    }

    private void downicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/down_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/down_pressed.png");
        down = new myButton(1200, 600, 100, 100, button_dep.getImage(), button_pre.getImage());
    }

    private void removeicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/remove_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/remove_pressed.png");
        remove = new myButton(1100, 600, 100, 100, button_dep.getImage(), button_pre.getImage());
    }

    private void addicons() {
        ImageIcon button_dep = new ImageIcon("src/Pics/plus_depressed.png");
        ImageIcon button_pre = new ImageIcon("src/Pics/plus_pressed.png");
        add = new myButton(1000, 600, 100, 100, button_dep.getImage(), button_pre.getImage());
    }

    public void paint(Graphics g, ImageObserver observer) {
        g.setColor(Color.darkGray);
        g.drawImage(layerHeading.getImage(), 1000, 0, observer);

        g.setFont(new Font("serif",Font.BOLD, 30));
        g.setColor(Color.white);

        g.setColor(new Color(253,236,229));
        g.fillRect(1000,50,400,600);
        g.setColor(new Color(235,184,64));
        g.fillRect(975, 0, 5, 1200);



        add.paint(g, observer);


        ln = "layer " + lnum;

        if (add.IsPressed()) {
            add.reset();
            if(layerslist.size()<13){
                lnum +=1;
                layerslist.add(new LayerButton(1000, 52+y, 400, 36, ln));
                y+=40;
            }
            else{
                System.out.println("Cant add anymore layers!");
            }

        }

        remove.paint(g,observer);
        if (remove.IsPressed() && layerslist.size()!=1) {
            remove.reset();
            {
                Iterator<LayerButton> it = layerslist.iterator();
                while (it.hasNext()) {
                    LayerButton b = it.next();
                    if (b.IsPressed()) {
                        it.remove();
                        for (int i = currentlyPressedLayerIndex; i < layerslist.size(); i++) {
                            LayerButton nextLayer = layerslist.get(i);
                            int newY = nextLayer.getY() - 40;
                            nextLayer.setY(newY);
                        }
                        y -= 40;
                        currentlyPressedLayerIndex = 0;
                    }
                }
            }
        }

        up.paint(g, observer);
        if (up.IsPressed()) {
            up.reset();
        }

        down.paint(g, observer);
        if (down.IsPressed()) {
            down.reset();
        }


        for(LayerButton b : layerslist){
            b.paint(g);
        }

        if (up.hovered) {
            up.paintTooltip(g,observer,"Move Up");
        }

        if (down.hovered) {
            down.paintTooltip(g,observer,"Move Down");
        }

        if (add.hovered) {
            add.paintTooltip(g,observer,"Add Layer");
        }

        if (remove.hovered) {
            remove.paintTooltip(g,observer,"Remove Layer");
        }
    }

    public void IsClicked(int x, int y) {
        add.IsClicked(x, y);
        remove.IsClicked(x,y);


        up.IsClicked(x, y);
        if (up.IsPressed()) {
            if (currentlyPressedLayerIndex > 0) {
                LayerButton currentLayer = layerslist.get(currentlyPressedLayerIndex);
                LayerButton previousLayer = layerslist.get(currentlyPressedLayerIndex - 1);
                int tempY = previousLayer.getY();
                previousLayer.setY(currentLayer.getY());
                currentLayer.setY(tempY);
                layerslist.set(currentlyPressedLayerIndex - 1, currentLayer);
                layerslist.set(currentlyPressedLayerIndex, previousLayer);
                currentlyPressedLayerIndex -= 1;
            }
        }


        down.IsClicked(x,y);
        if (down.IsPressed()) {
            if (currentlyPressedLayerIndex < layerslist.size() - 1) {
                LayerButton currentLayer = layerslist.get(currentlyPressedLayerIndex);
                LayerButton nextLayer = layerslist.get(currentlyPressedLayerIndex + 1);
                int tempY = nextLayer.getY();
                nextLayer.setY(currentLayer.getY());
                currentLayer.setY(tempY);
                layerslist.set(currentlyPressedLayerIndex + 1, currentLayer);
                layerslist.set(currentlyPressedLayerIndex, nextLayer);
                currentlyPressedLayerIndex += 1;
            }
        }

        for (int i = 0; i < layerslist.size(); i++) {
            layerslist.get(i).IsClicked(x, y);
            if (layerslist.get(i).IsPressed()) {
                currentlyPressedLayerIndex = i;
                for (int j = 0; j < layerslist.size(); j++) {
                    if (i != j) {
                        layerslist.get(j).reset();
                    }
                }
            }
        }
    }

    public void IsHovered(int x, int y){
        for (int i = 0; i < layerslist.size(); i++) {
            layerslist.get(i).IsHovered(x, y);
        }

        up.IsHovered(x, y);
        down.IsHovered(x, y);
        add.IsHovered(x,y);
        remove.IsHovered(x,y);
    }

}