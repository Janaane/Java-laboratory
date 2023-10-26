package org.example;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

public class Main {
    public static void searchWord(List<String> words) {
        System.out.println(words.stream()
                .collect(groupingBy(w -> w, counting())) // группировка слов по количеству их повторений
                .entrySet().stream()
                .collect(groupingBy(
                        Map.Entry::getValue, // группировка по количеству повторений
                        mapping(Map.Entry::getKey, toList()) // преобразование в список слов
                ))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getKey)) // нахождение группы с максимальным количеством повторений
                .map(Map.Entry::getValue) // извлечение списка слов с максимальным количеством повторений
                .orElse(Collections.emptyList())
                .stream().collect(groupingBy(String::length)) // группировка слов по их длине
                .entrySet().stream()
                .min(Comparator.comparingInt(Map.Entry::getKey)) // нахождение группы с минимальной длиной слов
                .map(Map.Entry::getValue) // извлечение списка слов с минимальной длиной
                .orElse(Collections.emptyList())
                .stream().collect(Collectors.joining(", ")));
    }

    public static void main(String[] args) {
        System.out.println(" — Задание №1");
        List<String> words = new ArrayList<>(Arrays.asList("appl", "banana", "appl", "cherry", "banana", "date", "appl", "date", "date"));
        searchWord(words);

        System.out.println("\n — Задание №2");
        Employee[] persons = new Employee[]{
                new Employee("Илон", 21, "Мужской", 20000),
                new Employee("Егор", 52, "Мужской",10),
                new Employee("Дима", 22, "Мужской", 40000),

                new Employee("Елена", 55, "Женский", 3000),
                new Employee("Марго", 22, "Женский", 21000),
                new Employee("Марго2", 100, "Женский", 7000),
                new Employee("Настя", 20, "Женский", 19000),
                new Employee("Вика", 15, "Женский", 12000)
        };
        findOldEmployees(persons, 3);
    }

    public static void findOldEmployees(Employee[] employees, int N) {
        System.out.println(Arrays.stream(employees)
                .filter(e -> "Женский".equals(e.getGender()))
                .sorted(Comparator.comparingInt(Employee::getAge).reversed()) // сортируем сотрудников по возрасту (по убыванию)
                .limit(N)
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed()) // сортируем сотрудников по зарплате (по убыванию)
                .map(e -> e.getName()) // преобразуем сотрудников в их имена
                .collect(joining(", ", N + " самых старших сотрудников зовут: ", "")));
    }
}