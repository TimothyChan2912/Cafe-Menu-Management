import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerManagementScreen extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;

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

    private JList<Customer> inactiveCustomerListDisplay;
    private JList<Customer> activeCustomerListDisplay;

    public static DefaultListModel<Customer> inactiveModel;
    public static DefaultListModel<Customer> activeModel;

    private JScrollPane inactiveScrollPane;
    private JScrollPane activeScrollPane;
    private Customer selectedCustomer;

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

        inactiveModel = new DefaultListModel<Customer>();
        activeModel = new DefaultListModel<Customer>();

        inactiveCustomerListDisplay = new JList<Customer>(inactiveModel);
        activeCustomerListDisplay = new JList<Customer>(activeModel);

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

        gbc.insets = new Insets(20, 0, 0, 0);
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

        Customer.setCompareBy(compare, filterOrder.getSelectedItem().toString().equals("Ascending"));

            List<Customer> inactiveList = Collections.list(inactiveModel.elements());
            List<Customer> activeList = Collections.list(activeModel.elements());

            Collections.sort(inactiveList);
            Collections.sort(activeList);

            inactiveModel.clear();
            activeModel.clear();

            for(Customer customer : inactiveList) {
                inactiveModel.addElement(customer);
            }

            for(Customer customer : activeList) {
                activeModel.addElement(customer);
            }
    }

    public void customerSearch() {
        String regex = searchField.getText();

        if(regex.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a search term", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cri = filterCriteria.getSelectedItem().toString();
        Pattern pattern = null;

        try {
            pattern = Pattern.compile(regex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid search term", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Customer> inactiveList = Collections.list(inactiveModel.elements());
        List<Customer> activeList = Collections.list(activeModel.elements());

        inactiveModel.clear();
        activeModel.clear();

        for(Customer customer : extractHelper(pattern, cri, inactiveList)) {
            inactiveModel.addElement(customer);
        }

        for(Customer customer : extractHelper(pattern, cri, activeList)) {
            activeModel.addElement(customer);
        }
    }

    private List<Customer> extractHelper(Pattern pattern, String field, List<Customer> list) {
        List<Customer> result = new ArrayList<Customer>();

        for(Customer customer: list) {
            String name = customer.toString();
            if(field.equals("First Name")) {
                name = customer.getFirstName();
            }
            else if(field.equals("Last Name")) {
                name = customer.getLastName();
            }
            else if(field.equals("Email")) {
                name = customer.getEmail();
            }
            else if(field.equals("Username")) {
                name = customer.getUserName();
            }

            if(pattern.matcher(name).find()) {
                result.add(customer);
            }
        }
        return result;
    }
}
