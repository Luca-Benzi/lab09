package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Controller");
    /**
     * @param controller
     */
    public SimpleGUI(final SimpleController controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        canvas.add(top, BorderLayout.NORTH);
        final JPanel mid = new JPanel();
        mid.setLayout(new BorderLayout());
        canvas.add(mid, BorderLayout.CENTER);
        final JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        canvas.add(bottom, BorderLayout.SOUTH);
        final JTextField currentString = new JTextField();
        top.add(currentString);
        final JTextArea stringHistory = new JTextArea();
        stringHistory.setEditable(false);
        mid.add(stringHistory);
        final JButton print = new JButton("print");
        final JButton showHistory = new JButton("Show History");
        bottom.add(print, BorderLayout.WEST);
        bottom.add(showHistory, BorderLayout.EAST);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setString(currentString.getText());
                controller.printString();
            }
        });
        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder content = new StringBuilder();
                final List<String> history = controller.getHistory();
                for (final String add : history) {
                    content.append(add).append('\n');
                }
                stringHistory.setText(content.toString());
            }
        });
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
    }
    private void display() {
        frame.setVisible(true);
    }
    /**
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display(); 
    }

}
