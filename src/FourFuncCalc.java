import java.awt.*;
import java.awt.event.*;

public class FourFuncCalc extends Frame {
    private Button[] btnNumbers;
    private String[] buttons;
    private TextField tfDisplay;
    private int count;

    public FourFuncCalc() {
        Panel panelDisplay = new Panel(new FlowLayout());
        tfDisplay = new TextField("0", 20);
        panelDisplay.add(tfDisplay);

        Panel panelButtons = new Panel(new GridLayout(4, 4, 4, 4));
        buttons = new String[] {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "C", "=", "/"};
        btnNumbers = new Button[buttons.length];

        for(int i = 0; i < buttons.length; i++) {
            btnNumbers[i] = new Button(buttons[i]);
            panelButtons.add(btnNumbers[i]);
        }

        setLayout(new BorderLayout());
        add(panelDisplay, BorderLayout.NORTH);
        add(panelButtons, BorderLayout.CENTER);

        ButtonListener listener = new ButtonListener();
        for(int i = 1; i < btnNumbers.length; i++) {
            btnNumbers[i].addActionListener(listener);
        }

        setTitle("Calculator Demo");
        setSize(400, 300);
        setVisible(true);
    }

    // The entry main() method
    public static void main(String[] args) {
        new FourFuncCalc();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Button source = (Button)e.getSource();
            String symbol = source.getLabel();

            if(isNumeric(symbol)) {
                tfDisplay.setText(symbol);
            } else if(symbol == "+") {
                tfDisplay.setText("addition");
            } else if(symbol == "-") {
                tfDisplay.setText("subtraction");
            } else if(symbol == "*") {
                tfDisplay.setText("multiplication");
            } else if(symbol == "/") {
                tfDisplay.setText("Division");
            } else {
                tfDisplay.setText("0");
            }
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
