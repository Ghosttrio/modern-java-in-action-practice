package future;

import java.util.concurrent.*;

import static future.Job.anotherJob;
import static future.Job.longTimeJob;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return longTimeJob();
            }
        });

        double another = anotherJob();
        System.out.println(another);

        Double result = future.get(1, TimeUnit.SECONDS);
        System.out.println(result);

        executorService.shutdown();
    }
}
