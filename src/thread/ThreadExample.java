package thread;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(() -> result.left = f(x));
        Thread t2 = new Thread(() -> result.left = g(x));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(result.left + result.right);


    }
    public static int f(int x) {
        return x;
    }

    public static int g(int y) {
        return y;
    }


    public static class Result {
        private int left;
        private int right;
    }

}
