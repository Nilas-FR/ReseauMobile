package reseau;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Classe qui la fenêtre affichant la liste des antennes
 * @author Adrien COUCHOT
 * @version 1.1
 * modification de Jonathan pour ajouter les bordures
 */
public class listeAntenne {
	
	public listeAntenne(FenetrePrincipale FP) {
		Dialog fLisAnten = new JDialog(FP);
		fLisAnten.setUndecorated(true);
		JPanel panel = (JPanel)((JDialog) fLisAnten).getContentPane();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		panel.setBorder(raisedbevel);
		fLisAnten.setAlwaysOnTop(true);
		fLisAnten.setTitle("Liste des antennes");
		JList<Antenne> liste = new JList<Antenne>(FP.Antennes);
		
		JPanel opt = new JPanel();
		JButton bQuit = new JButton("Fermer");
		JButton bSupp = new JButton("Supprimer");
		
		bQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fLisAnten.setVisible(false);
				
			}
		});
		
		bSupp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = liste.getSelectedIndex();
				if(0>index || index>=FP.Antennes.size()) return;
				FP.Antennes.remove(index);
				liste.updateUI();
				FP.fGraphe.repaint();
			}
		});
		
		opt.add(bQuit);
		opt.add(bSupp);
		
		fLisAnten.setLayout(new GridLayout(2,1));
		fLisAnten.add(liste, BorderLayout.NORTH);
		fLisAnten.add(opt, BorderLayout.SOUTH);
		
		fLisAnten.pack();
		fLisAnten.setLocationRelativeTo(FP);
		fLisAnten.setVisible(true);
	}
}
