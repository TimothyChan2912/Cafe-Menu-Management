import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;

public class SignupScreen extends JFrame{
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;

    private JLabel signupFirstName;
    private JLabel signupLastName;
    private JLabel signupEmail;
    private JLabel signupPassword;
    private JLabel signupRole;

    private static JTextField signupFirstNameText;
    private static JTextField signupLastNameText;
    private static JTextField signupEmailText;
    private static JPasswordField signupPasswordText;
    private static JComboBox<String> signupRoleText;
    
    private JButton btnSignupSubmit;
    private JButton btnSignupCancel;

	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	public static ArrayList<Admin> admins = new ArrayList<Admin>();

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

	private String username;

    public SignupScreen() {
        btnSignupSubmit = new JButton("Submit");
        btnSignupCancel = new JButton("Cancel");

        Btnlistener btnlistener = new Btnlistener();
        btnSignupSubmit.addActionListener(btnlistener);
        btnSignupCancel.addActionListener(btnlistener);

        signupFirstName = new JLabel("First Name");
        signupLastName = new JLabel("Last Name");
        signupEmail = new JLabel("Email");
        signupPassword = new JLabel("Password");
        signupRole = new JLabel("Role");

        signupFirstNameText = new JTextField("");
        signupLastNameText = new JTextField("");
        signupEmailText = new JTextField("");
        signupPasswordText = new JPasswordField("");
        signupRoleText = new JComboBox<String>();

        ImagePanel pSignup = new ImagePanel("src/resources/signupScreen.jpg");
        pSignup.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(160, 0, 0, 0);
        a.addObjects(signupFirstName, pSignup, layout, gbc, 0, 0, 1, 1, 50, 10);
        gbc.insets = new Insets(10, 0, 0, 0);
        a.addObjects(signupFirstNameText, pSignup, layout, gbc, 0, 1, 4, 1, 250, 10);

        a.addObjects(signupLastName, pSignup, layout, gbc, 0, 2, 1, 1, 50, 10);
        a.addObjects(signupLastNameText, pSignup, layout, gbc, 0, 3, 4, 1, 250, 10);

        a.addObjects(signupEmail, pSignup, layout, gbc, 0, 4, 1, 1, 50, 10);
        a.addObjects(signupEmailText, pSignup, layout, gbc, 0, 5, 4, 1, 250, 10);

        a.addObjects(signupPassword, pSignup, layout, gbc, 0, 6, 1, 1, 50, 10);
        a.addObjects(signupPasswordText, pSignup, layout, gbc, 0, 7, 4, 1, 250, 10);

        a.addObjects(signupRole, pSignup, layout, gbc, 0, 8, 1, 1, 50, 25);
		a.addObjects(signupRoleText, pSignup, layout, gbc, 0, 9, 4, 1, 250, 25);
        signupRoleText.addItem("Customer");
        signupRoleText.addItem("Admin");

        gbc.insets = new Insets(35, 0, 0, 0);
        a.addObjects(btnSignupSubmit, pSignup, layout, gbc, 0, 10, 2, 1, 0, 25);
        a.addObjects(btnSignupCancel, pSignup, layout, gbc, 2, 10, 2, 1, 0, 25);

        this.add(pSignup);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
    }

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<Admin> getAdmins() {
		return admins;
	}

	public String getFirstName() {
		return signupFirstNameText.getText();
	}

	public String getLastName() {
		return signupLastNameText.getText();
	}

	public String getEmail() {
		return signupEmailText.getText();
	}

	public String getPassword() {
		return new String(signupPasswordText.getPassword());
	}

	public String getRole() {
		return (String) signupRoleText.getSelectedItem();
	}

	private void SignupClick() {
		String firstname = getFirstName();

		String rand = "";
		for(int i = 0; i < 4; i++) {
			rand += (int)(Math.random() * 10);
		}

		username = firstname + rand;
	}

    class Btnlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnSignupSubmit) {
				SignupClick();
				if(getRole().equals("Customer")) {
					customers.add(new Customer(getFirstName(), getLastName(), getEmail(), username, getPassword(), true));
					new SignupSuccess(username);
                    dispose();
				}
				else {
					admins.add(new Admin(getFirstName(), getLastName(), getEmail(), username, getPassword(), true));
                    new SignupSuccess(username);
                    dispose();
				}
            }
            if(e.getSource() == btnSignupCancel) {
                new CafeOnlineOrderSystemGUI();
                dispose();
            }
        }
    }
}
