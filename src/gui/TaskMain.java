package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskMain {

    final static JButton startButton = new JButton("Start");
    final static JButton cancelButton = new JButton("Cancel");
    final static JLabel label = new JLabel("");

    static Future<?> runningTask;

    final static ExecutorService backgroundExec = Executors.newCachedThreadPool();

    public static void backgroundActionsListener(ActionEvent e) {
        backgroundExec.execute(() -> doBigComputation());
    }

    public static void cancellingActionsListener(ActionEvent e) {
            if (runningTask == null) {
                runningTask = backgroundExec.submit(() -> {
                    while (morework()) {
                        if (Thread.currentThread().isInterrupted()) {
                            cleanUpPartialWork();
                            break;
                        }
                        doSomeWork();
                    }
                });
            }
    }

    public static void cancelActionsListener(ActionEvent e) {
            if (runningTask != null) {
                runningTask.cancel(true);
            }
    }

    public static void feedbackActionListener(ActionEvent e) {
        startButton.setEnabled(false);
        label.setText("Busy");
        backgroundExec.execute(() -> {
            try {
                doBigComputation();
            } finally {
                GuiExecutor.instance().execute(() -> {
                    startButton.setEnabled(true);
                    label.setText("idle");
                });
            }

        });
    }

    public static void main(String[] args) {
        startButton.addActionListener(TaskMain::backgroundActionsListener);

        startButton.addActionListener(TaskMain::cancellingActionsListener);
        cancelButton.addActionListener(TaskMain::cancelActionsListener);
    }

    static void doBigComputation() {
    }

    private static void doSomeWork() {
    }

    private static void cleanUpPartialWork() {
    }

    private static boolean morework() {
        return false;
    }

}
