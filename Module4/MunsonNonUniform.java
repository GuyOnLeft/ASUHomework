import java.util.Random;
import java.lang.Math;

/**
 * (basic description of the program or class)
 * 
 * Completion time: (estimation of hours spent on this program)
 *
 * @author (your name), Acuna, Sedgewick
 * @version (a version number or a date)
 */


public class MunsonNonUniform implements SER222_02_01_HW02_Submission {


    @Override
    public Integer[] generateTestDataBinary(int size) {
        Integer[] a = new Integer[size];
        for (int i = 0; i < size; i++) {
            if (i <= size/2) {
                a[i] = 1;
            }
            else {
                a[i] = 0;
            }
        }
        return a;
    }

    @Override
    public Integer[] generateTestDataHalfs(int size) {
        Integer[] a = new Integer[size];
        int half = (size / 2) + (size % 2);
        int index = half;
        int value = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                half = (half / 2) + (half % 2);
                index += half;
                value++;
            }
            a[i] = value;
        }
        return a;

    }

    @Override
    public Integer[] generateTestDataHalfRandom(int size) {
        Random random = new Random();
        Integer[] a = new Integer[size];
        for (int i = 0; i < size; i++) {
            if (i <= size / 2) {
                a[i] = 0;
            } else {
                a[i] = random.nextInt();
            }
        }
        return a;
    }

    @Override
    public double computeDoublingFormula (double t1, double t2) {
        double preLog = (t2/t1);
        double bValue = Math.log(preLog) / Math.log(2);
        return bValue;
    }

    @Override
    public double benchmarkInsertionSort(Integer[] small, Integer[] large) {
        Stopwatch timer1 = new Stopwatch();
        insertionSort(small);
        double timeSmall = timer1.elapsedTime();

        Stopwatch timer2 = new Stopwatch();
        insertionSort(large);
        double timeLarge = timer2.elapsedTime();

        return computeDoublingFormula(timeSmall, timeLarge);
    }

    @Override
    public double benchmarkShellsort(Integer[] small, Integer[] large) {
        Stopwatch timer1 = new Stopwatch();
        insertionSort(small);
        double timeSmall = timer1.elapsedTime();

        Stopwatch timer2 = new Stopwatch();
        insertionSort(large);
        double timeLarge = timer2.elapsedTime();

        return computeDoublingFormula(timeSmall, timeLarge);
    }

    @Override
    public void runBenchmarks(int size) {
        Integer [] bin = generateTestDataBinary(size);
        Integer [] half = generateTestDataHalfs(size);
        Integer [] ranint = generateTestDataHalfRandom(size);
        Integer [] binTimesTwo = generateTestDataBinary(size*2);
        Integer [] halfTimesTwo = generateTestDataHalfs(size*2);
        Integer [] ranintTimesTwo = generateTestDataHalfRandom(size*2);

        double binBenchmarkInsertionSort = benchmarkInsertionSort(bin, binTimesTwo);
        double binBenchmarkShellSort = benchmarkShellsort(bin,binTimesTwo);
        double ranBenchmarkInsertionSort = benchmarkInsertionSort (ranint, ranintTimesTwo);
        double ranBenchmarkShellSort = benchmarkShellsort(ranint,ranintTimesTwo);
        double halfBenchmarkInsertionSort = benchmarkInsertionSort (half, halfTimesTwo);
        double halfBenchmarkShellSort = benchmarkShellsort(half,halfTimesTwo);


        System.out.println("         Insertion     Shellsort");
        System.out.println("Bin: " + binBenchmarkInsertionSort + " " + binBenchmarkShellSort );
        System.out.println("Half: " + halfBenchmarkInsertionSort + " " + halfBenchmarkShellSort );
        System.out.println("RanInt: " + ranBenchmarkInsertionSort + " " + ranBenchmarkShellSort);




    }


 
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    
    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/

    //TODO: implement interface methods.


    public static void main(String args[]) {
        SER222_02_01_HW02_Submission me = new MunsonNonUniform();
        int size = 4096;
        
        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);

    }
}