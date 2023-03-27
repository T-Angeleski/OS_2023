package Labs.Lab1_B;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class CountAB {

    public static int NUM_RUNS = 100;
    /**
     * Promenlivi koja treba da go sodrzat brojot na pojavuvanja na karakterite A i B.
     */
    static int countA = 0; // Shared resource -> static
    static int countB = 0;

    /**
     * Promenliva koja treba da go sodrzi prosecniot brojot na pojavuvanja na karakterite A i B.
     */
    double average = 0.0;

    /**
     * TODO: definirajte gi potrebnite elementi za sinhronizacija
     */
    static Semaphore lockA; // Shared semaphore across all threads, static
    static Semaphore lockB;

    public void init() {
        lockA = new Semaphore(1);
        lockB = new Semaphore(1);
    }

    class CounterA extends Thread {

        public void count(int[] data) throws InterruptedException {

            int counter = (int) Arrays.stream(data)
                    .filter(i -> (char)i == 'A')
                    .count();

            lockA.acquire();
            countA += counter;  // Critical region
            lockA.release();


        }

        private int[] data;

        public CounterA(int[] data) {
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

    class CounterB extends Thread {

        public void count(int[] data) throws InterruptedException {

            int counter = (int) Arrays.stream(data)
                    .filter(i -> (char)i == 'B')
                    .count();

            lockB.acquire();
            countB += counter;  // Critical region
            lockB.release();

        }

        private int[] data;

        public CounterB(int[] data) {
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
            CountAB environment = new CountAB();
            environment.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() throws Exception {

        init();

        HashSet<Thread> threads = new HashSet<Thread>();
        Scanner s = new Scanner(System.in);
        Random random = new Random();
        int total = s.nextInt();


        // total/NUM_RUNS for fragmenting
        for (int i = 0; i < NUM_RUNS; i++) {
            int[] data = new int[total / NUM_RUNS];
            for (int j = 0; j < total / NUM_RUNS; j++) {
                data[j] = random.nextInt(10) + 65; // +65 for ASCII char
            }
            CounterA c = new CounterA(data);
            threads.add(c);
        }

        for (int i = 0; i < NUM_RUNS; i++) {
            int[] data = new int[total / NUM_RUNS];
            for (int j = 0; j < total / NUM_RUNS; j++) {
                data[j] = random.nextInt(10) + 65;
            }
            CounterB c = new CounterB(data);
            threads.add(c);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(countA);
        System.out.println(countB);
        average = (countA + countB) * 1.f / total;
        System.out.printf("%.3f", average);


    }
}
