package cs230.A2Code;/*
 *    ==========================================================================================
 *    AnimationViewer.java : Moves shapes around on the screen according to different paths.
 *    It is the main drawing area where shapes are added and manipulated.
 *    YOUR UPI: xtu424
 *    ==========================================================================================
 */

import javax.swing.*;
import javax.swing.Painter;
import java.awt.*;
import java.awt.Shape;
import java.util.ArrayList;
import java.awt.event.*;

public class AnimationViewer extends JComponent implements Runnable {
	private Thread animationThread = null;    // the thread for animation
    private static int DELAY = 20;         // the current animation speed
	private ArrayList<java.awt.Shape> shapes = new ArrayList<java.awt.Shape>(); //create the ArrayList to store shapes
	private Painter painter = (Painter) new GraphicsPainter();
	private ShapeType currentShapeType= java.awt.Shape.DEFAULT_SHAPETYPE; // the current shape type,
	private PathType currentPathType= java.awt.Shape.DEFAULT_PATHTYPE;  // the current path type
	private Color currentBorderColor= java.awt.Shape.DEFAULT_BORDER_COLOR, currentFillColor= java.awt.Shape.DEFAULT_FILL_COLOR;  // the current fill colour of a shape
	private int marginWidth= java.awt.Shape.DEFAULT_MARGIN_WIDTH, marginHeight = java.awt.Shape.DEFAULT_MARGIN_HEIGHT, currentWidth= java.awt.Shape.DEFAULT_WIDTH, currentHeight= java.awt.Shape.DEFAULT_HEIGHT;
	private String currentImageFileName = java.awt.Shape.DEFAULT_IMAGE_FILENAME;
	/** Constructor of the AnimationPanel */
    public AnimationViewer(boolean isGraphicsVersion) {
		if (isGraphicsVersion) {
			start();
			addMouseListener(new MyMouseAdapter());
		}
	}

    /** create a new shape
     * @param x     the x-coordinate of the mouse position
     * @param y    the y-coordinate of the mouse position */
    protected void createNewShape(int x, int y) {
		switch (currentShapeType) {
			case RECTANGLE:
				RectangleShape r1 = new RectangleShape(x,y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType);
				shapes.add(r1);
				break;
			case XRECTANGLE:
				XRectangleShape r2 = new XRectangleShape(x,y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType);
				shapes.add(r2);
				break;
			case SQUARE:
				SquareShape r3 = new SquareShape(x,y,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType);
				shapes.add(r3);
				break;
			case OVAL:
				OvalShape r4 = new OvalShape(x,y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType);
				shapes.add(r4);
				break;
			case DYNAMIC:
				DynamicRectangleShape r5 = new DynamicRectangleShape(x,y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType);
				shapes.add(r5);
				break;
			case IMAGE:
				ImageRectangleShape r6 = new ImageRectangleShape(x,y,currentWidth,currentHeight,marginWidth,marginHeight,currentBorderColor,currentFillColor,currentPathType, currentImageFileName);
				shapes.add(r6);
				break;
			default: break;
					
		}
        // currentShapeType = currentShapeType.next(); //choose the next shape
        // currentPathType = currentPathType.next();           //choose the next path
    }
    /**    move and paint all shapes within the animation area
     * @param g    the Graphics control */
    public void paintComponent(Graphics g) {
		((GraphicsPainter)painter).setGraphics(g);
		super.paintComponent(g);
        for (java.awt.Shape currentShape: shapes) {
            currentShape.move();
		    currentShape.draw(painter);
		    currentShape.drawHandles(painter);
		}
    }
    // add a list of set methods: -- T
	public ShapeType getCurrentShapeType(){
		return currentShapeType;
	} 
	public PathType getCurrentPathType(){
		return currentPathType;
	}
	public void setCurrentShapeType(int index){
		currentShapeType = ShapeType.values()[index];
	}
	public void setCurrentPathType(int index){
		currentPathType = PathType.values()[index];
	}
	public void setCurrentWidth(int w){
		currentWidth = w;
		for(java.awt.Shape s: shapes){
			if(s.isSelected()){s.setWidth(w);}
			} 
		}
	public void setCurrentHeight(int h){
		currentHeight = h;
		for(java.awt.Shape s: shapes){
			if(s.isSelected()){s.setHeight(h);}
			} 
	}
	public void setCurrentFillColor(Color fc){
		currentFillColor = fc;
		for(java.awt.Shape s: shapes){
			if(s.isSelected()){s.setFillColor(fc);}
			} 
		
	}
	
	public void setCurrentBorderColor(Color bc){
		currentBorderColor = bc;
		for(java.awt.Shape s: shapes){
			if(s.isSelected()){s.setBorderColor(bc);}
			 
		}
	}

// you don't need to make any changes after this line ______________
	/** get the current width
	 * @return currentWidth - the width value */
	public int getCurrentWidth() { return currentWidth; }
	/** get the current height
	 * @return currentHeight - the height value */
	public int getCurrentHeight() { return currentHeight; }
	/** get the current fill colour
	 * @return currentFillColor - the fill colour value */
	public Color getCurrentFillColor() { return currentFillColor; }
	/** get the current border colour
	 * @return currentBorderColor - the border colour value */
	public Color getCurrentBorderColor() { return currentBorderColor; }

	/* Inner member class for mouse event handling */
    class MyMouseAdapter extends MouseAdapter {
		public void mouseClicked( MouseEvent e ) {
			boolean found = false;
			for (java.awt.Shape currentShape: shapes)
				if ( currentShape.contains( e.getPoint()) ) { // if the mousepoint is within a shape, then set the shape to be selected/deselected
					currentShape.setSelected( ! currentShape.isSelected() );
					found = true;
				}
			if (!found) createNewShape(e.getX(), e.getY());
		}
	}
    /**    update the painting area
     * @param g    the graphics control */
    public void update(Graphics g){ paint(g); }
    /** reset the margin size of all shapes from our ArrayList */
    public void resetMarginSize() {
        marginWidth = getWidth();
        marginHeight = getHeight() ;
        for (Shape currentShape: shapes)
			currentShape.setMarginSize(marginWidth,marginHeight );
    }
  public void start() {
        animationThread = new Thread(this);
        animationThread.start();
    }
    public void stop() {
        if (animationThread != null) {
            animationThread = null;
        }
    }
    public void run() {
        Thread myThread = Thread.currentThread();
        while(animationThread==myThread) {
            repaint();
            pause(DELAY);
        }
    }
    private void pause(int milliseconds) {
        try {
            Thread.sleep((long)milliseconds);
        } catch(InterruptedException ie) {}
    }
}
