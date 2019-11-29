package chapter2;
/*
jdk8用Contended注解解决伪共享
 */
public class FalseSharingTest {
    static final int LINE_NUM = 1024;
    static final int COLUMN_NUM = 1024;

    public static void main(String[] args) {
        long array[][] = new long[LINE_NUM][COLUMN_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; ++i) {
            for (int j = 0; j < COLUMN_NUM; j++) {
//                array[i][j] = i * 2 + j; //比下一行代码的执行快 因为数组内的内存地址是连续的 缓存会直接命中 会在缓存读取不读内存
                array[j][i] = i * 2 + j;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime-startTime;
        System.out.println(cacheTime);
    }
}
