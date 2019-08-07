package com.eopi.sorting;

public class MergeSort {

    public static void main(String[] args) {
        int[][] input = new int[][] { 
            {6, 2, 5, 4, 5, 1, 6},
            {3, 6, 4, 4, 5, 3, 1, 3}
    };

    for (int x = 0; x < input.length; x++) {
        int[] output = new int[input[x].length];
        mergeSort(input[x], 0 , input[x].length - 1, output);
        for (int y = 0; y < input[x].length; y++) {
            System.out.print(input[x][y] + ",");
        }
        System.out.println("");
    }
    System.out.println("Running merge sort 2");
    for (int x = 0; x < input.length; x++) {
        int[] output = new int[input[x].length];
        mergeSort2(input[x], 0 , input[x].length);
        for (int y = 0; y < input[x].length; y++) {
            System.out.print(input[x][y] + ",");
        }
        System.out.println("");
    }
    }

    private static void mergeSort(int[] input, int low, int high, int[] output) {
        if (low > high) {
            return;
        }
        if (low == high) {
          //  output[low] = input[low];
            return;
        }
        int mid = low + (high - low)/2;
        mergeSort(input, low, mid, output);
        mergeSort(input, mid + 1, high, output);
        merge(input, low, mid, high);
    }

    private static void merge(int[] input, int low, int mid, int high) {
        if (low >= high || low > mid || mid >= high) {
            return;
        }
//        System.out.println("low: " + low + " mid: " + mid + " high: " + high);
        int i = 0;
        int j = 0;
        int k = low;
        int[] l = new int[mid - low + 1];
        int[] r = new int[high - mid];
        for (int x = low; x <= mid; x++) {
            l[x-low] = input[x];
        }
        for (int x = mid + 1; x <= high; x++) {
            r[x-mid -1] = input[x];
        }
        
        while (i <= mid - low && j <= high - mid - 1) {
            if (l[i] <= r[j]) {
                input[k] = l[i++];
//                System.out.println("k ("+ k + ") is " + input[k]);
            } else {
                input[k] = r[j++];
//                System.out.println("k (" + k+ ") is " + input[k]);
            }
            k++;
        }
        while(i <= mid- low) {
            input[k] = l[i++];
//            System.out.println("k is " + input[k]);
        }
        while (j <= high - mid - 1) {
            input[k] = r[j++];
//            System.out.println("k is " + input[k]);
        }
        k++;

    }

    // Implementation with high always being an index greater than last element.
    // Prefer this implementation as this is way cleaner.
    public static void mergeSort2(int[] in, int low, int high) {
        if (low >= high || low + 1 == high) {
            return;
        }
        int mid = low + (high - low)/2;
        mergeSort2(in, low, mid);
        mergeSort2(in, mid, high);
        merge2(in, low, mid, high);
    }

    private static void merge2(int[] in, int low, int mid, int high) {
        int[] l = new int[mid - low];
        int[] r = new int[high - mid];
        for (int x = low; x < mid; x++) {
            l[x - low] = in[x];
        }
        for (int y = mid; y < high; y++) {
            r[y - mid] = in[y];
        }
        int k = low;
        int x = 0, y = 0;
        while (x < mid - low && y < high - mid) {
            if (l[x] > r[y]) {
                in[k++] = r[y++];
            } else {
                in[k++] = l[x++];
            }
        }
        while (x < mid - low) {
            in[k++] = l[x++];
        }
        while (y < high - mid) {
            in[k++] = r[y++];
        }
    }

}
