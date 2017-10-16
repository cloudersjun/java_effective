import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2017/7/12.
 */
public class Lock {
    static String LOCK_A = "source a";
    static String LOCK_B = "source b";

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService carPool =
                Executors.newFixedThreadPool(5);
        ThreadA thread1 = new ThreadA();
//        carPool.execute(thread1);
        thread1.start();
        System.out.println("**********");
//        ThreadB thread2 = new ThreadB();
////        carPool.execute(thread2);
//        thread2.start();
        while (true) {
//            System.out.println("thread1 state :" + thread1.getState() + ",thread1 state :" + thread2.getState());
            System.out.println("thread1 state :" + thread1.getState() );
            Thread.sleep(5000);
        }
    }

    public static class ThreadA extends Thread {
        public void run() {
            synchronized (LOCK_A) {
                System.out.println("thread a " + LOCK_A);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_B) {
                    System.out.println("thread a " + LOCK_B);
                }

            }
//            funA();
//            funB();
        }
    }

    public static class ThreadB extends Thread {
        public void run() {
            synchronized (LOCK_B) {
                System.out.println("thread b " + LOCK_B);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK_A) {
                    System.out.println("thread b " + LOCK_A);
                }
            }
//            funB();
//            funA();
        }
    }

    public static void funA() {
        synchronized (LOCK_A) {
            for (int i = 0; i < 5; i++) {
                System.out.println(LOCK_A);
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
        synchronized (LOCK_B) {
            for (int i = 0; i < 5; i++) {
                System.out.println(LOCK_B);
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }


    public static void funB() {
        synchronized (LOCK_A) {
            for (int i = 0; i < 5; i++) {
                System.out.println(LOCK_A);
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
        synchronized (LOCK_B) {
            for (int i = 0; i < 5; i++) {
                System.out.println(LOCK_B);
                System.out.println(Thread.currentThread().getName() + " synchronized loop " + i);
            }
        }
    }

}
