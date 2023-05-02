package Labs.Lab2_B;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class TreePlanting {
    private static final int NUM_THREADS = 100;
    private static final int M = 5000;
    private static final int N = 2000;

    // store the number of elements in count
    private static int count = 0;

    //TODO: declare synchronization mechanisms
    static Semaphore lock;

    public static void init() {
        // TODO: initialize synchronization mechanisms
        lock = new Semaphore(1);
    }


    public static void main(String[] args) {

        init();

        List<Thread> threadList = new ArrayList<>();

        String [][] matrix = MatrixGenerator.generate(M, N);

        int rowsPerThread = matrix.length / NUM_THREADS;

        for (int j = 0; j < NUM_THREADS; j++) {
            int startRow = j * rowsPerThread;
            int endRow = Math.min(matrix.length, (j + 1) * rowsPerThread);

            Thread t = new Thread(
                    new Counter(startRow, endRow, matrix)
            );

            threadList.add(t);
        }

        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        checkSynchronization();

        System.out.printf("The number of trees is: %d\n", count);
    }

    private static class MatrixGenerator {

        static int elementCount;
        private static final String[] elements = {
                "T",
                "K",
                "P",
                "D",
                "O",
                "X",
                "/"
        };

        static String[][] generate(int m, int n) {
            String[][] matrix = new String[m][n];
            Random random = new Random();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int index = random.nextInt(elements.length);
                    String element = elements[index];

                    if (element.equals("X")) {
                        elementCount++;
                    }

                    matrix[i][j] = element;
                }
            }

            return matrix;
        }
    }


    private static class Counter implements Runnable {
        private final int startRow;
        private final int endRow;
        private final String[][] matrix;

        public Counter(int startRow, int endRow, String[][] matrix) {
            this.startRow = startRow;
            this.endRow = endRow;
            this.matrix = matrix;
        }

        private void countElements() throws InterruptedException {
            // TODO: Implement counting of elements

            int countTmp = 0;

            for(int i = startRow; i < endRow; i++) {
                for(int j = 0; j < matrix[i].length; j++) {
                    if(matrix[i][j].equalsIgnoreCase("X"))
                        countTmp++;
                }
            }

            lock.acquire();
            count += countTmp;
            lock.release();
        }

        @Override
        public void run() {
            try {
                countElements();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void checkSynchronization() {
        if (MatrixGenerator.elementCount != count) {
            throw new RuntimeException(
                    String.format(
                            "Synchronization failed.\nOriginal value: %d, Calculated value %d\n",
                            MatrixGenerator.elementCount,
                            count
                    )
            );
        }
    }

}



