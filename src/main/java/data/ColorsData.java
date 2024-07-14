package data;

public enum ColorsData {
    BROWN("Коричневый"),

    GREY("Серый"),

    RED("Красный"),

    WHITE("Белый"),

    BLACK("Черный"),

    ORANGE("Рыжий");


    private String name;

    ColorsData(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }



}
