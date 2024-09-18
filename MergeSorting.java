import java.util.Random;

public class MergeSorting {
    //main
    public static void main(String[] args) {
        benchmarkWarmUp();
        benchmark();
    }

    private static void mergeSort(int[] array) {
		
		int length = array.length;
		if (length <= 1) return; //base case
		
		int middle = length / 2;
		int[] leftArray = new int[middle];
		int[] rightArray = new int[length - middle];
		
		int i = 0; //left array
		int j = 0; //right array
		
		for(; i < length; i++) {
			if(i < middle) {
				leftArray[i] = array[i];
			}
			else {
				rightArray[j] = array[i];
				j++;
			}
		}
		mergeSort(leftArray);
		mergeSort(rightArray);
		merge(leftArray, rightArray, array);
	}
	
	private static void merge(int[] leftArray, int[] rightArray, int[] array) {
		
		int leftSize = array.length / 2;
		int rightSize = array.length - leftSize;
		int i = 0, l = 0, r = 0; //indices
		
		//check the conditions for merging
		while(l < leftSize && r < rightSize) {
			if(leftArray[l] < rightArray[r]) {
				array[i] = leftArray[l];
				i++;
				l++;
			}
			else {
				array[i] = rightArray[r];
				i++;
				r++;
			}
		}
		while(l < leftSize) {
			array[i] = leftArray[l];
			i++;
			l++;
		}
		while(r < rightSize) {
			array[i] = rightArray[r];
			i++;
			r++;
		}
	}




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // This method benchmarks the selection sort algorithm for different sizes of arrays
    // It creates arrays with random elements and then apliers the selection sort algorithm to them and measures the time it takes to sort the array
    public static void benchmark(){
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12600, 25200, 51200, 102400}; //array som ska sorteras

        for(int n : sizes) {
            int[]arrayToBeSorted = createUnsortedArray(n);

            long n0SBR = System.nanoTime();
            mergeSort(arrayToBeSorted);
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
            mergeSort(keys);
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
