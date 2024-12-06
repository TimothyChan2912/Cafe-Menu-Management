import java.awt.*;
import java.util.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
	private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;

    private JLabel loginUsername;
    private JLabel loginPassword;

    static JTextField loginUsernameText;
    static JPasswordField loginPasswordText;

    private JButton btnLogin;
    private JButton btnLoginCancel;

    private String role;
    private int index;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

    public LoginScreen() {
        btnLogin = new JButton("Login");
        btnLoginCancel = new JButton("Cancel");

        BtnListener btnlistener = new BtnListener();
        btnLogin.addActionListener(btnlistener);
        btnLoginCancel.addActionListener(btnlistener);

        loginUsername = new JLabel("Username:");
        loginPassword = new JLabel("Password:");

        loginUsernameText = new JTextField("");
        loginPasswordText = new JPasswordField("");

        JPanel pLogin = new JPanel();
        pLogin.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(loginUsername, pLogin, layout, gbc, 0, 0, 1, 1, 50, 50);
        a.addObjects(loginUsernameText, pLogin, layout, gbc, 0, 1, 3, 1, 250, 25);
        a.addObjects(loginPassword, pLogin, layout, gbc, 0, 2, 1, 1, 50, 50);
        a.addObjects(loginPasswordText, pLogin, layout, gbc, 0, 3, 3, 1, 250, 25);

        gbc.ipady = 50;

        a.addObjects(btnLogin, pLogin, layout, gbc, 0, 6, 1, 1, 50, 25);
        a.addObjects(btnLoginCancel, pLogin, layout, gbc, 1, 6, 3, 1, 50, 25);

        this.add(pLogin);
        this.setTitle("Login");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public boolean checkUsernameAndPassword() {
        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();

        for(int i = 0; i < SignupScreen.admins.size(); i++) {
            if(SignupScreen.admins.get(i).getUserName().equals(username) && SignupScreen.admins.get(i).getPassword().equals(password)) {
                role = "admin";
                index = i;
                return true;
            }
        }
    
        for(int i = 0; i < SignupScreen.customers.size(); i++) {
            if(SignupScreen.customers.get(i).getUserName().equals(username) && SignupScreen.customers.get(i).getPassword().equals(password)) {
                role = "customer";
                index = i;
                return true;
            }
        }
        return false;
    }

    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnLogin) {
                if(checkUsernameAndPassword()) {
                    if(role.equals("admin")) {
                        new AdminDashboard(SignupScreen.admins.get(index));
                        dispose();
                    }
                    else if(role.equals("customer")) {
                        new CustomerDashboard();
                        dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(e.getSource() == btnLoginCancel) {
                new CafeOnlineOrderSystemGUI();
                dispose();
            }
        }
    }
}
