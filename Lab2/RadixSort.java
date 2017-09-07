package Lab2;

public class RadixSort {

    //get maximum of array to know how many digits to use
    private int getMax(int arr[]) {

        int max = arr[0];

        for(int i = 1; i < arr.length; i++) {

            if(arr[i] > max) {

                max = arr[i];
            }
        }
        return max;
    }
    //------------------------------------------------------------------------------------------------

    //method to print the array
    private void print(int arr[]) {

        for(int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    //------------------------------------------------------------------------------------------------

    //a count sort that will go through each digit place to sort
    private void cSort(int arr[], int digit) {


        int count[] = new int[10];
        int temp[] = new int[arr.length];

        //fill count array with zeroes. Each index will account for a number 0 - 9.
        for(int i = 0; i < count.length; i++) {

            count[i] = 0;
        }

        //add 1 for each occurrence of a number 0 - 9. Use modulo to
        //select the correct digit place
        for(int i = 0; i < arr.length; i++) {

            count[(arr[i] / digit) % 10]++;
        }

        //update count array to the actual position in temp[]
        for(int i = 1; i < count.length; i++) {

            count[i] += count[i - 1];

        }

        //sort the count[] array into the temp[] array
        for(int i = arr.length - 1; i >= 0; i--) {

            temp[count[(arr[i] / digit) % 10] - 1] = arr[i];
            //decrement position by 1
            count[(arr[i] / digit) % 10]--;
        }

        //copy temp[] to arr[]
        for(int i = 0; i < arr.length; i++) {

            arr[i] = temp[i];
        }
    }
    //----------------------------------------------------------------------------------------------
    private void rSort(int arr[]) {

        //use getMax to know the number of digits the sort will go through
        int max = getMax(arr);
        int digit = 1;

        while(max / digit > 0) {

            cSort(arr, digit);

            //increment to move to next digit i.e 10, 100, 1000 etc.
            digit *= 10;
        }
    }
    //------------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        RadixSort sort = new RadixSort();
        int arr[] = new int[10];

        //generate array of random numbers
        for(int i = 0; i < arr.length; i++) {

            arr[i] = (int)(Math.random() * 999);
        }

        //unsorted
        sort.print(arr);
        sort.rSort(arr);

        //sorted
        sort.print(arr);
    }
}
