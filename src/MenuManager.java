import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
	
public class MenuManager {
	public List<MenuItem> menuList;

	private final int FRAME_WIDTH = 1000;
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
    private JRadioButton current;
    private JRadioButton offSeason;

    private JButton btnCancel;

	private JFrame fAdd;
	private JFrame fEdit;
    private JFrame fDelete;
    private JFrame fReactivate;
    private JFrame fDeactivate;

    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

	public MenuManager () {
        menuList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/menuData.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 7) {
                    String type = parts[0];
                    String title = parts[1];
                    String itemID = parts[2];
                    String description = parts[3];
                    float price = Float.parseFloat(parts[4]);
                    int count = Integer.parseInt(parts[5]);
                    boolean available = Boolean.parseBoolean(parts[6]);

                    if (type.equals("Pancake")) {
                        menuList.add(new PancakeMenuItem(title, itemID, description, price, count, available));
                    } else if (type.equals("Diner")) {
                        menuList.add(new DinerMenuItem(title, itemID, description, price, count, available));
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    //Menu Add
    private GridBagLayout addLayout = new GridBagLayout();
    private JButton addBtnOk;

	public void add() {
        fAdd = new JFrame("Enter User Details");
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
        itemTitleField = new JTextField();
        itemDescriptionField = new JTextField();
        itemIDField = new JTextField();
        itemPriceField = new JTextField();
        itemCountField = new JTextField();
        current = new JRadioButton("Current");
        offSeason = new JRadioButton("Off-Season");
        current.setSelected(true);
        
        BtnListener btnListener = new BtnListener();
        addBtnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");
        addBtnOk.addActionListener(btnListener);
        btnCancel.addActionListener(btnListener);

        JPanel pAdd = new JPanel();
        pAdd.setLayout(addLayout);

        a.addObjects(menuType, pAdd, addLayout, gbc, 0, 0, 1, 1);
        a.addObjects(menuTypeDropDown, pAdd, addLayout, gbc, 1, 0, 1, 1);
        a.addObjects(title, pAdd, addLayout, gbc, 2, 0, 1, 1);
        a.addObjects(itemTitleField, pAdd, addLayout, gbc, 3, 0, 1, 1);
        a.addObjects(description, pAdd, addLayout, gbc, 4, 0, 1, 1);
        a.addObjects(itemDescriptionField, pAdd, addLayout, gbc, 5, 0, 1, 1);
        a.addObjects(itemID, pAdd, addLayout, gbc, 6, 0, 1, 1);
        a.addObjects(itemIDField, pAdd, addLayout, gbc, 7, 0, 1, 1);
        a.addObjects(price, pAdd, addLayout, gbc, 8, 0, 1, 1);
        a.addObjects(itemPriceField, pAdd, addLayout, gbc, 9, 0, 1, 1);
        a.addObjects(count, pAdd, addLayout, gbc, 10, 0, 1, 1);
        a.addObjects(itemCountField, pAdd, addLayout, gbc, 11, 0, 1, 1);
        a.addObjects(status, pAdd, addLayout, gbc, 12, 0, 1, 1);
        a.addObjects(current, pAdd, addLayout, gbc, 13, 0, 1, 1);
        a.addObjects(offSeason, pAdd, addLayout, gbc, 14, 0, 1, 1);

        JPanel pButtons = new JPanel();
        pButtons.add(addBtnOk);
        pButtons.add(btnCancel);
        a.addObjects(pButtons, pAdd, addLayout, gbc, 0, 1, 15, 1);

		fAdd.add(pAdd);
        fAdd.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fAdd.setVisible(true);
	}

    
    //Menu Edit
    private GridBagLayout editLayout = new GridBagLayout();
    private JButton editBtnOk;


	public void edit(/*selected item */) {
        fEdit = new JFrame("Update Item Details");
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
        itemTitleField = new JTextField();              // make sure these are initialized with the current item's info
        itemDescriptionField = new JTextField();
        itemIDField = new JTextField();
        itemPriceField = new JTextField();
        itemCountField = new JTextField();
        current = new JRadioButton("Current");
        offSeason = new JRadioButton("Off-Season");
        current.setSelected(true);
        
        BtnListener btnListener = new BtnListener();
        editBtnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");
        editBtnOk.addActionListener(btnListener);
        btnCancel.addActionListener(btnListener);

        JPanel pEdit = new JPanel();
        pEdit.setLayout(editLayout);

        a.addObjects(menuType, pEdit, editLayout, gbc, 0, 0, 1, 1);
        a.addObjects(menuTypeDropDown, pEdit, editLayout, gbc, 1, 0, 1, 1);
        a.addObjects(title, pEdit, editLayout, gbc, 2, 0, 1, 1);
        a.addObjects(itemTitleField, pEdit, editLayout, gbc, 3, 0, 1, 1);
        a.addObjects(description, pEdit, editLayout, gbc, 4, 0, 1, 1);
        a.addObjects(itemDescriptionField, pEdit, editLayout, gbc, 5, 0, 1, 1);
        a.addObjects(itemID, pEdit, editLayout, gbc, 6, 0, 1, 1);
        a.addObjects(itemIDField, pEdit, editLayout, gbc, 7, 0, 1, 1);
        a.addObjects(price, pEdit, editLayout, gbc, 8, 0, 1, 1);
        a.addObjects(itemPriceField, pEdit, editLayout, gbc, 9, 0, 1, 1);
        a.addObjects(count, pEdit, editLayout, gbc, 10, 0, 1, 1);
        a.addObjects(itemCountField, pEdit, editLayout, gbc, 11, 0, 1, 1);
        a.addObjects(status, pEdit, editLayout, gbc, 12, 0, 1, 1);
        a.addObjects(current, pEdit, editLayout, gbc, 13, 0, 1, 1);
        a.addObjects(offSeason, pEdit, editLayout, gbc, 14, 0, 1, 1);
        
        JPanel pButtons = new JPanel();
        pButtons.add(editBtnOk);
        pButtons.add(btnCancel);
        a.addObjects(pButtons, pEdit, editLayout, gbc, 0, 1, 15, 1);

		fEdit.add(pEdit);
        fEdit.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        fEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fEdit.setVisible(true);
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

        delete = new JLabel("Are you sure you want to delete the selected user?");

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
                    JOptionPane.showMessageDialog(fReactivate, "Selected customer should be Inactive", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedItem == null) {
                    JOptionPane.showMessageDialog(fReactivate, "No customer selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedItem.setAvailable(true);
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
                    JOptionPane.showMessageDialog(fDeactivate, "Selected customer should be Active", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if(selectedItem == null) {
                    JOptionPane.showMessageDialog(fDeactivate, "No customer selected", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    selectedItem.setAvailable(true);
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

	class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editBtnOk) {
                fEdit.dispose();
            } else if (e.getSource() == addBtnOk) {
                fAdd.dispose();
            } else if (e.getSource() == btnCancel) {
                fAdd.dispose();
                fEdit.dispose();
            }
        }
    }
}
