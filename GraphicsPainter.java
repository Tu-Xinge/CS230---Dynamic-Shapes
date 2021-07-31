package cs230.A2Code;/*
 *  ============================================================================================
 *  Text version of a painter
 *  YOUR UPI: xtu424
 *  ============================================================================================
 */

import java.awt.*;
public class GraphicsPainter implements Painter {
	private Graphics2D g;
	public GraphicsPainter() { }
	public void setGraphics(Graphics g) {
		this.g = (Graphics2D)g;
	}
	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}
	@Override
	public void drawRect(int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}
	@Override
	public void fillRect(int x, int y, int width, int height) {
		g.fillRect(x, y, width, height);
	}
	@Override
	public void drawOval(int x, int y, int width, int height) {
		g.drawOval(x, y, width, height);
	}
	@Override
	public void fillOval(int x, int y, int width, int height) {
		g.fillOval(x, y, width, height);
	}
	@Override
	public void setPaint(Color c) {
		g.setPaint(c);
	}
	@Override
	public void drawHandles(boolean isSelected, int x, int y, int width, int height) {
		if (isSelected) {
            g.setPaint(Color.black);
            g.fillRect(x -2, y-2, 4, 4);
            g.fillRect(x + width -2, y + height -2, 4, 4);
            g.fillRect(x -2, y + height -2, 4, 4);
            g.fillRect(x + width -2, y-2, 4, 4);
        }
	}
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, width, height, null);
		
	}
}
