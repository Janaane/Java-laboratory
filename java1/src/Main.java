import animals.*;


public class Main {
    public static void main(String[] args) {
        Animal[] animals  = {new Dog("Шарик",  300, 1),
                new Dog("Бобик", 200, 2),
                new Cat("Мурзик",  150, 0,3),
                new Cat("Барсик",  200, 0,4),
                new Tiger("Тим",  600, 450,5),
                new Bear("Михаил",1000, 400, 15, "Лес"),
                new Moose("Лосяш",2000, 100, 12, "Степи"),
                new Moose("Лосяш2",3000, 100, 12, "Степи"),
                new Lion("Лёва", 3000,2000,9,"Саванна")
        };


        System.out.println("Количество котов: " + Cat.getCount());
        System.out.println("Количество собак: " + Dog.getCount());
        System.out.println("Количество тигров: " + Tiger.getCount());
        System.out.println("Количество медведей: " + Bear.getCount());
        System.out.println("Количество лосей: " + Moose.getCount());
        System.out.println("Количество львов: " + Lion.getCount());
        System.out.println("Количество диких животных: "+ WildAnimals.getCountWild());
        System.out.println("Общее количество животных: " + Animal.getCount());
        System.out.println("\nВид соревнования: бег");
        for (Animal i : animals) {
            i.run(400);
        }

        System.out.println("\nВид соревнования: плавание");
        for (Animal i : animals) {
            i.swim(400);
        }
        System.out.println(animals[8].getName() + " " + (((WildAnimals) animals[8]).getHabitat()));

    }
}