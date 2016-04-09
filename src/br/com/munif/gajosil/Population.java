package br.com.munif.gajosil;

import java.io.PrintStream;
import java.util.Arrays;

public class Population {

    private Individual[] individuals;
    private FitnessAvaliator avaliator;

    public Population(Variable[] variables, int populationSize, FitnessAvaliator avaliator) {
        this.avaliator = avaliator;
        individuals = new Individual[populationSize];
        for (int i = 0; i < populationSize; i++) {
            individuals[i] = new Individual(variables);
        }
    }

    @Override
    public String toString() {
        return Arrays.asList(individuals).toString();
    }

    public void sort() {
        for (int i = 0; i < individuals.length - 1; i++) {
            for (int j = i + 1; j < individuals.length; j++) {
                if (individuals[i].fitness(avaliator) < individuals[j].fitness(avaliator)) {
                    Individual aux = individuals[i];
                    individuals[i] = individuals[j];
                    individuals[j] = aux;
                }
            }
        }
    }

    public void describe(PrintStream ps) {
        for (int i = 0; i < individuals.length; i++) {
            ps.printf("%03d ", i);
            ps.print("fitness=" + individuals[i].fitness(avaliator) + " ");
            ps.println(individuals[i].describe());

        }
    }

    public void test(PrintStream ps) {
        for (int i = 0; i < individuals.length; i++) {
            Individual id = individuals[i];
            ps.printf("%03d ", i);
            ps.print("fitness=" + id.fitness(avaliator) + " ");
            ps.println(id.describe());
            id.setBits(id.getBitString());
            ps.printf("%03d ", i);
            ps.print("fitness=" + id.fitness(avaliator) + " ");
            ps.println(id.describe());

        }
    }

    public void evolute() {
        for (int i = 0; i < individuals.length / 2; i += 2) {
            Individual father = individuals[i];
            Individual mother = individuals[i + 1];
            String fatherDNA = father.getBitString();
            String motherDNA = mother.getBitString();
            int pointCut = 1 + Individual.RANDOM.nextInt(fatherDNA.length());
            String sonDNA = fatherDNA.substring(0, pointCut) + motherDNA.substring(pointCut);
            String dautherDNA = motherDNA.substring(0, pointCut) + fatherDNA.substring(pointCut);
            Individual son = new Individual(father.getVariables(), sonDNA);
            Individual dauther = new Individual(mother.getVariables(), dautherDNA);
            individuals[individuals.length - 1 - i] = son;
            individuals[individuals.length - 2 - i] = dauther;
        }

    }

    public Individual getFirst() {
        return individuals[0];
    }

}
