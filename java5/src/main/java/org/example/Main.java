package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("— Задание №1");
        String[] words = {"asd", "vv", "asd", "zz", "slovo", "help", "zz", "help", "word", "help"};
        HashMap<String, Integer> wordList =  new HashMap<>();
        for (String i : words){
            wordList.merge(i, 1, (oldValue, newValue) -> oldValue + newValue);
        }

        for (Map.Entry<String, Integer> k : wordList.entrySet()){
            System.out.println(k);
        }
        System.out.println("\n— Задание №2");

        ExplanatoryDictionary dictionary  = new ExplanatoryDictionary();
        dictionary.add("ПАВИЛЬОН","Беседка или лёгкая постройка в саду, парке");
        dictionary.add("ПАВИЛЬОН","Беседка или лёгкая постройка в саду, парке");

        dictionary.add("ПАВИЛЬОН","Лёгкая крытая постройка");


        dictionary.add("ЛАВИНА","Массы снега, снежных глыб, низвергающихся с гор");
        dictionary.add("ЛАВИНА","То, что движется стремительной массой");

        dictionary.add("ЙОД","Химический элемент чёрно-серого цвета");
        dictionary.add("ЙОД","Раствор этого вещества в спирте, употр. в медицине");

        //dictionary.get("ЙОД");

        dictionary.printAll();
    }
}