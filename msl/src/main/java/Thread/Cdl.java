package Thread;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description: 线程池操作
 *
 * @author shuangling.mao
 * @date 2019/5/5 15:53
 */
public class Cdl {

    /**
     * 性感大师在线起名
     * @param cats
     */
    public void setName(List<Cat> cats) {
        System.out.println("起名前:"+ JSONObject.toJSONString(cats));

//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(20);

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
//                Runtime.getRuntime().availableProcessors(),1, TimeUnit.SECONDS,new LinkedBlockingDeque<>());


        final CountDownLatch cdl = new CountDownLatch(cats.size());
        System.out.println("需要起名字的小孩子数量："+cats.size());

        try {
            for (Cat cat : cats) {
                executorService.execute(() -> {
                    try {
                        this.setName(cat);
                    } finally {
                        cdl.countDown();
                    }
                });
            }

            cdl.await();
            executorService.shutdown();
        } catch (Exception e) {
            System.out.println("大师起名失败");
        } finally {
            executorService = null;
        }
        System.out.println("起名后:"+ JSONObject.toJSONString(cats));
    }

    private void setName(Cat cat) {
        cat.setName(cat.getId()+"黑");
    }

    public static void main(String[] args) {
        List<Cat> list = new ArrayList<Cat>();
        for (int i = 0; i < 20; i++) {
            list.add(new Cdl().new Cat(i));
        }
        final long start = System.currentTimeMillis();
        new Cdl().setName(list);
        final long end = System.currentTimeMillis();
        System.out.println("有线程池->执行耗时:"+(end-start));

        final long start1 = System.currentTimeMillis();
        new Cdl().setName1(list);
        final long end1 = System.currentTimeMillis();
        System.out.println("无线程池->执行耗时:"+(end1-start1));

    }

    public void setName1(List<Cat> cats) {
        for (Cat cat : cats) {
            setName(cat);
        }
    }











    /**
     * Description: 猫
     *
     * @author shuangling.mao
     * @date 2019/5/5 15:57
     */
    public class Cat {
        private Integer id;
        private String name;

        public Cat(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        /**
         * 起名字的时候大师都要想一秒
         * @param name
         */
        public void setName(String name) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.name = name;
        }
    }
}
