import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCustomerManagerEdit extends JFrame {
     private final int FRAME_WIDTH = 1600;
    private final int FRAME_HEIGHT = 300;

    private JLabel userType;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel email;
    private JLabel password;
    private JLabel status;

    private JButton btnOk;
    private JButton btnCancel;

    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField emailText;
    private JTextField passwordText;

    private ButtonGroup statusCheck;
    private JRadioButton statusCheckActive;
    private JRadioButton statusCheckInactive;   

    private JComboBox<String> userTypeList;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

    Customer customer;
    int customerIndex;
    boolean isActive;

    public AdminCustomerManagerEdit(Customer customer, boolean isActive) {
        this.customer = customer;
        customerIndex = AdminCustomerManager.activeModel.indexOf(customer);
        this.isActive = isActive;

        btnOk = new JButton("Ok");
        btnCancel = new JButton("Cancel");

        BtnListener btnlistener = new BtnListener();
        btnOk.addActionListener(btnlistener);
        btnCancel.addActionListener(btnlistener);

        userType = new JLabel("User Type:");
        firstName = new JLabel("First Name:");
        lastName = new JLabel("Last Name:");
        email = new JLabel("Main ID:");
        password = new JLabel("Password:");
        status = new JLabel("Status:");

        firstNameText = new JTextField(customer.getFirstName());
        lastNameText = new JTextField(customer.getLastName());
        emailText = new JTextField(customer.getEmail());
        passwordText = new JTextField(customer.getPassword());

        statusCheck = new ButtonGroup();
        statusCheckActive = new JRadioButton("Active");
        statusCheckInactive = new JRadioButton("Inactive");

        userTypeList = new JComboBox<String>();
        userTypeList.addItem("Admin");
        userTypeList.addItem("Customer");
        userTypeList.setSelectedItem("Customer");
        
        statusCheck.add(statusCheckActive);
        statusCheck.add(statusCheckInactive);
        statusCheckActive.setSelected(customer.isActive());

        JPanel pAdd = new JPanel();
        pAdd.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(userType, pAdd, layout, gbc, 0, 0, 1, 1, 10, 50);
        a.addObjects(userTypeList, pAdd, layout, gbc, 1, 0, 1, 1, 50, 5);
        a.addObjects(firstName, pAdd, layout, gbc, 2, 0, 1, 1, 25, 50);
        a.addObjects(firstNameText, pAdd, layout, gbc, 3, 0, 1, 1, 100, 5);
        a.addObjects(lastName, pAdd, layout, gbc, 4, 0, 1, 1, 25, 50);
        a.addObjects(lastNameText, pAdd, layout, gbc, 5, 0, 1, 1, 100, 5);
        a.addObjects(email, pAdd, layout, gbc, 6, 0, 1, 1, 25, 50);
        a.addObjects(emailText, pAdd, layout, gbc, 7, 0, 1, 1, 100, 5);
        a.addObjects(password, pAdd, layout, gbc, 8, 0, 1, 1, 25, 50);
        a.addObjects(passwordText, pAdd, layout, gbc, 9, 0, 1, 1, 100, 5);
        a.addObjects(status, pAdd, layout, gbc, 10, 0, 1, 1, 25, 50);
        a.addObjects(statusCheckActive, pAdd, layout, gbc, 11, 0, 1, 1, 50, 15);
        a.addObjects(statusCheckInactive, pAdd, layout, gbc, 12, 0, 1, 1, 50, 15);

        gbc.ipady = 50;

        a.addObjects(btnOk, pAdd, layout, gbc, 0, 6, 1, 1, 50, 25);
        a.addObjects(btnCancel, pAdd, layout, gbc, 1, 6, 3, 1, 50, 25);

        this.add(pAdd);
        this.setTitle("Update User Details");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnOk) {
                editUser();
            } else if (e.getSource() == btnCancel) {
                dispose();
            }
        }
    }

    public void editUser() {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String email = emailText.getText();
        String password = passwordText.getText();
        boolean isActiveChecked = statusCheckActive.isSelected();
        String username = customer.getUserName();

        if (isActiveChecked) {
            if(isActiveChecked != isActive) {
                AdminCustomerManager.activeModel.addElement(customer);
                AdminCustomerManager.inactiveModel.removeElement(customer);
            }
            else {
            Customer activeCustomer = new Customer(firstName, lastName, email, username, password, true);
            AdminCustomerManager.activeModel.set(customerIndex, activeCustomer);
            }
        } 
        else {
            if(isActiveChecked != isActive) {
                AdminCustomerManager.inactiveModel.addElement(customer);
                AdminCustomerManager.activeModel.removeElement(customer);
            }
            else {
                Customer inactiveCustomer = new Customer(firstName, lastName, email, username, password, false);
                AdminCustomerManager.inactiveModel.set(customerIndex, inactiveCustomer);
            }
        } 
        dispose(); 
    }
}