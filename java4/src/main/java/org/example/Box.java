package org.example;

import java.util.*;

public class Box<T extends Fruit> {
    private List<T> list = new ArrayList<>();
    private List<T> getList() {
        return list;
    }

    public Box(T obj) {
        list.add(obj);
    }

    public void add(int count) {
        for (int i = 0; i < count; i++) {
            try {
                list.add((T) list.get(0).getClass().newInstance());
            } catch (InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }

    public void add(Lemon obj) {
        list.add((T) obj);
    }

    public void moveTo(Box<T> box) {
        if (!box.getList().equals(list)) {
            box.getList().addAll(list);
            list.clear();
            System.out.println("Успешно пересыпано.");
        }
        else{
            System.out.println("Не пересыпано.");
        }
    }

    public float getWeight() {
        if (list.isEmpty()) {
            return 0;
        }
        float weight = 0;
        for (T l : list) {
            weight += l.getWeight();
        }
        return weight;
    }
    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }
    public void printBox() {
        if (list.isEmpty()) {
            System.out.println("Коробка пустая");
        } else {
            for (T l : list) {
                System.out.println(l);
            }
            System.out.println();
        }
    }
}
