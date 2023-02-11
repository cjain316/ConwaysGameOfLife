import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Selector {
	private int x,y;
	private int affline;
	private int size;
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	
	public int getX() {return x;}
	public int getY() {return y;}
	
	public Selector(Grid g) {
		affline = 1600/g.getLength();
		size = affline/2;
		x = 0;
		y = 0;
	}
	
	public void update(Point p, boolean clicking, Grid g, boolean drawing) {
		if ((int)p.getY()/(1600/g.getLength()) < g.getHeight()) {
			x = (int)p.getX()/(1600/g.getLength());
			y = (int)p.getY()/(900/g.getHeight());
		}
		
		
		if (clicking && drawing) {
			g.setValue(x, y, 1);
		}
		if (clicking && !drawing) {
			g.setValue(x, y, 0);
		}
	}
	
	public void paint(Graphics g, Grid f) {
		g.setColor(new Color(100,100,100));
		g.fillRect((x*affline+size/2), (y*affline+size/2), size, size);
	}
	
	public void moveX(boolean right, Grid g) {
		if (right && (x+1 < g.getLength())) {x++;}
		if (!right && (x-1 >= 0)) {x--;}
	}
	public void moveY(boolean up, Grid g) {
		if (up && (y-1 >= 0)) {y--;}
		if (!up && (y+1 <= g.getHeight()-1)) {y++;}
	}
}
