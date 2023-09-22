package org.example;

//в ячейке массива лежит что-то не то
public class MyArrayDataException extends RuntimeException{
    public MyArrayDataException(int i, int j, String value){
        super("В матрице в строке [" + (i+1) + "] в столбце ["+ (j+1) +"] лежит некое \"" + value + "\", что в свою очередь не является целым числом.");
    }
}
