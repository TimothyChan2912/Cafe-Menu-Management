import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
	
public class MenuManager {
	private List<PancakeMenuItem> pancakeMenuList;
	private List<DinerMenuItem> dinerMenuList;

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

    private JButton btnOk;
    private JButton btnCancel;

	JFrame addFrame = new JFrame("Enter Item Details");
	JFrame editFrame = new JFrame("Update Item Details");
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

	public MenuManager () {
	}

	public void add() {
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
        btnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");
        btnOk.addActionListener(btnListener);
        btnCancel.addActionListener(btnListener);

        JPanel pAdd = new JPanel();
        pAdd.setLayout(layout);

        // a.addObjects(menuType, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(menuTypeDropDown, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(title, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemTitleField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(description, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemDescriptionField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemID, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemIDField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(price, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemPriceField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(count, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemCountField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(status, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(current, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(offSeason, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(btnOk, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(btnCancel, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);

		addFrame.add(pAdd);
        addFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.setVisible(true);
	}

	public void edit(/*selected item */) {
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
        btnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");
        btnOk.addActionListener(btnListener);
        btnCancel.addActionListener(btnListener);

        JPanel pEdit = new JPanel();
        pEdit.setLayout(layout);

        // a.addObjects(menuType, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(menuTypeDropDown, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(title, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemTitleField, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(description, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemDescriptionField, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemID, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemIDField, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(price, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemPriceField, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(count, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(itemCountField, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(status, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(current, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(offSeason, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(btnOk, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
        // a.addObjects(btnCancel, pEdit, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);

		editFrame.add(pEdit);
        editFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editFrame.setVisible(true);
	}

	public void delete(/*selected item */) {
	}

	public void reactivate() {
	}

	public void deactivate() {
	}

	public void sort() {
	}

	public void search() {
	}	

	class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnOk) {
                addFrame.dispose();
            }
            else if (e.getSource() == btnCancel) {
                addFrame.dispose();
            }
        }
    }
}
