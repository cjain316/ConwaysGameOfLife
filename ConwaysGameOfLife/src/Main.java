

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main extends JPanel implements KeyListener, ActionListener, MouseListener {
	Grid grid = new Grid(2);
	Selector s = new Selector(grid);
	private AffineTransform tx;
	boolean PLAYING = false;
	private Image Sprite;
	int COUNTER = 0;
	boolean CLICKING = false;
	int type;
	boolean DRAWING = true;
	private int TICKRATE = 25;
	private Rectangle cursor = new Rectangle(0,0,4,4);
	private Rectangle pausebutton = new Rectangle(1500,916,256,96);
	private Rectangle speedbutton = new Rectangle(550,916,256,96);
	private Rectangle resetbutton = new Rectangle(1100,916,256,96);
	private Rectangle speedbutton1 = new Rectangle(900,916,128,96);
	private Rectangle togglebutton = new Rectangle(150,916,256,96);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main f = new Main();
	}
	
	public void paint(Graphics g) {
        super.paintComponent(g);
        COUNTER++;
        Graphics2D g2 = (Graphics2D) g;
        
        
        PointerInfo p = MouseInfo.getPointerInfo();
        Point point = p.getLocation();
        SwingUtilities.convertPointFromScreen(point, getFocusCycleRootAncestor());
        point.setLocation(point.getX()-5,point.getY()-30);
        
        //Painting
        g.setColor(new Color(45,45,45));
        g.fillRect(0, 0, 2000, 2000);
        grid.paint(g);
        s.update(point, CLICKING, grid,DRAWING);
        cursor.setLocation(point);
        s.paint(g, grid);
        if (COUNTER >= TICKRATE) {
        	COUNTER = 0;
        	if (PLAYING) {grid.update();}
        }
        
        tx = AffineTransform.getTranslateInstance(1800, 30);
        if (!PLAYING) {Sprite = getImage("Resources\\\\Pause.png");}
        if (PLAYING) {Sprite = getImage("Resources\\\\play.png");}
		g2.drawImage(Sprite, tx, null);
		
		tx = AffineTransform.getTranslateInstance(1500, 916);
		Sprite = getImage("Resources\\\\pause button.png");
		g2.drawImage(Sprite, tx, null);
		
		tx = AffineTransform.getTranslateInstance(1100, 916);
		Sprite = getImage("Resources\\\\resetbutton.png");
		g2.drawImage(Sprite, tx, null);
		
		tx = AffineTransform.getTranslateInstance(150, 916);
		Sprite = getImage("Resources\\\\togglebutton.png");
		g2.drawImage(Sprite, tx, null);
		
		tx = AffineTransform.getTranslateInstance(30, 30);
        if (!DRAWING) {Sprite = getImage("Resources\\\\eraser.png");}
        if (DRAWING) {Sprite = getImage("Resources\\\\pencil.png");}
		g2.drawImage(Sprite, tx, null);
		
		tx = AffineTransform.getTranslateInstance(550, 916);
		Sprite = getImage("Resources\\\\speedbutton.png");
		g2.drawImage(Sprite, tx, null);
		
		
		String path = "Resources\\\\"+TICKRATE+".png";
		tx = AffineTransform.getTranslateInstance(900, 916);
		Sprite = getImage(path);
		g2.drawImage(Sprite, tx, null);
        
	}
	
	private void toggleSpeed() {
		TICKRATE += 10;
		if (TICKRATE>60) {TICKRATE= 5;}
	}
	
	private void place() {
		grid.setValue(s.getX(), s.getY(), 1);
	}
	
	private boolean toggle(boolean b) {
		return !b;
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getExtendedKeyCode());
		if (arg0.getExtendedKeyCode() == 87) {s.moveY(true,grid);}
		if (arg0.getExtendedKeyCode() == 65) {s.moveX(false,grid);}
		if (arg0.getExtendedKeyCode() == 83) {s.moveY(false,grid);}
		if (arg0.getExtendedKeyCode() == 68) {s.moveX(true,grid);}
		if (arg0.getExtendedKeyCode() == 32) {DRAWING = toggle(DRAWING);}
		if (arg0.getExtendedKeyCode() == 80) {PLAYING = toggle(PLAYING);}
		if (arg0.getExtendedKeyCode() == 82) {grid.reset();}
		if (arg0.getExtendedKeyCode() == 39) {if (TICKRATE-10 > 0) {TICKRATE-=10;}}
		if (arg0.getExtendedKeyCode() == 37) {if (TICKRATE+10 < 60) {TICKRATE+=10;}}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	Timer t;
    
    public Main() {
    	
        JFrame f = new JFrame("Conway's Game of Life");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1617,1140);

        f.add(this);
        f.addMouseListener(this);
        f.addKeyListener(this);
        f.setResizable(false);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        t = new Timer(1, this);
        t.start();
        f.setVisible(true);
        
       
        
    }
    
    private void update() {

    }
    
    protected Image getImage(String path) {

        Image tempImage = null;
        try {
            URL imageURL = Background.class.getResource(path);
            tempImage    = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {e.printStackTrace();}
        return tempImage;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		CLICKING = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		CLICKING = false;
		if (cursor.intersects(pausebutton)) {PLAYING = toggle(PLAYING);}
		if (cursor.intersects(resetbutton)) {grid.reset();}
		if (cursor.intersects(togglebutton)) {DRAWING = toggle(DRAWING);}
		if (cursor.intersects(speedbutton) || cursor.intersects(speedbutton1)) {toggleSpeed();}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 

}
