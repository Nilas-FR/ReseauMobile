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
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, getWidth(), getHeight());
    }
}
