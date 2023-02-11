

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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
	boolean playing = false;
	private Image Sprite;
	int counter = 0;
	boolean clicking = false;
	int type;
	boolean drawing = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main f = new Main();
	}
	
	public void paint(Graphics g) {
        super.paintComponent(g);
        counter++;
        Graphics2D g2 = (Graphics2D) g;
        
        
        PointerInfo p = MouseInfo.getPointerInfo();
        Point point = p.getLocation();
        SwingUtilities.convertPointFromScreen(point, getFocusCycleRootAncestor());
        point.setLocation(point.getX()-5,point.getY()-30);
        
        //Painting
        g.setColor(new Color(45,45,45));
        g.fillRect(0, 0, 2000, 2000);
        grid.paint(g);
        s.update(point, clicking, grid,drawing);
        s.paint(g, grid);
        if (counter == 25) {
        	counter = 0;
        	if (playing) {grid.update();}
        }
        
        tx = AffineTransform.getTranslateInstance(1400, 920);
        if (!playing) {Sprite = getImage("Resources\\\\Pause.png");}
        if (playing) {Sprite = getImage("Resources\\\\play.png");}
		g2.drawImage(Sprite, tx, null);
		
		tx = AffineTransform.getTranslateInstance(1100, 916);
		Sprite = getImage("Resources\\\\pause button.png");
		g2.drawImage(Sprite, tx, null);
		
		tx = AffineTransform.getTranslateInstance(60, 920);
        if (!drawing) {Sprite = getImage("Resources\\\\eraser.png");}
        if (drawing) {Sprite = getImage("Resources\\\\pencil.png");}
		g2.drawImage(Sprite, tx, null);
        
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
		if (arg0.getExtendedKeyCode() == 32) {drawing = toggle(drawing);}
		if (arg0.getExtendedKeyCode() == 80) {playing = toggle(playing);}
		if (arg0.getExtendedKeyCode() == 82) {grid.reset();}
		
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
		clicking = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		clicking = false;
		
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
