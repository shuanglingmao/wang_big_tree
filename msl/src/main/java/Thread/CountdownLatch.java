package Thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2018/6/19 11:27
 */
public class CountdownLatch {
    /**并发数*/
    private static final int USR_NUM = 200;
    /**初始化发强器*/
    private static CountDownLatch cdl = new CountDownLatch(USR_NUM);

    /**优惠券*/
    private int discount = 20;

    @Test
    public void testInvoke ()  throws Exception{
        for (int i = 1; i <= USR_NUM; i++) {
            new Thread(new Request("线程"+i)).start();
            cdl.countDown();
        }
        Thread.sleep(3000);
    }

    public class Request implements Runnable {
        private String name;
        public Request(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invoke(name);
        }
    }

    public synchronized void invoke(String name) {
        if (discount > 0) {
            discount --;
            System.out.println(name+"--->"+discount);
        }
    }
}
