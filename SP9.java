package ypp170130;

/**
 *     Team No: 33
 *     @author Vineet Vats: vxv180008
 *     @author Yash Pradhan: ypp170130
 *     Short Project 9: Divide and Conquer algorithms
 */

import java.util.Random;

public class SP9 {
    public static Random random = new Random();
    public static int numTrials = 100;
    public static void main(String[] args) {
        int n = 100000;
        int choice = 1 + random.nextInt(4);
        // n and choice can be provided as command line arguments

        if(args.length > 0) { n = Integer.parseInt(args[0]); }
        if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;
        }
        Timer timer = new Timer();
        switch(choice) {
            case 1:
                Shuffle.shuffle(arr);
                numTrials = 1;
                insertionSort(arr);
                break;
            case 2:
                numTrials = 100;
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort1(arr);
                }
                break;
            case 3:
                numTrials = 100;
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort2(arr);
                }
                break;
            case 4:
                numTrials = 100;
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort3(arr);
                }
                break;
        }
        timer.end();
        timer.scale(numTrials);

        System.out.println("Choice: " + choice + "\n" + timer);
    }

    public static void insertionSort(int[] arr) {
        insertionSort(arr, 0, arr.length-1);
    }

    /**
     * Sorts arr[p..r] by insertion sort
     * @param arr
     * @param p
     * @param r
     */
    private static void insertionSort(int[] arr, int p, int r) {
        int temp, j;

        //LI: arr from p to i is sorted
        for (int i = p + 1; i <= r; i++) {

            temp = arr[i];
            j = i - 1;

            while (j >= p && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void mergeSort1(int[] arr) {
        mergesort1(arr, 0, arr.length-1);
    }

    /**
     * Sorts A[p..r] using merge sort take 1
     * @param A
     * @param p
     * @param r
     */
    private static void mergesort1(int[] A, int p, int r) {
        int q;
        if(p<r) {
            //divide
            q=(p+r)/2;
            mergesort1(A,p,q);
            mergesort1(A,q+1,r);

            //conquer
            merge1(A,p,q,r);
        }
    }

    private static void merge1(int[] A, int p, int q, int r) {
        int[] L= new int[q-p+1];
        int[] R= new int[r-q];

        System.arraycopy(A, p, L, 0, q-p+1);
        System.arraycopy(A, q+1, R, 0, r-q);

        int i=0, j=0;

        //merge two sorted arrays into A
        for(int k=p;k<=r;k++) {
            if(j>=R.length || (i< L.length && L[i]<=R[j]))
                A[k]=L[i++];
            else
                A[k]=R[j++];
        }
    }

    public static void mergeSort2(int[] arr) {
        int[] B = new int[arr.length];
        mergesort2(arr, B, 0, arr.length-1);
    }

    /**
     * Sorte A[p..r] using merge sort take 2
     * B is used as an auxillary array, hence saving space
     * @param A
     * @param B
     * @param p
     * @param r
     */
    private static void mergesort2(int[] A, int[] B, int p, int r) {
        int q;
        int T = 64;
        if(r-p+1<T) {
            insertionSort(A, p, r);
        }
        else{
            //divide
            q=(p+r)/2;
            mergesort2(A,B,p,q);
            mergesort2(A,B,q+1,r);

            //conquer
            merge2(A, B, p,q,r);
        }
    }

    private static void merge2(int[] A, int[] B, int p, int q, int r) {

        System.arraycopy(A, p, B, p, r-p+1);

        int i=p, j=q+1;

        //merge the 2 sorted array into A
        for(int k=p;k<=r;k++) {
            if(j>=r+1 || (i<q+1 && B[i]<=B[j]))
                A[k]=B[i++];
            else
                A[k]=B[j++];
        }
    }

    public static void mergeSort3(int[] A)
    {
        int[] B = new int[A.length];
        System.arraycopy(A, 0, B, 0, A.length);
        mergeSort3(A,B,0,A.length);
    }

    /**
     * Sorts A using merge sort take 3
     * @param A
     * @param B
     * @param left
     * @param n
     */
    private static void mergeSort3(int[] A, int[] B, int left, int n)
    {

        if(n<64) {
            insertionSort(A, left, left+n-1);
        }
        else {
            //divide
            int Ln = n/2;
            mergeSort3(B,A, left, Ln);
            mergeSort3(B,A, left+ Ln, n-Ln);

            //conquer
            merge3(A,B, left, left+Ln-1, left+n-1);
        }


    }

    private static void merge3(int[] A, int[] B, int p, int q, int r) {
        int i= p;
        int j= q+1;
        int k= p;
        while(i<=q && j<=r) {
            if(B[i]<= B[j]) {
                A[k++]= B[i++];
            }
            else {
                A[k++]= B[j++];
            }

        }
        while(i<q) {
            A[k++]= B[i++];

        }
        while(j<=r) {
            A[k++]= B[j++];
        }
    }

    /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

        public void scale(int num) { elapsedTime /= num; }

        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }

    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {

        public static void shuffle(int[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static<T> void shuffle(T[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static void shuffle(int[] arr, int from, int to) {
            int n = to - from  + 1;
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        public static<T> void shuffle(T[] arr, int from, int to) {
            int n = to - from  + 1;
            Random random = new Random();
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        static void swap(int[] arr, int x, int y) {
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        static<T> void swap(T[] arr, int x, int y) {
            T tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        public static<T> void printArray(T[] arr, String message) {
            printArray(arr, 0, arr.length-1, message);
        }

        public static<T> void printArray(T[] arr, int from, int to, String message) {
            System.out.print(message);
            for(int i=from; i<=to; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }
}