import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CafeOnlineOrderSystemGUI extends JFrame{
    private final int FRAME_WIDTH = 1000; 
    private final int FRAME_HEIGHT = 1000;
    
    private JButton btnLogin;
    private JButton btnSignup;
    private JButton btnExit;

    private JLabel help;

    public static JFrame frame;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    AddObjects a = new AddObjects();

    public CafeOnlineOrderSystemGUI() {
        btnLogin = new JButton("Login");
        btnSignup = new JButton("Sign Up");
        btnExit = new JButton("Exit");

        help = new JLabel("Not a User? Click here to sign up");
        help.setOpaque(true);
        help.setBackground(new Color(255, 255, 255));
        help.setForeground(new Color(0, 0, 0));

        BtnListener btnlistener = new BtnListener();
        btnLogin.addActionListener(btnlistener);
        btnSignup.addActionListener(btnlistener);
        btnExit.addActionListener(btnlistener);

        ImagePanel pMain = new ImagePanel("src/resources/homeScreen.jpg");
        pMain.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 250;
        gbc.ipady = 100;

        help.setHorizontalAlignment(SwingConstants.CENTER);
        
        gbc.ipady = 50;
        a.addObjects(btnExit, pMain, layout, gbc, 1, 2, 3, 1);
        a.addObjects(btnSignup, pMain, layout, gbc, 1, 4, 3, 1);
        gbc.insets = new Insets(200, 0, 0, 0);
        a.addObjects(btnLogin, pMain, layout, gbc, 1, 1, 3, 1);

        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.ipadx = 10;
        gbc.ipady = 10;
        a.addObjects(help, pMain, layout, gbc, 1, 3, 3, 1);

        this.setTitle("Black Bear Diner");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(pMain);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           if(e.getSource() == btnLogin) {
               new LoginScreen();
               dispose();
           } 
           else if(e.getSource() == btnSignup) {
                new SignupScreen();
                dispose();
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
