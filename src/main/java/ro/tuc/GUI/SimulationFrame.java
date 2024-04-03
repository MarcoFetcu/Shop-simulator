package ro.tuc.GUI;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class SimulationFrame extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel MaxArrivalTime;
    private JButton Startt;
    private JPanel mainPanel;
    private JScrollPane scroll;
    private JTextPane textPane1;
    private JTextField textField6;
    private JTextField textField7;

    public SimulationFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        Startt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int simt, mina, maxa, mins, maxs;
                String s;
                s = textField1.getText();
                simt = Integer.valueOf(s);

                s = textField2.getText();
                mina = Integer.valueOf(s);

                s = textField3.getText();
                maxa = Integer.valueOf(s);

                s = textField4.getText();
                mins = Integer.valueOf(s);

                s = textField1.getText();
                maxs = Integer.valueOf(s);


            }
        });
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JTextField getTextField6() {
        return textField6;
    }

    public JTextField getTextField7() {
        return textField7;
    }

    public JButton getStartt() {
        return Startt;
    }

    public JTextPane getTextPane1() {
        return textPane1;
    }

    public void setTextPane1(JTextPane textPane1) {
        this.textPane1 = textPane1;
    }



}






