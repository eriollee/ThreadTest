package chapter1.test;

public class InterruptTest {

    public static void main(String[] args) {
        Thread threadone = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(;;){}
                    }
                }
        );
        threadone.start();
        //设置中断标志
        threadone.interrupt();

        System.out.println("isInterruptered :" +threadone.isInterrupted()); //获取中断标志
        System.out.println("isInterruptered :" +threadone.interrupted()); //获取中断标志并清空 获取的是主线程的终端标志
        System.out.println("isInterruptered :" +Thread.interrupted());//获取中断标志并清空 与上一行是等价的
        System.out.println("isInterruptered :" +threadone.isInterrupted());//获取中断标志
    }
}
