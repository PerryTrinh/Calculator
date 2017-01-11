import java.awt.*;
import java.awt.event.*;

public class FourFuncCalc extends Frame {
    private Button[] btnNumbers;
    private String[] buttons;
    private TextField tfDisplay;
    private int count;
    private String operator;

    public FourFuncCalc() {
        Panel panelDisplay = new Panel(new FlowLayout());
        tfDisplay = new TextField("0", 20);
        panelDisplay.add(tfDisplay);
        operator = "";

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
        for(Button i: btnNumbers) {
            i.addActionListener(listener);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setTitle("Four Function Calculator");
        setSize(400, 300);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w)/2;
        int y = (dim.height - h)/2;

        setLocation(x, y);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FourFuncCalc();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Button source = (Button)e.getSource();
            String symbol = source.getLabel();

            if(Solver.isNumeric(symbol)) {
                if(!operator.equals("")) {
                    count = Solver.evaluate(operator, count, Integer.parseInt(symbol));
                    operator = "";
                } else {
                    count = Integer.parseInt(symbol);
                }
                tfDisplay.setText(symbol + "");
            } else if(symbol.equals("C")) {
                count = 0;
                tfDisplay.setText(count + "");
            } else if(symbol.equals("=")){
                tfDisplay.setText(count + "");
            } else {
                operator = symbol;
            }
        }
    }
}