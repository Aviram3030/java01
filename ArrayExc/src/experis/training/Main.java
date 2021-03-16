package experis.training;

import java.util.Random;

public class Main {

    public static Boolean isNullOrEmpty(int[] v){
        return v == null || v.length == 0;
    }

    public static Boolean isNullOrEmpty(int[][] v){
        return v == null || v.length == 0;
    }

    //question #6
    public static void print(int[] v){
        if(isNullOrEmpty(v)) {
            System.out.print("[]");
            return;
        }

        System.out.print("[");

        for(int i = 0; i < v.length - 1; i++){
            System.out.printf("%d,", v[i]);
        }

        System.out.printf("%d]", v[v.length - 1]);

    }

    //question #6
    public static void print(int[][] v){
        if(isNullOrEmpty(v)){
            System.out.print("[]");
            return;
        }

        System.out.print("[ ");

        for(int i = 0; i < v.length - 1; i++){
                print(v[i]);
                System.out.print(",");
        }

        print(v[v.length - 1]);
        System.out.println(" ]");

    }

    // question #1
    public static int[] makeArray(int len, int start, int step){
        if(len <= 0)
            return null;

        int[] v = new int[len];
        Boolean isStepPositive = step > 0;


        v[0] = start;

        for(int i = 1; i < len ; i++){
            v[i] = v[i - 1] + step;

            if(isStepPositive && checkOverFlow(v[i-1], v[i])){
                v[i] = Integer.MAX_VALUE;
            }
            else if(!isStepPositive && checkOverFlow(v[i], v[i-1])){
                v[i] = Integer.MIN_VALUE;
            }
        }
        return v;
    }

    public static Boolean checkOverFlow(int a, int b){
        return a > b;
    }

    // question #2
    public static void bubbleSort(int[] v){
        if(isNullOrEmpty(v)) {
            return;
        }

        for(int i = 0; i < v.length - 1; i++){
            for(int j = 0; j < v.length - i - 1; j++){
                if(v[j] > v[j + 1]){
                    swap(v, j);
                }
            }
        }
    }

    public static void swap(int[] v, int position){
        int temp = v[position];
        v[position] = v[position + 1];
        v[position + 1] = temp;
    }

    // question #7
    public static void mergeImportantElements(int[] a,int[] b){  // time complexity : O(m + n)
        if(isNullOrEmpty(a) || isNullOrEmpty(b)) {
            return;
        }

        if(isEqualLength(a, b)){
            copy(a, b);
        }

        if(isLegalLength(a, b)) {
            return;
        }

        int updatedArrayIndex = a.length - 1;
        int aIndex = a.length - b.length - 1;
        int bIndex = b.length - 1;

        while(aIndex >= 0 && bIndex >= 0){
           if(compareTwoElements(a, b, aIndex, bIndex)){
               insertElement(a, a[aIndex], updatedArrayIndex);
               --aIndex;
           }
           else{
               insertElement(a, b[bIndex], updatedArrayIndex);
               --bIndex;
           }
            --updatedArrayIndex;
        }

        if(aIndex == -1){
            fillTheArray(a, b, updatedArrayIndex, bIndex);
        }

    }

    public static Boolean isEqualLength(int[] a, int[] b){
        return b.length == a.length;
    }

    public static Boolean isLegalLength(int[] a, int[] b){
        return b.length > a.length;
    }

    public static void copy(int[] a, int[] b){
        for(int i = 0; i < a.length ; i++){
            a[i] = b[i];
        }
    }

    public static Boolean compareTwoElements(int[] a, int[] b, int aIndex, int bIndex){
        return a[aIndex] > b[bIndex];
    }

    public static void insertElement(int[] a, int num, int index){
        a[index] = num;
    }

    public static void fillTheArray(int[] a,int[] b,int aIndex,int bIndex){
        while(bIndex >= 0){
            a[aIndex] = b[bIndex];
            --bIndex;
        }
    }

