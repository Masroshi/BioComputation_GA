/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomp_ga;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Masroshi
 */
//class for setting selection,mutation and crossover function
public class Controller {
    //set mutation and crossover rate
    final static double CROSSOVERRATE = 0.2;
    final static double MUTATIONRATE = 0.008;

    public static void getFitness(int ruleAmount, Individual[] individual, String[] dataset) {
        int fitness;
        String[] datasetcomp;
        char[] a, b;
        for (Individual individual1 : individual) {
            fitness = 0;
            datasetcomp = dataset;
            for (String dataset1 : datasetcomp) {
                for (String gene : individual1.gene) {
                    a = dataset1.toCharArray();
                    b = gene.toCharArray();
                    if (wildcards(a, b)) {//true or false function
                        if (a[a.length - 1] == b[b.length - 1]) {
                            individual1.setFitness(fitness++);
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    //evolve the population using tournament selection
    public static Population evolvePopulation(Population pop) {
        Population evolvedPopulation;
        evolvedPopulation = mutation(crossover(tournament(pop)));
        return evolvedPopulation;
    }

    //mutation function for mutating the genes of a population to add back to the gene pool
    private static Population mutation(Population pop) {
        Random random = new Random();
        char[] arr;
        char[] comp;
        Population mut = pop;
        for (Individual individual : mut.individual) {
            
            for (int i = 0; i < individual.gene.length; i++) {
                arr = individual.gene[i].toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    if (Math.random() <= MUTATIONRATE) {
                        comp = Integer.toString(random.nextInt(2)).toCharArray();
                        if (arr[j] == comp[comp.length - 1]) {
                            if (j == individual.gene[0].length()) {
                                comp = Integer.toString(random.nextInt(2)).toCharArray();
                                arr[j] = comp[comp.length - 1];
                            } else {
                                arr[j] = '2';
                            }

                        } else {
                            arr[j] = comp[comp.length - 1];
                        }
                        individual.gene[i] = String.valueOf(arr);
                    }
                }
            }
        }
        return mut;

    }
    
    //selection method is tournament reason for use in report
    private static Population tournament(Population population) {
        int PID1; //parent 1
        int PID2; //parent 2
        int r = population.popsize;
        Population offspring = population;
        Random random = new Random();
        for (Individual individual : offspring.individual) {
            PID1 = random.nextInt(r);
            PID2 = random.nextInt(r);
            //the individual with the highest fitness gets to crossover
            if (population.individual[PID1].fitness >= population.individual[PID2].fitness) {
                System.arraycopy(population.individual[PID1].gene, 0, individual.gene, 0, individual.gene.length);
            } else {
                System.arraycopy(population.individual[PID2].gene, 0, individual.gene, 0, individual.gene.length);
            }
        }
        return offspring;
    }

    // Get optimum fitness
    static int getMaxFitness() {
        int maxFitness = 0;
        return maxFitness;
    }

    //crossover function which takes a population and retuns a new population after the function has been applied
    private static Population crossover(Population p) {
        Population crossPop = p;
        Individual offspring;
        char[] a, b;
        for (int i = 0; i < p.popsize; i++) {
            for (int j = 1; j < p.popsize; j++) {
                for (int k = 0; k < p.individual[0].gene.length; k++) {
                    if (Math.random() <= CROSSOVERRATE) {
                        a = p.individual[i].gene[k].toCharArray();
                        b = p.individual[j].gene[k].toCharArray();
                        offspring = childGene(a, b);
                        crossPop.individual[i].gene[k] = offspring.getGene(0);
                        crossPop.individual[j].gene[k] = offspring.getGene(1);
                    }
                }
            }
        }
        return crossPop;
    }
    
    //gives the childgenes after crossover
    private static Individual childGene(char[] arr, char[] arr2) {
        Random random = new Random();
        Individual offsping = new Individual();
        String a = "", b = "";
        char comp;
        int counter = 0;
        boolean tmp;
        for (int i = 0; i < arr.length; i++) {
            tmp = random.nextBoolean();
            if (tmp) {
                comp = arr[i];
                arr[i] = arr2[i];
                arr2[i] = comp;
            }
            a += String.valueOf(arr[i]);
            b += String.valueOf(arr2[i]);
            counter++;
        }
        if (counter == arr.length) {
            offsping.gene[0] = a;
            offsping.gene[1] = b;
        }
        return offsping;
    }
    
    //gets total sum of population
    //used to calculate avg fitness
    static int getSumofPop(Population pop) {
        int totalFitness = 0;
        for (int i = 0; i < pop.popsize; i++) {
            totalFitness += pop.individual[i].fitness;
        }
        return totalFitness;
    }

    //a true or false function which matches the 2 character arrays which the fitness calculation function uses
    static boolean wildcards(char[] arr, char[] arr2) {
        boolean a = false;
        char comp = '2';
        int counter = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr2[i] || comp == arr2[i]) {
                counter++;
            }
        }
        if (counter == arr.length - 1) {
            a = true;
        }
        return a;
    }
    
//grabs the data file and adds it into an array
    public String[] getData(String dataFile, int data) {
        String[] dataset = new String[data];
        Scanner sc = new Scanner(Controller.class.getResourceAsStream(dataFile));
        Pattern reg = Pattern.compile("[0-1]+.[0-1]");
        Matcher matcher = reg.matcher("");
        for (int i = 0; i < data;) {
            String next = sc.nextLine();
            matcher.reset(next);
            if (matcher.find()) {

                next = next.replaceAll("\\s+", "");
                dataset[i] = next;
                i++;

            }
        }

        return dataset;
    }
    
}
