import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MenuManagementScreen extends JFrame {
		private final int FRAME_WIDTH = 1000;
		private final int FRAME_HEIGHT = 800;

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

		private JFrame frame = new JFrame();

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

			breakfastLunch = new JCheckBox("Breakfast/Lunch");
			dinner = new JCheckBox("Dinner");

			breakfastLunch.setSelected(true);
			dinner.setSelected(true);

			ItemListener itemListener = new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						inactiveModel.clear();
						activeModel.clear();
						if (breakfastLunch.isSelected() && dinner.isSelected()) {
							for (MenuItem menuItem : menuManager.menuList) {
								if (menuItem.isCurrent()) {
									activeModel.addElement(menuItem);
								} 
								else {
									inactiveModel.addElement(menuItem);
								}
							}
						} else if (breakfastLunch.isSelected()) {
							for (MenuItem menuItem : menuManager.menuList) {
								if (menuItem.getMenuType().equals("Pancake")) {
									if (menuItem.isCurrent()) {
										activeModel.addElement(menuItem);
									} 
									else {
										inactiveModel.addElement(menuItem);
									}
								}
							}
						} else if (dinner.isSelected()) {
							for (MenuItem menuItem : menuManager.menuList) {
								if (menuItem.getMenuType().equals("Diner")) {
									if (menuItem.isCurrent()) {
										activeModel.addElement(menuItem);
									} 
									else {
										inactiveModel.addElement(menuItem);
									}
								}
							}
						}
					} else if (e.getStateChange() == ItemEvent.DESELECTED) {
						activeModel.clear();
						inactiveModel.clear();
						if (!breakfastLunch.isSelected() && !dinner.isSelected()) {

						} else if (breakfastLunch.isSelected()) {
							for (MenuItem menuItem : menuManager.menuList) {
								if (menuItem.getMenuType().equals("Pancake")) {
									if (menuItem.isCurrent()) {
										activeModel.addElement(menuItem);
									} 
									else {
										inactiveModel.addElement(menuItem);
									}
								}
							}
						} else if (dinner.isSelected()) {
							for (MenuItem menuItem : menuManager.menuList) {
								if (menuItem.getMenuType().equals("Diner")) {
									if (menuItem.isCurrent()) {
										activeModel.addElement(menuItem);
									} 
									else {
										inactiveModel.addElement(menuItem);
									}
								}
							}
						}
					}
				}
			};

			breakfastLunch.addItemListener(itemListener);
			dinner.addItemListener(itemListener);

			BtnListener btnlistener = new BtnListener();
        	btnReactivate.addActionListener(btnlistener);
        	btnDeactivate.addActionListener(btnlistener);
        	btnAdd.addActionListener(btnlistener);
        	btnEdit.addActionListener(btnlistener);
        	btnDelete.addActionListener(btnlistener);
        	btnSort.addActionListener(btnlistener);
			btnSearch.addActionListener(btnlistener);
			btnLogout.addActionListener(btnlistener);
			btnBack.addActionListener(btnlistener);

			searchField = new JTextField(10);

			inactiveModel = new DefaultListModel<MenuItem>();
			activeModel = new DefaultListModel<MenuItem>();

			inactiveMenuListDisplay = new JList<MenuItem>(inactiveModel);
			activeMenuListDisplay = new JList<MenuItem>(activeModel);

			for(MenuItem menuItem : menuManager.menuList) {
				if(menuItem.isCurrent()) {
                    activeModel.addElement(menuItem);
                } else {
                	inactiveModel.addElement(menuItem);
				}
            }

			inactiveMenuListDisplay.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2) {
						offSeasonDisplayInfo(inactiveMenuListDisplay.locationToIndex(e.getPoint()));
					}
				}
			});

			activeMenuListDisplay.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2) {
						currentDisplayInfo(activeMenuListDisplay.locationToIndex(e.getPoint()));
					}
				}
			});


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

			gbc.insets = new Insets(45, 0, 0, 0);
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
			this.setResizable(false);
		}


		class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == btnReactivate) {
                menuManager.reactivate(selectedItem, activeModel, inactiveModel, activeMenuListDisplay);
            } else if(e.getSource() == btnDeactivate) {
                menuManager.deactivate(selectedItem, activeModel, inactiveModel, inactiveMenuListDisplay);
            } else if(e.getSource() == btnAdd) {
                menuManager.add();
            } else if(e.getSource() == btnEdit) {
                menuManager.edit(selectedItem, selectedItem.isCurrent());
            } else if(e.getSource() == btnDelete) {
                menuManager.delete(selectedItem);
            } else if(e.getSource() == btnSort) {
                menuSort();
            } else if (e.getSource() == btnLogout) {
                new CafeOnlineOrderSystemGUI();
				dispose();
            } else if(e.getSource() == btnSearch) {
                menuSearch();
            } else if (e.getSource() == btnBack) {
				new AdminDashboard(admin);
				dispose();
			}
        }
    }

	public void menuSort() {
        String compareFilter = sortByDropDown.getSelectedItem().toString();
        MenuItem.CompareBy compare;
        
        if(compareFilter.equals("Title")) {
            compare = MenuItem.CompareBy.TITLE;
        }
        else if(compareFilter.equals("ID")) {
            compare = MenuItem.CompareBy.ITEM_ID;
        }
        else if(compareFilter.equals("Description")) {
            compare = MenuItem.CompareBy.DESCRIPTION;
        }
        else {
            compare = MenuItem.CompareBy.PRICE;
        }

		DinerMenuItem.setCompareBy(compare, sortOrderDropDown.getSelectedItem().toString().equals("Ascending"));
		PancakeMenuItem.setCompareBy(compare, sortOrderDropDown.getSelectedItem().toString().equals("Ascending"));

            List<MenuItem> activeList = Collections.list(activeModel.elements());
            List<MenuItem> inactiveList = Collections.list(inactiveModel.elements());

            Collections.sort(activeList);
            Collections.sort(inactiveList);

            activeModel.clear();
            inactiveModel.clear();

            for(MenuItem activeItem : activeList) {
                activeModel.addElement(activeItem);
            }
            for(MenuItem inactiveItem : inactiveList) {
                inactiveModel.addElement(inactiveItem);
            }
    }

    public void menuSearch() {
        String regex = searchField.getText();

        if(regex.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a search term", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String cri = sortByDropDown.getSelectedItem().toString();
        Pattern pattern = null;

        try {
            pattern = Pattern.compile(regex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid search term", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<MenuItem> inactiveList = Collections.list(inactiveModel.elements());
        List<MenuItem> activeList = Collections.list(activeModel.elements());

        inactiveModel.clear();
        activeModel.clear();

        for(MenuItem item : extractHelper(pattern, cri, inactiveList)) {
            inactiveModel.addElement(item);
        }

        for(MenuItem item : extractHelper(pattern, cri, activeList)) {
            activeModel.addElement(item);
        }
    }

    private List<MenuItem> extractHelper(Pattern pattern, String field, List<MenuItem> list) {
        List<MenuItem> result = new ArrayList<MenuItem>();

        for(MenuItem item: list) {
            String name = item.toString();
            if(field.equals("Title")) {
                name = item.getTitle();
            }
            else if(field.equals("ID")) {
                name = item.getItemID();
            }
            else if(field.equals("Description")) {
                name = item.getDescription();
            }
            else if(field.equals("Price")) {
                name = Float.toString(item.getPrice());
            }

            if(pattern.matcher(name).find()) {
                result.add(item);
            }
        }
        return result;
    }

	public void currentDisplayInfo(int index) {
		MenuItem item = activeModel.getElementAt(index);
		String displayInfo = "Title: " + item.getTitle() + "\n" +
							"Description: " + item.getDescription() + "\n" +
							"ItemID: " + item.getItemID() + "\n" +
							"Price: $" + item.getPrice() + "\n" +
							"Count: " + item.getCount() + "\n";

		JOptionPane.showMessageDialog(null, displayInfo, "Item Details", JOptionPane.INFORMATION_MESSAGE);
	}

    public void offSeasonDisplayInfo(int index) {
		MenuItem item = inactiveModel.getElementAt(index);
		String displayInfo = "Title: " + item.getTitle() + "\n" +
							"Description: " + item.getDescription() + "\n" +
							"ItemID: " + item.getItemID() + "\n" +
							"Price: $" + item.getPrice() + "\n" +
							"Count: " + item.getCount() + "\n";

		JOptionPane.showMessageDialog(null, displayInfo, "Item Details", JOptionPane.INFORMATION_MESSAGE);
	}
}
