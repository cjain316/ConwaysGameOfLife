import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Grid {
	private int[][] grid;
	private int size;
	
	public Grid(int size) {
		this.size = size;
		grid = new int[size*32][size*18];
		
		for (int a = 0; a < grid.length;a++) {
		for (int b = 0; b < grid[0].length;b++) {
			grid[a][b] = 0;
		}}
	}
	
	
	public void reset() {
		for (int a = 0; a < grid.length;a++) {
		for (int b = 0; b < grid[0].length;b++) {
			grid[a][b] = 0;
		}}
	}
	
	public void toggle(int x, int y) {
		if (grid[x][y] == 0) {grid[x][y] = 1;}
		else if (grid[x][y] == 1) {grid[x][y] = 0;}
	}
	
	public void update() {
		int[][] newGrid = new int[grid.length][grid[0].length];
		for (int a = 0; a < grid.length;a++) {for (int b = 0; b < grid[0].length;b++) {
			newGrid[a][b] = 0;
		}}
		
		
		for (int a = 0; a < grid.length;a++) {
		for (int b = 0; b < grid[a].length;b++) {
			if (getSurrounding(a,b) == 2 && grid[a][b] == 1) {newGrid[a][b] = 1;}
			if (getSurrounding(a,b) == 3) {newGrid[a][b] = 1;}
		}}
		for (int a = 0; a < grid.length;a++) {
			for (int b = 0; b < grid[0].length;b++) {
				grid[a][b] = newGrid[a][b];
		}}
	}
	
	private int getSurrounding(int x, int y) {
		int sum = 0;
		
		try {if (grid[x-1][y-1] == 1) {sum++;}} catch(Exception e) {}
		try {if (grid[x][y-1] == 1) {sum++;}} catch(Exception e) {}
		try {if (grid[x+1][y-1] == 1) {sum++;}} catch(Exception e) {}
		try {if (grid[x-1][y] == 1) {sum++;}} catch(Exception e) {}
		try {if (grid[x+1][y] == 1) {sum++;}} catch(Exception e) {}
		try {if (grid[x-1][y+1] == 1) {sum++;}} catch(Exception e) {}
		try {if (grid[x][y+1] == 1) {sum++;}} catch(Exception e) {}
		try {if (grid[x+1][y+1] == 1) {sum++;}} catch(Exception e) {}
		

		return sum;
	}
	
	public int getLength()      {return grid.length;}
	public int getHeight()      {return grid[0].length;}
	public int getValue(int x, int y)      {return grid[x][y];}
	public int getSize() {return size;}
	
	public void setValue(int x, int y, int val)   {grid[x][y] = val;}
	
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		for (int a = 0; a < grid.length; a++) {for (int b = 0; b < grid[a].length;b++) {
			g.drawLine(0, b*(900/grid[a].length), 1600, b*(900/grid[a].length));
			if (grid[a][b] == 1) {
				g.setColor(Color.WHITE);
				g.fillRect(a*(1600/grid.length), b*(900/grid[a].length), 900/grid[a].length, 900/grid[a].length);
				g.setColor(Color.GRAY);
			}
		}
			g.drawLine(a*(1600/grid.length), 0, a*(1600/grid.length), 900);
		}
		g.drawLine(1600, 0, 1600, 900);
		g.drawLine(0, 900, 1600, 900);
	}
}
