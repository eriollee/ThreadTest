package chapter1.test;

public class JoinInterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("threadOne begin");
                        for (; ; ) {

                        }
                    }
                }
        );

        final Thread mainThread = Thread.currentThread();

        Thread threadTwo= new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000); //sleep不参与CPU调用但是锁还是不会让出的 睡眠时终端会抛出异常
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mainThread.interrupt();//其他线程调用主线程
                    }
                }
        );

        threadOne.start();

        threadTwo.start();

        try {
            threadOne.join(); //主线程调用线程A的join方法
        } catch (InterruptedException e) {
            System.out.println("mainThread:" + e  );//主线程的 不是threadOne的

        }
    }
}
