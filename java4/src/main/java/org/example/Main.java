package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("— Задание №1");
        Integer[] arrayInt = {1, 2, 3, 4, 5, 6};
        String[] arrayStr = {"a", "b", "c", "d", "e", "f"};
        swapElements(arrayInt, 0, 1);
        swapElements(arrayStr, 0, 1);
        System.out.println("— Задание №2");
        convertToList(arrayStr);
        System.out.println("— Задание №3");
        Box<Apple> appleBox = new Box<>(new Apple());
        appleBox.add(2);
        Box<Orange> orangeBox = new Box<>(new Orange());
        orangeBox.add(3);
        //orangeBox.printBox();
        Box lemonBox = new Box(new Lemon());
        //lemonBox.add(new Apple());
        lemonBox.add(new Lemon());
    }
    public static void swapElements(Object[] array, int index1, int index2) {
        System.out.println("Исходный массив: " + Arrays.toString(array));
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        System.out.println("Итоговый массив: " + Arrays.toString(array) + "\n");
    }

    public static <T> void convertToList(T[] array) {
        System.out.println("Исходный массив: " + Arrays.toString(array));
        ArrayList<T> list = new ArrayList<>(Arrays.asList(array));
        System.out.println("Итоговый ArrayList: " + list + "\n");
    }

}