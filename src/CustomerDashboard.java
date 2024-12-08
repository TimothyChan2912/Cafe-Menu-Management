import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import javax.swing.text.*;

public class CustomerDashboard extends JFrame {
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 1000;

	private UserManager userManager;
	private User currentUser; // The user who is currently logged in
	MenuManager menuManager = new MenuManager();

	private JTextPane cartPane;
	private JTextPane billPane;

	private StyledDocument cartDoc;
	private StyledDocument billDoc;

 	private JList<MenuItem> menuListDisplay;
    private static DefaultListModel<MenuItem> menuModel;

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
		System.out.println("Initializing CustomerDashboard for user: " + currentUser.getUserName());

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
		btnBack = new JButton("Back");

		sortOrderDropDown = new JComboBox<String>();
        sortByDropDown = new JComboBox<String>();

        sortOrderDropDown.addItem("Ascending");
        sortOrderDropDown.addItem("Descending");
		sortByDropDown.addItem("Title");
		sortByDropDown.addItem("ID");
		sortByDropDown.addItem("Description");
        sortByDropDown.addItem("Price");

		noTipButton = new JRadioButton("No Tip");
		tenPercentButton = new JRadioButton("10% Tip");
		fifteenPercentButton = new JRadioButton("15% Tip");
		twentyPercentButton = new JRadioButton("20% Tip");

		searchField = new JTextField(10);
        	

		breakfastCheckbox = new JCheckBox("Breakfast/Lunch");
		dinnerCheckbox = new JCheckBox("Dinner");

		BtnListener btnlistener = new BtnListener();
        btnAddToCart.addActionListener(btnlistener);
        btnCancelOrder.addActionListener(btnlistener);
        btnPlaceOrder.addActionListener(btnlistener);
        btnSort.addActionListener(btnlistener);
		btnSearch.addActionListener(btnlistener);
		btnLogout.addActionListener(btnlistener);

		// PANES AND DOCS
		cartDoc = new DefaultStyledDocument();
		billDoc = new DefaultStyledDocument();
		menuModel = new DefaultListModel<MenuItem>();	
		cartPane = new JTextPane(cartDoc);
		billPane = new JTextPane(billDoc);

		JScrollPane spMenu = new JScrollPane(menuModel);
		menuListDisplay = new JList<MenuItem>(menuModel);
		for(MenuItem menuItem : menuManager.menuList) {
			if(menuItem.isCurrent()) {
                menuModel.addElement(menuItem);
            }
        }

		JPanel pCustomerDashboard = new JPanel();
        pCustomerDashboard.setLayout(layout);

    	gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;

		JPanel pCheckboxes = new JPanel();
		pCheckboxes.setLayout(new GridLayout(0, 2));
		pCheckboxes.add(breakfastCheckbox);
		pCheckboxes.add(dinnerCheckbox);

		JPanel pUserInfo = new JPanel();
		pUserInfo.setLayout(new GridLayout(0, 2));
		pUserInfo.add(userInfo);
		pUserInfo.add(btnLogout);
		//pUserInfo.add(btnBack);

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

		gbc.insets = new Insets(0, 0, 0, 10);
		a.addObjects(pCheckboxes, pCustomerDashboard, layout, gbc, 0, 0, 1, 1, 0, 10);
		a.addObjects(cartLabel, pCustomerDashboard, layout, gbc, 0, 1, 1, 1, 200, 50);
		a.addObjects(cartPane, pCustomerDashboard, layout, gbc, 0, 2, 1, 1, 200, 150);
		a.addObjects(billLabel, pCustomerDashboard, layout, gbc, 0, 3, 1, 1, 0, 50);
		a.addObjects(billPane, pCustomerDashboard, layout, gbc, 0, 4, 1, 1, 200, 150);
		a.addObjects(pTip, pCustomerDashboard, layout, gbc, 0, 5, 1, 1, 0, 10);
		a.addObjects(pActionButtons, pCustomerDashboard, layout, gbc, 0, 6, 1, 1, 0, 10);

		gbc.insets = new Insets(0, 10, 0, 0);
		a.addObjects(pUserInfo, pCustomerDashboard, layout, gbc, 1, 0, 1, 1, 0, 10);
		a.addObjects(menuLabel, pCustomerDashboard, layout, gbc, 1, 1, 1, 1,200, 50);
		a.addObjects(spMenu, pCustomerDashboard, layout, gbc, 1, 2, 1, 4,200,425);  // replace this one with List and DefaultListModel
		a.addObjects(btnAddToCart, pCustomerDashboard, layout, gbc, 1, 6, 1, 1, 0, 10);

		gbc.insets = new Insets(0, 0, 0, 0);
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
				addToCart();
			}
			else if (e.getSource() == btnCancelOrder) {
				// Cancel the order
			}
			else if (e.getSource() == btnPlaceOrder) {
				// Place the order
			}
			else if (e.getSource() == btnSort) {
				menuSort();
			}
			else if (e.getSource() == btnSearch) {
				// Search for menu items
			}
		}
	}

	//Add to cart
	public void addToCart() {
		MenuItem selectedMenuItem = menuListDisplay.getSelectedValue();

		menuModel.removeElement(selectedMenuItem);
		try {
			cartDoc.insertString(cartDoc.getLength(), selectedMenuItem.toString() + "\n", null);	
		} 
		catch (Exception e) {
			System.out.println("Error adding to cart");
		}
	}

	//Cancel order
	public void cancelOrder() {
		String selected = cartPane.getSelectedText();
	}

	public void menuSort() {
        String compareFilter = sortByDropDown.getSelectedItem().toString();
        Customer.CompareBy compare;
        
        if(compareFilter.equals("First Name")) {
            compare = Customer.CompareBy.FIRST_NAME;
        }
        else if(compareFilter.equals("Last Name")) {
            compare = Customer.CompareBy.LAST_NAME;
        }
        else if(compareFilter.equals("Email")) {
            compare = Customer.CompareBy.EMAIL;
        }
        else {
            compare = Customer.CompareBy.USERNAME;
        }

        Customer.setCompareBy(compare, sortByDropDown.getSelectedItem().toString().equals("Ascending"));

            List<MenuItem> menuList = Collections.list(menuModel.elements());

            Collections.sort(menuList);

            menuModel.clear();

            for(MenuItem menuItem : menuList) {
                menuModel.addElement(menuItem);
            }
    }
}
