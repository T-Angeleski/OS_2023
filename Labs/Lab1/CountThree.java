package Labs.Lab1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class CountThree {

    public static int NUM_RUNS = 100;

    static int count = 0;

    static Semaphore semaphore = new Semaphore(1);

    public void init() {
    }

    class Counter extends Thread {

        public static void count(int[] data) throws InterruptedException {

            semaphore.acquire();  // Critical region, use binary semaphore
            // Print array
            Arrays.stream(data).forEach(i -> System.out.print(i + " "));
            System.out.println();

            // Get count of 3s in array
            int counter = (int) Arrays.stream(data).filter(i -> i == 3).count();
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

        HashSet<Thread> threads = new HashSet<>();
        Scanner s = new Scanner(System.in);
        int total = s.nextInt();
        Random random = new Random();

        for (int i = 0; i < NUM_RUNS; i++) {
            // Fragment array with total/num runs
            int[] data = new int[total / NUM_RUNS];

            for (int j = 0; j < (total / NUM_RUNS); j++) {
                data[j] = random.nextInt(10); // Get random number from 1/10
            }

            Counter c = new Counter(data);
            threads.add(c);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(count);


    }
}