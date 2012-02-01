package gui;

import java.util.Arrays;
import java.util.concurrent.*;

public abstract class BackgroundTask<V> implements Runnable, Future<V> {

    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {

        public Computation() {
            super(BackgroundTask.this::compute);
        }

        protected final void done() {
            GuiExecutor.instance().execute(() -> {
                V value = null;
                Throwable thrown = null;
                boolean cancelled = false;
                try {
                    value = get();
                } catch (ExecutionException e) {
                    thrown = e.getCause();
                } catch (CancellationException e) {
                    cancelled = true;
                } catch (InterruptedException consumed) {
                } finally {
                    onCompletion(value, thrown, cancelled);
                }
            });
        }
    }

    protected void setProgress(int current, int max) {
        GuiExecutor.instance().execute(() -> onProgress(current, max));
    }

    protected abstract V compute() throws Exception;

    protected void onCompletion(V result, Throwable exception, boolean cancelled) { };

    protected void onProgress(int current, int max) { };

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void run() {
    }

}
