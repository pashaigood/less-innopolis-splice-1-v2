package less.android;

import java.util.Iterator;
import java.util.Map;

public class Listener extends Thread {
    private final Generator generator;

    public Listener(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        synchronized (generator) {
            while (! generator.isInterrupted()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Iterator<Map.Entry<Integer,Integer>> numberMapIterator = generator.getNumberMapIterator();
                Map.Entry<Integer,Integer> entry = numberMapIterator.next();

                try {
                    while (numberMapIterator.hasNext()) {
                        System.out.printf("%s generated %s time(s)\n", entry.getKey(), entry.getValue());
                        entry = numberMapIterator.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("\n");
            }
        }

    }
}
