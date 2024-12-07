import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminDashboard extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;

    private JLabel welcome;

    private JButton btnManageCustomers;
    private JButton btnManageMenu;
    private JButton btnCustomerLogin;

    private Admin admin;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();
    
    public AdminDashboard(Admin admin) {

        this.admin = admin;
        welcome = new JLabel("Welcome, " + admin.getFirstName());

        btnManageCustomers = new JButton("Manage Customers");
        btnManageMenu = new JButton("Manage Menu");
        btnCustomerLogin = new JButton("Customer Login");

        BtnListener btnlistener = new BtnListener();
        btnManageCustomers.addActionListener(btnlistener);
        btnManageMenu.addActionListener(btnlistener);
        btnCustomerLogin.addActionListener(btnlistener);

        welcome.setFont(welcome.getFont().deriveFont(36f).deriveFont(Font.BOLD));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pAdminDashboard = new JPanel();
        pAdminDashboard.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(welcome, pAdminDashboard, layout, gbc, 1, 0, 3, 1);
        a.addObjects(btnManageCustomers, pAdminDashboard, layout, gbc, 0, 1, 1, 1, 300, 25);
        a.addObjects(btnManageMenu, pAdminDashboard, layout, gbc, 1, 1, 1, 1, 300, 25);
        a.addObjects(btnCustomerLogin, pAdminDashboard, layout, gbc, 1, 2, 1, 1, 800, 25);

        this.add(pAdminDashboard);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnManageCustomers) {
                new AdminCustomerManager(admin);
                dispose();
            } else if (e.getSource() == btnManageMenu) {
                new MenuManagementScreen(admin);
                dispose();
            } else if (e.getSource() == btnCustomerLogin) {
                new LoginScreen();
                dispose();
            }
        }
    }
}
