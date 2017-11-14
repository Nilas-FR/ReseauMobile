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

import dialog.ajoutAntenne1;
import dialog.listeAntenne;

/**
 * 
 * @author Jonathan BOERLEN et Adrien COUCHOT
 * @version 1.0
 */
public class Options extends JPanel {
	
	private FenetrePrincipale FP;
	
	/**
	 * @author Adrien COUCHOT
	 * @param FP
	 */
	public Options(FenetrePrincipale FP) {
		this.FP = FP;
		JButton bNouAnten1 = new JButton("Ajouter une antenne (Clavier)");
		JButton bNouAnten2 = new JButton("Ajouter une antenne (Graphique)");
		JButton bLisAnten = new JButton("Voir la liste");
		
		add(bNouAnten1);
		add(bNouAnten2);
		add(bLisAnten);
                
        bNouAnten1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ajoutAntenne1(FP);
			}
		});        
        bLisAnten.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new listeAntenne(FP);
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
