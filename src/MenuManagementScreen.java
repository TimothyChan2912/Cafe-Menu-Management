import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class MenuManagementScreen extends JFrame {
		private final int FRAME_WIDTH = 1000;
		private final int FRAME_HEIGHT = 1000;

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

		private JList<String> inactiveMenuListDisplay;
    	private JList<String> activeMenuListDisplay;

    	public ArrayList<String> activeMenuList;
    	public ArrayList<String> inactiveMenuList;

		public MenuManagementScreen(Admin admin) {
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


			JPanel pAdminMenuManager = new JPanel();
        	pAdminMenuManager.setLayout(layout);

        	gbc.fill = GridBagConstraints.HORIZONTAL;

			a.addObjects(breakfastLunch, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(dinner, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(adminInfo, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnLogout, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(backupMenu, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(currentMenu, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(inactiveMenuListDisplay, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(activeMenuListDisplay, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnReactivate, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnDeactivate, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnAdd, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnEdit, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnDelete, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(sortOrder, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(sortOrderDropDown, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(searchSortBy, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(sortByDropDown, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnSort, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(searchField, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
			a.addObjects(btnSearch, pAdminMenuManager, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);

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
            }
        }
    }
}
