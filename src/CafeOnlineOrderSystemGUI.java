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

    public static JFrame frame;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    AddObjects a = new AddObjects();

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

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 250;
        gbc.ipady = 100;

        a.addObjects(display, pMain, layout, gbc, 1, 0, 3, 1);
        a.addObjects(help, pMain, layout, gbc, 1, 3, 3, 1);

        gbc.ipady = 50;

        a.addObjects(btnLogin, pMain, layout, gbc, 1, 1, 3, 1);
        a.addObjects(btnSignup, pMain, layout, gbc, 1, 2, 3, 1);
        a.addObjects(btnExit, pMain, layout, gbc, 1, 4, 3, 1);

        this.add(pMain);
        this.setTitle("Blackbear Diner");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           if(e.getSource() == btnLogin) {
               new LoginScreen();
           } 
           else if(e.getSource() == btnSignup) {
                new SignupScreen();
           } 
           else if(e.getSource() == btnExit) {
               System.exit(0);
           }
        }
    }

    public static void main(String[] args) {
        frame = new CafeOnlineOrderSystemGUI();
    }
}
