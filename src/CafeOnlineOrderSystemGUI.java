import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CafeOnlineOrderSystemGUI extends JFrame{
    private final int FRAME_WIDTH = 1000; 
    private final int FRAME_HEIGHT = 1000;
    
    private JButton btnLogin;
    private JButton btnSignup;
    private JButton btnExit;

    private JLabel display;
    private JLabel help;

    private GridBagLayout layout = new GridBagLayout();

    public CafeOnlineOrderSystemGUI() {
        btnLogin = new JButton("Login");
        btnSignup = new JButton("Sign Up");
        btnExit = new JButton("Exit");

        display = new JLabel("Welcome to (Restaurant Name)");
        help = new JLabel("Not a User? Click here to sign up");

        BtnListener btnlistener = new BtnListener();
        btnLogin.addActionListener(btnlistener);
        btnSignup.addActionListener(btnlistener);
        btnExit.addActionListener(btnlistener);

        JPanel pMain = new JPanel();
        pMain.setLayout(layout);

        this.add(pMain);
        this.setTitle("Blackbear Diner");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           if(e.getSource() == btnLogin) {
               // Open login page
           } else if(e.getSource() == btnSignup) {
               // Open sign up page
           } else if(e.getSource() == btnExit) {
               System.exit(0);
           }
        }
    }
}
