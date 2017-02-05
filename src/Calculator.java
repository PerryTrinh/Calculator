import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame {
    private TextField tfDisplay;
    private Solver solver;

    public Calculator() {
        solver = new Solver();
        Font uniform = new Font("Times New Roman", Font.PLAIN, 25);

        Panel panelDisplay = new Panel(new FlowLayout());
        tfDisplay = new TextField("0", 20);
        tfDisplay.setEditable(false);
        tfDisplay.setFont(uniform);
        panelDisplay.add(tfDisplay);

        Panel panelButtons = new Panel(new GridLayout(5, 4, 5, 5));
        String[] buttonNames = new String[] {"sin", "cos", "tan", "+", "7", "8", "9",
                "-", "4", "5", "6", "*", "1", "2", "3", "/", "0", ".", "C", "="};
        Button[] buttons = new Button[buttonNames.length];

        for(int i = 0; i < buttonNames.length; i++) {
            buttons[i] = new Button(buttonNames[i]);
            panelButtons.add(buttons[i]);
        }

        setLayout(new BorderLayout());
        add(panelDisplay, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

        ButtonListener listener = new ButtonListener();
        for(Button i: buttons) {
            i.addActionListener(listener);
            i.setFont(uniform);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        int calcHeight = 400;
        int calcWidth = 450;
        setTitle("Calculator");
        setSize(calcWidth, calcHeight);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - calcWidth)/2;
        int y = (dim.height - calcHeight)/2;

        setLocation(x, y);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol = ((Button) e.getSource()).getLabel();
            tfDisplay.setText(solver.compute(symbol));
        }
    }
}