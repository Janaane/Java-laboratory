package org.example;

@Table(title = "LION")
public class Lion {

    public enum Color {
        ORANGE("Оранжевый"), WHITE("Белый"), BLACK("Черный");
        private Color(String translate){
            this.translate = translate;
        }
        String translate;
        public String getTranslate(){
            return this.translate;
        }

    }
    public Lion(String name, int age, String maxRunDistance, Color color) {
        this.name = name;
        this.age = age;
        this.maxRunDistance = maxRunDistance;
        this.color = color;
        this.translateColor = color.getTranslate();
    }
    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String maxRunDistance;

    @Column
    private Color color;

    private String translateColor;
}
