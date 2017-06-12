package less.android;

import java.util.Iterator;
import java.util.Map;

public class Listener extends Thread {
    private final Generator generator;
    private int interval = 5000;

    public Listener(Generator generator, int showingInterval) {
        this.generator = generator;
        this.interval = showingInterval;
    }

    public Listener(Generator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        while (! generator.isReady()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printOutput();
        }
    }

    private void printOutput() {
        Iterator<Map.Entry<Integer,Integer>> numberMapIterator = generator.getNumberMapIterator();
        Map.Entry<Integer,Integer> entry = numberMapIterator.next();

        System.out.println(interval / 1000 + " sec. listener with");
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
