/*
 *	===============================================================================
 *	NestedShape.java : A shape that is a nested.
 *  YOUR UPI: xtu424
 *  Student Name: Xinge Tu
 *  Date: 06/06
 *	=============================================================================== */

import java.awt.*;
import java.util.ArrayList;


public class NestedShape extends Shape{
    private ArrayList<Shape> nestedShapes;
    private static ShapeType nextShapeType = ShapeType.NESTED;
    
    public NestedShape() {
        super();
        nestedShapes = new ArrayList<Shape>();
    }
    public NestedShape(ArrayList<Shape> a, Color bc, Color fc) {
		super();
		this.setWidth(super.DEFAULT_MARGIN_WIDTH);
		this.setHeight(super.DEFAULT_MARGIN_HEIGHT);
		this.setBorderColor(bc);
		this.setFillColor(fc);
		nestedShapes = a;
	}
	public NestedShape(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, PathType pt) {
		super(x ,y ,w, h ,mw ,mh, bc, fc, pt);
		nestedShapes = new ArrayList<Shape>();
		nextShapeType = nextShapeType.next();
		createAddInnerShape(nextShapeType);
	}
	public NestedShape(int x, int y, int w, int h, int mw, int mh, Color bc, Color fc, PathType pt, String a) {
		super(x ,y ,w, h ,mw ,mh, bc, fc, pt, a);
		nestedShapes = new ArrayList<Shape>();
		nextShapeType = nextShapeType.next();
		createAddInnerShape(nextShapeType);
	}
	public void createAddInnerShape(ShapeType st){
	    if(st == ShapeType.RECTANGLE){
        	Shape inner = new RectangleShape(0,0,this.width/2,this.height/2, this.width,this.height,this.borderColor,this.fillColor,PathType.BOUNCE,this.text);
	        inner.parent = this;
	        nestedShapes.add(inner);
	    }else if(st == ShapeType.XRECTANGLE){
	        Shape inner = new XRectangleShape(0,0,this.width/2,this.height/2, this.width,this.height,this.borderColor,this.fillColor,PathType.BOUNCE,this.text);
	        inner.parent = this;
	        nestedShapes.add(inner);
	    }else if(st == ShapeType.OVAL){
	        Shape inner = new OvalShape(0,0,this.width/2,this.height/2, this.width,this.height,this.borderColor,this.fillColor,PathType.BOUNCE,this.text);
	        inner.parent = this;
	        nestedShapes.add(inner);
	    }else if(st == ShapeType.SQUARE){
	        SquareShape inner = new SquareShape(0,0,Math.min(this.width/2,this.height/2), this.width,this.height,this.borderColor,this.fillColor,PathType.BOUNCE,this.text);
	        inner.parent = this;
	        nestedShapes.add(inner);
	    }else if(st == ShapeType.NESTED){
	        NestedShape inner = new NestedShape(0,0,this.width/2,this.height/2, this.width,this.height,this.borderColor,this.fillColor,PathType.BOUNCE,this.text);
	        inner.parent = this;
	        nestedShapes.add(inner);
	    }
	    
	}
	public Shape getShapeAt(int index){return nestedShapes.get(index);}
	public int getSize(){return nestedShapes.size();}
	public void draw(Painter g2d) {
		g2d.setPaint(Color.black);
		g2d.drawRect(this.x, this.y, width, height);
		g2d.translate(this.x,this.y);
		for(Shape i: nestedShapes){
		    i.draw(g2d);
		}
		g2d.translate(-this.x,-this.y);
	}
	public boolean contains(Point mousePt) {
		return (x <= mousePt.x && mousePt.x <= (x + width + 1)	&&	y <= mousePt.y && mousePt.y <= (y + height + 1));
	}
    public void add(Shape s){
	    nestedShapes.add(s);
	    s.setParent(this);
	}
	public void remove(Shape s){
	    nestedShapes.remove(s);
	    s.setParent(null);
	}
    public int indexOf(Shape s){
        return nestedShapes.indexOf(s);
    }
    public Shape[] getChildren(){
        Shape[] s = new Shape[nestedShapes.size()];
        for(int i=0; i<nestedShapes.size(); i++){
		    s[i] = nestedShapes.get(i);
		}
		return s;
    }
    public void move(){
        super.move();
        for(Shape i: nestedShapes){
            i.move();
        }
        
        
    }
	
	
	
}
