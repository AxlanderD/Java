import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sun.nio.ch.ThreadPool;

public class tempTest {
    // callBack
    public static void main(String[] args) {
    
        System.out.println("start----");
        new Thread(() -> {[]

            0.

            Helper helper = new Helper();
            try {
                helper.doSome("do This task", str -> System.out.println("when finsh task:" + str));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
        System.out.println(" end ----");

        CountDownLatch singal = new CountDownLatch(5);
        System.out.println("main proceed running");
        int i = 0;
        while (i < 5) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is running");
                singal.countDown();
                try {
                    singal.await();
                    System.out.println(Thread.currentThread().getName() + " restart running");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }).start();
            i++;
        }
        try {
            singal.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("main proceed restart");
    }

}

class tempAfter implements callBack{

    @Override
    public void call(String say) {
        // TODO Auto-generated method stub
        System.out.println("this is another implements function:"+say);
    }

}
class Helper {
   
    void doSome(String say,callBack c) throws InterruptedException {
        System.out.println("Helper start do it");
        Thread.sleep(2000);
        c.call(say);
    }

}




// interface
interface callBack{
    void call(String say);
}