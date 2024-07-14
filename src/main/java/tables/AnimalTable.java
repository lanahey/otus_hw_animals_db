package tables;

import animals.Animal;
import data.AnimalInputData;
import data.ColorsData;
import factory.AnimalFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTable {

    public AnimalTable() {
        super("animals");
    }

    public void addAnimal(Animal animal) throws SQLException {
        String sql = "INSERT INTO animals (name, age, weight, color, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = idbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setInt(3, animal.getWeight());
            preparedStatement.setString(4, String.valueOf(animal.getColor()));
            preparedStatement.setString(5, animal.getClass().getSimpleName());
            preparedStatement.executeUpdate();
        }
    }

    public List<Animal> getAllAnimals() throws SQLException {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals";

        try (Connection connection = idbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String typeStr = resultSet.getString("type");
                String typeStrUpper = typeStr.toUpperCase(); // Преобразование в верхний регистр

                AnimalInputData type = getAnimalInputData(typeStr, typeStrUpper);

                Animal animal = AnimalFactory.createAnimal(type);
                animal.setId(resultSet.getLong("id"));
                animal.setName(resultSet.getString("name"));
                animal.setAge(resultSet.getInt("age"));
                animal.setWeight(resultSet.getInt("weight"));
                animal.setColor(ColorsData.valueOf(resultSet.getString("color")));
                animals.add(animal);

                // Вывод информации о животном на экран
                System.out.printf("ID: %d, Name: %s, Age: %d, Weight: %d, Color: %s, Type: %s%n",
                        animal.getId(), animal.getName(), animal.getAge(),
                        animal.getWeight(), animal.getColor(), typeStrUpper);
            }
        }

        return animals;
    }

    private AnimalInputData getAnimalInputData(String typeStr, String typeStrUpper) {
        AnimalInputData type;
        switch (typeStrUpper) {
            case "CAT":
                type = AnimalInputData.CAT;
                break;
            case "DOG":
                type = AnimalInputData.DOG;
                break;
            case "DUCK":
                type = AnimalInputData.DUCK;
                break;
            default:
                throw new IllegalArgumentException("Unknown animal type: " + typeStr);
        }
        return type;
    }


    public Animal getAnimalById(long id) throws SQLException {
        String sql = "SELECT * FROM animals WHERE id = ?";
        try (Connection connection = idbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                String typeStr = resultSet.getString("type");
                String typeStrUpper = typeStr.toUpperCase(); // Преобразование в верхний регистр

                AnimalInputData type = getAnimalInputData(typeStr, typeStrUpper);

                Animal animal = AnimalFactory.createAnimal(type);
                animal.setId(resultSet.getLong("id"));
                animal.setName(resultSet.getString("name"));
                animal.setAge(resultSet.getInt("age"));
                animal.setWeight(resultSet.getInt("weight"));
                animal.setColor(ColorsData.valueOf(resultSet.getString("color")));
                return animal;
            }
        }
        return null;
    }

    public void updateAnimal(Animal animal) throws SQLException {
        String sql = "UPDATE animals SET name = ?, age = ?, weight = ?, color = ? WHERE id = ?";
        try (Connection connection = idbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setInt(3, animal.getWeight());
            preparedStatement.setString(4, String.valueOf(animal.getColor()));
            preparedStatement.setLong(5, animal.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteAnimal(long id) throws SQLException {
        String sql = "DELETE FROM animals WHERE id = ?";
        try (Connection connection = idbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
