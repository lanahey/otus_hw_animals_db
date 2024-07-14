import animals.Animal;
import data.AnimalInputData;
import data.ColorsData;
import data.CommandsData;
import factory.AnimalFactory;
import tables.AnimalTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static selectors.UserSelector.*;

public class Main {
    public static void main(String... args) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        AnimalTable animalTable = new AnimalTable();
        animalTable.createTable();

        Scanner scanner = new Scanner(System.in);

        CommandsData[] commandsData = CommandsData.values();
        String[] commandsStr = new String[commandsData.length];
        for (int i = 0; i < commandsData.length; i++){
            commandsStr[i] = commandsData[i].name().toLowerCase();
        }

        while(true) {
            System.out.printf("Введите одну из команд: %s\n", String.join("/", commandsStr));
            CommandsData userCommandData = selectCommand(scanner);

            switch (userCommandData) {
                case ADD -> {
                    AnimalInputData userAnimalInputData = selectAnimal(scanner);

                    AnimalFactory animalFactory = new AnimalFactory();
                    Animal userAnimal = animalFactory.createAnimal(userAnimalInputData);

                    System.out.println("Введите имя животного");
                    String name = scanner.next();
                    userAnimal.setName(name);

                    userAnimal.setAge(getAge(scanner));
                    userAnimal.setWeight(getWetgh(scanner));
                    userAnimal.setColor(ColorsData.valueOf(String.valueOf(selectAnimalColor(scanner))));

                    animalTable.addAnimal(userAnimal);
                    animals.add(userAnimal);

                    userAnimal.say();
                }
                case LIST -> {
                    System.out.println("Вы ввели List");
                    List<Animal> animalsFromDB = animalTable.getAllAnimals();
                }
                case DELETE -> {
                    System.out.println("Вы ввели Delete");
                    System.out.println("Введите id животного, которое вы хотите удалить?");
                    long id = getId(scanner);

                    System.out.println("Будет удален:");
                    List<Animal> animalsFromDB = Collections.singletonList(animalTable.getAnimalById(id));
                    for (Animal animal: animalsFromDB){
                        System.out.println(animal.toString());
                    }
                    animalTable.deleteAnimal(id);
                }

                case UPDATE -> {
                    System.out.println("Вы ввели Update");
                    updateAnimalById(animalTable, scanner);

                }

                case EXIT -> {
                    System.out.println("До новых встреч!");
                    System.exit(0);
                }
            }
        }
    }


}
