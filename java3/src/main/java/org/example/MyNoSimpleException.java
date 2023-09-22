package org.example;

//В ячейке массива не простое трехзначное число
public class MyNoSimpleException extends RuntimeException{

    public MyNoSimpleException(int i, int j, String value){
        super("В матрице в строке [" + (i+1) + "] в столбце ["+ (j+1) +"] лежит \"" + value + "\", что в свою очередь является трехзначным не простым числом.");
    }

}
