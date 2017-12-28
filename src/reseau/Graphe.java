package reseau;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

/**
 * 
 * @author Jonathan BOERLEN
 * @version 1.0
 */

public class Graphe extends JPanel{
	
	private FenetrePrincipale FP;
	private double zoom = 1;
	private int OrigineX=0, OrigineY=0;
	private int X=0, Y=0;
	
	public Graphe(FenetrePrincipale FP) {
		this.FP = FP;
		//FP.Antennes.elementAt(i) -> tableau contenant les antennes
		setPreferredSize(new Dimension(1200, 600));
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {if (e.getWheelRotation() < 0) zoom *= 1.25; else zoom *= 0.75; repaint();}});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				X = e.getX();
				Y = e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				OrigineX+=e.getX() - X;
				OrigineY+=e.getY() - Y;
				repaint();
				X = e.getX();
				Y = e.getY();
				
			}
	});
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.WHITE);
        //g.fillRect(0, 0, getWidth(), getHeight());
        for (Antenne antenne : FP.Antennes)
        {
        	tracerUneAntenne(g, antenne);	
        }
    }
	
	private void tracerUneAntenne(Graphics g, Antenne a) {
		g.setColor(Color.ORANGE);
		double PositionX = a.position_x;
        double PositionY = a.position_y;
        double Puissance = a.puissance;
        
        g.fillOval((int)((PositionX-(Puissance/2)+OrigineX) *zoom), (int)((PositionY-(Puissance/2)+OrigineY) *zoom), (int)(Puissance*zoom), (int)(Puissance*zoom));
    	g.setColor(Color.BLACK);
    	g.fillOval((int)((PositionX+OrigineX)*zoom), (int)((PositionY+OrigineY)*zoom), (int)(3*zoom), (int)(3*zoom));   
	}
	
	public void reset() {
		zoom = 1;
		OrigineX = 0;
		OrigineY = 0;
		repaint();
	}
}
