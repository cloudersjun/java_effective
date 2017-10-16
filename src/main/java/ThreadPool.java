import io.netty.util.concurrent.Future;

import java.util.concurrent.*;


/**
 * Created by lenovo on 2017/9/29.
 */
public class ThreadPool {
    public String get() {
        ExecutorService exe = Executors.newFixedThreadPool(20);
        TaskWithResult ta = new TaskWithResult("A");
        TaskWithResult tb = new TaskWithResult("B");
        TaskWithResult tc = new TaskWithResult("C");
        java.util.concurrent.Future<String> fa = exe.submit(ta);
        java.util.concurrent.Future<String> fb = exe.submit(tb);
        java.util.concurrent.Future<String> fc = exe.submit(tc);
        try {
            while (true) {
                if (fa.isDone()) {
                    return fa.get();
                }
                if (fb.isDone()) {
                    return fb.get();
                }
                if (fc.isDone()) {
                    return fc.get();
                }
            }
        } catch (Exception e) {
            return null;
        } finally {
            exe.shutdown();
        }
    }
    private class TaskWithResult implements Callable<String> {
        private String type;

        public TaskWithResult(String type) {
            this.type = type;
        }

        @Override
        public String call() throws Exception {
            if (type.equalsIgnoreCase("A")) {
                return A();
            } else if (type.equalsIgnoreCase("B")) {
                return B();
            } else if (type.equalsIgnoreCase("C")) {
                return C();
            }
            return null;
        }
    }

    private String A() {
        return "A";
    }

    private String B() {
        return "B";
    }

    private String C() {
        return "C";
    }

}
