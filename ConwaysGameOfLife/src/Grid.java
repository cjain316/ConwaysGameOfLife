import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Grid {
	private int[][] grid;
	
	public Grid(int size) {
		grid = new int[size*32][size*18];
		
		for (int a = 0; a < grid.length;a++) {
		for (int b = 0; b < grid[0].length;b++) {
			grid[a][b] = 0;
		}}
	}
	
	public int getLength()      {return grid.length;}
	public int getHeight()      {return grid[0].length;}
	public int getValue(int x, int y)      {return grid[x][y];}
	
	public void setValue(int x, int y, int val)   {grid[x][y] = val;}
	
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		for (int a = 0; a < grid.length; a++) {for (int b = 0; b < grid[a].length;b++) {
			g.drawLine(0, b*(900/grid[a].length), 1600, b*(900/grid[a].length));
		}
			g.drawLine(a*(1600/grid.length), 0, a*(1600/grid.length), 900);
		}
	}
}
