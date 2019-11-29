package chapter1.test;

public class WaitNotifyAllTest {
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (resourceA) {
                                System.out.println("threadA get resourceA lock");
                                resourceA.wait();
                                System.out.println("threadA release resourceA lock");

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );


        Thread threadB = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (resourceA) {
                                System.out.println("threadB get resourceA lock");
                                resourceA.wait();
                                System.out.println("threadB release resourceA lock");

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        Thread threadC = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (resourceA) {
                                System.out.println("threadC begin notify");
                               // resourceA.notify(); //谁先启动线程谁先释放
                                resourceA.notifyAll();//所有所被释放
                            }
                        } catch (IllegalMonitorStateException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        threadA.start();
        threadB.start();

       // Thread.sleep(1000); 注释会有概率 线程B调用wait方法前先调用了nofityAll方法 B没有唤醒导致阻塞
        threadC.start();

        //等待释放
        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("end");
    }
}
