import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    private User admin;

    public AdminDashboard(JFrame parent, User admin) {
        super("Admin Dashboard");
        this.admin = admin;
        UserManager userManager = new UserManager(); 

        // Create a panel to hold all other components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        add(topPanel);

        // Create a welcome message
        JLabel welcomeLabel = new JLabel("Welcome " + admin.getFirstName() + " " + admin.getLastName() + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Create a button for viewing all users
        JButton viewUsersButton = new JButton("View All Users");
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userManager.viewAllUsers();
            }
        });
        buttonPanel.add(viewUsersButton);

        // Create a button for viewing all items
        JButton viewItemsButton = new JButton("View All Items");
        viewItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemManager itemManager = new ItemManager();
                itemManager.viewAllItems();
            }
        });

        buttonPanel.add(viewItemsButton);

        // Create a button for viewing all orders
        JButton viewOrdersButton = new JButton("View All Orders");
        viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderManager orderManager = new OrderManager();
                orderManager.viewAllOrders();
            }
        });

        buttonPanel.add(viewOrdersButton);

        // Create a button for logging out
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });

        buttonPanel.add(logoutButton);

        // Set the size of the window and display it
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
