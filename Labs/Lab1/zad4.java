package Labs.Lab1;

public class zad4 {
    public static class ThreadAB implements Runnable {

        public String s1;
        public String s2;

        public ThreadAB(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public void run() {
            System.out.println(s1);
            System.out.println(s2);
        }
    }

    public static void main(String[] args) {

        ThreadAB t1 = new ThreadAB("A", "B");
        ThreadAB t2 = new ThreadAB("1", "2");

        Thread threadA = new Thread(t1);
        Thread threadB = new Thread(t2);

        threadA.start();
        threadB.start();
    }

}

