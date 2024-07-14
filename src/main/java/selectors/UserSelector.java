package selectors;

import animals.Animal;
import data.AnimalInputData;
import data.ColorsData;
import data.CommandsData;
import tables.AnimalTable;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class UserSelector {

    public static CommandsData selectCommand(Scanner scanner){
        CommandsData[] commandsData = CommandsData.values();
        CommandsData userCommandData;
        while (true) {
            String commandStr = scanner.next().trim().toUpperCase();

            boolean isExist = false;
            for(CommandsData command: commandsData){
                if(command.name().equals(commandStr)){
                    isExist = true;
                    break;
                }
            }
            if (!isExist){
                System.out.println("Вы ввели неверную команду, попробуйте еще раз.");
                continue;
            }
            userCommandData = CommandsData.valueOf(commandStr);
            break;
        }
        return userCommandData;
    }

    public static AnimalInputData selectAnimal(Scanner scanner){
        AnimalInputData[] animalInputData = AnimalInputData.values();
        AnimalInputData userAnimalData;
        String[] animalList = new String[animalInputData.length];
        for (int i = 0; i < animalInputData.length; i++) {
            animalList[i] = animalInputData[i].name().toLowerCase();
        }
        while (true) {
            System.out.printf("Введите тип животного из списка: %s\n", String.join("/", animalList));
            String animalStr = scanner.next().trim().toUpperCase();

            boolean isExist = false;
            for(AnimalInputData animal: animalInputData){
                if(animal.name().equals(animalStr)){
                    isExist = true;
                    break;
                }
            }
            if (!isExist){
                if (animalStr.equalsIgnoreCase("EXIT")) {
                    System.out.println("До новых встреч!");
                    System.exit(0);
                } else {
                    System.out.println("Вы ввели неверное животное, попробуйте еще раз.");
                    System.out.println("Для выхода из системы введите exit.");
                    continue;
                }
            }
            userAnimalData = AnimalInputData.valueOf(animalStr);
            break;
        }
        return userAnimalData;
    }

    public static ColorsData selectAnimalColor(Scanner scanner){
        ColorsData[] colorsData = ColorsData.values();
        ColorsData userColorData;
        String[] colorsStr = new String[colorsData.length];
        for (int i = 0; i < colorsData.length; i++) {
            colorsStr[i] = colorsData[i].name().toLowerCase();
        }
        while (true) {
            System.out.printf("Введите цвет животного из списка: %s\n", String.join("/", colorsStr));
            String colorInputStr = scanner.next().trim().toUpperCase();

            boolean isColorExist = false;
            for (ColorsData colorInput : colorsData) {
                if (colorInput.name().equals(colorInputStr)) {
                    isColorExist = true;
                    break;
                }
            }
            if (!isColorExist) {
                if (colorInputStr.equalsIgnoreCase("EXIT")) {
                    System.out.println("До новых встреч!");
                    System.exit(0);
                } else {
                    System.out.println("Цвет должен быть из списка, попробуйте еще раз.");
                    System.out.println("Для выхода из системы введите exit.");
                }
                continue;
            }
            userColorData = ColorsData.valueOf(colorInputStr);
            break;
        }
        return userColorData;
    }


    public static Integer getWetgh(Scanner scanner) {
        while(true) {
            try {
                System.out.println("Введите вес животного. Вес это число больше нуля и меньше 59.");
                int weight = Integer.parseInt(scanner.next());
                if (weight <= 0) {
                    System.out.println("Вы ввели неверный вес. Вес должен быть больше нуля");
                }
                if (weight > 59) {
                    System.out.println("Обратитесь в Книгу рекордов Гиннеса.");
                    System.out.println("Вот вам несколько интересных фактов:");
                    System.out.println("- самая толстая кошка, занесенный в Книгу рекордов Гиннеса, весила 21 кг.");
                    System.out.println("- самая толстая собака, занесенная в Книгу рекордов Гиннеса, весила 58 кг.");
                    System.out.println("- самая толстая утка, занесенная в Книгу рекордов Гиннеса, весила 2 кг.");
                }
                if (weight >= 1 && weight < 60) {
                    return weight;
                }
            } catch (Exception e){
                System.out.println("Вы должны ввести целое число.");
            }
        }
    }

    public static Integer getAge(Scanner scanner) {
        while(true) {
            try {
                System.out.println("Введите возраст животного. Возраст это число больше нуля и меньше 50.");
                int age = Integer.parseInt(scanner.next());
                if (age <= 0) {
                    System.out.println("Вы ввели неверный возраст. Возраст должен быть больше нуля");
                }
                if (age > 49) {
                    System.out.println("Обратитесь в Книгу рекордов Гиннеса.");
                    System.out.println("Вот вам несколько интересных фактов:");
                    System.out.println("- самый старый кот, занесенный в Книгу рекордов Гиннеса, дожил до 38 лет.");
                    System.out.println("- самая старая собака, занесенная в Книгу рекордов Гиннеса, дожила до 31 года.");
                    System.out.println("- самая старая утка, занесенная в Книгу рекордов Гиннеса, дожила до 49 лет.");
                }
                if (age >= 1 && age < 50) {
                    return age;
                }
            } catch (Exception e){
                System.out.println("Вы должны ввести целое число.");
            }
        }
    }

    public static Integer getId(Scanner scanner) {
        while(true) {
            try {
                int num = Integer.parseInt(scanner.next());
                if (num <= 0) {
                    System.out.println("Id должен быть больше 0");
                }
                            /*if (num > 59) {
                                System.out.println("Валидация на максимальное количество записей в таблице");
                            }*/
                if (num >= 1 && num < 60) {
                    return num;
                }
            } catch (Exception e){
                System.out.println("Вы должны ввести целое число.");
            }
        }
    }

    public static void updateAnimalById(AnimalTable animalTable, Scanner scanner) throws SQLException {
        System.out.println("Введите id животного, которое вы хотите отредактировать?");
        long id = getId(scanner);
        Animal animalToUpdate = animalTable.getAnimalById(id);
        if (animalToUpdate != null) {
            System.out.println("Текущие данные животного: " + animalToUpdate);

            System.out.println("Введите новое имя животного (если не хотите менять имя введите No:");
            String newName = scanner.next();
            if (!newName.equalsIgnoreCase("No")) {
                animalToUpdate.setName(newName);
            }

            System.out.println("Введите новый возраст животного от:");
            animalToUpdate.setAge(getAge(scanner));

            System.out.println("Введите новый вес животного:");
            animalToUpdate.setWeight(getWetgh(scanner));

            System.out.println("Введите новый цвет животного:");
            String newColor = scanner.next();
            animalToUpdate.setColor(ColorsData.valueOf(String.valueOf(selectAnimalColor(scanner))));

            animalTable.updateAnimal(animalToUpdate);
            System.out.println("Данные животного обновлены.");
        } else {
            System.out.println("Животное с указанным ID не найдено.");
        }
    }
}
