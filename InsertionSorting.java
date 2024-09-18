import java.util.Random;

public class InsertionSorting {
    //main
    public static void main(String[] args) {
        benchmarkWarmUp();
        benchmark();
    }

    public static int[] insertionSort(int[] arrayUnSorted){
        int n = arrayUnSorted.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--){
                if (arrayUnSorted[j] < arrayUnSorted[j-1]){
                    arrayUnSorted = arrayElementSwap(arrayUnSorted, j, j-1);
                }
            }
        }
        return arrayUnSorted;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // This method benchmarks the selection sort algorithm for different sizes of arrays
    // It creates arrays with random elements and then apliers the selection sort algorithm to them and measures the time it takes to sort the array
    public static void benchmark(){
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12600, 25200}; //array som ska sorteras

        for(int n : sizes) {
            int[]arrayToBeSorted = createUnsortedArray(n);

            long n0SBR = System.nanoTime();
            insertionSort(arrayToBeSorted);
            long n1SBR = System.nanoTime();
            long avgSBR = (n1SBR-n0SBR);

            System.out.println(n + " " + avgSBR);
        }
    }

    // This method is used to warm up the JVM before the benchmarking is done to get more accurate results
    public static void benchmarkWarmUp(){
        int[] sizes = {1000, 10000, 35000}; //array som ska sorteras

        for(int n : sizes) {
            int[]keys = createUnsortedArray(n);
            insertionSort(keys);
        }
    }

    // Method to create an array with random elements
    public static int[] createUnsortedArray(int size) {
        Random random = new Random();

        int[] array = new int[size];
        for (int index = 0; index < size; index++) {
            array[index] = random.nextInt(size*5);
        }
        return array;
    }

    // Method to swap the places of two elements in an array
    // Stores the element that is to be swapped in a temporary variable
    // Moves the element at the current index to the index of the element that is to be swapped
    // Moves the element that is to be swapped to the index of the element that was at the current index
    // The current index represents the position in the array which is currently being sorted
    // The minimum index represents the position of the smallest element found in the array ahead of the current index
    public static int[] arrayElementSwap(int[] array, int minIndex, int currentIndex) {
        int temp = array[minIndex];
        array[minIndex] = array[currentIndex];
        array[currentIndex] = temp;
        return array;
    }
}
