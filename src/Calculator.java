import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame {
    private TextField tfDisplay;
    private Solver solver;
    private Button radDeg;

    public Calculator() {
        solver = new Solver();
        Font uniform = new Font("Times New Roman", Font.PLAIN, 25);
        ButtonListener listener = new ButtonListener();

        Panel panelDisplay = new Panel(new FlowLayout());

        radDeg = new Button("Deg");
        radDeg.setFont(uniform);
        radDeg.addActionListener(listener);
        panelDisplay.add(radDeg);

        tfDisplay = new TextField("0", 21);
        tfDisplay.setEditable(false);
        tfDisplay.setFont(uniform);
        panelDisplay.add(tfDisplay);

        Panel panelButtons = new Panel(new GridLayout(5, 5, 5, 5));
        String[] buttonNames = new String[] {"pi", "sin", "cos", "tan", "/", "1/x", "7", "8", "9",
                "*", "x^2", "4", "5", "6", "-", "sqrt", "1", "2", "3", "+", "x!", "0", ".", "C", "="};
        Button[] buttons = new Button[buttonNames.length];

        for(int i = 0; i < buttonNames.length; i++) {
            buttons[i] = new Button(buttonNames[i]);
            panelButtons.add(buttons[i]);
        }

        setLayout(new BorderLayout());
        add(panelDisplay, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

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
        int calcWidth = 420;
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
            if (symbol.equals("Rad")) {
                solver.switchRadDeg();
                radDeg.setLabel("Deg");
            } else if (symbol.equals("Deg")) {
                solver.switchRadDeg();
                radDeg.setLabel("Rad");
            } else {
                tfDisplay.setText(solver.compute(symbol));
            }
        }
    }
}