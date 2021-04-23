package hu.nive.ujratervezes.kepesitovizsga.zoo;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return null;
    }

    public int countWeights() {
        return 0;
    }

    public ZooAnimal findExactAnimal(ZooAnimal za) {
        return null;
    }

    public ZooAnimal findExactAnimalByName(String s) {
        return null;
    }

    public int getNumberOfAnimals() {
        return 0;
    }

    public List<ZooAnimal> getAnimalsOrderedByName() {
        return null;
    }

    public Map<AnimalType, Integer> getAnimalStatistics() {
        return null;
    }
}
