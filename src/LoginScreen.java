import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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
    private boolean adminAsCustomer;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

    public LoginScreen(boolean adminAsCustomer) {
        this.adminAsCustomer = adminAsCustomer;
        btnLogin = new JButton("Login");
        btnLoginCancel = new JButton("Cancel");

        BtnListener btnlistener = new BtnListener();
        btnLogin.addActionListener(btnlistener);
        btnLoginCancel.addActionListener(btnlistener);

        loginUsername = new JLabel("Username:");
        loginPassword = new JLabel("Password:");

        loginUsernameText = new JTextField("");
        loginPasswordText = new JPasswordField("");

        ImagePanel pLogin = new ImagePanel("src/resources/loginScreen.jpg");
        pLogin.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(15, 0, 0, 0);
        a.addObjects(loginUsername, pLogin, layout, gbc, 0, 0, 1, 1, 50, 10);
        gbc.insets = new Insets(10, 0, 0, 0);
        a.addObjects(loginUsernameText, pLogin, layout, gbc, 0, 1, 4, 1, 357, 10);
        a.addObjects(loginPassword, pLogin, layout, gbc, 0, 2, 1, 1, 50, 10);
        a.addObjects(loginPasswordText, pLogin, layout, gbc, 0, 3, 4, 1, 357, 10);

        gbc.insets = new Insets(55, 0, 0, 0);
        JPanel pButtons  = new JPanel();
        pButtons.setLayout(new GridLayout(0,2));
        pButtons.add(btnLogin);
        pButtons.add(btnLoginCancel);
        pButtons.setOpaque(false);
        a.addObjects(pButtons, pLogin, layout, gbc, 0, 6, 4, 1, 50, 25);

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
                    if(role.equals("admin") && !adminAsCustomer) {
                        System.out.println("Launching AdminDashboard for: " + SignupScreen.admins.get(index).getUserName());
                        new AdminDashboard(SignupScreen.admins.get(index));
                        dispose();
                    }
                    else if(role.equals("customer") || adminAsCustomer) {
                        if (!adminAsCustomer) {
                            System.out.println("Launching CustomerDashboard for: " + SignupScreen.customers.get(index).getUserName());
                            new CustomerDashboard(SignupScreen.customers.get(index));
                        } else {
                            System.out.println("Launching CustomerDashboard for: " + SignupScreen.admins.get(index).getUserName());
                            new CustomerDashboard(SignupScreen.admins.get(index));
                        }
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
