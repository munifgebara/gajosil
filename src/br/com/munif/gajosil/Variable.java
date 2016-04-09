package br.com.munif.gajosil;

public class Variable {

    private final String name;
    private final int minimunValue;
    private final int maximumValue;

    public Variable(String name, int minimunValue, int maximumValue) {
        this.name = name;
        this.minimunValue = minimunValue;
        this.maximumValue = maximumValue;
    }

    public String getName() {
        return name;
    }

    public int getBitSize() {
        return (int) Math.ceil(Math.log(maximumValue - minimunValue + 1) / Math.log(2));
    }

    public int getMinimunValue() {
        return minimunValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    @Override
    public String toString() {
        return "Variable{" + "name=" + name + ", minimunValue=" + minimunValue + ", maximumValue=" + maximumValue + ", bitSize=" + getBitSize() + '}';
    }

}
