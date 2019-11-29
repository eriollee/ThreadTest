package chapter1.test;

public class YieldTest implements Runnable {
    public YieldTest() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread() + "yield cpu");
                Thread.yield(); //sleep会阻塞挂起 yield会让出自己的时间片处于就绪状态
            }
        }
        System.out.println(Thread.currentThread() + "is  over");
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
        Thread.interrupted();

    }
}
