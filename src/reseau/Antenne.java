package reseau;

/**
 * Classe qui g�re les antennes
 * @author Adrien COUCHOT
 * @version 1.1
 * modification de Jonathan pour int�grer Frequence min et max
 */
public class Antenne {

	/**
	 * Nom de l'antenne
	 */
	public final String nom;
	/**
	 * Position X de l'antenne
	 */
	public final double position_x;
	/**
	 * Position Y de l'antenne
	 */
	public final double position_y;
	/**
	 * Puissance de l'antenne
	 */
	public final double puissance;
	/**
	 * Fr�quence de l'antenne
	 */
	public final double frequence;

	public static final double POSITIONX_DEFAULT = 0;
	public static final double POSITIONY_DEFAULT = 0;
	public static final double PUISSANCE_DEFAULT = 20;
	public static final double FREQUENCE_DEFAULT = 700;
	public static final double FREQUENCE_MIN = 700;
	public static final double FREQUENCE_MAX = 3800;
	
	/**
	 * Constructeur d�taill� d'une antenne
	 * @param nom Nom de l'antenne
	 * @param positionX Position X de l'antenne
	 * @param positionY Position Y de l'antenne
	 * @param puissance Puissance de l'antenne
	 * @param frequence Fr�quence de l'antenne
	 */
	public Antenne(String nom, double positionX, double positionY, double puissance, double frequence){
		this.nom = nom;
		this.position_x = positionX;
		this.position_y = positionY;
		this.puissance = puissance;
		if (frequence < FREQUENCE_MIN) this.frequence = FREQUENCE_MIN;
		else if (frequence > FREQUENCE_MAX) this.frequence = FREQUENCE_MAX;
		else this.frequence = frequence;
	}
	
	/**
	 * Constructeur simplifi� d'une antenne
	 * @param nom Nom de l'antenne
	 * @param positionX Position X de l'antenne
	 * @param positionY Position Y de l'antenne
	 */
	public Antenne(String nom, double positionX, double positionY){
		this(nom, positionX, positionY, PUISSANCE_DEFAULT, FREQUENCE_DEFAULT);
	}
	
	/**
	 * Constructeur par d�fault d'une antenne
	 * @param nom Nom de l'antenne
	 */
	public Antenne(String nom){
		this(nom, POSITIONX_DEFAULT, POSITIONY_DEFAULT, PUISSANCE_DEFAULT, FREQUENCE_DEFAULT);
	}
	
        /**
         * Constructeur par recopie
         * @author Hicham CHOUHAD
         * @param a antenne d'objet Antenne
         */
        public Antenne(Antenne a)
        {
            this(a.nom, a.position_x, a.position_y, a.puissance, a.frequence);
        }
        
        public Antenne(Antenne a, double puissance, double frequence)
        {
            this(a.nom, a.position_x, a.position_y, puissance, frequence);
        }
	/**
	 * M�thode par d�fault d'un objet
	 * @return la description de l'antenne
	 */
	@Override
	public String toString(){
		return nom+" ("+position_x+','+position_y+") "+puissance+" "+frequence;
	}
}
