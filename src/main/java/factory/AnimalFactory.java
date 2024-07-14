package factory;

import animals.*;
import animals.birds.Duck;
import animals.pats.Cat;
import animals.pats.Dog;
import data.AnimalInputData;

public class AnimalFactory {
    public static Animal createAnimal(AnimalInputData type) {
        switch (type) {
            case CAT:
                return new Cat();
            case DOG:
                return new Dog();
            case DUCK:
                return new Duck();
            default:
                throw new IllegalArgumentException("Неизвестный тип животного: " + type);
        }
    }
}
