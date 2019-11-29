package chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    //Unsafe是BootStrap加载器 不是AppClassLoader加载器 系统不让你用Bootstrap所以报错
    //    static final Unsafe unsafe = Unsafe.getUnsafe();
    static  Unsafe unsafe;

    private volatile long state = 0;

    static  long  stateOffset ;

    static {


        try {
            //  stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));

            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);

            unsafe = (Unsafe)field.get(null);
            stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));


        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        UnsafeTest unsafeTest = new UnsafeTest();
        Boolean success = unsafe.compareAndSwapInt(unsafeTest,stateOffset,0,1);
        System.out.println(success);
    }
}
