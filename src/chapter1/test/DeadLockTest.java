package chapter1.test;

/**
 * 死锁
 * resourceA和resourceB是互斥资源
 * 持有资源并请求
 * 持有资源不可剥夺
 * 环路不可剥夺
 */
public class DeadLockTest {
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        synchronized (resourceA) {
                            System.out.println(Thread.currentThread() + "get ResourceA");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread() + "waiting to get ResourceB");
                            synchronized (resourceB) {
                                System.out.println(Thread.currentThread() + "get ResourceB");
                            }
                        }
                    }
                }
        );

        //死锁代码
//        Thread threadB = new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        synchronized (resourceB) {
//                            System.out.println(Thread.currentThread() + "get ResourceB");
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println(Thread.currentThread() + "waiting to get ResourceA");
//                            synchronized (resourceA) {
//                                System.out.println(Thread.currentThread() + "get ResourceA");
//                            }
//                        }
//                    }
//                }
//        );
        //非死锁代码  资源有序性破坏请求并持有
        Thread threadB = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        synchronized (resourceA) {
                            System.out.println(Thread.currentThread() + "get ResourceA");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(Thread.currentThread() + "waiting to get ResourceB");
                            synchronized (resourceB) {
                                System.out.println(Thread.currentThread() + "get ResourceB");
                            }
                        }
                    }
                }
        );

        threadA.start();
        threadB.start();
    }
}