    //question #4
   public static void magicSquare(int [][] v){
        if(isNullOrEmpty(v)){
            return;
        }
        int N = v.length;

        if(N % 2 == 1){
            allToZero(v, N);
            evenSize(v, N);
        }
        else if(N % 4 == 0){
            indexToValue(v, N);
            doublyEvenTopLeft(v, N);
            doublyEvenTopRight(v, N);
            doublyEvenBottomLeft(v, N);
            doublyEvenBottomRight(v, N);
            doublyEvenCentre(v, N);
        }

   }
    public static void allToZero(int[][] v, int N){
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                v[i][j] = 0;
        }
    }

   public static void indexToValue(int[][] v, int N){
       for (int i = 0; i < N; i++)
       {
           for (int j = 0; j < N; j++)
               v[i][j] = N * i + j + 1;
       }
   }

   public static void evenSize(int[][] v, int N) {
       int number = 1;
       int row = 0;
       int column = N / 2;
       int currentRow;
       int currentColumn;

       while (number <= N * N) {
           v[row][column] = number;
           number++;
           currentRow = row;
           currentColumn = column;
           row -= 1;
           column += 1;

           if (row == -1) {
               row = N - 1;
           }

           if (column == N) {
               column = 0;
           }

           if (v[row][column] != 0) {
               row = currentRow + 1;
               column = currentColumn;
               if (row == -1) {
                   row = N - 1;
               }
           }
       }
   }

   public static void doublyEvenTopLeft(int [][] v,int N){
       for (int i = 0; i < N / 4; i++)
       {
           for (int j = 0; j < N / 4; j++)
               v[i][j] = N * N + 1 - v[i][j];
       }
   }
    public static void doublyEvenTopRight(int [][] v,int N){
        for (int i = 0; i < N/4; i++)
        {
            for (int j = 3* N/4; j < N; j++)
                v[i][j] = N * N + 1 - v[i][j];
        }
    }
    public static void doublyEvenBottomLeft(int [][] v,int N){
        for (int i = 3 * N / 4; i < N; i++)
        {
            for (int j = 0; j < N / 4; j++)
                v[i][j] = N * N + 1 - v[i][j];
        }
    }

    public static void doublyEvenBottomRight(int [][] v,int N){
        for (int i = 3 * N / 4; i < N; i++)
        {
            for (int j = 3 * N / 4; j < N; j++)
               v[i][j] = N * N + 1 - v[i][j];
        }
    }
    public static void doublyEvenCentre(int [][] v,int N){
        for (int i = N / 4; i < 3 * N / 4; i++)
        {
            for (int j = N / 4; j < 3* N / 4; j++)
                v[i][j] = N * N + 1 - v[i][j];
        }
    }

    // question #5
   public static int [][] diagonalLine(int n, int m){
        if(n < 1 || m < 1){
            System.out.println("illegal input");
            return null;
        }

        int[][] v = new int [n][m];

        int count = fillFromTheLeft(v, n, m);
        fillFromTheBottom(v,n,m,count);
        return v;

   }

   public static int fillFromTheLeft(int[][] v, int n, int m) {
       int currentRow = 0;
       int count = 1;

       while (currentRow < n) {
           int rowIndex = currentRow;
           int columnIndex = 0;

           count = writingIntoTheCells(v, rowIndex, columnIndex, count, m);
           currentRow++;
       }
       return count;
   }

   public static void fillFromTheBottom(int[][] v, int n, int m,int count){
        int currentColumn = 1;

        while(currentColumn < m){
            int rowIndex = n - 1;
            int columnIndex = currentColumn;

            count = writingIntoTheCells(v, rowIndex, columnIndex, count, m);
            currentColumn++;
        }
   }

   public static int writingIntoTheCells(int[][] v, int rowIndex, int columnIndex, int count,int m){
       while(rowIndex >= 0 && columnIndex < m){
           v[rowIndex][columnIndex] = count;
           count++;
           rowIndex--;
           columnIndex++;
       }

       return count;
   }

   public static void shakeArray(int [] v){
        Random getNumber = new Random();

        for(int i = v.length - 1; i >= 0; i--){
            int j = getNumber.nextInt(i + 1);

            shake(v, i, j);

        }
   }

   public static void shake(int[] v,int i,int j){
       int temp = v[i];
       v[i] = v[j];
       v[j] = temp;
   }


    public static void main(String[] args) {
    int[] a = {3,4,5,8,12,11};
    shakeArray(a);
    print(a);
    System.out.println();

    int[][] b = {
            {1, 2, 5},
            {},
            {5, 19, 8},
            a
    };
    print(b);

    int[] c = makeArray(4,2,3);
    print(c);
    System.out.println();

    int[] d = {7,3,2,6,5,9,20,8,4};
    bubbleSort(d);
    bubbleSort(a);
    print(d);
    System.out.println();

    print(a);
    System.out.println();

    print(diagonalLine(4,2));

    mergeImportantElements(d , a);
    print(d);
    System.out.println();

    int[][] e = new int[5][5];
    magicSquare(e);
    print(e);




    }

}
