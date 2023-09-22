package org.example;

// неправильный размер массива
public class MyArraySizeException extends RuntimeException{
    public MyArraySizeException(String msg) {
        super(msg);
    }

}
