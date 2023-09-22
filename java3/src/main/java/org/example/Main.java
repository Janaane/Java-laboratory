package org.example;

public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"101", "90", "107", "109"},
                {"113", "100", "90", "137"},
                {"139", "149", "160", "157"},
                {"163", "167", "173", "11"}
        };

        try {
            System.out.println("Сумма чисел матрицы: " + method(array));
        }
        catch (MyArraySizeException | MyArrayDataException | MyNoSimpleException e){
                e.printStackTrace();
        }
    }
    public static int method(String[][] array) throws MyArraySizeException, MyArrayDataException, MyNoSimpleException{

        if(array.length != 4){
            throw new MyArraySizeException("Размер матрицы не 4x4!");
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4){
                throw new MyArraySizeException("Размер матрицы не 4x4!");
            }
        }

        int number;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                try {
                    number = Integer.parseInt(array[i][j]);
                    sum += number;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i,j,array[i][j]);
                }
                try {
                    if (array[i][j].length() == 3 && !isPrime(number))
                        throw new MyNoSimpleException(i, j, array[i][j]);
                } catch (MyNoSimpleException e) {
                    e.printStackTrace();
                }
            }
        }
        return sum;
    }

    public static boolean isPrime(int number)
    {
        for (int i = 2; i <= Math.sqrt(number); i++)
        {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}