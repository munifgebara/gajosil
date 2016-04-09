package br.com.munif.gajosil;

import java.math.BigInteger;
import java.util.Random;

public class Individual {

    public final static Random RANDOM = new Random();

    private final Variable[] variables;
    private final int[] values;

    public Individual(Variable[] variables) {
        this.variables = variables;
        values = new int[variables.length];
        for (int i = 0; i < values.length; i++) {
            int range = variables[i].getMaximumValue() - variables[i].getMinimunValue();
            values[i] = RANDOM.nextInt(variables[i].getMinimunValue() + range + 1);
        }
    }

    public Variable[] getVariables() {
        return variables;
    }

    public Individual(Variable[] variables, String bits) {
        this.variables = variables;
        values = new int[variables.length];
        setBits(bits);
    }

    public int[] getValues() {
        return values;
    }

    public String getBitString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(String.format("%0" + variables[i].getBitSize() + "d", new BigInteger(Integer.toBinaryString(values[i]))));
        }
        return sb.toString();
    }

    public void setBits(String bits) {
        int bitPosition = 0;
        for (int i = 0; i < variables.length; i++) {
            String substring = bits.substring(bitPosition, bitPosition + variables[i].getBitSize());
            values[i] = Integer.parseInt(substring, 2);
            bitPosition += variables[i].getBitSize();
        }
    }

    @Override
    public String toString() {
        return getBitString();
    }

    public double fitness(FitnessAvaliator fa) {
        return fa.avaliateFitness(this);
    }

    public int getValue(int i) {
        return variables[i].getMinimunValue() + values[i];
    }

    public String describe() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(variables[i].getName() + "=" + getValue(i) + " ");
        }
        return sb.toString();
    }

}
