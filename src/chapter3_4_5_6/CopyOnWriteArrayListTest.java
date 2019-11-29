package chapter3_4_5_6;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList弱一致性
 * 主线程不受子线程增删改查影响
 */
public class CopyOnWriteArrayListTest {
    private static volatile CopyOnWriteArrayList arrayList = new CopyOnWriteArrayList();

    public static void main(String[] args) throws InterruptedException {
        arrayList.add("0");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                arrayList.set(1, "213123");
                arrayList.remove(2);
                arrayList.remove(3);

                Iterator<String> iterator = arrayList.iterator();
                while (iterator.hasNext()) {
                    System.out.println("subThread" + iterator.next());
                }

            }
        });

        Iterator<String> iterator = arrayList.iterator();

        thread.start();

        thread.join();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
