package gui;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

public class SwingUtilities {

    private static final ExecutorService exec = Executors.newSingleThreadExecutor(
            r -> swingThread = new Thread(r));

    private static volatile Thread swingThread;

    public static boolean isEventDispatchThread() {
        return Thread.currentThread() == swingThread;
    }

    public static void invokeLater(Runnable task) {
        exec.execute(task);
    }

    public static void invokeAndWait(Runnable task)
            throws InterruptedException, InvocationTargetException {
        Future f = exec.submit(task);
        try {
            f.get();
        } catch (ExecutionException e) {
            throw new InvocationTargetException(e);
        }
    }

}
