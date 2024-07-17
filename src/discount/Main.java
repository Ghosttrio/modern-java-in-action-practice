package discount;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        List<Shop> shops = Arrays.asList(new Shop("a"),
                new Shop("b"),
                new Shop("c"),
                new Shop("d"));
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 1000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

//        shops.stream()
//                .map(shop -> shop.getPrice("product"))
//                .map(Quote::parse)
//                .map(Discount::applyDiscount)
//                .collect(Collectors.toList());

        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice("product"), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        List<String> collect = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }
}
