package hu.nive.ujratervezes.kepesitovizsga.zoo;

public class Elephant extends ZooAnimal {
    public Elephant(String s, int i, int j) {
        super(s);
        super.setLength(i);
        super.setWeight(j);
        super.setType(AnimalType.ELEPHANT);
    }
}
