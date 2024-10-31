package zxcv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame {

	private JTextField t1;
    private StringBuilder currentInput;
    private double result;
    private String lastOperator;
	
	calculator() {
        this.setTitle("계산기");
        this.setSize(500, 250); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t1 = new JTextField("");
        t1.setHorizontalAlignment(JTextField.LEFT); 
        t1.setEnabled(false);
        this.add(t1, BorderLayout.NORTH); 

        currentInput = new StringBuilder();
        result = 0;
        lastOperator = "";
        
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

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C":
                    currentInput.setLength(0);
                    result = 0;
                    lastOperator = "";
                    t1.setText("");
                    break;
                case "CE":
                    currentInput.setLength(0);
                    t1.setText("");
                    break;
                case "Backspace":
                    if (currentInput.length() > 0) {
                        currentInput.deleteCharAt(currentInput.length() - 1);
                        t1.setText(currentInput.toString());
                    }
                    break;
                case "+":
                case "-":
                case "x":
                case "/":
                case "%":
                    calculate();
                    lastOperator = command;
                    currentInput.setLength(0);
                    break;
                case "sqrt":
                    if (currentInput.length() > 0) {
                        double value = Double.parseDouble(currentInput.toString());
                        result = Math.sqrt(value);
                        t1.setText(String.valueOf(result));
                        currentInput.setLength(0);
                    }
                    break;
                case "1/x":
                    if (currentInput.length() > 0) {
                        double value = Double.parseDouble(currentInput.toString());
                        if (value != 0) {
                            result = 1 / value;
                            t1.setText(String.valueOf(result));
                            currentInput.setLength(0);
                        } else {
                            t1.setText("Error");
                        }
                    }
                    break;
                case "=":
                    calculate();
                    t1.setText(String.valueOf(result));
                    currentInput.setLength(0);
                    break;
                default:
                    currentInput.append(command);
                    t1.setText(currentInput.toString());
                    break;
            }
        }

        private void calculate() {
            if (currentInput.length() == 0) {
                return;
            }

            double currentValue = Double.parseDouble(currentInput.toString());

            switch (lastOperator) {
                case "+":
                    result += currentValue;
                    break;
                case "-":
                    result -= currentValue;
                    break;
                case "x":
                    result *= currentValue;
                    break;
                case "/":
                    if (currentValue != 0) {
                        result /= currentValue;
                    } else {
                        t1.setText("Error");
                        currentInput.setLength(0);
                        return;
                    }
                    break;
                case "%":
                    result = result % currentValue;
                    break;
                default:
                    result = currentValue;
                    break;
            }
        }
    }

    
    public static void main(String[] args) {
        new calculator(); 
    }
}

