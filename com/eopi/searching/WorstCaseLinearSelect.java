package com.eopi.searching;

/**
 * Search for the kth smallest value in an array in linear time. 
 * The algorithm requires us to get a partition element that is compared with all the elements for a section of the 
 * array to determine the position of the partition element. If the position of the partition element matches k 
 * => the partition element is the value we were looking for i.e. the kth element is the partition element itself.
 * If partition element's position is greater than k, the kth element lies somewhere to the left of the partition
 * elements position. If partition element's position is lesser than k, the kth element lies somewhere to the right 
 * of the parition element.
 * Time Complexity: O(n) 
 * Note, this implementation uses a random index of (low + high) / 2 as the partition element that in the strictest
 * sense would be O(n^2) but O(n) for average case. In order to achieve O(n), divide the array into chunks of 5
 * elements and computer the median for each chunk. Use the median of these medians as the partition element. 
 * This will ensure the partition element does not end up as the lowest or the largest element for any iteration of the
 * search. 
 * @author marwaha
 *
 */
public class WorstCaseLinearSearch {

    public static void main(String[] args) {
        int[][] in = new int[][] 
                {
                    { 2, 1, 7, 10, 13, 5, 8, 3, 2},
                    {3},
                    {1, 2, 2, 2, 4}
                };
        int[][] searchKthElement = new int[][] {
                { 3, 4, 5, 6},
                { 1, 2},
                { 3, 4, 5}
            };
        for (int x = 0; x < in.length; x++) {
            for (int y = 0; y < searchKthElement[x].length; y++) {
                System.out.println(searchKthElement[x][y] + "th element is " + search(in[x], searchKthElement[x][y]));
            }
        }
    }

    
    private static Integer search(int[] A, int k) {
        int low = 0;
        int high = A.length - 1;
        // Bugometer 4/5: make sure you search for low <= high instead of low < high, because you want to return
        // a result if you are 
        while (low <= high) {
            int pivotIndex = pivotIndex(A, low, high, (low + high)/2);
            if (pivotIndex == k - 1) {
                return A[pivotIndex];
            } else if (pivotIndex > k - 1) {
                high = pivotIndex - 1;
            } else {
                low = pivotIndex + 1;
            }
        }
        return null;
    }

    private static int pivotIndex(int[] A, int low, int high, int pivotIndex) {
        int pivot = A[pivotIndex];
        swap(A, pivotIndex, high);
        int lesserThanPivotIndex = low;
        for (int i = low; i < high; i++) {
            if (A[i] < pivot) {
                swap(A, lesserThanPivotIndex, i);
                lesserThanPivotIndex++;
            }
        }
        swap(A, lesserThanPivotIndex, high);
        return lesserThanPivotIndex;
    }

    private static void swap(int[] A, int pivotIndex, int high) {
        int temp = A[pivotIndex];
        A[pivotIndex] = A[high];
        A[high] = temp;
    }

}
