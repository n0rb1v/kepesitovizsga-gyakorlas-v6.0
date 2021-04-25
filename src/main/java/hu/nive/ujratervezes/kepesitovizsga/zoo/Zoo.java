package hu.nive.ujratervezes.kepesitovizsga.zoo;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Zoo {
    private List<ZooAnimal> animals = new ArrayList<>();
    private JdbcTemplate jdbc;

    public Zoo(DataSource dataSource) {
        jdbc = new JdbcTemplate(dataSource);
    }

    public List<ZooAnimal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void loadAnimals() {
        animals = jdbc.query("select * from animals",
                (rs, index) -> new ZooAnimal(
                        rs.getString("animal_name"),
                        rs.getInt("length_of_member"),
                        rs.getLong("weight"),
                        AnimalType.valueOf(rs.getString("animal_type"))));
    }

    public void addAnimal(ZooAnimal za) {
        if (!animals.contains(za)) {
            jdbc.update("insert into animals(animal_name, length_of_member, weight, animal_type) values(?,?,?,?)",
                    za.getName(), za.getLength(), za.getWeight(), za.getType().toString());
        }
    }

    public ZooAnimal getHeaviestAnimalInTheZoo() {
        return animals.stream()
                .max(Comparator.comparing(ZooAnimal::getWeight))
                .orElseThrow(() -> new IllegalStateException("no animal"));
    }

    public long countWeights() {
        return animals.stream()
                .mapToLong(ZooAnimal::getWeight)
                .peek(System.out::println)
                .sum();
    }

    public ZooAnimal findExactAnimal(ZooAnimal za) {
        return animals.stream()
                .filter(animal -> animal.equals(za))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no such animal in the zoo!"));
    }

    public ZooAnimal findExactAnimalByName(String s) {
        return animals.stream()
                .filter(animal -> animal.getName().equals(s))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no such animal in the zoo!"));
    }

    public int getNumberOfAnimals() {
        return (int) animals.stream()
                .count();
    }

    public List<ZooAnimal> getAnimalsOrderedByName() {
        return animals.stream()
                .sorted(Comparator.comparing(ZooAnimal::getName))
                .collect(Collectors.toList());
    }

    public Map<AnimalType, Integer> getAnimalStatistics() {
        return animals.stream()
                .collect(Collectors.toMap(ZooAnimal::getType, v -> 1, Integer::sum));
    }
}
