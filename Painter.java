package cs230.A2Code;/*
 *  ============================================================================================
 *  Painter.java : Painter interface
 *  YOUR UPI: xtu424
 *  ============================================================================================
 */
import java.awt.*;
interface Painter {
	public void drawLine(int x1, int y1, int x2, int y2);
    public void drawRect(int x, int y, int width, int height);
    public void drawOval(int x, int y, int width, int height);
    public void fillRect(int x, int y, int width, int height);
    public void fillOval(int x, int y, int width, int height);
	public void setPaint(Color color);
	public void setGraphics(Graphics g);
	public void drawHandles(boolean isSelected, int x, int y, int width, int height);
    public void drawImage(Image img, int x, int y, int width, int height);
}