package chapter3_4_5_6;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < 10; ++i) {
            System.out.println(threadLocalRandom.nextInt(5));
        }
    }
}
