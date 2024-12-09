import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
	
public class MenuManager {
	public static List<MenuItem> menuList;

	private final int FRAME_WIDTH = 1600;
	private final int FRAME_HEIGHT = 200;

    private JLabel menuType;
    private JLabel title;
    private JLabel description;
    private JLabel itemID;
    private JLabel price;
    private JLabel count;
    private JLabel status;

    private JComboBox<String> menuTypeDropDown;
    private JTextField itemTitleField;
    private JTextField itemDescriptionField;
    private JTextField itemIDField;
    private JTextField itemPriceField;
    private JTextField itemCountField;

    private ButtonGroup statusCheck;
    private JRadioButton current;
    private JRadioButton offSeason;

	private JFrame fAdd;
	private JFrame fEdit;
    private JFrame fDelete;
    private JFrame fReactivate;
    private JFrame fDeactivate;

    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

	public MenuManager () {
        menuList = cafe.readMenu();
	}

    //Menu Add
    private GridBagLayout addLayout = new GridBagLayout();
    private JButton addBtnOk;
    private JButton addBtnCancel;

	public void add() {
        fAdd = new JFrame("Enter Item Details");
		menuType = new JLabel("Menu Type:");
        title = new JLabel("Title:");
        description = new JLabel("Description:");
        itemID = new JLabel("Item ID:");
        price = new JLabel("Price:");
        count = new JLabel("Count:");
        status = new JLabel("Status:");

        menuTypeDropDown = new JComboBox<String>();
        menuTypeDropDown.addItem("Diner");
        menuTypeDropDown.addItem("Pancake");
        itemTitleField = new JTextField(10);
        itemDescriptionField = new JTextField(10);
        itemIDField = new JTextField(10);
        itemPriceField = new JTextField(10);
        itemCountField = new JTextField(10);

        statusCheck = new ButtonGroup();
        current = new JRadioButton("Current");
        offSeason = new JRadioButton("Off-Season");
        statusCheck.add(current);
        statusCheck.add(offSeason);
        
        BtnListener btnListener = new BtnListener();
        addBtnOk = new JButton("OK");
        addBtnCancel = new JButton("Cancel");
        addBtnOk.addActionListener(btnListener);
        addBtnCancel.addActionListener(btnListener);

        JPanel pAdd = new JPanel();
        pAdd.setLayout(addLayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(menuType, pAdd, addLayout, gbc, 0, 0, 1, 1);
        a.addObjects(menuTypeDropDown, pAdd, addLayout, gbc, 1, 0, 1, 1);
        a.addObjects(title, pAdd, addLayout, gbc, 2, 0, 1, 1);
        a.addObjects(description, pAdd, addLayout, gbc, 4, 0, 1, 1);
        a.addObjects(itemID, pAdd, addLayout, gbc, 6, 0, 1, 1);
        a.addObjects(price, pAdd, addLayout, gbc, 8, 0, 1, 1);
        a.addObjects(count, pAdd, addLayout, gbc, 10, 0, 1, 1);
        a.addObjects(status, pAdd, addLayout, gbc, 12, 0, 1, 1);
        a.addObjects(current, pAdd, addLayout, gbc, 13, 0, 1, 1);
        a.addObjects(offSeason, pAdd, addLayout, gbc, 14, 0, 1, 1);

        JPanel pButtons = new JPanel();
        pButtons.add(addBtnOk);
        pButtons.add(addBtnCancel);
        a.addObjects(pButtons, pAdd, addLayout, gbc, 0, 1, 15, 1);

        a.addObjects(itemTitleField, pAdd, addLayout, gbc, 3, 0, 1, 1);
        a.addObjects(itemDescriptionField, pAdd, addLayout, gbc, 5, 0, 1, 1);
        a.addObjects(itemIDField, pAdd, addLayout, gbc, 7, 0, 1, 1);
        a.addObjects(itemPriceField, pAdd, addLayout, gbc, 9, 0, 1, 1);
        a.addObjects(itemCountField, pAdd, addLayout, gbc, 11, 0, 1, 1);


		fAdd.add(pAdd);
        fAdd.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fAdd.setVisible(true);
	}

    

    public void addItem() {
        String menuType = menuTypeDropDown.getSelectedItem().toString();
        String title = itemTitleField.getText();
        String description = itemDescriptionField.getText();
        String id = itemIDField.getText();
        String price = itemPriceField.getText();
        String count = itemCountField.getText();
        boolean isCurrent = current.isSelected();

        if (isCurrent) {
            if (menuType.equals("Diner")) {
                DinerMenuItem activeDinerMenuItem = new DinerMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), true);
                MenuManagementScreen.activeModel.addElement(activeDinerMenuItem);
                menuList.add(activeDinerMenuItem);
            } else {
                PancakeMenuItem activePancakeMenuItem = new PancakeMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), true);
                MenuManagementScreen.activeModel.addElement(activePancakeMenuItem);
                menuList.add(activePancakeMenuItem);
            }
        } else {
            if (menuType.equals("Diner")) {
                DinerMenuItem inactiveDinerMenuItem = new DinerMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), false);
                MenuManagementScreen.activeModel.addElement(inactiveDinerMenuItem);
                menuList.add(inactiveDinerMenuItem);
            } else {
                PancakeMenuItem inactivePancakeMenuItem = new PancakeMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), false);
                MenuManagementScreen.activeModel.addElement(inactivePancakeMenuItem);
                menuList.add(inactivePancakeMenuItem);
            }
        }  
        cafe.writeMenu(menuList);
        fAdd.dispose();
    }

    
    //Menu Edit
    private GridBagLayout editLayout = new GridBagLayout();
    private JButton editBtnOk;
    private JButton editBtnCancel;
    MenuItem item;
    int itemIndex;
    boolean isActive;


	public void edit(MenuItem item, boolean isActive) {
        fEdit = new JFrame("Update Item Details");

        this.item = item;
        itemIndex = MenuManagementScreen.activeModel.indexOf(item);
        this.isActive = isActive;

		menuType = new JLabel("Menu Type:");
        title = new JLabel("Title:");
        description = new JLabel("Description:");
        itemID = new JLabel("Item ID:");
        price = new JLabel("Price:");
        count = new JLabel("Count:");
        status = new JLabel("Status:");

        menuTypeDropDown = new JComboBox<String>();
        menuTypeDropDown.addItem("Diner");
        menuTypeDropDown.addItem("Pancake");
        menuTypeDropDown.setSelectedItem(item.getClass().getName().equals("DinerMenuItem") ? "Diner" : "Pancake");
        itemTitleField = new JTextField(item.getTitle());             
        itemDescriptionField = new JTextField(item.getDescription());
        itemIDField = new JTextField(item.getItemID());
        itemPriceField = new JTextField(Float.toString(item.getPrice()));
        itemCountField = new JTextField(Integer.toString(item.getCount()));
        
        statusCheck = new ButtonGroup();
        current = new JRadioButton("Current");
        offSeason = new JRadioButton("Off-Season");
        statusCheck.add(current);
        statusCheck.add(offSeason);
        current.setSelected(item.isCurrent());
        offSeason.setSelected(!item.isCurrent());
        
        BtnListener btnListener = new BtnListener();
        editBtnOk = new JButton("OK");
        editBtnCancel = new JButton("Cancel");
        editBtnOk.addActionListener(btnListener);
        editBtnCancel.addActionListener(btnListener);

        JPanel pEdit = new JPanel();
        pEdit.setLayout(editLayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(menuType, pEdit, editLayout, gbc, 0, 0, 1, 1);
        a.addObjects(menuTypeDropDown, pEdit, editLayout, gbc, 1, 0, 1, 1);
        a.addObjects(title, pEdit, editLayout, gbc, 2, 0, 1, 1);
        a.addObjects(description, pEdit, editLayout, gbc, 4, 0, 1, 1);
        a.addObjects(itemID, pEdit, editLayout, gbc, 6, 0, 1, 1);
        a.addObjects(price, pEdit, editLayout, gbc, 8, 0, 1, 1);
        a.addObjects(count, pEdit, editLayout, gbc, 10, 0, 1, 1);
        a.addObjects(status, pEdit, editLayout, gbc, 12, 0, 1, 1);
        a.addObjects(current, pEdit, editLayout, gbc, 13, 0, 1, 1);
        a.addObjects(offSeason, pEdit, editLayout, gbc, 14, 0, 1, 1);
        
        JPanel pButtons = new JPanel();
        pButtons.add(editBtnOk);
        pButtons.add(editBtnCancel);
        a.addObjects(pButtons, pEdit, editLayout, gbc, 0, 1, 15, 1);

        a.addObjects(itemTitleField, pEdit, editLayout, gbc, 3, 0, 1, 1);
        a.addObjects(itemDescriptionField, pEdit, editLayout, gbc, 5, 0, 1, 1);
        a.addObjects(itemIDField, pEdit, editLayout, gbc, 7, 0, 1, 1);
        a.addObjects(itemPriceField, pEdit, editLayout, gbc, 9, 0, 1, 1);
        a.addObjects(itemCountField, pEdit, editLayout, gbc, 11, 0, 1, 1);


		fEdit.add(pEdit);
        fEdit.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fEdit.setVisible(true);
	}

    public void editItem() {
        String menuType = menuTypeDropDown.getSelectedItem().toString();
        String title = itemTitleField.getText();
        String description = itemDescriptionField.getText();
        String id = itemIDField.getText();
        String price = itemPriceField.getText();
        String count = itemCountField.getText();
        boolean isCurrent = current.isSelected();

        if (isCurrent) {
            if(isCurrent != isActive) {
                MenuManagementScreen.activeModel.addElement(item);
                MenuManagementScreen.inactiveModel.removeElement(item);
                item.setCurrent(isCurrent);
                menuList.remove(item);
                menuList.add(itemIndex, item);
            }
            else {
                if (menuType.equals("Diner")) {
                    DinerMenuItem activeDinerMenuItem = new DinerMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), true);
                    MenuManagementScreen.activeModel.set(itemIndex, activeDinerMenuItem);
                    menuList.remove(item);
                    menuList.add(itemIndex, activeDinerMenuItem);
                } else {
                    PancakeMenuItem activePancakeMenuItem = new PancakeMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), true);
                    MenuManagementScreen.activeModel.set(itemIndex, activePancakeMenuItem);
                    menuList.remove(item);
                    menuList.add(itemIndex, activePancakeMenuItem);
                }
            }
        } 
        else {
            if(isCurrent != isActive) {
                MenuManagementScreen.inactiveModel.addElement(item);
                MenuManagementScreen.activeModel.removeElement(item);
            }
            else {
                if (menuType.equals("Diner")) {
                    DinerMenuItem inactiveDinerMenuItem = new DinerMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), false);
                    MenuManagementScreen.activeModel.set(itemIndex, inactiveDinerMenuItem);
                    menuList.remove(item);
                    menuList.add(itemIndex, inactiveDinerMenuItem);
                } else {
                    PancakeMenuItem inactivePancakeMenuItem = new PancakeMenuItem(title, id, description, Float.parseFloat(price), Integer.parseInt(count), false);
                    MenuManagementScreen.activeModel.set(itemIndex, inactivePancakeMenuItem);
                    menuList.remove(item);
                    menuList.add(itemIndex, inactivePancakeMenuItem);
                }
            }
        }
         
        cafe.writeMenu(menuList);
        fEdit.dispose(); 
    }



    //Menu Delete
	private MenuItem selectedItem;
    private JButton btnYes;
    private JButton btnNo;
    private JLabel delete;
    private GridBagLayout deleteLayout = new GridBagLayout();

    public void delete(MenuItem item) {
        fDelete = new JFrame("Confirm Deletion");
        selectedItem = item;

        btnYes = new JButton("Yes");
        btnNo = new JButton("No");

        BtnListener btnlistener = new BtnListener();
        btnYes.addActionListener(btnlistener);
        btnNo.addActionListener(btnlistener);

        delete = new JLabel("Are you sure you want to delete the selected item?");

        JPanel pDelete = new JPanel();
        pDelete.setLayout(deleteLayout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(delete, pDelete, deleteLayout, gbc, 0, 0, 1, 1, 50, 50);

        a.addObjects(btnYes, pDelete, deleteLayout, gbc, 0, 1, 1, 1, 25, 25);
        a.addObjects(btnNo, pDelete, deleteLayout, gbc, 1, 1, 1, 1, 25, 25);

        fDelete.add(pDelete);
        fDelete.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fDelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fDelete.setVisible(true);
    }

	//Menu Reactivate
    private JList<MenuItem> activeMenuListDisplay;
    public static DefaultListModel<MenuItem> inactiveModel;
    public static DefaultListModel<MenuItem> activeModel;
    public void reactivate (MenuItem item, DefaultListModel<MenuItem> active, DefaultListModel<MenuItem> inactive, JList<MenuItem> display) {
        fReactivate = new JFrame();
        selectedItem = item;
        activeModel = active;
        inactiveModel = inactive;
        activeMenuListDisplay = display;

        if(selectedItem.isCurrent()) {
                    JOptionPane.showMessageDialog(fReactivate, "Selected item should be Inactive", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedItem == null) {
                    JOptionPane.showMessageDialog(fReactivate, "No item selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedItem.setCurrent(true);
                    activeModel.addElement(selectedItem);
                    inactiveModel.removeElement(selectedItem);
                    selectedItem = null;
                    activeMenuListDisplay.clearSelection();
                }
    }


    //Menu deactivate
    private JList<MenuItem> inactiveMenuListDisplay;
    public void deactivate (MenuItem item, DefaultListModel<MenuItem> active, DefaultListModel<MenuItem> inactive, JList<MenuItem> display) {
        fDeactivate = new JFrame();
        selectedItem = item;
        activeModel = active;
        inactiveModel = inactive;
        inactiveMenuListDisplay = display;
        
        if(!selectedItem.isCurrent()) {
                    JOptionPane.showMessageDialog(fDeactivate, "Selected item should be Active", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedItem == null) {
                    JOptionPane.showMessageDialog(fDeactivate, "No item selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedItem.setCurrent(false);
                    inactiveModel.addElement(selectedItem);
                    activeModel.removeElement(selectedItem);
                    selectedItem = null;
                    inactiveMenuListDisplay.clearSelection();
                }
    }

	public void sort() {
	}

	public void search() {
	}	

	//Buttons
    class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addBtnOk) {
                addItem();
            } else if (e.getSource() == editBtnOk) {
                editItem();
            } else if (e.getSource() == addBtnCancel) {
                fAdd.dispose();
            } else if (e.getSource() == editBtnCancel) {
                fEdit.dispose();
            } else if (e.getSource() == btnYes) {
                if(selectedItem != null) {
                    if(MenuManagementScreen.inactiveModel.contains(selectedItem)) {
                        MenuManagementScreen.inactiveModel.removeElement(selectedItem);
                        menuList.remove(selectedItem);
                        cafe.writeMenu(menuList);
                    } else {
                        MenuManagementScreen.activeModel.removeElement(selectedItem);
                        menuList.remove(selectedItem);
                        cafe.writeMenu(menuList);
                    }
                }
                fDelete.dispose();
            } 
            else if (e.getSource() == btnNo) {
                fDelete.dispose();
            }
        }
    }
}
