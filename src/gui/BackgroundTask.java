package gui;

import java.util.concurrent.*;

public class BackgroundTask<V> implements Runnable, Future<V> {

    private final FutureTask<V> computation = new Computation();

    private static class Computation extends Future<V> {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCancelled() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isDone() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
