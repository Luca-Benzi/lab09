
package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "IO Controller";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);
    /**
     * @param controller
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel top = new JPanel(new BorderLayout());
        final JTextField filePath = new JTextField(controller.getFilePath());
        filePath.setEditable(false);
        final JButton browse = new JButton("Browse...");
        final JTextArea text = new JTextArea();
        final JButton save = new JButton("save");
        top.add(filePath, BorderLayout.CENTER);
        top.add(browse, BorderLayout.EAST);
        canvas.add(top, BorderLayout.NORTH);
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
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser("Choose where to save");
                fileChooser.setSelectedFile(controller.getFile());
                final int result = fileChooser.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    final File newFile = fileChooser.getSelectedFile();
                    controller.setFile(newFile);
                    filePath.setText(controller.getFilePath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, result, "An Error has occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void display() {
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
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
