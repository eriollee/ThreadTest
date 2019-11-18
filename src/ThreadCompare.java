import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadCompare {
    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("I'm a thread");
            System.out.println(this.getName());
        }
    }

    public static class RunableTask implements Runnable {
        @Override
        public void run() {
            System.out.println("I'm a Runnable thread");
        }
    }

    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("I'm a Callable thread");
            return "hello";
        }
    }

    public static void main(String[] args) {
//
//        MyThread thread = new MyThread();
//        thread.start();//就绪状态 获取除CPU状态以外的其他资源 run方法执行完毕后处于终止状态 不能继承其他类
//        thread.start();//不能多任务   否则报Exception in thread "main" java.lang.IllegalThreadStateException

//        MyThread thread = new MyThread();
//        try {
//          thread.wait(); // 如果没有获取监视器锁则抛出java.lang.IllegalMonitorStateException
//        } catch (IllegalMonitorStateException e) {
//            e.printStackTrace();
//        }

//        MyThread thread = new MyThread();
//        try {
//            thread.notify();// 如果没有获取监视器锁则抛出java.lang.IllegalMonitorStateException
//        }catch (IllegalMonitorStateException e) {
//            e.printStackTrace();
//        }

//        RunableTask runableTask = new RunableTask();
//        new Thread(runableTask).start(); //可以添加参数 可以继承其他类
//        new Thread(runableTask).start(); //支持多任务 这是两个任务
//        runableTask.run();
//        runableTask.run();

//        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
//        new Thread(futureTask).start();
//        new Thread(futureTask).start(); //这是1个任务 第二个start不生效
//        try {
//            String result = futureTask.get();//执行完毕返回结果
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
