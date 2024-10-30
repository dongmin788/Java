package zxcv;

import javax.swing.*;
import java.awt.*;

public class calculator extends JFrame {

	calculator() {
        this.setTitle("계산기");
        this.setSize(500, 250); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField t1 = new JTextField("0.");
        t1.setHorizontalAlignment(JTextField.LEFT); 
        t1.setEnabled(false);
        this.add(t1, BorderLayout.NORTH); 

        JPanel b1 = new JPanel();
        b1.setLayout(new GridLayout(5, 5, 5, 5)); 

        this.addButtons(b1);
        this.add(b1, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void addButtons(JPanel panel) {
        String[] l1 = {
            "Backspace", "", "", "CE", "C",
            "7", "8", "9", "/", "sqrt",
            "4", "5", "6", "x", "%",
            "1", "2", "3", "-", "1/x",
            "0", "+/-", ".", "+", "="
        };

        for (String label : l1) {
            JButton button = new JButton(label);
            button.setBackground(Color.white); 
            button.setForeground(Color.BLACK); 
            panel.add(button); 
        }
    }

    public static void main(String[] args) {
        new calculator(); 
    }
}

