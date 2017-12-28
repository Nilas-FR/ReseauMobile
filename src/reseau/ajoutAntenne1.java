package reseau;


import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ajoutAntenne1 {
	
	public ajoutAntenne1(FenetrePrincipale FP) {
		Dialog fNouAnten1 = new JDialog(FP);
		fNouAnten1.setUndecorated(true);
		JPanel panel = (JPanel)((JDialog) fNouAnten1).getContentPane();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		panel.setBorder(raisedbevel);
		fNouAnten1.setBackground(Color.BLACK);
        fNouAnten1.setAlwaysOnTop(true);
        fNouAnten1.setTitle("Ajouter une antenne");
        fNouAnten1.setLayout(new GridLayout(6,2));
        
        JLabel lTextNom = new JLabel("Nom : ");
        JLabel lTextPosX = new JLabel("Position X : ");
        JLabel lTextPosY = new JLabel("Position Y : ");
        JLabel lTextPuis = new JLabel("Puissance : ");
        JLabel lTextFreq = new JLabel("Fréquence : ");
        
        JTextField tNom = new JTextField("DefaultName");
        JTextField tPosX = new JTextField(Antenne.POSITIONX_DEFAULT+"");
        JTextField tPosY = new JTextField(Antenne.POSITIONY_DEFAULT+"");
        JTextField tPuis = new JTextField(Antenne.PUISSANCE_DEFAULT+"");
        JTextField tFreq = new JTextField(Antenne.FREQUENCE_DEFAULT+"");
        
        JButton valider = new JButton("Valider");
        JButton annuler = new JButton("Annuler");
        
        fNouAnten1.add(lTextNom);
        fNouAnten1.add(tNom);
        fNouAnten1.add(lTextPosX);
        fNouAnten1.add(tPosX);
        fNouAnten1.add(lTextPosY);
        fNouAnten1.add(tPosY);
        fNouAnten1.add(lTextPuis);
        fNouAnten1.add(tPuis);
        fNouAnten1.add(lTextFreq);
        fNouAnten1.add(tFreq);
        fNouAnten1.add(valider);
        fNouAnten1.add(annuler);
        
        annuler.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                        fNouAnten1.setVisible(false);

                }
        });
        
        valider.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                        String nom = tNom.getText();
                        String posx = tPosX.getText();
                        String posy = tPosY.getText();
                        String puis = tPuis.getText();
                        String freq = tFreq.getText();
                        fNouAnten1.setVisible(false);
                        //Faire des exceptions
                        FP.Antennes.add(new Antenne(nom, Double.parseDouble(posx), Double.parseDouble(posy), Double.parseDouble(puis), Double.parseDouble(freq)));
                        FP.fGraphe.repaint();
                }
        });
        
        fNouAnten1.pack();
        fNouAnten1.setLocationRelativeTo(FP);
        fNouAnten1.setVisible(true);
	}
}
