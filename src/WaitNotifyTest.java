public class WaitNotifyTest {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    /**
     * threadB 不会获取 resourceB的锁 即只释放当前共享变量的锁 其他共享变量的锁不会被释放
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            synchronized (resourceA) {
                                System.out.println("threadA get resourceA lock");
                                synchronized (resourceB) {
                                    System.out.println("threadA get resourceB lock");
                                    System.out.println("threadA release resourceA lock");
                                    resourceA.wait();
                                   // resourceB.wait();//  resourceA.wait(); resourceA阻塞此代码不会执行
                                    System.out.println("threadA release resourceA lock");//resourceA.wait(); 阻塞此代码不会执行
                                }

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

                            Thread.sleep(5000);
                            synchronized (resourceA) {
                                System.out.println("threadB get resourceA lock");

                                System.out.println("threadB try to get resourceB lock");
                                synchronized (resourceB) {
                                    System.out.println("threadB get resourceB lock");
                                    System.out.println("threadB release resourceA lock");
                                    resourceA.wait();
                                    System.out.println("threadB release resourceA lock");//此代码不会执行
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        threadA.start();

//        Thread.sleep(1000); //主线程休眠1s
//        threadA.interrupt();  //中断则异常 释放resourceA和resourceB的锁
        threadB.start();

        threadA.join();
        threadB.join(); //threadB阻塞 不会打印end

        System.out.println("end");
    }
}
