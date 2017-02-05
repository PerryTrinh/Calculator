import java.awt.*;
import java.awt.event.*;

public class FourFuncCalc extends Frame {
    private Button[] buttons;
    private String[] buttonNames;
    private TextField tfDisplay;
    private final int CALCHEIGHT = 450;
    private final int CALCWIDTH = 350;
    private String curr, prev;
    private String operator;

    public FourFuncCalc() {
        operator = "";
        prev = "0";
        curr = "0";

        Font uniform = new Font("Times New Roman", Font.PLAIN, 25);

        Panel panelDisplay = new Panel(new FlowLayout());
        tfDisplay = new TextField("0", 20);
        tfDisplay.setEditable(false);
        tfDisplay.setFont(uniform);
        panelDisplay.add(tfDisplay);

        Panel panelButtons = new Panel(new GridLayout(4, 4, 5, 5));
        buttonNames = new String[] {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "C", "=", "/"};
        buttons = new Button[buttonNames.length];

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

        setTitle("Four Function Calculator");
        setSize(CALCWIDTH, CALCHEIGHT);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - CALCWIDTH)/2;
        int y = (dim.height - CALCHEIGHT)/2;

        setLocation(x, y);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FourFuncCalc();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String symbol = ((Button) e.getSource()).getLabel();

            if(Solver.isNumeric(symbol)) {
                if(curr.equals("0")) {
                    curr = symbol;
                } else {
                    curr += symbol;
                }
                tfDisplay.setText(curr);
            } else if(symbol.equals("C")) {
                curr = "0";
                prev = "0";
                operator = "";
                tfDisplay.setText(curr);
            } else { //symbol is an operator
                if(operator.equals("")) {
                    prev = curr;
                    curr = "0";
                } else if(!operator.equals("=") && !symbol.equals(operator)){
                    prev = Solver.evaluate(operator, prev, curr);
                    tfDisplay.setText(prev);
                    curr = "0";
                } // if operator equals "=", just want to set operator = symbol
                operator = symbol;
            }
        }
    }
}