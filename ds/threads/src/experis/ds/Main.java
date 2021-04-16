package experis.ds;

import java.util.HashSet;

public class Main {
    private static void sortArray(int[] v) {
        SortArray firstHalf = new SortArray(v,0, v.length / 2);
        SortArray secondHalf = new SortArray(v,v.length / 2, v.length);

        var t1 = new Thread(firstHalf);
        var t2 = new Thread(secondHalf);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mergeTwoSortedSubArray(v);
    }


    private static void mergeTwoSortedSubArray(int[] v) {
        int start1 = 0;
        int start2 = v.length / 2;

        while (start1 != v.length / 2 && start2 != v.length) {
            if (v[start1] > v[start2]) {
                swap(v, start1, start2);
                start1++;
            } else {
                start2++;
            }
        }
    }

    private static void swap(int[] v, int i, int j) {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    private static Boolean search(int[] v, int x, int nThreads) {
        int multiply;
        if (v.length < nThreads) {
            multiply = 1;
            nThreads = v.length;
        } else {
            multiply = v.length / nThreads;
        }

        Thread[] threads = new Thread[nThreads];
        ArraySearcher[] arraySearcher = new ArraySearcher[nThreads];

        for (int i = 0; i < nThreads; i++) {
            int start = i * multiply;
            int end = declareEnd(i, nThreads, v.length, multiply);

            arraySearcher[i] = new ArraySearcher(v, start, end, x);
            threads[i] = new Thread(arraySearcher[i]);

            threads[i].start();
        }

        for (int i = 0; i < nThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return ArraySearcher.getFound();
    }

    private static int declareEnd(int i, int nThreads, int length, int multiply){
        if (i == nThreads - 1) {
            return length;
        } else {
            return (i + 1) * multiply;
        }
    }

    private static int countPairs(int[] v, int sum) {
        Thread[] threads = new Thread[v.length - 1];
        HashSet<Integer> pairs = new HashSet<>();
        PairsSum[] pairsSums = new PairsSum[v.length - 1];
        int count = 0;

        for (int i = 0; i < (v.length - 1) / 2; i++) {
            pairsSums[i] = new PairsSum(v, i, sum);
            threads[i] = new Thread(pairsSums[i]);
            threads[i].start();
        }

        for (int i = 0; i < (v.length - 1) / 2; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < (v.length - 1) / 2; i++) {
            Pair result = pairsSums[i].getResult();
            Integer firstResult = result.getFirstLoopResult();
            Integer secondResult = result.getSecondLoopResult();

            if (!isContainResult(firstResult, sum, pairs)) {
                pairs.add(firstResult);
                count++;
            }
            if (!isContainResult(secondResult, sum, pairs)) {
                pairs.add(secondResult);
                count++;
            }
        }
        System.out.println(pairs.toString());
        return count;
    }

    private static Boolean isContainResult(Integer result, int sum, HashSet<Integer> pairs) {
        return result == null || pairs.contains(result) || pairs.contains(sum - result);
    }

    private static int sum(int[] v){
        SumTask task1 = new SumTask(v, 0, v.length / 2);
        SumTask task2 = new SumTask(v, v.length / 2, v.length);
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return task1.getSum() + task2.getSum();

    }

    public static void main(String[] args) {
        int[] a = new int[1000];
        for (int i = a.length - 1; i >= 0; i--) {
            a[i] = i;
        }

        //question #1
        System.out.println("Sum: " + sum(a));

        //question #2
        sortArray(a);

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                System.out.println("Error");
                return;
            }
        }

        //question #3
        System.out.println(search(a, 888, 1100));

        //question #4
        System.out.println(countPairs(a, 17));

    }
}

