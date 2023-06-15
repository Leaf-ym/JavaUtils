package designPattern.singleton;

import com.ncepu.designPattern.singleton.LazyMan;
import com.ncepu.designPattern.singleton.LazyManByDoubleCheck;
import org.junit.Test;

/**
 * @author wengym
 * @version 1.0
 * @desc 懒汉单例模式测试类
 * @date 2023/4/27 9:16
 */
public class LazyManTest {
    /**
     * 加入双重检测的懒汉式单例模式也存在问题，因为创建示例的代码不是一个原子性操作
     *
     * @author wengym
     *
     * @date 2023/4/27 9:20
     *
     * @return void
     */
    @Test
    public void testLazyManByDoubleCheckByMultiThread() {
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                LazyManByDoubleCheck.getInstance();
            }).start();
        }
    }

    /**
     * 懒汉式单例模式在多线程环境下存在很大的问题，如下的代码会创建多个单例对象，使单例变得不再单例，
     *
     * @author wengym
     *
     * @date 2023/4/27 9:20
     *
     * @return void
     */
    @Test
    public void testLazyManByMultiThread() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
            }).start();
        }
    }

    /**
     * 在单线程环境下，懒汉式单例是没有问题的
     *
     * @author wengym
     *
     * @date 2023/4/27 9:22
     *
     * @return void
     */
    @Test
    public void testLazyMan() {
        for (int i = 0; i < 10; i++) {
            LazyMan.getInstance();
        }
    }
}
