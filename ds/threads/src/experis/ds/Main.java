package experis.ds;

    class LeftTask implements Runnable{
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

    class RightTask implements Runnable{
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

    public class Main {
        public static int[] a = new int[1000];
        volatile static Boolean found = false;

        public static void mergeArray(int[] v){
            Runnable lambda = () -> {
            for (int i = 0; i < v.length / 2; i++) {
                for (int j = 0; j < v.length / 2 - i - 1; j++) {
                    if (a[j] > a[j + 1])
                        swap(j + 1, j);
                }
            }};

            var t1 = new Thread(lambda);
            var t2 = new Thread(() -> {
                for (int i = v.length / 2; i < v.length; i++) {
                    for (int j = v.length / 2; j < v.length - i - 1; j++) {
                        if (a[j] > a[j + 1])
                            swap(j + 1, j);
                    }
            }});

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mergeTwoSortedSubArray(0, a.length/2, a.length/2 + 1, a.length);
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

        public static Boolean search(int[] v, int x, int nThreads){
            Thread[] threads = new Thread[nThreads];
            for(int i = 0; i < nThreads; i++){
                int finalI = i;

                threads[i] = new Thread(() ->{
                    int multiply = v.length / nThreads;

                    int start = finalI * multiply;
                    int end;
                    if(finalI == nThreads - 1) {
                        end = v.length;
                    }
                    else{
                        end = (finalI + 1) * multiply;
                    }
                    for(int j = start; j < end; j++){
                        if(v[j] == x || found){
                            found = true;
                            return;
                        }
                    }
                });

                threads[i].start();
            }

            for(int i = 0; i < nThreads; i++){
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return found;
        }

        public static void main(String[] args) {

            for (int i =  a.length - 1; i > 0; i--) {
                a[i] = i;
            }

            LeftTask task1 = new LeftTask();
            RightTask task2 = new RightTask();
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

            mergeArray(a);

            for(int i = 0; i < a.length - 1; i++){
                if(a[i] > a[i+1]){
                    System.out.println("Error");
                    return;
                }
                System.out.println(a[i]);
            }

            System.out.println(search(a, 999, 1005));
        }
    }

