import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuManagementScreen extends JFrame {
		private final int FRAME_WIDTH = 1000;
		private final int FRAME_HEIGHT = 1000;

		private Admin admin;

		private JLabel backupMenu;
		private JLabel currentMenu;
		private JLabel adminInfo;
		private JLabel sortOrder;
		private JLabel searchSortBy;

		private JButton btnAdd;
		private JButton btnEdit;
		private JButton btnDelete;
		private JButton btnReactivate;
		private JButton btnDeactivate;
		private JButton btnLogout;
		private JButton btnBack;
		private JButton btnSort;
		private JButton btnSearch;

		private JComboBox<String> sortOrderDropDown;
		private JComboBox<String> sortByDropDown;

		private JCheckBox breakfastLunch;
		private JCheckBox dinner;

		private JTextField searchField;

		private GridBagLayout layout = new GridBagLayout();
    	private GridBagConstraints gbc = new GridBagConstraints();
    	private AddObjects a = new AddObjects();
		MenuManager menuManager = new MenuManager();

		private JList<MenuItem> inactiveMenuListDisplay;
    	private JList<MenuItem> activeMenuListDisplay;

    	public static DefaultListModel<MenuItem> inactiveModel;
    	public static DefaultListModel<MenuItem> activeModel;

		private JScrollPane inactiveScrollPane;
		private JScrollPane activeScrollPane;
		private MenuItem selectedItem;

		public MenuManagementScreen(Admin admin) {
			this.admin = admin;
			adminInfo = new JLabel(admin.getFirstName() + " " + admin.getLastName() + ": " + admin.getUserName());
			backupMenu = new JLabel("Backup (Off-season) Menu:");
			currentMenu = new JLabel("Current Menu:");
			sortOrder = new JLabel("Sort Order:");
			searchSortBy = new JLabel("Search/Sort By:");
			
			btnAdd = new JButton("Add");
			btnEdit = new JButton("Edit");
			btnDelete = new JButton("Delete");
			btnReactivate = new JButton("Re-activate");
			btnDeactivate = new JButton("Deactivate");
			btnBack = new JButton("Back");
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
        	

			breakfastLunch = new JCheckBox("Breakfast/Lunch");
			dinner = new JCheckBox("Dinner");

			BtnListener btnlistener = new BtnListener();
        	btnReactivate.addActionListener(btnlistener);
        	btnDeactivate.addActionListener(btnlistener);
        	btnAdd.addActionListener(btnlistener);
        	btnEdit.addActionListener(btnlistener);
        	btnDelete.addActionListener(btnlistener);
        	btnSort.addActionListener(btnlistener);
			btnSearch.addActionListener(btnlistener);
			btnLogout.addActionListener(btnlistener);

			searchField = new JTextField(10);

			inactiveModel = new DefaultListModel<MenuItem>();
			activeModel = new DefaultListModel<MenuItem>();

			inactiveMenuListDisplay = new JList<MenuItem>(inactiveModel);
			activeMenuListDisplay = new JList<MenuItem>(activeModel);

			inactiveMenuListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			inactiveMenuListDisplay.addListSelectionListener(e -> {
				if(!e.getValueIsAdjusting()) {
					if(inactiveMenuListDisplay != null) {
						selectedItem = inactiveMenuListDisplay.getSelectedValue();
						activeMenuListDisplay.clearSelection();
					}
				}
			});

			activeMenuListDisplay.addListSelectionListener(e -> {
				if(!e.getValueIsAdjusting()) {
					if(activeMenuListDisplay != null) {
						selectedItem = activeMenuListDisplay.getSelectedValue();
						inactiveMenuListDisplay.clearSelection();
					}
				}
			});

			inactiveScrollPane = new JScrollPane(inactiveMenuListDisplay);
			activeScrollPane = new JScrollPane(activeMenuListDisplay);

			backupMenu.setFont(backupMenu.getFont().deriveFont(20f).deriveFont(Font.BOLD));
			currentMenu.setFont(currentMenu.getFont().deriveFont(20f).deriveFont(Font.BOLD));



			ImagePanel pAdminMenuManager = new ImagePanel("src/resources/manageMenuScreen.jpg");
        	pAdminMenuManager.setLayout(layout);

        	gbc.fill = GridBagConstraints.HORIZONTAL;

			JPanel pCheckboxes = new JPanel();
			pCheckboxes.setLayout(new GridLayout(0, 2));
			pCheckboxes.add(breakfastLunch);
			pCheckboxes.add(dinner);
			pCheckboxes.setOpaque(false);

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
			pSearchSort.add(sortOrderDropDown);
			pSearchSort.add(searchSortBy);
			pSearchSort.add(sortByDropDown);
			pSearchSort.add(btnSort);
			pSearchSort.add(searchField);
			pSearchSort.add(btnSearch);
			pSearchSort.setOpaque(false);

			gbc.insets = new Insets(30, 0, 0, 0);
			a.addObjects(pCheckboxes, pAdminMenuManager, layout, gbc, 0, 0, 1, 1, 0, 10);
			a.addObjects(pAdminInfo, pAdminMenuManager, layout, gbc, 1, 0, 1, 1, 0, 10);
			gbc.insets = new Insets(0, 0, 0, 0);
			a.addObjects(backupMenu, pAdminMenuManager, layout, gbc, 0, 1, 1, 1, 200, 50);
			a.addObjects(currentMenu, pAdminMenuManager, layout, gbc, 1, 1, 1, 1,200, 50);
			a.addObjects(inactiveScrollPane, pAdminMenuManager, layout, gbc, 0, 2, 1, 1, 200, 200);
			a.addObjects(activeScrollPane, pAdminMenuManager, layout, gbc, 1, 2, 1, 1,200,200);
			a.addObjects(btnReactivate, pAdminMenuManager, layout, gbc, 0, 3, 1, 1, 0, 10);
			a.addObjects(btnDeactivate, pAdminMenuManager, layout, gbc, 1, 3, 1, 1, 0, 10);
			a.addObjects(pItemControls, pAdminMenuManager, layout, gbc, 0, 4, 2, 1, 0, 10);
			a.addObjects(pSearchSort, pAdminMenuManager, layout, gbc, 0, 5, 2, 1, 0 ,10);

			this.add(pAdminMenuManager);
        	this.setTitle("Menu Management Dashboard");
        	this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	this.setVisible(true);
		}


		class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == btnReactivate) {
                menuManager.reactivate();
            } else if(e.getSource() == btnDeactivate) {
                menuManager.deactivate();
            } else if(e.getSource() == btnAdd) {
                menuManager.add();
            } else if(e.getSource() == btnEdit) {
                menuManager.edit();
            } else if(e.getSource() == btnDelete) {
                menuManager.delete();
            } else if(e.getSource() == btnSort) {
                // Sort customers
            } else if (e.getSource() == btnLogout) {
                // Logout
            } else if(e.getSource() == btnSearch) {
                // Search customers
            } else if (e.getSource() == btnBack) {
				new AdminDashboard(admin);
				dispose();
			}
        }
    }
}
