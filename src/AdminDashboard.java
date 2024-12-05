import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;

    private JLabel welcome;

    private JButton btnManageCustomers;
    private JButton btnManageMenu;
    private JButton btnCustomerLogin;

    private User admin;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();
    
    public AdminDashboard(User admin) {

        welcome = new JLabel("Welcome, " + admin.getUserName());

        btnManageCustomers = new JButton("Manage Customers");
        btnManageMenu = new JButton("Manage Menu");
        btnCustomerLogin = new JButton("Customer Login");

        BtnListener btnlistener = new BtnListener();
        btnManageCustomers.addActionListener(btnlistener);
        btnManageMenu.addActionListener(btnlistener);
        btnCustomerLogin.addActionListener(btnlistener);

        JPanel pAdminDashboard = new JPanel();
        pAdminDashboard.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 250;
        gbc.ipady = 100;

        a.addObjects(welcome, pAdminDashboard, layout, gbc, 1, 1, 3, 1, 300, 100);
        a.addObjects(btnManageCustomers, pAdminDashboard, layout, gbc, 0, 2, 1, 1, 250, 25);
        a.addObjects(btnManageMenu, pAdminDashboard, layout, gbc, 2, 2, 3, 1, 250, 25);
        a.addObjects(btnCustomerLogin, pAdminDashboard, layout, gbc, 1, 3, 3, 1, 500, 25);

        this.add(pAdminDashboard);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnManageCustomers) {
                //Manage Customer Screen
                dispose();
            } else if (e.getSource() == btnManageMenu) {
                //Manage Menu Screen
                dispose();
            } else if (e.getSource() == btnCustomerLogin) {
                new SignupScreen();
                dispose();
            }
        }
    }
}
