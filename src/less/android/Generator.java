package less.android;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Generator extends Thread {
    private final ConcurrentHashMap<Integer, Integer> numberMap = new ConcurrentHashMap<>();

    synchronized public Iterator<Map.Entry<Integer,Integer>> getNumberMapIterator() {
        return numberMap.entrySet().iterator();
    }

    @Override
    public void run() {
        while (! isInterrupted()) {
            try {
                Thread.sleep(1000);
                Integer number = ThreadLocalRandom.current().nextInt(1, 99 + 1);
                Integer currentValue = 0;

                if (numberMap.containsKey(number)) {
                    currentValue = numberMap.get(number);
                }
                numberMap.put(number, ++currentValue);
                if (currentValue == 5) {
                    interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
