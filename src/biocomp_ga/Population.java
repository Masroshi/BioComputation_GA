/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomp_ga;
import java.util.Random;

/**
 *
 * @author Masroshi
 */

 //class for creating the population
class Population {
    int popsize = 50;
    Individual[] individual = new Individual[popsize];
    
    public Population(int popsize, int numberOfRules) {
        Random random = new Random();
        //Create the Population by random using the integrated randomification feature of netbeans
        for (int i = 0; i < popsize; i++) {
            individual[i] = new Individual();
            for (int j = 0; j < numberOfRules; j++) {
                individual[i].gene[j] = "";
                for (int k = 0; k < 5; k++) {
                    individual[i].gene[j] += Integer.toString(random.nextInt(3));
                }
                individual[i].gene[j] += Integer.toString(random.nextInt(2));
            }
            individual[i].fitness = 0;
        }
    }
    //getters and setters for population and individual
    public void setIndividual(Individual[] individual) {
        this.individual = individual;
    }

    public int getPopsize() {
        return popsize;
    }

    public void setPopsize(int popsize) {
        this.popsize = popsize;
    }
}
