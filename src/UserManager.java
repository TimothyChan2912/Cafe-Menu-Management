import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserManager {
	private final int FRAME_WIDTH = 1600;
    private final int FRAME_HEIGHT = 300;

    private JFrame fAdd;
    private JFrame fEdit;
    private JFrame fDelete;
    private JFrame fReactivate;
    private JFrame fDeactivate;

    private JLabel userType;
    private JLabel firstName;
    private JLabel lastName;
    private JLabel email;
    private JLabel password;
    private JLabel status;

    private JTextField firstNameText;
    private JTextField lastNameText;
    private JTextField emailText;
    private JTextField passwordText;

    private ButtonGroup statusCheck;
    private JRadioButton statusCheckActive;
    private JRadioButton statusCheckInactive;   

    private JComboBox<String> userTypeList;

    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

	public UserManager() {
    }



    //User Add
    private JButton addBtnOk;
    private JButton addBtnCancel;
    private GridBagLayout addLayout = new GridBagLayout();

    public void add() {
        fAdd = new JFrame("Enter User Details");
        addBtnOk = new JButton("Ok");
        addBtnCancel = new JButton("Cancel");

        BtnListener btnlistener = new BtnListener();
        addBtnOk.addActionListener(btnlistener);
        addBtnCancel.addActionListener(btnlistener);

        userType = new JLabel("User Type:");
        firstName = new JLabel("First Name:");
        lastName = new JLabel("Last Name:");
        email = new JLabel("Main ID:");
        password = new JLabel("Password:");
        status = new JLabel("Status:");

        firstNameText = new JTextField("");
        lastNameText = new JTextField("");
        emailText = new JTextField("");
        passwordText = new JTextField("");

        statusCheck = new ButtonGroup();
        statusCheckActive = new JRadioButton("Active");
        statusCheckInactive = new JRadioButton("Inactive");

        userTypeList = new JComboBox<String>();
        userTypeList.addItem("Admin");
        userTypeList.addItem("Customer");
        
        statusCheck.add(statusCheckActive);
        statusCheck.add(statusCheckInactive);

        JPanel pAdd = new JPanel();
        pAdd.setLayout(addLayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(userType, pAdd, addLayout, gbc, 0, 0, 1, 1, 10, 50);
        a.addObjects(userTypeList, pAdd, addLayout, gbc, 1, 0, 1, 1, 50, 5);
        a.addObjects(firstName, pAdd, addLayout, gbc, 2, 0, 1, 1, 25, 50);
        a.addObjects(firstNameText, pAdd, addLayout, gbc, 3, 0, 1, 1, 100, 5);
        a.addObjects(lastName, pAdd, addLayout, gbc, 4, 0, 1, 1, 25, 50);
        a.addObjects(lastNameText, pAdd, addLayout, gbc, 5, 0, 1, 1, 100, 5);
        a.addObjects(email, pAdd, addLayout, gbc, 6, 0, 1, 1, 25, 50);
        a.addObjects(emailText, pAdd, addLayout, gbc, 7, 0, 1, 1, 100, 5);
        a.addObjects(password, pAdd, addLayout, gbc, 8, 0, 1, 1, 25, 50);
        a.addObjects(passwordText, pAdd, addLayout, gbc, 9, 0, 1, 1, 100, 5);
        a.addObjects(status, pAdd, addLayout, gbc, 10, 0, 1, 1, 25, 50);
        a.addObjects(statusCheckActive, pAdd, addLayout, gbc, 11, 0, 1, 1, 50, 15);
        a.addObjects(statusCheckInactive, pAdd, addLayout, gbc, 12, 0, 1, 1, 50, 15);

        gbc.ipady = 50;

        a.addObjects(addBtnOk, pAdd, addLayout, gbc, 0, 6, 1, 1, 50, 25);
        a.addObjects(addBtnCancel, pAdd, addLayout, gbc, 1, 6, 3, 1, 50, 25);

        fAdd.add(pAdd);
        fAdd.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fAdd.setVisible(true);
    }

    public void addUser() {
        String userType = userTypeList.getSelectedItem().toString();
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String email = emailText.getText();
        String password = passwordText.getText();
        boolean isActive = statusCheckActive.isSelected();

        String rand = "";
        for(int i = 0; i < 4; i++) {
            rand += (int)(Math.random() * 10);
        }

        String username = firstName + rand;

        if (isActive) {
            Customer activeCustomer = new Customer(firstName, lastName, email, username, password, true);
            CustomerManagementScreen.activeModel.addElement(activeCustomer);
        } else {
            Customer inactiveCustomer = new Customer(firstName, lastName, email, username, password, false);
            CustomerManagementScreen.inactiveModel.addElement(inactiveCustomer); 
        }  
        fAdd.dispose();
    }





    //User edit
    Customer customer;
    int customerIndex;
    boolean isActive;
    private JButton editBtnOk;
    private JButton editBtnCancel;
    private GridBagLayout editLayout = new GridBagLayout();

    public void edit(Customer customer, boolean isActive) {
        fEdit = new JFrame("Update User Details");

        this.customer = customer;
        customerIndex = CustomerManagementScreen.activeModel.indexOf(customer);
        this.isActive = isActive;

        editBtnOk = new JButton("Ok");
        editBtnCancel = new JButton("Cancel");

        BtnListener btnlistener = new BtnListener();
        editBtnOk.addActionListener(btnlistener);
        editBtnCancel.addActionListener(btnlistener);

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

        JPanel pEdit = new JPanel();
        pEdit.setLayout(editLayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(userType, pEdit, editLayout, gbc, 0, 0, 1, 1, 10, 50);
        a.addObjects(userTypeList, pEdit, editLayout, gbc, 1, 0, 1, 1, 50, 5);
        a.addObjects(firstName, pEdit, editLayout, gbc, 2, 0, 1, 1, 25, 50);
        a.addObjects(firstNameText, pEdit, editLayout, gbc, 3, 0, 1, 1, 100, 5);
        a.addObjects(lastName, pEdit, editLayout, gbc, 4, 0, 1, 1, 25, 50);
        a.addObjects(lastNameText, pEdit, editLayout, gbc, 5, 0, 1, 1, 100, 5);
        a.addObjects(email, pEdit, editLayout, gbc, 6, 0, 1, 1, 25, 50);
        a.addObjects(emailText, pEdit, editLayout, gbc, 7, 0, 1, 1, 100, 5);
        a.addObjects(password, pEdit, editLayout, gbc, 8, 0, 1, 1, 25, 50);
        a.addObjects(passwordText, pEdit, editLayout, gbc, 9, 0, 1, 1, 100, 5);
        a.addObjects(status, pEdit, editLayout, gbc, 10, 0, 1, 1, 25, 50);
        a.addObjects(statusCheckActive, pEdit, editLayout, gbc, 11, 0, 1, 1, 50, 15);
        a.addObjects(statusCheckInactive, pEdit, editLayout, gbc, 12, 0, 1, 1, 50, 15);

        gbc.ipady = 50;

        a.addObjects(editBtnOk, pEdit, editLayout, gbc, 0, 6, 1, 1, 50, 25);
        a.addObjects(editBtnCancel, pEdit, editLayout, gbc, 1, 6, 3, 1, 50, 25);

        fEdit.add(pEdit);
        fEdit.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fEdit.setVisible(true);
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
                CustomerManagementScreen.activeModel.addElement(customer);
                CustomerManagementScreen.inactiveModel.removeElement(customer);
            }
            else {
            Customer activeCustomer = new Customer(firstName, lastName, email, username, password, true);
            CustomerManagementScreen.activeModel.set(customerIndex, activeCustomer);
            }
        } 
        else {
            if(isActiveChecked != isActive) {
                CustomerManagementScreen.inactiveModel.addElement(customer);
                CustomerManagementScreen.activeModel.removeElement(customer);
            }
            else {
                Customer inactiveCustomer = new Customer(firstName, lastName, email, username, password, false);
                CustomerManagementScreen.inactiveModel.set(customerIndex, inactiveCustomer);
            }
        } 
        fEdit.dispose(); 
    }





    //User Delete
    private Customer selectedCustomer;
    private JButton btnYes;
    private JButton btnNo;
    private JLabel delete;
    private GridBagLayout deleteLayout = new GridBagLayout();

    public void delete(Customer customer) {
        fDelete = new JFrame("Confirm Deletion");
        selectedCustomer = customer;

        btnYes = new JButton("Yes");
        btnNo = new JButton("No");

        BtnListener btnlistener = new BtnListener();
        btnYes.addActionListener(btnlistener);
        btnNo.addActionListener(btnlistener);

        delete = new JLabel("Are you sure you want to delete the selected user?");

        JPanel pDelete = new JPanel();
        pDelete.setLayout(deleteLayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(delete, pDelete, deleteLayout, gbc, 0, 0, 1, 1, 50, 50);

        a.addObjects(btnYes, pDelete, deleteLayout, gbc, 0, 1, 1, 1, 25, 25);
        a.addObjects(btnNo, pDelete, deleteLayout, gbc, 1, 1, 1, 1, 25, 25);

        fDelete.add(pDelete);
        fDelete.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fDelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fDelete.setVisible(true);
    }



    //User Reactivate
    private JList<Customer> activeCustomerListDisplay;
    public static DefaultListModel<Customer> inactiveModel;
    public static DefaultListModel<Customer> activeModel;
    public void reactivate (Customer customer, DefaultListModel<Customer> active, DefaultListModel<Customer> inactive, JList<Customer> display) {
        fReactivate = new JFrame();
        selectedCustomer = customer;
        activeModel = active;
        inactiveModel = inactive;
        activeCustomerListDisplay = display;

        if(selectedCustomer.isActive()) {
                    JOptionPane.showMessageDialog(fReactivate, "Selected customer should be Inactive", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedCustomer == null) {
                    JOptionPane.showMessageDialog(fReactivate, "No customer selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedCustomer.setActive(true);
                    activeModel.addElement(selectedCustomer);
                    inactiveModel.removeElement(selectedCustomer);
                    selectedCustomer = null;
                    activeCustomerListDisplay.clearSelection();
                }
    }


    //User deactivate
    private JList<Customer> inactiveCustomerListDisplay;
    public void deactivate (Customer customer, DefaultListModel<Customer> active, DefaultListModel<Customer> inactive, JList<Customer> display) {
        fDeactivate = new JFrame();
        selectedCustomer = customer;
        activeModel = active;
        inactiveModel = inactive;
        inactiveCustomerListDisplay = display;

        if(!selectedCustomer.isActive()) {
                    JOptionPane.showMessageDialog(fDeactivate, "Selected customer should be Active", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedCustomer == null) {
                    JOptionPane.showMessageDialog(fDeactivate, "No customer selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedCustomer.setActive(false);
                    inactiveModel.addElement(selectedCustomer);
                    activeModel.removeElement(selectedCustomer);
                    selectedCustomer = null;
                    inactiveCustomerListDisplay.clearSelection();
                }
    }


    //Buttons
    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addBtnOk) {
                addUser();
            } else if (e.getSource() == editBtnOk) {
                editUser();
            } else if (e.getSource() == addBtnCancel) {
                fAdd.dispose();
            } else if (e.getSource() == editBtnCancel) {
                fEdit.dispose();
            } else if (e.getSource() == btnYes) {
                if(selectedCustomer != null) {
                    if(CustomerManagementScreen.inactiveModel.contains(selectedCustomer)) {
                        CustomerManagementScreen.inactiveModel.removeElement(selectedCustomer);
                    } else {
                        CustomerManagementScreen.activeModel.removeElement(selectedCustomer);
                    }
                }
                fDelete.dispose();
            } 
            else if (e.getSource() == btnNo) {
                fDelete.dispose();
            }
        }
    }
}
