import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDashboard extends JFrame {

	private UserManager userManager;
	private User currentUser; // The user who is currently logged in

	private JTextPane cartPane;
	private JTextPane billPane;
	private JTextPane menuPane;
	private StyledDocument cartDoc;
	private StyledDocument billDoc;
	private StyledDocument menuDoc;

	private JCheckBox breakfastCheckbox;
	private JCheckBox dinnerCheckbox;

	private JPanel tipPanel;
	private ButtonGroup tipGroup;
	private JRadioButton noTipButton;
	private JRadioButton tenPercentButton;
	private JRadioButton fifteenPercentButton;
	private JRadioButton twentyPercentButton;

	private JButton btnLogout;
	private JButton btnAddToCart;
	private JButton btnPlaceOrder;
	private JButton btnCancelOrder;
	private JButton btnSort;
	private JButton btnSearch;

	private JLabel cartLabel;
	private JLabel billLabel;
	private JLabel menuLabel;
	private JLabel userInfo;
	private JLabel sortOrder;
	private JLabel searchSortBy;

	private JComboBox<String> sortOrderDropDown;
	private JComboBox<String> sortByDropDown;
	private JTextField searchField;

	private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

   	public CustomerDashboard (User currentUser) {
		this.currentUser = currentUser;

		userInfo = new JLabel(currentUser.getFirstName() + " " + currentUser.getLastName() + ": " + currentUser.getUserName());
		cartLabel = new JLabel("Cart");
		billLabel = new JLabel("Bill");
		menuLabel = new JLabel("Cafe Menu");
		sortOrder = new JLabel("Sort Order:");
		searchSortBy = new JLabel("Search/Sort By:");
			
		btnAddToCart = new JButton("Add to Cart");
		btnCancelOrder = new JButton("Cancel");
		btnPlaceOrder = new JButton("Order");
		btnLogout = new JButton("Logout");
		btnSort = new JButton("Sort");
		btnSearch = new JButton("Search");

		sortOrderDropDown = new JComboBox<String>();
        sortByDropDown = new JComboBox<String>();

        sortOrderDropDown.addItem("Ascending");
        sortOrderDropDown.addItem("Descending");
		sortByDropDown.addItem("Title");
		sortByDropDown.addItem("ID");
		sortByDropDown.addItem("Description");
        sortByDropDown.addItem("Price");
		sortByDropDown.addItem("Count");
		sortByDropDown.addItem("Menu Type");
		sortByDropDown.addItem("Current");
		sortByDropDown.addItem("Available");
        	

		breakfastCheckbox = new JCheckBox("Breakfast/Lunch");
		dinnerCheckbox = new JCheckBox("Dinner");

		BtnListener btnlistener = new BtnListener();
        btnAddToCart.addActionListener(btnlistener);
        btnCancelOrder.addActionListener(btnlistener);
        btnPlaceOrder.addActionListener(btnlistener);
        btnSort.addActionListener(btnlistener);
		btnSearch.addActionListener(btnlistener);
		btnLogout.addActionListener(btnlistener);
	}

	class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogout) {
				new CafeOnlineOrderSystemGUI();
				dispose();
			}
			else if (e.getSource() == btnAddToCart) {
				// Add the selected item to the cart
			}
			else if (e.getSource() == btnCancelOrder) {
				// Cancel the order
			}
			else if (e.getSource() == btnPlaceOrder) {
				// Place the order
			}
			else if (e.getSource() == btnSort) {
				// Sort the menu items
			}
			else if (e.getSource() == btnSearch) {
				// Search for menu items
			}
		}
	}
}
