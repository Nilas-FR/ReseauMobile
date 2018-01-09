package reseau;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Classe qui g�re le chargement et la sauvegarde dans un fichier des antennes
 * @author Adrien COUCHOT
 * @version 1.0
 */
public class FichierAntenne {
	
	/**
	 * Lien vers la fen�tre principale pour acc�der aux variables globale et �tre accessible
	 */
	private final FenetrePrincipale FP;
	/**
	 * Fichier dans lequel on conserve les donn�es
	 */
	private final File f = new File("_data.txt");
	/**
	 * Charact�re pour s�parer les lignes
	 */
	private final char cFinLigne = '�';
	
	/**
	 * Constructeur
	 * @param FP Lien vers la fen�tre principale
	 */
	public FichierAntenne(FenetrePrincipale FP) {
		this.FP = FP;
	}
	
	/**
	 * Sauvegarde les donn�es dans le fichier
	 * Peut �tre utilis� plusieurs fois
	 * Est appel� par un bouton
	 */
	public void sauvegarder() {
		try {
			FileWriter fw = new FileWriter(f);
			for (int i = 0; i < FP.Antennes.size(); i++){
				fw.write(FP.Antennes.elementAt(i).toString()+cFinLigne);
			}
				
			fw.close();
		} catch (Exception e) {
			System.out.println("ERREUR (FICHIER) : Sauvegarde non effectu�e.");
			//e.printStackTrace();
		}
	}
	
	/**
	 * Charge les donn�es du fichier
	 * Est utilis� une seule fois
	 * Est appel� lors au constructeur de la fen�tre principale
	 */
	public void charger() {
		try {
			FileReader fr = new FileReader(f);
			int c;
			String ligne ="";
			while ((c = fr.read()) >= 0) {
				if ((char) c != cFinLigne) ligne +=(char) c;
				else {
					String nom = ligne.split(" ")[0];
					String pos = ligne.split(" ")[1];
					double posx = Double.parseDouble(pos.split(",")[0].substring(1));
					double posy = Double.parseDouble(pos.split(",")[1].substring(0,pos.split(",")[1].length()-1));
					double puissance = Double.parseDouble(ligne.split(" ")[2]);
					double frequence = Double.parseDouble(ligne.split(" ")[3]);
					FP.Antennes.addElement(new Antenne(nom,posx,posy,puissance,frequence));
					ligne = "";
				}
			}
			fr.close();
		} catch (Exception e) {
			System.out.println("ERREUR (FICHIER) : Pas de fichier '_data' trouv�.");
			//e.printStackTrace();
		}
	}
	
	/**
	 * M�thode par d�fault d'un objet
	 * @return le chemin o� le fichier est sauvegard�
	 */
	@Override
	public String toString(){
		return f.getAbsolutePath();
	}

}
