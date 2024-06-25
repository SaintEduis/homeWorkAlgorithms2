package org.example;

import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Main {
    public static void sortBubble(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 -i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void sortSelection(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = MAX_VALUE;
            int index = 0;
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    index = j;
                }
            }
            array[index] = array[i];
            array[i] = min;
        }
    }

    public static void sortInsertion(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100000];
        int[] arr2;
        int[] arr3;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        arr2 = arr;
        arr3 = arr;

        long start = System.currentTimeMillis();
        sortInsertion(arr);
        System.out.println(System.currentTimeMillis() - start);

        long secondStart = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println(System.currentTimeMillis() - secondStart);

        long thirdStart = System.currentTimeMillis();
        sortBubble(arr3);
        System.out.println(System.currentTimeMillis() - thirdStart);
    }
}