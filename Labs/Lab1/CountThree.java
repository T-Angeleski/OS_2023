package Labs.Lab1;

import java.util.*;
import java.util.concurrent.Semaphore;

public class CountThree {

    static int count = 0;
    static Semaphore semaphore = new Semaphore(1);


    public void init() {
    }

    class Counter extends Thread {

        public void count(int[] data) throws InterruptedException {

            semaphore.acquire();

            int counter = 0;
            for(int i = 0 ; i < data.length ; i++) {
                if (data[i] == 3) counter++;
            }
            count += counter;
            semaphore.release();
        }

        private int[] data;

        public Counter(int[] data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                count(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            CountThree environment = new CountThree();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();

        int[] numbers = new int[1000];
        Random random = new Random();
//        for (int i = 0; i < 1000; i++)
//            numbers.add(random.nextInt(10));
        for(int i = 0 ; i < 1000 ; i++) {
            if(i <=500) {
                numbers[i] = 2;
            } else {
                numbers[i] = 3;
            }
        }

        for (int i = 0; i < 4; i++) {
            
            threads.add(new Counter(numbers));
        }


        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.printf("For array %s\n There are %d 3's",
                Arrays.toString(numbers), count);

    }
}
