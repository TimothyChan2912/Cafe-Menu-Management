import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.*;

public class CustomerDashboard extends JFrame {
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 1000;

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

	private ButtonGroup tipGroup;
	private JRadioButton noTipButton;
	private JRadioButton tenPercentButton;
	private JRadioButton fifteenPercentButton;
	private JRadioButton twentyPercentButton;

	private JButton btnLogout;
	private JButton btnBack;
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

		cartPane = new JTextPane(cartDoc);
		billPane = new JTextPane(billDoc);
		menuPane = new JTextPane(menuDoc);

		// Set up panes / docs

		JPanel pCustomerDashboard = new JPanel();
        pCustomerDashboard.setLayout(layout);

    	gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel pCheckboxes = new JPanel();
		pCheckboxes.setLayout(new GridLayout(0, 2));
		pCheckboxes.add(breakfastCheckbox);
		pCheckboxes.add(dinnerCheckbox);

		JPanel pUserInfo = new JPanel();
		pUserInfo.setLayout(new GridLayout(0, 3));
		pUserInfo.add(userInfo);
		pUserInfo.add(btnLogout);
		pUserInfo.add(btnBack);

		tipGroup = new ButtonGroup();
		tipGroup.add(noTipButton);
		tipGroup.add(tenPercentButton);
		tipGroup.add(fifteenPercentButton);
		tipGroup.add(twentyPercentButton);
		JPanel pTip = new JPanel();
		pTip.setLayout(new FlowLayout());
		pTip.add(noTipButton);
		pTip.add(tenPercentButton);
		pTip.add(fifteenPercentButton);
		pTip.add(twentyPercentButton);
		
		JPanel pActionButtons = new JPanel();
		pActionButtons.setLayout(new GridLayout(0, 2));
		pActionButtons.add(btnPlaceOrder);
		pActionButtons.add(btnCancelOrder);

		JPanel pSearchSort = new JPanel();
		pSearchSort.setLayout(new FlowLayout());
		pSearchSort.add(sortOrder);
		pSearchSort.add(sortOrderDropDown);
		pSearchSort.add(searchSortBy);
		pSearchSort.add(sortByDropDown);
		pSearchSort.add(btnSort);
		pSearchSort.add(searchField);
		pSearchSort.add(btnSearch);

		a.addObjects(pCheckboxes, pCustomerDashboard, layout, gbc, 0, 0, 1, 1, 0, 10);
		a.addObjects(pUserInfo, pCustomerDashboard, layout, gbc, 1, 0, 1, 1, 0, 10);
		a.addObjects(cartLabel, pCustomerDashboard, layout, gbc, 0, 1, 1, 1, 200, 50);
		a.addObjects(menuLabel, pCustomerDashboard, layout, gbc, 1, 1, 1, 1,200, 50);
		a.addObjects(cartPane, pCustomerDashboard, layout, gbc, 0, 2, 1, 1, 200, 200);
		a.addObjects(menuPane, pCustomerDashboard, layout, gbc, 1, 2, 1, 4,200,200);
		a.addObjects(billLabel, pCustomerDashboard, layout, gbc, 0, 3, 1, 1, 0, 10);
		a.addObjects(billPane, pCustomerDashboard, layout, gbc, 0, 4, 1, 1, 0, 10);
		a.addObjects(pTip, pCustomerDashboard, layout, gbc, 0, 5, 1, 1, 0, 10);
		a.addObjects(pActionButtons, pCustomerDashboard, layout, gbc, 0, 6, 1, 1, 0, 10);
		a.addObjects(btnAddToCart, pCustomerDashboard, layout, gbc, 1, 6, 1, 1, 0, 10);
		a.addObjects(pSearchSort, pCustomerDashboard, layout, gbc, 0, 7, 2, 1, 0 ,10);


		this.add(pCustomerDashboard);
    	this.setTitle("Customer Dashboard");
    	this.setSize(FRAME_WIDTH, FRAME_HEIGHT); // Set appropriate size
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
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
