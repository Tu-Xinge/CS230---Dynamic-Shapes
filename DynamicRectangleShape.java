package cs230.A2Code;/*
 *	===============================================================================
 *	ImageRectangleShape.java : A shape that is a image.
 *  YOUR UPI: xtu424
 *	=============================================================================== */

import java.awt.*;
public class DynamicRectangleShape extends RectangleShape{
    private int check = 0;
    private Color s;
    public DynamicRectangleShape(){}
    public DynamicRectangleShape(int x,int y,int width,int height,int margin_w,int margin_h,Color border_color,Color fill_color,PathType path_type){
        super(x, y,width,height, margin_w,margin_h,border_color,fill_color,path_type);
        if(this.y+this.height == this.marginHeight || y< 0){
            s = fillColor;
            fillColor = borderColor;
            borderColor = s;
        }
    }
    public void draw(Painter g){
        if(y+height == marginHeight || y< 0){
            check += 1;
        }
        if(check>1){
            check = 0;
            s = fillColor;
            fillColor = borderColor;
            borderColor = s;   
        }
        g.setPaint(fillColor);
        g.fillRect(x,y,width,height);
        g.setPaint(borderColor);
        g.drawRect(x,y,width,height);

    }
}
