package less.android;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool(5);
        Generator generator = new Generator();

        executorService.execute(new Listener(generator));
        executorService.execute(new Listener(generator, 3000));

        generator.start();
        executorService.shutdown();
    }
}
