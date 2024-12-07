import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class SignupSuccess extends JFrame {
    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 150;

    private JLabel signupSuccess;
    private JButton btnSuccessOk;

    public SignupSuccess(String username) {
        signupSuccess = new JLabel("Signup Successful! Your username is " + username);
        
        btnSuccessOk = new JButton("OK");
        btnSuccessOk.addActionListener(new BtnListener());

        JPanel pSignupSuccess = new JPanel();
        pSignupSuccess.setLayout(new FlowLayout());
    
        pSignupSuccess.add(signupSuccess);
        pSignupSuccess.add(btnSuccessOk);
    
        this.add(pSignupSuccess);
        this.setTitle("Signup Success");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSuccessOk) {
                new CafeOnlineOrderSystemGUI();
                dispose();
            }
        }
    }
}

