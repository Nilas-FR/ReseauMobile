/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;
import static java.lang.Math.*;
import java.util.Random;
/**
 * Classe permettant l'optimisation des fréquences des antennes
 * @author Hicham CHOUHAD
 */
public class Optimisation {
    public Antenne tableauAntenne[];
    public int tailleAntenne;
    
    public double fitness[];
    
    final int GENERATIONS  = 100;
    final int MAX_ANTENNES = 100;
    final double LIMITE = 0.3;
    public Optimisation()
    {
        int i = 0;
        tableauAntenne = new Antenne[MAX_ANTENNES];
        tailleAntenne = 0;
        //récupération des antennes
        
        //tant que : arret ou nb gen
        while(i < GENERATIONS && fitness[tailleAntenne - 1] < LIMITE)
        {
            mutation();
            calculFitness();
            trie();
            //calcul fitness
            //trie
            i++;
        }
    }
    
    public boolean intersection(Antenne antenne1, Antenne antenne2)
    {
        double distance = sqrt(pow(antenne1.position_x - antenne2.position_x, 2) + pow(antenne1.position_x - antenne2.position_x, 2));
        //si la distance entre deux antennes est plus petite que l'addition de leur puissance, alors il y a intersection
        return distance < antenne1.puissance + antenne2.puissance;
    }
    
    public double fitness(int indiceAntenne)
    {
        double fitness = Double.MAX_VALUE;
        double sommeSIR = 0;
        boolean intersection[] = new boolean[tailleAntenne];
        for(int i = 0; i < tailleAntenne; i++)
        {
            if(indiceAntenne != i && intersection(tableauAntenne[indiceAntenne], tableauAntenne[i]))
            {
                sommeSIR += tableauAntenne[i].puissance * tableauAntenne[i].frequence;
            }
        }
        
        //signal to interference ratio = puissance*frequence / (somme des puissance*frequence des antennes en interference)
        return (tableauAntenne[indiceAntenne].puissance * tableauAntenne[indiceAntenne].frequence) / sommeSIR;
    }
    
    
    public void trie()
    {
        Antenne tableauTrie[] = new Antenne[MAX_ANTENNES];
        double plusPetit;
        int indicePlusPetit;
        Antenne antenneTemp;
        double fitnessTemp;
        for(int i = 0; i < tableauAntenne.length; i++)
        {
            tableauTrie[i] = tableauAntenne[i];
            plusPetit = Double.MAX_VALUE;
            indicePlusPetit = i;
            for(int j = i; j < tableauTrie.length; j++)
            {
                if(fitness(j) < plusPetit)
                {
                    indicePlusPetit = j;
                    plusPetit = fitness(j);
                }
            }
            
            if(i != indicePlusPetit)
            {
                antenneTemp = new Antenne(tableauTrie[i].nom, tableauTrie[i].position_x, tableauTrie[i].position_y, tableauTrie[i].puissance, tableauTrie[i].frequence);
                fitnessTemp = fitness[i];
                tableauTrie[i] = tableauTrie[indicePlusPetit];
                fitness[i] = fitness[indicePlusPetit];
                tableauTrie[indicePlusPetit] = antenneTemp;
                fitness[indicePlusPetit] = fitnessTemp;
            }
            
            
        }
        
        tableauAntenne = tableauTrie;
    }
    
    public void mutation()
    {
        Random r = new Random();
        double frequenceAleatoire, puissanceAleatoire;
        Antenne mutationAntenne;
        for(int i = (tailleAntenne / 2); i < tailleAntenne; i++)
        {
            frequenceAleatoire = Antenne.FREQUENCE_MIN + (Antenne.FREQUENCE_MAX - Antenne.FREQUENCE_MIN) * r.nextDouble();
            puissanceAleatoire = 100 * r.nextDouble();
            mutationAntenne = new Antenne(tableauAntenne[i].nom, tableauAntenne[i].position_x, tableauAntenne[i].position_y, puissanceAleatoire, frequenceAleatoire);
            tableauAntenne[i] = mutationAntenne;
        }
    }
    
    public void calculFitness()
    {
        for(int i = 0; i < tailleAntenne; i++)
        {
            fitness[i] = fitness(i);
        }
    }
}
