// Utsav Acharya
// CS 1181 - Lab: Sort Trace

public class BubbleSortTrace {
    public static void main(String[] args) {
        int[] array = {6, 3, 11, 4, 9, 8, 17, 7};

        System.out.println("Original array:");
        printArray(array);

        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap the elements
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // Print the array after each pass
            System.out.print("After pass " + (i + 1) + ": ");
            printArray(array);

            // Optional: Stop early if no swaps
            if (!swapped) break;
        }
    }

    // Helper method to print the array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
