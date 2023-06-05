import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileChooser extends JPanel
        implements ActionListener, MouseInputListener {

    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 400;

    ArrayList<LayerButton> files;
    int change = 40;
    public boolean filesPressed;
    public FileChooser() {
        initChooser();
    }

    private void initChooser() {

        addMouseListener( this );
        addMouseMotionListener( this );
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();
    }

    private void InitializeAssets() {
        files = new ArrayList<>();
        filesPressed = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,600,50);
        g.setColor(Color.white);
        g.setFont(new Font("Comic Sans", Font.BOLD, 40));
        g.drawString("Choose a File: ", 10,40);

        for (LayerButton l: files) {
            l.paint(g);
        }

        if (files.size()==0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comic Sans", Font.BOLD, 40));
            g.drawString("NO FILES SAVED!", 30,150);
        }

            repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    public void addFile () {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()) + ".txt";
        files.add(new LayerButton(0, 12 + change, 230, 40, timeStamp));
        change += 45;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            for (LayerButton l: files) {
                l.IsClicked(e.getX(),e.getY());
                     if (l.IsPressed()) {
                        filesPressed =true;
                        l.reset();
                        break;
                    }
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (LayerButton l: files) {
            l.IsHovered(e.getX(),e.getY());
        }
    }
}
