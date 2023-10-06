package org.example;

import java.sql.SQLOutput;
import java.util.*;

public class ExplanatoryDictionary {
    private TreeMap<String, LinkedHashSet <String>> explanatoryDictionary = new TreeMap<>();

    public void add(String term, String interpretation) {
        LinkedHashSet <String> interpretations = explanatoryDictionary.getOrDefault(term, new LinkedHashSet <>());
        if(interpretations.contains(interpretation)){
            System.out.println("Толкование " + interpretation + " для слова " + term + " уже было добавлено ранее");
        }
        interpretations.add(interpretation);
        explanatoryDictionary.put(term, interpretations);
    }

    public void get(String term) {
        if(explanatoryDictionary.isEmpty() || !explanatoryDictionary.containsKey(term)){
            System.out.println("Значения нет в толковом словаре!");
        }
        else if(explanatoryDictionary.get(term).size() > 1 ){
            System.out.println(term + ": значения: " + String.join(" ИЛИ ",explanatoryDictionary.get(term)));
        }
        else{
            System.out.println(term + ": значение: " + String.join("", explanatoryDictionary.get(term)));
        }
    }

    public void printAll() {
        for (String key : explanatoryDictionary.keySet()) {
            LinkedHashSet <String> interpretations = explanatoryDictionary.get(key);

            if(interpretations.size() > 1){
                System.out.println(key + " значения: " + String.join(" ИЛИ ", interpretations));
            }
            else{
                System.out.println(key + " значение: " + String.join("", interpretations));
            }
        }
    }
}
