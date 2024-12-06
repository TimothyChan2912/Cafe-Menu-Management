import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;


public class AdminCustomerManagerAdd extends JFrame {
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

    public AdminCustomerManagerAdd() {
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
        this.setTitle("Enter User Details");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnOk) {
                // Add user to database
            } else if (e.getSource() == btnCancel) {
                dispose();
            }
        }
    }

    public void addUser() {
        String userType = userTypeList.getSelectedItem().toString();
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String email = emailText.getText();
        String password = passwordText.getText();
        boolean isActive = statusCheckActive.isSelected();

        if (isActive) {
            // Add user to database
        } else {
            // Add user to database   
        }  
    }
}
