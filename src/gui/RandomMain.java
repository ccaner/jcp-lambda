package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RandomMain {

    final static java.util.Random random = new java.util.Random();
    final static JButton button = new JButton("Change Color");

    public static void randomColorActionsListener(ActionEvent e) {
        button.setBackground(new Color(random.nextInt()));
    }

    public static void main(String[] args) {
        button.addActionListener(RandomMain::randomColorActionsListener);
    }

}
