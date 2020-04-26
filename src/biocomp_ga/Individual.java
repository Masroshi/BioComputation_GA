/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biocomp_ga;
import java.util.Arrays;

/**
 *
 * @author Masroshi
 */
//class for the individual gene, getters and setters 
public class Individual {
    int numberofrules = 15;
    String[] gene;
    int fitness;

    public int getNumberofrules() {
        return numberofrules;
    }

    public void setNumberofrules(int numberofrules) {
        this.numberofrules = numberofrules;
    }

    public Individual() {
        gene = new String[numberofrules];
    }

    public String getGene(int i) {
        return gene[i];
    }

    public void setGene(String[] gene) {
        this.gene = gene;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int size() {
        return gene.length;
    }

}
