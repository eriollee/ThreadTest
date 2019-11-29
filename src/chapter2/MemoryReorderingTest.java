package chapter2;

public class MemoryReorderingTest {
    private static boolean ready = false;
    private static int num = 0;

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println(num + num);
                }
                System.out.println("read Thread...");
            }
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write thread is over");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();



        WriteThread writeThread = new WriteThread();
        writeThread.start();

        Thread.sleep(10);

        readThread.interrupt();
        System.out.println("exit");
    }

}

