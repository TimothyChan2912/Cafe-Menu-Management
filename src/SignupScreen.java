import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.awt.event.*;
import javafx.scene.control.*;
import java.awt.event.ActionListener;



public class SignupScreen extends JDialog {

	private CafeOnlineOrderSystemGUI mainGUI;
    private cafe mycafe;

   	private final int FRAME_WIDTH = 1000;
	private final int FRAME_LENGTH = 1000;

	private JLabel signupFirstName;
	private JLabel signupLastName;
	private JLabel signupEmail;
	private JLabel signupPassword;
	private JLabel userType;

	private JButton btnSignupSubmit;

	public static JTextField signupFirstNameText;
	public static JTextField signupLastNameText;
	public static JTextField signupEmailText;
	public static JTextField signupPasswordText;

	
	// ToggleGroup userTypeGroup = new ToggleGroup();
	// private RadioButton userTypeCustomer = new RadioButton("Customer");
	// private RadioButton userTypeAdministrator = new RadioButton("Administrator");
	// userTypeCustomer.setToggleGroup(userTypeGroup);
	// userTypeAdministrator.setToggleGroup(userTypeGroup);
	// userTypeCustomer.setSelected(true);

	public SignupScreen() {
		signupFirstName = new JLabel("First Name: ");
		signupLastName = new JLabel("Last Name: ");
		signupEmail = new JLabel("Email: ");
		signupPassword = new JLabel("Password: ");
		
		BtnListener btnlistnener = new BtnListener();
		btnSignupSubmit.addActionListener(btnlistnener);
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
	
	public  String getPassword() {
		return signupPasswordText.getText();
	}

	class BtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSignupSubmit) {
				//password submit method
			}
		}
	}

}	
