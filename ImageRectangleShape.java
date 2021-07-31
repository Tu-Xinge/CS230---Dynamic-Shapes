package cs230.A2Code;/*
 *	===============================================================================
 *	ImageRectangleShape.java : A shape that is a image.
 *  YOUR UPI: xtu424
 *	=============================================================================== */

import java.awt.*;
import java.net.URL;

public class ImageRectangleShape extends RectangleShape{
    private Image image;
    private String imageFilename;
    public ImageRectangleShape(String i){imageFilename = i; image = loadImage(i); }
    public ImageRectangleShape(int x,int y,int width,int height,int margin_w,int margin_h,Color border_color,Color fill_color,PathType path_type,String i){
        super(x, y,width,height, margin_w,margin_h,border_color,fill_color,path_type);
        this.imageFilename = i;
        image = loadImage(i);
        
    }
    public Image loadImage(String filename){
        URL url = A2.class.getResource(imageFilename);
        image = Toolkit.getDefaultToolkit().createImage(url);
        return image;
    }
    public void draw(Painter g){
        g.drawImage(image, x, y, width, height);
        g.setPaint(borderColor);
        g.drawRect(x,y,width,height);
    }
}