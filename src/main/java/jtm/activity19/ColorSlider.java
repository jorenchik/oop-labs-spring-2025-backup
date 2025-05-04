package jtm.activity19;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class ColorSlider {

    JFrame frame;
    JSlider redSlider, greenSlider, blueSlider;
    JTextArea txtTest;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ColorSlider window = new ColorSlider();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ColorSlider() {
        initialize();
        add_listeners();
        setBackgroundColor();  // Ensure initial color is set
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Color slider");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new MigLayout("", "[][][grow]", "[][][][][][grow]"));

        txtTest = new JTextArea();
        txtTest.setName("testArea");
        txtTest.setText("Test area");
        frame.getContentPane().add(txtTest, "cell 0 3 3 3,grow");

        JLabel lblR = new JLabel("R");
        frame.getContentPane().add(lblR, "cell 1 0");

        JLabel lblG = new JLabel("G");
        frame.getContentPane().add(lblG, "cell 1 1");

        JLabel lblB = new JLabel("B");
        frame.getContentPane().add(lblB, "cell 1 2");

        // Add sliders
        redSlider = new JSlider(0, 255, 0);
        redSlider.setName("redSlider");
        frame.getContentPane().add(redSlider, "cell 2 0,growx");

        greenSlider = new JSlider(0, 255, 0);
        greenSlider.setName("greenSlider");
        frame.getContentPane().add(greenSlider, "cell 2 1,growx");

        blueSlider = new JSlider(0, 255, 0);
        blueSlider.setName("blueSlider");
        frame.getContentPane().add(blueSlider, "cell 2 2,growx");
    }

    private void add_listeners() {
        ChangeListener listener = e -> setBackgroundColor();

        redSlider.addChangeListener(listener);
        greenSlider.addChangeListener(listener);
        blueSlider.addChangeListener(listener);
    }

    private void setBackgroundColor() {
        int r = redSlider.getValue();
        int g = greenSlider.getValue();
        int b = blueSlider.getValue();
        Color color = new Color(r, g, b);
        txtTest.setBackground(color);
    }
}
