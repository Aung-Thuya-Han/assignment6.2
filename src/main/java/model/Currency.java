package model;

public class Currency {
    private final String abbreviation;
    private final String name;
    private final double unitsPerEuro;

    public Currency(String abbreviation, String name, double unitsPerEuro) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.unitsPerEuro = unitsPerEuro;
    }

    public double getUnitsPerEuro() {
        return unitsPerEuro;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}
