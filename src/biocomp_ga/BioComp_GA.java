/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomp_ga;

/**
 *
 * @author Masroshi
 */

//main class used to run the algorithm
public class BioComp_GA {

    public static void main(String[] args) {
        Individual rules = new Individual();
        //popsize is the population size
        //number of rules hold the amount of rules applied
        //ChromoL is the length of each chromosome
        //IMPORTANT: depending on the dataset used the chromoL needs to be changed accordingly 
        //32 for dataset1 and 64 for dataset2
        //maxGen is the amount of generations the algorithm will run for
        final int popsize = 50, numberofrules = rules.getNumberofrules(), ChromoL = 32, maxGen = 100;
        Controller calc = new Controller();
        String dataFile = "data1.txt"; //defines which dataset to use, use either 1 or 2
        Population pop = new Population(popsize, numberofrules);
        String[] dataset = calc.getData(dataFile, ChromoL);
        int maxIndividualfitness = 0, maxfitness = 0, average = 1;
        Controller.getFitness(numberofrules, pop.individual, dataset);
        int genCount = 0;
        //loop for algorithm
        while (genCount != maxGen) {//while gen count is not reached
            pop = Controller.evolvePopulation(pop); // evolve the population
            Controller.getFitness(numberofrules, pop.individual, dataset); // check fitness
            for (Individual individual : pop.individual) {
                average = Controller.getSumofPop(pop) / pop.popsize; //calculate the average
                maxIndividualfitness = getMaxIndividualFitness(individual, maxIndividualfitness); // calculate the max individuals fitness
                maxfitness = getMaxFitness(pop, maxfitness); //calculate max pop fitness

            }
            genCount++;
            System.out.println("Generations Completed: " + genCount);
            System.out.println("Total fit:" + Controller.getSumofPop(pop) + "\nMax fit: " + maxfitness + "\nMax Individual Fit: " + maxIndividualfitness + "\nAvg fitness: " + average + "\n\n");
            if (genCount == maxGen) {// print operation finished statement
                System.out.println("Max generation reached\n");
            }
        }
    }
    //get the summed fitness amount from the population
    private static int getMaxFitness(Population pop, int maxfitness) {
        if (Controller.getSumofPop(pop) > maxfitness) {
            maxfitness = Controller.getSumofPop(pop);
        }
        return maxfitness;
    }
    
    //get the fitness rate for an individial 
    private static int getMaxIndividualFitness(Individual individual, int maxIndividualfitness) {
        if (individual.getFitness() > maxIndividualfitness) {
            maxIndividualfitness = individual.getFitness();

        }
        return maxIndividualfitness;
    }
    
}
