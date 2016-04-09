package br.com.munif.gajosil.example;

import br.com.munif.gajosil.FitnessAvaliator;
import br.com.munif.gajosil.GAJosil;
import br.com.munif.gajosil.Individual;
import br.com.munif.gajosil.Variable;

public class Programa {

    public static void main(String[] args) {
        GAJosil ag = new GAJosil(1000, 10, new Variable[]{
            new Variable("Garrafas de Suco", 0, 800),
            new Variable("Garrafas de Leite", 0, 800)},
                new FitnessAvaliator() {
            @Override
            public double avaliateFitness(Individual individual) {
                int suco = individual.getValue(0);
                int leite = individual.getValue(1);
                double fb = suco * 5 + leite * 4.5;
                double h = leite / 100 * 6 + suco / 100 * 5;
                double v = leite * 10 + suco * 20;
                if (h > 60) {
                    fb = fb * 0.1;
                }
                if (v > 15000) {
                    fb = fb * 0.1;
                }
                if (leite > 800) {
                    fb = fb * 0.1;
                }
                return fb;
            }
        });
        ag.run();
        avaliateFitness(ag.getBest());

    }

    public static double avaliateFitness(Individual individual) {
        int suco = individual.getValue(0);
        int leite = individual.getValue(1);
        double fb = suco * 5 + leite * 4.5;
        double h = leite / 100 * 6 + suco / 100 * 5;
        double v = leite * 10 + suco * 20;
        System.out.println(individual.describe());
        System.out.println("Lucro"+fb+" Volume "+v+" Horas "+h);
        return fb;
    }

}
