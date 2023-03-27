package Labs.Lab1_B;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class ThreadClassLettersNumbers<E> implements Runnable {

    List<E> data;

    public ThreadClassLettersNumbers(List<E> data) {
        this.data = data;
    }

    @Override
    public void run() {
        data.forEach(System.out::print);
        System.out.println();
    }
}

public class TwoThreads {


    public static void main(String[] args) throws InterruptedException {

        List<Integer> nums = new ArrayList<>();
        List<Character> bukvi = new ArrayList<>();

        IntStream.range(1, 11)
                .boxed()
                .forEach(nums::add);

        IntStream.range(0, 10)
                .boxed()
                .forEach(i -> bukvi.add((char) (i + 65)));


        ThreadClassLettersNumbers numbers = new ThreadClassLettersNumbers(nums);
        ThreadClassLettersNumbers letters = new ThreadClassLettersNumbers<>(bukvi);

        Thread t1 = new Thread(numbers);
        Thread t2 = new Thread(letters);

        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }


}



