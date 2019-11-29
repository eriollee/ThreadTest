package chapter2;

/**
 * ThreadLocal提供线程本地变量 如果创建ThreadLocal变量 每个线程都会有这个变量的本地副本 当多线程操作这个变量时
 * 实际操作的是本地副本,从而避免线程安全
 * 不同线程有不同的threadLocals的本地变量(hashmap)存储 key为线程的引用,value为set的值
 * 若不及时remove掉set的值则容易移除
 * 用inheritableThreadLocal共享子线程和父线程的变量 场景:子线程需要父线程的用户登录信息 子线程的变量传入父线程变量从而其他子线程用父线程的变量
 */
public class ThreadLocalTest {
    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    static void print(String str) {
        System.out.println(str + ":"+localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadOne local variable");
                print("threadOne");
                System.out.println("threadOne remove after :"+ localVariable.get());
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadTwo local variable");
                print("threadTwo");
                System.out.println("threadTwo remove after :"+ localVariable.get());
            }
        });

        threadOne.start();
        threadTwo.start();
    }
}
