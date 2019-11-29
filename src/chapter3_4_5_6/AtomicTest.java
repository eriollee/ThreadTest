package chapter3_4_5_6;

import java.util.concurrent.atomic.AtomicLong;


/**
 *利用CAS非阻塞算法性能更好 但在高并发的情况下还是有性能问题(多次自旋尝试),推荐LongAddr
 */
public class AtomicTest {
    private static AtomicLong atomicLong = new AtomicLong();

    private static Integer[] arrayOne = new Integer[] {0,1,2,3,0,5,6,0,56,0};

    private static Integer[] arayTwo = new Integer[] {10,1,2,3,0,5,6,0,56,0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                int size = arrayOne.length;
                for (int i = 0; i < size; ++i) {
                    if(arrayOne[i].intValue() == 0){
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                int size = arayTwo.length;
                for (int i = 0; i < size; ++i) {
                    if(arayTwo[i].intValue() == 0){
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("count 0:" + atomicLong);

    }


}
