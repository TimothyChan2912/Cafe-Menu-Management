import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCustomerManager extends JFrame {
    private final int FRAME_WIDTH = 1000;
    private final int FRAME_HEIGHT = 1000;

    private JLabel adminInfo;
    private JLabel inactiveCustomers;
    private JLabel activeCustomers;
    private JLabel sortOrder;
    private JLabel SearchBy;


    private JButton btnReactivate;
    private JButton btnInactivate;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnSort;

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
    
    public AdminCustomerManager(Admin admin) {    
        btnReactivate = new JButton("Re-activate");
        btnInactivate = new JButton("Inactivate");
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnSort = new JButton("Sort");

        adminInfo = new JLabel(admin.getFirstName() + " " + admin.getLastName() + ": " + admin.getUserName());
        inactiveCustomers = new JLabel("Cafe Inactive Customers:");
        activeCustomers = new JLabel("Cafe Active Customers:");
        sortOrder = new JLabel("Sort Order:");
        SearchBy = new JLabel("Search/Sort By:");

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

        JPanel pAdminCustomerManager = new JPanel();
        pAdminCustomerManager.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(adminInfo, pAdminCustomerManager, layout, gbc, 0, 0, 1, 1, 50, 50);
        a.addObjects(inactiveCustomers, pAdminCustomerManager, layout, gbc, 0, 1, 1, 1, 50, 50);
        a.addObjects(activeCustomers, pAdminCustomerManager, layout, gbc, 1, 1, 1, 1, 50, 50);
        a.addObjects(inactiveScrollPane, pAdminCustomerManager, layout, gbc, 0, 2, 1, 1, 50, 50);
        a.addObjects(activeScrollPane, pAdminCustomerManager, layout, gbc, 1, 2, 1, 1, 50, 50);
        a.addObjects(btnReactivate, pAdminCustomerManager, layout, gbc, 0, 3, 1, 1, 50, 25);
        a.addObjects(btnInactivate, pAdminCustomerManager, layout, gbc, 1, 3, 1, 1, 50, 25);
        a.addObjects(btnAdd, pAdminCustomerManager, layout, gbc, 0, 4, 1, 1, 50, 25);
        a.addObjects(btnEdit, pAdminCustomerManager, layout, gbc, 1, 4, 1, 1, 50, 25);
        a.addObjects(btnDelete, pAdminCustomerManager, layout, gbc, 2, 4, 1, 1, 50, 25);
        a.addObjects(SearchBy, pAdminCustomerManager, layout, gbc, 2, 5, 1, 1, 50, 50);
        a.addObjects(filterCriteria, pAdminCustomerManager, layout, gbc, 3, 5, 1, 1, 50, 25);
        a.addObjects(sortOrder, pAdminCustomerManager, layout, gbc, 0, 5, 1, 1, 50, 50);
        a.addObjects(filterOrder, pAdminCustomerManager, layout, gbc, 1, 5, 1, 1, 50, 25);
        a.addObjects(btnSort, pAdminCustomerManager, layout, gbc, 4, 5, 1, 1, 50, 25);

        this.add(pAdminCustomerManager);
        this.setTitle("Customer Management Dashboard");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnReactivate) {
                if(selectedCustomer.isActive()) {
                    JOptionPane.showMessageDialog(frame, "Selected customer should be Inactive", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedCustomer == null) {
                    JOptionPane.showMessageDialog(frame, "No customer selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedCustomer.setActive(true);
                    activeModel.addElement(selectedCustomer);
                    inactiveModel.removeElement(selectedCustomer);
                    selectedCustomer = null;
                    activeCustomerListDisplay.clearSelection();
                }
            }
            else if(e.getSource() == btnInactivate) {
                if(!selectedCustomer.isActive()) {
                    JOptionPane.showMessageDialog(frame, "Selected customer should be Active", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedCustomer == null) {
                    JOptionPane.showMessageDialog(frame, "No customer selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedCustomer.setActive(false);
                    inactiveModel.addElement(selectedCustomer);
                    activeModel.removeElement(selectedCustomer);
                    selectedCustomer = null;
                    inactiveCustomerListDisplay.clearSelection();
                }
            } 
            else if(e.getSource() == btnAdd) {
                new AdminCustomerManagerAdd();
            } 
            else if(e.getSource() == btnEdit) {
                new AdminCustomerManagerEdit(selectedCustomer, selectedCustomer.isActive());
            } 
            else if(e.getSource() == btnDelete) {
                if(selectedCustomer != null) {
                    if(inactiveModel.contains(selectedCustomer)) {
                        inactiveModel.removeElement(selectedCustomer);
                    } else {
                        activeModel.removeElement(selectedCustomer);
                    }
                }
            } 
            else if(e.getSource() == btnSort) {
                // Sort customers
            }
        }
    }


}
