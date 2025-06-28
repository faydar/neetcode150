package utils;

import java.util.List;

public class Print {
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println(arr[arr.length - 1]);
    }

    public static void printList(List<?> val) {
        for (int i = 0; i < val.size() - 1; i++) {
            System.out.print(val.get(i) + " ");
        }

        System.out.println(val.get(val.size() - 1));
    }
}
