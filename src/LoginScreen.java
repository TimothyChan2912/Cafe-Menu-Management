import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class LoginScreen extends JDialog {
	
	private Map<String, User> users;

    public LoginScreen(JFrame parent, Map<String, User> users) {
        super(parent, "Login", true);
        UserManager userManager = new UserManager();
        setLayout(new BorderLayout());
        setSize(300, 300);
        setLocationRelativeTo(parent);

	// xxx your codes
    }
}
