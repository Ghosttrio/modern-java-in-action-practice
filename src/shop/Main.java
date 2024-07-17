package shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static Executor CustomThread() {
        return Executors.newFixedThreadPool(Math.min(shops.size(), 1000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
    }

    private static List<Shop> shops = Arrays.asList(
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d"),
            new Shop("a"),
            new Shop("b"),
            new Shop("c"),
            new Shop("d")
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        ShopAsync shopAsync = new ShopAsync();
//        Future<Double> test1 = shopAsync.getPrice("Test");
//        System.out.println(test1.get());

        long start = System.nanoTime();
        System.out.println(findPrices("test"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        long start1 = System.nanoTime();
        System.out.println(findPricesWithParallel("test"));
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");

        long start2 = System.nanoTime();
        System.out.println(findPricesWithCompletableFuture("test"));
        long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");

        long start3 = System.nanoTime();
        System.out.println(findPricesWithExecutor("test"));
        long duration3 = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Done in " + duration3 + " msecs");
    }


    static List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    static List<String> findPricesWithParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    static List<String> findPricesWithCompletableFuture(String product) {
        List<CompletableFuture<String>> collect = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());

        return collect.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    static List<String> findPricesWithExecutor(String product) {
        List<CompletableFuture<String>> collect = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product), CustomThread()))
                .collect(Collectors.toList());

        return collect.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
