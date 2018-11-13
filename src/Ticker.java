import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Ticker extends Thread {

    private Button b = new Button("Toggle");
    private TextField t = new TextField(10);
    private int count = 0;
    private boolean runFlag = true;

    public Ticker(Container c) {
        b.addActionListener(new ToggleL());
        JPanel p = new JPanel();
        p.add(t);
        p.add(b);
        c.add(p);
    }

    class ToggleL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
            if (count == 30) {
                stopThread();
            }
        }
    }

    public void run() {
        while (true) {
            if (runFlag)
                t.setText(Integer.toString(count++));
            if (count == 30)
                stopThread();
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    public void stp() {
        runFlag = false;
    }

    public void restart() {
        runFlag = true;
        if (count == 30) {
            stopThread();
        }
    }

    public void stopThread() {
        runFlag = false;
        Ticker.currentThread().interrupt();
    }
}