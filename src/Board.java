import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel
        implements ActionListener , MouseInputListener{

    private final int B_WIDTH = 1450;
    private final int B_HEIGHT = 725;
    private final int DELAY = 25;

    private Timer timer;
    private int key = 0;
    private boolean keyPressed = false;
    private boolean mousePressed = false;

    private boolean start_drawing = false;

    private int x_init;
    private int y_init;
    private int x_final;
    private int y_final;

    private FileMenu fileMenu;
    private EditMenu editMenu;

    private ShapeToolbar shapeToolbar;

    private ColorToolbar colorToolbar;
    private LayerToolbar layerToolbar;
    private DrawMenu drawMenu;
    private Grid grid;

    private Point r = new Point(0, 0);
    private Point c = new Point(0, 0);
    private Shape shape;
    ArrayList<Shape> shapes = new ArrayList<>();
    freeDraw fDraw = new freeDraw();
    public Queue redoQueue;
    boolean dragged;



    Color fc;
    Color sc;
    boolean rightMousePressed;
    boolean middleMousePressed;

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            keyPressed = false;

            if (key == KeyEvent.VK_SPACE) {

            }

        }

        @Override
        public void keyPressed(KeyEvent e) {

        	keyPressed = true;
            key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

            }

        }
    }

    public Board() {

        initBoard();
    }

    private void InitializeAssets() {

        fileMenu = new FileMenu();
        editMenu = new EditMenu();
        shapeToolbar = new ShapeToolbar();
        colorToolbar = new ColorToolbar();
        layerToolbar = new LayerToolbar();
        drawMenu = new DrawMenu();
        grid = new Grid();
        redoQueue = new Queue();
    }

    private void initBoard() {

    	addMouseListener( this );
    	addMouseMotionListener( this );
    	addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        try {
            for (int i=layerToolbar.layerslist.size()-1; i>=0; i--) {

                for (Shape s : layerToolbar.layerslist.get(i).shapes){
                    s.draw(g);
                }
            }
        }
        catch (Exception e){
            return;
        }


        shapes = layerToolbar.layerslist.get(layerToolbar.currentlyPressedLayerIndex).shapes;
        redoQueue = layerToolbar.layerslist.get(layerToolbar.currentlyPressedLayerIndex).redoQueue;


        try {

            fDraw.draw(g);

        }
        catch (Exception e){

            return;
        }




        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 2000, 125);
        g.fillRect(975, 0, 2000, 2000);



        g.setColor(new Color(235,184,64));
        g.fillRect(0, 125, 975, 4); //border line

        grid.paint(g);
        grid.drawGrid(g);
        fileMenu.paint(g,this);
        editMenu.paint(g,this);




        drawMenu.paint(g,this);
        colorToolbar.paint(g, this);
        shapeToolbar.paint(g,this);
        layerToolbar.paint(g, this);

        fc = colorToolbar.Color1.getColor();
        sc = colorToolbar.Color2.getColor();

        if (colorToolbar.Color1.IsPressed()) {
            fDraw.setColor(fc);
        }else
            fDraw.setColor(sc);

        try{
            shape.draw(g);
        }
        catch(Exception e){
            return;
        }

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 2000, 125);
        g.fillRect(975, 0, 2000, 2000);

        g.setColor(new Color(235,184,64));
        g.fillRect(0, 125, 975, 4); //border line

        grid.paint(g);
        grid.drawGrid(g);

        fileMenu.paint(g,this);
        editMenu.paint(g,this);
        drawMenu.paint(g,this);
        colorToolbar.paint(g, this);
        shapeToolbar.paint(g,this);
        layerToolbar.paint(g, this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    public void IsClicked(int x, int y)
    {
        fileMenu.IsClicked(x,y);
        if(fileMenu.New.IsPressed()){
            reset();
        }

        editMenu.IsClicked(x, y);
        if (editMenu.Undo.IsPressed() || rightMousePressed) {
            shape=null;

            try{
                Shape s =  shapes.remove(shapes.size()-1);
                redoQueue.enqueue(s);

            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Undo stack is empty!");

            }

        }

        if ((editMenu.Redo.IsPressed() || middleMousePressed) && !redoQueue.isEmpty()){
            shapes.add(redoQueue.removeLast());
        }

        shapeToolbar.IsClicked(x,y);
        if (shapeToolbar.isAnyPressed()){
            drawMenu.pencil.reset();
        }
        colorToolbar.IsClicked(x,y);

        layerToolbar.IsClicked(x,y);
        drawMenu.IsClicked(x,y);



        if (drawMenu.pencil.IsPressed())
            shapeToolbar.resetAll();

        grid.IsClicked(x, y);

    }


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		IsClicked(e.getX(), e.getY());
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

            x_init = e.getX();
            y_init = e.getY();
            mousePressed = true;
            start_drawing = true;
            c.x = x_init;
            c.y = y_init;

            if(SwingUtilities.isRightMouseButton(e)){
                rightMousePressed = true;
            }
            else{
                rightMousePressed = false;
            }

            if (SwingUtilities.isMiddleMouseButton(e)){
                middleMousePressed = true;
            }
            else{
                middleMousePressed = false;
            }




	}

	@Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        mousePressed = false;

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (dragged) { // Check if mouse was dragged
                if (shapeToolbar.Circle.IsPressed()) {
                    r.x = e.getX();
                    r.y = e.getY();
                    shape = new Circle(c, r, fc, sc);
                    shapes.add(shape);
                } else if (shapeToolbar.Rectangle.IsPressed()) {
                    r.x = e.getX();
                    r.y = e.getY();
                    shape = new Rectangle(c, r, fc, sc);
                    shapes.add(shape);
                } else if (shapeToolbar.Hexagon.IsPressed()) {
                    r.x = e.getX();
                    r.y = e.getY();
                    shape = new Hexagon(c, r, fc, sc);
                    shapes.add(shape);
                } else if (shapeToolbar.Eqtriangle.IsPressed()) {
                    r.x = e.getX();
                    r.y = e.getY();
                    shape = new EquilateralTriangle(c, r, fc, sc);
                    shapes.add(shape);
                } else if (shapeToolbar.RAtriangle.IsPressed()) {
                    r.x = e.getX();
                    r.y = e.getY();
                    shape = new RATriangle(c, r, fc, sc);
                    shapes.add(shape);
                } else if (shapeToolbar.Pentagram.IsPressed()) {
                    r.x = e.getX();
                    r.y = e.getY();
                    shape = new Pentagram(c, r, fc, sc);
                    shapes.add(shape);
                } else if (drawMenu.pencil.IsPressed()) {
                    shapes.add(fDraw);
                    fDraw = new freeDraw();
                }
            redoQueue.clear();
            }
        }
        if(shape!=null){
            if (drawMenu.lowStroke.IsPressed()){
                shape.setStrokeThickness(5);
            }
            else if (drawMenu.medStroke.IsPressed()){
                shape.setStrokeThickness(10);
            }
            else if (drawMenu.largeStroke.IsPressed()){
                shape.setStrokeThickness(20);
            }}



        shape = null;
        dragged = false;
    }







	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {

        r.x = e.getX();
        r.y = e.getY();



        dragged = true;

        if (shapeToolbar.Circle.IsPressed()){
            shape = new Circle(c, r, fc, sc);
        }

        if (shapeToolbar.Rectangle.IsPressed()) {
            shape = new Rectangle(c, r, fc, sc);
        }

        if (shapeToolbar.Hexagon.IsPressed()){
            shape = new Hexagon(c, r, fc, sc);
        }

        if (shapeToolbar.Eqtriangle.IsPressed()){
            shape = new EquilateralTriangle(c, r, fc, sc);
        }

        if (shapeToolbar.RAtriangle.IsPressed()){
            shape = new RATriangle(c, r, fc, sc);
        }

        if (shapeToolbar.Pentagram.IsPressed()){
            shape = new Pentagram(c, r, fc, sc);
        }



        if(drawMenu.pencil.IsPressed()){
            fDraw.points.add((e.getPoint()));
            if (drawMenu.lowStroke.IsPressed()){
                fDraw.setStroke(5);
            }
            else if (drawMenu.medStroke.IsPressed()){
                fDraw.setStroke(10);
            }
            else if(drawMenu.largeStroke.IsPressed()){
                fDraw.setStroke(20);
            }
            fDraw.points.add((e.getPoint()));
        }

        if(shape!=null){
            if (drawMenu.lowStroke.IsPressed()){
                shape.setStrokeThickness(5);
            }
            else if (drawMenu.medStroke.IsPressed()){
                shape.setStrokeThickness(10);
            }
            else if (drawMenu.largeStroke.IsPressed()){
                shape.setStrokeThickness(20);
            }
        }

        }
    }

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
        grid.IsHovered(e.getX(), e.getY());
        shapeToolbar.IsHovered(e.getX(), e.getY());
        drawMenu.IsHovered(e.getX(),e.getY());
        fileMenu.IsHovered(e.getX(),e.getY());
        editMenu.IsHovered(e.getX(),e.getY());
        layerToolbar.IsHovered(e.getX(),e.getY());
        colorToolbar.IsHovered(e.getX(),e.getY());
	}

    public void reset() {
        fileMenu = new FileMenu();
        editMenu = new EditMenu();
        shapeToolbar = new ShapeToolbar();
        colorToolbar = new ColorToolbar();
        layerToolbar = new LayerToolbar();
        drawMenu = new DrawMenu();
        grid = new Grid();
        redoQueue = new Queue();
        shapes = new ArrayList<>();
    }
}