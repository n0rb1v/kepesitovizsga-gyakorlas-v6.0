package hu.nive.ujratervezes.kepesitovizsga.zoo;

public class Giraffe extends ZooAnimal {
    public Giraffe(String s, int i) {
        super(s);
        super.setLength(i);
        super.setType(AnimalType.GIRAFFE);
    }
}
