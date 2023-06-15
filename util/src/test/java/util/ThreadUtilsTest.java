package util;

import com.ncepu.util.PrintUtils;
import com.ncepu.util.ThreadUtils;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wengym
 * @version 1.0
 * @desc 县城工具测试类
 * @date 2022/9/20 14:14
 */
public class ThreadUtilsTest {

    @Test
    public void testAtomic() {
        ThreadUtils.atomic();
    }

    @Test
    public void testJoin() {
        ThreadUtils.join();
    }

    @Test
    public void testCreateThreadPool3() {
        // 好像也可以让子线程执行完后，父线程才结束
        Integer taskNum = 100;
        ExecutorService threadPool = ThreadUtils.createThreadPool();
        for (int i = 0; i < taskNum; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                PrintUtils.println(Thread.currentThread().getName() + "--" + finalI);
            });
        }
        threadPool.shutdown();
        while(true) {
            if (threadPool.isTerminated()) {
                break;
            }
        }
    }

    @Test
    public void testCreateThreadPool() {
        Integer taskNum = 50;
        ExecutorService threadPool = ThreadUtils.createThreadPool();
        for (int i = 0; i < taskNum; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                PrintUtils.println(Thread.currentThread().getName() + "--" + finalI);
            });
        }
        threadPool.shutdown();
        while(true) {
            try {
                if (threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCreateThreadPool2() {
        Integer taskNum = 20;
        CountDownLatch latch = new CountDownLatch(taskNum);
        ExecutorService threadPool = ThreadUtils.createThreadPool();
        for (int i = 0; i < taskNum; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                PrintUtils.println(Thread.currentThread().getName() + "--" + finalI + "--" + latch.getCount());
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException E) {
            // handle
        }
    }

    @Test
    public void testGetNumOfProcessors() {
        Integer r1 = ThreadUtils.getNumOfProcessors();
        PrintUtils.println(r1);
    }

    @SneakyThrows
    @Test
    public void testCount() {
        long startTime = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 10000000; i++) {
            Thread.sleep(1);
            sum += i;
        }
        PrintUtils.println(sum);
        long endTime = System.currentTimeMillis();
        PrintUtils.println(endTime - startTime);
    }

    @Test
    public void testPrintThreadInfo() {
        int pageIndexCount = 1 / 1000 + (1 % 1000 != 0 ? 1 : 0);
        PrintUtils.println(pageIndexCount);
        String result  = ThreadUtils.printThreadInfo(new Thread("threadName"));
        PrintUtils.println(result);
    }

    @Test
    public void testSimpleTimer() {
        ThreadUtils.simpleTimer(new TimerTask() {
            @Override
            public void run() {
                System.out.println("HelloWorld!!!");
            }
        }, 1, 1);
        // 阻塞主线程，等待子线程执行完成
        while (true);
    }
}
