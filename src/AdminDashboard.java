import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminDashboard extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 800;

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

        ImagePanel pAdminDashboard = new ImagePanel("src/resources/adminDashboardScreen.jpg");
        pAdminDashboard.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(25, 0, 35, 0);
        a.addObjects(welcome, pAdminDashboard, layout, gbc, 0, 0, 2, 1);
        gbc.insets = new Insets(0, 0, 0, 10);
        a.addObjects(btnManageCustomers, pAdminDashboard, layout, gbc, 0, 1, 1, 1, 225, 25);
        gbc.insets = new Insets(0, 10, 0, 0);
        a.addObjects(btnManageMenu, pAdminDashboard, layout, gbc, 1, 1, 1, 1, 225, 25);
        gbc.insets = new Insets(45, 200, 55, 200);
        a.addObjects(btnCustomerLogin, pAdminDashboard, layout, gbc, 0, 2, 2, 1, 200, 25);

        this.add(pAdminDashboard);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnManageCustomers) {
                new CustomerManagementScreen(admin);
                dispose();
            } else if (e.getSource() == btnManageMenu) {
                new MenuManagementScreen(admin);
                dispose();
            } else if (e.getSource() == btnCustomerLogin) {
                System.out.println("Logging in as customer...");
                new LoginScreen(true);
                dispose();
            }
        }
    }
}
