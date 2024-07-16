package shop;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Shop shop = new Shop();
//        double test = shop.getPrice("test");
//        System.out.println(test);

        ShopAsync shopAsync = new ShopAsync();
        Future<Double> test1 = shopAsync.getPrice("Test");
        System.out.println(test1.get());
    }

}
