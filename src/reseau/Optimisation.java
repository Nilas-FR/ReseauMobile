/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseau;
import static java.lang.Math.*;
import java.util.Random;
import java.util.Vector;
/**
 * Classe permettant l'optimisation des fréquences et puissances des antennes
 * @version 1.1
 * @author Hicham CHOUHAD
 */
public class Optimisation {
    private final FenetrePrincipale FP;
    public Vector<Vector<Antenne>> genome = new Vector();
    public Vector<Vector<Double>>  fitnessAntenne = new Vector();
    public Vector<Double>          fitnessMoyenne = new Vector();
    final int FAMILLE_MAX = 20;
    final int GENERATION_MAX = 100;
    final double LIMITE = 0.1;
    
    
    public Optimisation(FenetrePrincipale FP)
    {
        this.FP = FP;
        fitnessMoyenne.setSize(FAMILLE_MAX);
        for (int i = 0; i < FAMILLE_MAX; i++) {
            genome.add(new Vector<Antenne>());
            genome.add(generation());
            fitnessAntenne.add(new Vector<Double>());
            for (int j = 0; j < FP.Antennes.size(); j++) {
                //genome.get(i).add(new Antenne(FP.Antennes.get(j).nom, FP.Antennes.get(j).position_x, FP.Antennes.get(j).position_y));
                fitnessAntenne.get(i).add(Double.MAX_VALUE);
            }
            
        }
        for(int i = 0; i < FAMILLE_MAX; i++)
        {
            fitnessMoy(i);
        }
        //initialisation des paramètres
        int nbGeneration = 1;
        
        //paramétrage
        //fitnessAntenne.setSize(FAMILLE_MAX);
        
        /*for(int i = 0; i < FAMILLE_MAX; i++)
        {
            fitnessAntenne.get(i).setSize(FP.Antennes.size());
            genome.add(generation());
            for(int j = 0; j < FP.Antennes.size(); j++)
            {
                fitnessAntenne.get(i).add(Double.MAX_VALUE);
            }
        }*/
        
        //generations
        //&& fitnessMoyenne.get(FAMILLE_MAX - 1) < LIMITE
        while(nbGeneration <= GENERATION_MAX )
        {
            if(nbGeneration > 1)
            {
                for(int i = ((int)0.1*FAMILLE_MAX); i < FAMILLE_MAX; i++)
                {
                    genome.set(i, generation());
                }
                mutation();
            }
            
            for(int i = 0; i < FAMILLE_MAX; i++)
            {
                for(int j = 0; j < genome.get(i).size(); j++)
                {
                    fitness(i, j);
                    
                }
                fitnessMoy(i);
                //trie();
            }
            for(int i = 0; i < FAMILLE_MAX; i++)
            {
                System.err.println(fitnessMoyenne.get(i));
                
            }
            System.err.println("__________________________________");
            nbGeneration++;
        }
        int plusPetitGenome = 0;
        for(int i = 1; i < FAMILLE_MAX; i++)
        {
            if(fitnessMoyenne.get(plusPetitGenome) > fitnessMoyenne.get(i))
            {
                plusPetitGenome = i;
            }
        }
        
        FP.Antennes = genome.get(plusPetitGenome);
        
        
        
    }
    
    public Vector<Antenne> generation()
    {
        Vector<Antenne> tableauAntenne = new Vector();
        Random r = new Random();
        double frequenceAleatoire, puissanceAleatoire;
        
        for(int i = 0; i < FP.Antennes.size(); i++)
        {
            frequenceAleatoire = Antenne.FREQUENCE_MIN + (Antenne.FREQUENCE_MAX - Antenne.FREQUENCE_MIN) * r.nextDouble();
            puissanceAleatoire = 5 + (50) * r.nextDouble();
            tableauAntenne.add(new Antenne(FP.Antennes.get(i), puissanceAleatoire, frequenceAleatoire));
            //tableauAntenne.add(new Antenne(FP.Antennes.get(i).nom, FP.Antennes.get(i).position_x, FP.Antennes.get(i).position_y, puissanceAleatoire, frequenceAleatoire));
        }
        
        return tableauAntenne;
    }
    
    public boolean intersection(Antenne antenne1, Antenne antenne2)
    {
        double distance = sqrt(pow(antenne1.position_x - antenne2.position_x, 2) + pow(antenne1.position_x - antenne2.position_x, 2));
        //si la distance entre deux antennes est plus petite que l'addition de leur puissance, alors il y a intersection
        return distance < antenne1.puissance + antenne2.puissance;
    }
    
    public void fitnessMoy(int indiceGenome)
    {
        double moyenne = 0;
        for(int i = 0; i < genome.get(indiceGenome).size(); i++)
        {
            moyenne += fitnessAntenne.get(indiceGenome).get(i);
        }
        
        fitnessMoyenne.set(indiceGenome, (moyenne / genome.get(indiceGenome).size()));
    }
    
    public void fitness(int indiceGenome, int indiceAntenne)
    {
        double sir;
        fitnessAntenne.get(indiceGenome).set(indiceAntenne, Double.MAX_VALUE);
        for(int i = 0; i < genome.get(indiceGenome).size(); i++)
        {
            if(indiceAntenne != i && intersection(genome.get(indiceGenome).get(indiceAntenne), genome.get(indiceGenome).get(i)));
            {
                sir = (genome.get(indiceGenome).get(indiceAntenne).puissance * genome.get(indiceGenome).get(indiceAntenne).frequence) / (genome.get(indiceGenome).get(i).puissance * genome.get(indiceGenome).get(i).frequence);
                if(sir > 1)
                {
                    sir = 1 / sir;
                }
                if(sir < fitnessAntenne.get(indiceGenome).get(indiceAntenne))
                {
                    fitnessAntenne.get(indiceGenome).set(indiceAntenne, sir);
                }
            }
        }
    }
    
    /*public void trie()
    {
        Vector<Vector<Antenne>> tableauTrie = new Vector();
        double plusPetit;
        int indicePlusPetit;
        
        
        
        //Antenne tableauTrie[] = new Antenne[MAX_ANTENNES];
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
    }*/
    
    public void mutation()
    {
        Random r = new Random();
        int indicePireAntenne = -1;
        double pireFitness;
        double frequenceAleatoire, puissanceAleatoire;
        Antenne mutationAntenne;
        
        for(int i = 0; i < ((int)FAMILLE_MAX*0.1); i++)
        {
            pireFitness = Double.MIN_VALUE;
            for(int j = 0; j < genome.get(i).size(); j++)
            {
                if(fitnessAntenne.get(i).get(j) > pireFitness)
                {
                    indicePireAntenne = j;
                    pireFitness = fitnessAntenne.get(i).get(j);
                }
            }
            
            frequenceAleatoire = Antenne.FREQUENCE_MIN + (Antenne.FREQUENCE_MAX - Antenne.FREQUENCE_MIN) * r.nextDouble();
            puissanceAleatoire = 100 * r.nextDouble();
            
            if(indicePireAntenne != -1)
            {
                mutationAntenne = new Antenne(genome.get(i).get(indicePireAntenne).nom, genome.get(i).get(indicePireAntenne).position_x, genome.get(i).get(indicePireAntenne).position_y, puissanceAleatoire, frequenceAleatoire);
                genome.get(i).set(indicePireAntenne, mutationAntenne);
            }
        }
    }
}
