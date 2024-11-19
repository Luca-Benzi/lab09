package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI{

    private static final String TITLE = "IO Controller";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);
    /**
     * @param controller
     */
    public SimpleGUI(final Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();
        final JButton save = new JButton("save");
        canvas.add(text, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try  {
                    controller.write(text.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void display2() { 
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
}
    /**
     * @param args
     */
    public static void main(final String... args) {
        new SimpleGUI(new Controller()).display2();
    }
}
