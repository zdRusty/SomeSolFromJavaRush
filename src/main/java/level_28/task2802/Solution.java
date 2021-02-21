package level_28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/*
Пишем свою ThreadFactory
*/
public class Solution {

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }

    public static class AmigoThreadFactory implements ThreadFactory {

        public static final AtomicInteger a = new AtomicInteger(1);
        public AtomicInteger b = new AtomicInteger(1);
        public final ThreadGroup group;
        public final String namePrefix;

        public AmigoThreadFactory(){
            group = Thread.currentThread().getThreadGroup();
            namePrefix = group.getName()+"-pool-" + a.getAndIncrement()+"-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group,r,namePrefix+b.getAndIncrement(),0);
            if (thread.isDaemon()) thread.setDaemon(false);
            if (thread.getPriority()!=Thread.NORM_PRIORITY) thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }
}


