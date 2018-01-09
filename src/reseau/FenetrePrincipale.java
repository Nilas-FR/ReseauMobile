package reseau;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import map.MyMap;

/**
 * Classe qui g�re la fen�tre principale
 * Rassemble tous les �l�ments
 * @author Adrien COUCHOT
 * @version 1.0
 */
public class FenetrePrincipale extends JFrame{
	
    private final FenetrePrincipale FP;
    public final MyMap.Gui gui;
	public final Options fOptions;
	public final Graphe fGraphe;
	public final FichierAntenne fichier;
	
	public Vector<Antenne> Antennes = new Vector<Antenne>();
	public JMenuBar menuBar;
	
	/**
	 * Fen�tre principale du programme
	 */
	public FenetrePrincipale(){
		super("Projet R�seau Mobile");
                FP = this;
		
		fOptions = new Options(this);
		fGraphe = new Graphe(this);
		fichier = new FichierAntenne(this);
		
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        
        gui = new MyMap.Gui();
        menuBar = gui.createMenuBar();
		setJMenuBar(menuBar);
		
		add(fGraphe, BorderLayout.SOUTH);
		add(fOptions, BorderLayout.NORTH);
        
		pack();
        setVisible(true);
        fichier.charger();
        
        addWindowListener(new WindowListener() {
            //I skipped unused callbacks for readability

                    @Override
                    public void windowClosing(WindowEvent e) {
                        if(JOptionPane.showConfirmDialog(FP, "�tes-vous s�r de vouloir quitter ?") == JOptionPane.OK_OPTION){
                            fichier.sauvegarder();
                            FP.setVisible(false);
                            FP.dispose();
                        }
                    }

                    @Override
                    public void windowOpened(WindowEvent e) {}

                    @Override
                    public void windowClosed(WindowEvent e) {}

                    @Override
                    public void windowIconified(WindowEvent e) {}

                    @Override
                    public void windowDeiconified(WindowEvent e) {}

                    @Override
                    public void windowActivated(WindowEvent e) {}

                    @Override
                    public void windowDeactivated(WindowEvent e) {}
        });
	}
}
