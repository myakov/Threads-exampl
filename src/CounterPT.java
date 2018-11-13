import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CounterPT extends JPanel {

    private Button start = new Button("Start");
    private Button stop = new Button("Stop");
    private Button restart = new Button("Restart");
    private boolean started = false;
    private Ticker[] s;
    private int size;

    public void init() {
        this.setLayout(new FlowLayout());
        s = new Ticker[size];
        for (int i = 0; i < s.length; i++)
            s[i] = new Ticker(this);
        start.addActionListener(new StartL());
        add(start);
        stop.addActionListener(new StopL());
        add(stop);
        restart.addActionListener(new RestartL());
        add(restart);
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!started) {
                started = true;
                for (int i = 0; i < s.length; i++) s[i].start();
            }
        }
    }

    class StopL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < s.length; i++) s[i].stp();

        }
    }

    class RestartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < s.length; i++) s[i].restart();
        }
    }

    public static void main(String[] args) {
        CounterPT cnt = new CounterPT();
        cnt.size = (args.length == 0 ? 120 : Integer.parseInt(args[0]));
        JFrame aFrame = new JFrame("CounterPT");
        aFrame.add(cnt);
        aFrame.setSize(200 * (1 + cnt.size / 10), 500);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cnt.init();
        aFrame.setVisible(true);
    }
}