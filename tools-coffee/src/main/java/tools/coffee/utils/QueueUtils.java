package tools.coffee.utils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * QueueUtils
 *
 * @author: hyr
 * @date: 2023-03-02
 **/
public class QueueUtils {

    public static void main(String[] args) {
        test02();
    }

    private static void test01() {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < 8; i++) {
            try {
                queue.put(i);
            } catch (InterruptedException e) {
                queue.poll();
            }
            System.out.println(queue);
        }
    }

    private static void test02() {
        LimitedQueue<BigDecimal> queue = new LimitedQueue<>(4);
        BigDecimal[] bigDecimals = new BigDecimal[]{
            new BigDecimal("1"),
            new BigDecimal("2"),
            new BigDecimal("3"),
            new BigDecimal("4"),
            new BigDecimal("5"),
            new BigDecimal("4"),
            new BigDecimal("6"),
            new BigDecimal("6"),
            new BigDecimal("6"),
            new BigDecimal("6"),
            new BigDecimal("7"),
            new BigDecimal("8"),
            new BigDecimal("9"),
            new BigDecimal("10"),
            new BigDecimal("8"),};
        for (int j = 0; j < bigDecimals.length; j++) {

            queue.add(bigDecimals[j]);

            if (queue.size() < 4) {
                System.out.println(String.format("index: %s, queue: %s, up: %s", j, queue, false));
            } else {

                BigDecimal tmp = queue.get(0);
                boolean waterLevelUp = true;
                for (int i = 1; i < queue.size(); i++) {
                    if (queue.get(i).compareTo(tmp) < 0) {
                        waterLevelUp = false;
                        break;
                    }
                    tmp = queue.get(i);
                }
                waterLevelUp = waterLevelUp && queue.get(3).compareTo(queue.get(0)) > 0;
                System.out.println(
                    String.format("index: %s, queue: %s, up: %s", j, queue, waterLevelUp));

            }
        }


    }

    static class LimitedQueue<E> extends LinkedList<E> {

        private static final long serialVersionUID = 1L;
        private final int size;

        public LimitedQueue(int size) {
            this.size = size;
        }

        @Override
        public boolean add(E o) {
            super.add(o);
            while (size() > size) {
                super.remove();
            }
            return true;
        }
    }
}
