package reseau;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Graphe extends JPanel{
	
	private FenetrePrincipale FP;
	
	public Graphe(FenetrePrincipale FP) {
		this.FP = FP;
		//FP.Antennes.elementAt(i) -> tableau contenant les antennes
		setPreferredSize(new Dimension(1200, 600));
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
        
        g.fillOval((int)PositionX-((int)Puissance/2), (int)PositionY-((int)Puissance/2), (int)Puissance, (int)Puissance);
    	g.setColor(Color.BLACK);
    	g.fillOval((int)PositionX, (int)PositionY, 3, 3);   
	}
}
