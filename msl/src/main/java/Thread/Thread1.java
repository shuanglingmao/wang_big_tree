package Thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Description: 线程基本使用
 *
 * @author shuangling.mao
 * @date 2019/5/9 10:54
 */
public class Thread1 {
    private static volatile boolean isStop = false;

    /**
     *  非守护线程，虚拟机等待所有的线程结束程序才结束。
     *  守护线程例外，会随程序主动结束。
     */
    @Test
    public void test1() {
        final Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("啊啊啊");
            }
        }, "啊啊啊线程");
        thread.start();


    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (!isStop) {
                System.out.println("啊啊啊");
            }
        },"while_true").start();

        TimeUnit.SECONDS.sleep(3);

//        isStop = true;
    }


}
