package experis.ds;

    class FirstTask implements Runnable{
        private int sum = 0;

        @Override
        public void run() {
            for(int i = 0; i < Main.a.length / 2; i++){
                sum += Main.a[i];
            }
        }

        public int getSum(){
            return sum;
        }
    }

    class SecondTask implements Runnable{
        private int sum = 0;

        @Override
        public void run() {
            for(int i = Main.a.length / 2; i < Main.a.length; i++){
                sum += Main.a[i];
            }
        }

        public int getSum(){
            return sum;
        }
    }

    class FirstSort implements Runnable{

        @Override
        public void run() {
            Main.bubbleSort(0, Main.a.length / 4);
        }
    }

    class SecondSort implements Runnable{

        @Override
        public void run() {
            Main.bubbleSort(Main.a.length / 4, Main.a.length / 2);
        }
    }


    class ThirdSort implements Runnable{

        @Override
        public void run() {
            Main.bubbleSort(Main.a.length / 2, 3 * Main.a.length / 4);
        }
    }

    class FourthSort implements Runnable{

        @Override
        public void run() {
            Main.bubbleSort(3 * Main.a.length / 4, Main.a.length);
        }
    }

    public class Main {
        public static int[] a = new int[1000];

        public static void bubbleSort(int start, int end){
            for (int i = start; i < end - 1; i++) {
                for (int j = 0; j < end - i - 1; j++) {
                    if (a[j] > a[j + 1])
                        swap(j + 1, j);
                }
            }
        }

        public static void mergeArray(){
            mergeTwoSortedSubArray(0, a.length/4, a.length/4 + 1, a.length / 2);
            mergeTwoSortedSubArray(a.length / 2, 3 * a.length/4, 3 * a.length/4 + 1, a.length);
        }

        private static void mergeTwoSortedSubArray(int start1, int end1, int start2, int end2){
            while(start1 != end1 && start2 != end2){
                if(a[start1] > a[start2]){
                    swap(start1, start2);
                    start1++;
                }
                else{
                    start2++;
                }
            }
        }

        private static void swap(int i, int j){
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        public static void main(String[] args) {

            for (int i =  a.length - 1; i > 0; i--) {
                a[i] = i;
            }


            FirstTask task1 = new FirstTask();
            SecondTask task2 = new SecondTask();
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

            System.out.println("Sum: " + task1.getSum() + task2.getSum());

            FirstSort firstPart = new FirstSort();
            SecondSort secondPart = new SecondSort();
            ThirdSort thirdPart = new ThirdSort();
            FourthSort fourthPart = new FourthSort();

            Thread t3 = new Thread(firstPart);
            Thread t4 = new Thread(secondPart);
            Thread t5 = new Thread(thirdPart);
            Thread t6 = new Thread(fourthPart);


            t3.start();
            t4.start();
            t5.start();
            t6.start();

            try {
                t3.join();
                t4.join();
                t5.join();
                t6.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mergeArray();

            for(int i = 0; i < a.length - 1; i++){
                if(a[i] > a[i+1]){
                    System.out.println("Error");
                    return;
                }
            }


        }
    }

