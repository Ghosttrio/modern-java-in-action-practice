package thread;

import java.util.function.IntConsumer;

import static thread.CallbackInterface.f;
import static thread.CallbackInterface.g;

public class CallbackStyleExample {

    public static void main(String[] args) {
        int x = 1337;
        Result result = new Result();
        f(x, (int y) -> {
           result.left = y;
            System.out.println(result.left + result.right);
        });
        g(x, (int z) -> {
            result.right = z;
            System.out.println(result.left + result.right);
        });
    }

    public static class Result {
        private int left;
        private int right;
    }
}
