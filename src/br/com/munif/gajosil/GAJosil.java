package br.com.munif.gajosil;

public class GAJosil {

    private final Variable[] variables;
    private final int populationSize;
    private final int generations;
    private Population population;
    private final FitnessAvaliator avaliator;

    public GAJosil(int population, int generations, Variable[] vs, FitnessAvaliator avaliator) {
        variables = vs;
        this.populationSize = population;
        this.generations = generations;
        this.avaliator = avaliator;
    }

    public void run() {
        initPopulation();

        for (int i = 0; i < generations; i++) {
            System.out.println("Generation " + i);
            population.sort();
            System.out.println(population.getFirst().describe());
            population.evolute();
        }
        System.out.println("Generation " + generations);
        System.out.println(population.getFirst().describe());
    }

    public Individual getBest() {
        population.sort();
        return population.getFirst();
    }

    private void initPopulation() {
        population = new Population(variables, populationSize, avaliator);
    }


}
