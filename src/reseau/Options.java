package reseau;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Adrien COUCHOT
 * @version 1.1
 * modification Jonathan pour ajouter le bouton pour remettre à l'échelle initiale
 */
public class Options extends JPanel {
	
	private FenetrePrincipale FP;
	
	public Options(FenetrePrincipale FP) {
		this.FP = FP;
		JButton bNouAnten1 = new JButton("Ajouter une antenne (Clavier)");
		JButton bLisAnten = new JButton("Voir la liste");
		JButton ResetGraph = new JButton("Remettre à l'echelle 1:1");
		
		add(bNouAnten1);
		//add(bNouAnten2);
		add(bLisAnten);
		add(ResetGraph);
		
        bNouAnten1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ajoutAntenne1(FP,Antenne.POSITIONX_DEFAULT,Antenne.POSITIONY_DEFAULT);
			}
		});        
        bLisAnten.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new listeAntenne(FP);
			}
		});
        /** Le bouton pour remettre à l'échelle 1:1
         * @author Jonathan BOERLEN
         */
        ResetGraph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FP.fGraphe.reset();
			}
		});
	}
	
	/**
	 * @author Adrien COUCHOT
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
    }

}
