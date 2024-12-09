import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;

public class CustomerManagementScreen extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 800;

    private Admin admin;
    UserManager userManager = new UserManager();

    private JLabel adminInfo;
    private JLabel inactiveCustomers;
    private JLabel activeCustomers;
    private JLabel sortOrder;
    private JLabel searchBy;


    private JButton btnReactivate;
    private JButton btnInactivate;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnSort;
    private JButton btnLogout;
    private JButton btnBack;
    private JButton btnSearch;

    private JTextField searchField;

    private JComboBox<String> filterOrder;
    private JComboBox<String> filterCriteria;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

    private JList<User> inactiveCustomerListDisplay;
    private JList<User> activeCustomerListDisplay;

    public static DefaultListModel<User> inactiveModel;
    public static DefaultListModel<User> activeModel;

    private JScrollPane inactiveScrollPane;
    private JScrollPane activeScrollPane;
    private User selectedCustomer;

    private JFrame frame = new JFrame();
    
    public CustomerManagementScreen(Admin admin) { 
        this.admin = admin;
        
        btnReactivate = new JButton("Re-activate");
        btnInactivate = new JButton("Inactivate");
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnSort = new JButton("Sort");
        btnLogout = new JButton("Logout");
        btnBack = new JButton("Back");
        btnSearch = new JButton("Search");

        searchField = new JTextField(10);

        adminInfo = new JLabel(admin.getFirstName() + " " + admin.getLastName() + ": " + admin.getUserName());
        inactiveCustomers = new JLabel("Cafe Inactive Customers:");
        activeCustomers = new JLabel("Cafe Active Customers:");
        sortOrder = new JLabel("Sort Order:");
        searchBy = new JLabel("Search/Sort By:");

        filterOrder = new JComboBox<String>();
        filterCriteria = new JComboBox<String>();

        filterOrder.addItem("Ascending");
        filterOrder.addItem("Descending");

        filterCriteria.addItem("First Name");
        filterCriteria.addItem("Last Name");
        filterCriteria.addItem("Email");
        filterCriteria.addItem("Username");

        BtnListener btnlistener = new BtnListener();
        btnReactivate.addActionListener(btnlistener);
        btnInactivate.addActionListener(btnlistener);
        btnAdd.addActionListener(btnlistener);
        btnEdit.addActionListener(btnlistener);
        btnDelete.addActionListener(btnlistener);
        btnSort.addActionListener(btnlistener);
        btnLogout.addActionListener(btnlistener);
        btnBack.addActionListener(btnlistener);
        btnSearch.addActionListener(btnlistener);

        inactiveModel = new DefaultListModel<User>();
        activeModel = new DefaultListModel<User>();

        inactiveCustomerListDisplay = new JList<User>(inactiveModel);
        activeCustomerListDisplay = new JList<User>(activeModel);

        for(User user : userManager.userList) {
			if(user.isActive()) {
                activeModel.addElement(user);
            } else {
               	inactiveModel.addElement(user);
			}
        }

        inactiveCustomerListDisplay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					inactiveUserDisplayInfo(inactiveCustomerListDisplay.locationToIndex(e.getPoint()));
				}
			}
		});

        activeCustomerListDisplay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					activeUserDisplayInfo(activeCustomerListDisplay.locationToIndex(e.getPoint()));
				}
			}
		});
        

        inactiveCustomerListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        inactiveCustomerListDisplay.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                if(inactiveCustomerListDisplay != null) {
                    selectedCustomer = inactiveCustomerListDisplay.getSelectedValue();
                    activeCustomerListDisplay.clearSelection();
                }
            }
        });

        activeCustomerListDisplay.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) {
                if(activeCustomerListDisplay != null) {
                    selectedCustomer = activeCustomerListDisplay.getSelectedValue();
                    inactiveCustomerListDisplay.clearSelection();
                }
            }
        });

        inactiveScrollPane = new JScrollPane(inactiveCustomerListDisplay);
        activeScrollPane = new JScrollPane(activeCustomerListDisplay);

        inactiveCustomers.setFont(inactiveCustomers.getFont().deriveFont(20f).deriveFont(Font.BOLD));
        activeCustomers.setFont(activeCustomers.getFont().deriveFont(20f).deriveFont(Font.BOLD));

        ImagePanel pAdminCustomerManager = new ImagePanel("src/resources/manageUserScreen.jpg");
        pAdminCustomerManager.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

		JPanel pAdminInfo = new JPanel();
		pAdminInfo.setLayout(new GridLayout(0, 3));
		pAdminInfo.add(adminInfo);
		pAdminInfo.add(btnLogout);
		pAdminInfo.add(btnBack);
        pAdminInfo.setOpaque(false);

		JPanel pItemControls = new JPanel();
		pItemControls.setLayout(new GridLayout(0, 3));
		pItemControls.add(btnAdd);
		pItemControls.add(btnEdit);
		pItemControls.add(btnDelete);
        pItemControls.setOpaque(false);

		JPanel pSearchSort = new JPanel();
		pSearchSort.setLayout(new FlowLayout());
		pSearchSort.add(sortOrder);
		pSearchSort.add(filterOrder);
		pSearchSort.add(searchBy);
		pSearchSort.add(filterCriteria);
		pSearchSort.add(btnSort);
		pSearchSort.add(searchField);
		pSearchSort.add(btnSearch);
        pSearchSort.setOpaque(false);

        gbc.insets = new Insets(45, 0, 0, 0);
		a.addObjects(pAdminInfo, pAdminCustomerManager, layout, gbc, 1, 0, 1, 1, 0, 10);
        gbc.insets = new Insets(0, 0, 0, 0);
		a.addObjects(inactiveCustomers, pAdminCustomerManager, layout, gbc, 0, 1, 1, 1, 200, 50);
		a.addObjects(activeCustomers, pAdminCustomerManager, layout, gbc, 1, 1, 1, 1,200, 50);
		a.addObjects(inactiveScrollPane, pAdminCustomerManager, layout, gbc, 0, 2, 1, 1, 200, 200);
		a.addObjects(activeScrollPane, pAdminCustomerManager, layout, gbc, 1, 2, 1, 1,200,200);
		a.addObjects(btnReactivate, pAdminCustomerManager, layout, gbc, 0, 3, 1, 1, 0, 10);
		a.addObjects(btnInactivate, pAdminCustomerManager, layout, gbc, 1, 3, 1, 1, 0, 10);
		a.addObjects(pItemControls, pAdminCustomerManager   , layout, gbc, 0, 4, 2, 1, 0, 10);
		a.addObjects(pSearchSort, pAdminCustomerManager, layout, gbc, 0, 5, 2, 1, 0 ,10);

        this.add(pAdminCustomerManager);
        this.setTitle("Customer Management Dashboard");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnReactivate) {
                userManager.reactivate(selectedCustomer, activeModel, inactiveModel, activeCustomerListDisplay);
            }
            else if(e.getSource() == btnInactivate) {
                userManager.deactivate(selectedCustomer, activeModel, inactiveModel, inactiveCustomerListDisplay);
            } 
            else if(e.getSource() == btnAdd) {
                userManager.add();
            } 
            else if(e.getSource() == btnEdit) {
                userManager.edit(selectedCustomer, selectedCustomer.isActive());
            } 
            else if(e.getSource() == btnDelete) {
                userManager.delete(selectedCustomer);
            } 
            else if(e.getSource() == btnSort) {
                customerSort();
            } else if (e.getSource() == btnBack) {
                new AdminDashboard(admin);
                dispose();
            } else if (e.getSource() == btnLogout) {
                new CafeOnlineOrderSystemGUI();
                dispose();
            } else if (e.getSource() == btnSearch) {
                customerSearch();
            }
        }
    }

    public void customerSort() {
        String compareFilter = filterCriteria.getSelectedItem().toString();
        User.CompareBy compare;
        
        if(compareFilter.equals("Title")) {
            compare = User.CompareBy.FIRST_NAME;
        }
        else if(compareFilter.equals("ID")) {
            compare = User.CompareBy.LAST_NAME;
        }
        else if(compareFilter.equals("Description")) {
            compare = User.CompareBy.EMAIL;
        }
        else {
            compare = User.CompareBy.USERNAME;
        }

		Admin.setCompareBy(compare, filterOrder.getSelectedItem().toString().equals("Ascending"));
		Customer.setCompareBy(compare, filterOrder.getSelectedItem().toString().equals("Ascending"));

            List<User> activeList = Collections.list(activeModel.elements());
            List<User> inactiveList = Collections.list(inactiveModel.elements());

            Collections.sort(activeList);
            Collections.sort(inactiveList);

            activeModel.clear();
            inactiveModel.clear();

            for(User activeUser : activeList) {
                activeModel.addElement(activeUser);
            }
            for(User inactiveUser : inactiveList) {
                inactiveModel.addElement(inactiveUser);
            }
    }

    public ArrayList<User> extractActive(Pattern pattern, String field) {
        ArrayList<User> result = new ArrayList<>();
		System.out.println("Extracting" + Collections.list(activeModel.elements()).size());
        for(User user : Collections.list(activeModel.elements())) {
            String name = user.extractString();

            if(field.equals(UserFields.FIRST_NAME.toString())) {
                name = user.getFirstName();
            }
            else if(field.equals(UserFields.LAST_NAME.toString())) {
                name = user.getLastName();
            }
            else if(field.equals(UserFields.EMAIL.toString())) {
                name = user.getEmail();
            }
            else if(field.equals(UserFields.USERNAME.toString())) {
                name = user.getUserName();
            }
            if(pattern.matcher(name).matches()) {
                result.add(user);
            }  
        }
        return result;
    }

    public ArrayList<User> extractInactive(Pattern pattern, String field) {
        ArrayList<User> result = new ArrayList<>();
		System.out.println("Extracting" + Collections.list(inactiveModel.elements()).size());
        for(User user : Collections.list(inactiveModel.elements())) {
            String name = user.extractString();

            if(field.equals(UserFields.FIRST_NAME.toString())) {
                name = user.getFirstName();
            }
            else if(field.equals(UserFields.LAST_NAME.toString())) {
                name = user.getLastName();
            }
            else if(field.equals(UserFields.EMAIL.toString())) {
                name = user.getEmail();
            }
            else if(field.equals(UserFields.USERNAME.toString())) {
                name = user.getUserName();
            }
            if(pattern.matcher(name).matches()) {
                result.add(user);
            }  
        }
        return result;
    }
	
	public void customerSearch() {
		String regex = searchField.getText();
		if(regex.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter a search term");
			return;
		}

		String cri = (String)filterCriteria.getSelectedItem();
		Pattern pattern = null;

		try {
			pattern = Pattern.compile(regex);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Invalid search term");
			return;
		}

		ArrayList<User> resultActive = extractActive(pattern, cri);
        ArrayList<User> resultInactive = extractInactive(pattern, cri);

		activeModel.clear();
        inactiveModel.clear();

		for(User user : resultActive) {
			activeModel.addElement(user);
		}

        for(User user : resultInactive) {
            inactiveModel.addElement(user);
        }

	}


    public void activeUserDisplayInfo(int index) {
		User user = activeModel.getElementAt(index);
		String displayInfo = "Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
							"Username: " + user.getUserName() + "\n" +
							"Email: " + user.getEmail() + "\n" +
							"Active: true" + "\n" +
							"Password: " + user.getPassword() + "\n" + 
                            "Ordered Items: ";
        
        for (String item : user.getOrderedItems()) {
			displayInfo += item + ", ";
		}

		JOptionPane.showMessageDialog(null, displayInfo, "User Details", JOptionPane.INFORMATION_MESSAGE);
	}

    public void inactiveUserDisplayInfo(int index) {
		User user = inactiveModel.getElementAt(index);
		String displayInfo = "Name: " + user.getFirstName() + " " + user.getLastName() + "\n" +
							"Username: " + user.getUserName() + "\n" +
							"Email: " + user.getEmail() + "\n" +
							"Active: false" + "\n" +
							"Password: " + user.getPassword() + "\n" + 
                            "Ordered Items: ";
        
        for (String item : user.getOrderedItems()) {
			displayInfo += item + ", ";
		}

		JOptionPane.showMessageDialog(null, displayInfo, "User Details", JOptionPane.INFORMATION_MESSAGE);
	}
}
