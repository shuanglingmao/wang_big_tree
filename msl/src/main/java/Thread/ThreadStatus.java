package Thread;

import java.util.concurrent.TimeUnit;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/9 13:32
 */
public class ThreadStatus {
    public static void main(String[] args) {

        /**TIME_WAITING*/
        new Thread(()->{
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"time_waiting").start();


        /**WAITING*/
        new Thread(()->{
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                       e.printStackTrace();
                    }
                }
            }
        },"waiting").start();

        new Thread(new BlockDemo(),"BlockDemo1").start();
        new Thread(new BlockDemo(),"BlockDemo2").start();
    }

    private static class BlockDemo implements Runnable {

        @Override
        public void run() {
            synchronized (BlockDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
