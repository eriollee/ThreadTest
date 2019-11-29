package chapter3_4_5_6;

import java.util.concurrent.locks.LockSupport;

public class ParkUnparkTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("subThread begin");
//                LockSupport.park();
                while (!Thread.currentThread().isInterrupted()){
                    LockSupport.park();
                }
                System.out.println("subThread end");
            }
        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin");

      //  LockSupport.unpark(thread);
        thread.interrupt();
    }
}
