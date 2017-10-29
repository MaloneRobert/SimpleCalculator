package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * In this program, the user can use a very simple calculator. I created
 * this project for fun as a way to expand my java knockledge and also
 * just to create a calculator. It was made in my early years of java, so
 * everything is in one class. Obviously, if I were to do this project again
 * I would separate the classes with polymorphism and see if other design
 * techniques could fit in well.
 */
public class SimpleCalc extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Calculator");
        SimpleCalc content = new SimpleCalc();
        window.setContentPane(content);
        window.pack();  // Sizes window to preferred size of contents.
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }

    //-------------------------------------------------------------------------


    private JTextField xInput, yInput;

    private JLabel answer;

    public SimpleCalc() {

        setBackground(Color.GRAY);

        setBorder( BorderFactory.createEmptyBorder(5,5,5,5) );

        xInput = new JTextField("0", 10);
        xInput.setBackground(Color.WHITE);
        yInput = new JTextField("0", 10);
        yInput.setBackground(Color.WHITE);

        JPanel xPanel = new JPanel();
        xPanel.add( new JLabel(" x = "));
        xPanel.add(xInput);

        JPanel yPanel = new JPanel();
        yPanel.add( new JLabel(" y = "));
        yPanel.add(yInput);

        /** Here I create a panel to hold the four buttons for the four
             operations.  A GridLayout is used so that the buttons
             will all have the same size and will fill the panel.
             The main panel serves as ActionListener for the buttons.
				**/

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,4));

        JButton plus = new JButton("+");
        plus.addActionListener(this);
        buttonPanel.add(plus);

        JButton minus = new JButton("-");
        minus.addActionListener(this);
        buttonPanel.add(minus);

        JButton times = new JButton("*");
        times.addActionListener(this);
        buttonPanel.add(times);

        JButton divide = new JButton("/");
        divide.addActionListener(this);
        buttonPanel.add(divide);

        answer = new JLabel("x + y = 0", JLabel.CENTER);
        answer.setForeground(Color.RED);
        answer.setBackground(Color.WHITE);
        answer.setOpaque(true);

        setLayout(new GridLayout(4,1,3,3));
        add(xPanel);
        add(yPanel);
        add(buttonPanel);
        add(answer);
    }


    /**
     * When the user clicks a button, get the numbers from the input boxes
     * and perform the operation indicated by the button.  Put the result in
     * the answer label.  If an error occurs, an error message is put in the label.
     */
    public void actionPerformed(ActionEvent evt) {

        double x, y;
        try {
            String xStr = xInput.getText();
            x = Double.parseDouble(xStr);
        }
        catch (NumberFormatException e) {
            answer.setText("Illegal data for x.");
            xInput.requestFocusInWindow();
            return;
        }
        try {
            String yStr = yInput.getText();
            y = Double.parseDouble(yStr);
        }
        catch (NumberFormatException e) {
            answer.setText("Illegal data for y.");
            yInput.requestFocusInWindow();
            return;
        }

        /* Perform the operation based on the action command
             from the button.  Note that division by zero produces
             an error message. */

        String op = evt.getActionCommand();
        if (op.equals("+"))
            answer.setText( "x + y = " + (x+y) );
        else if (op.equals("-"))
            answer.setText( "x - y = " + (x-y) );
        else if (op.equals("*"))
            answer.setText( "x * y = " + (x*y) );
        else if (op.equals("/")) {
            if (y == 0)
                answer.setText("Can't divide by zero!");
            else
                answer.setText( "x / y = " + (x/y) );
        }

    }


}  
