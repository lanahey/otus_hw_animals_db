package animals;

import data.AnimalInputData;
import data.ColorsData;

public abstract class Animal {

    private long id;
    private AnimalInputData type;
    private String name="";
    private int age=0;
    private int weight=0;
    private ColorsData color;

    public long getId() { return id;}

    public void setId(long id) { this.id = id;}

    public AnimalInputData getType() { return type; }

    public void setType(AnimalInputData type) { this.type = type; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ColorsData getColor() {
        return color;
    }

    public void setColor(ColorsData color) {
        this.color = color;
    }



    public void say(){
        System.out.println("Я говорю");
    };
    public void go(){
        System.out.println("Я иду");
    };;
    public void drink(){
        System.out.println("Я пью");
    };
    public void eat(){
        System.out.println("Я ем");
    };

    @Override
    public String toString() {
        return String.format("Привет! Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s",
                name, age, getAgeEnd(), weight, color);
    }

    private String getAgeEnd(){
        int ostatok = age % 10;

        if (ostatok == 1 ){
            return " год";
        }

        if (ostatok >= 2 && ostatok <= 4 ){
            return " года";
        }

        if (age >= 11 && age <= 19 ){
            return " лет";
        }

        return " лет";
    }
}
