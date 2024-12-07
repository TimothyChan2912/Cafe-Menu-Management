// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.*;

// public class MenuEditItem extends JFrame {
//     private final int FRAME_WIDTH = 1000;
// 	private final int FRAME_HEIGHT = 200;

//     private JLabel menuType;
//     private JLabel title;
//     private JLabel description;
//     private JLabel itemID;
//     private JLabel price;
//     private JLabel count;
//     private JLabel status;

//     private JComboBox<String> menuTypeDropDown;
//     private JTextField itemTitleField;
//     private JTextField itemDescriptionField;
//     private JTextField itemIDField;
//     private JTextField itemPriceField;
//     private JTextField itemCountField;
//     private JRadioButton current;
//     private JRadioButton offSeason;

//     private JButton btnOk;
//     private JButton btnCancel;

//     private GridBagLayout layout = new GridBagLayout();
//     private GridBagConstraints gbc = new GridBagConstraints();
//     private AddObjects a = new AddObjects();

//     public MenuEditItem() {
//         menuType = new JLabel("Menu Type:");
//         title = new JLabel("Title:");
//         description = new JLabel("Description:");
//         itemID = new JLabel("Item ID:");
//         price = new JLabel("Price:");
//         count = new JLabel("Count:");
//         status = new JLabel("Status:");

//         menuTypeDropDown = new JComboBox<String>();
//         menuTypeDropDown.addItem("Diner");
//         menuTypeDropDown.addItem("Pancake");
//         itemTitleField = new JTextField();              // make sure these are initialized with the current item's info
//         itemDescriptionField = new JTextField();
//         itemIDField = new JTextField();
//         itemPriceField = new JTextField();
//         itemCountField = new JTextField();
//         current = new JRadioButton("Current");
//         offSeason = new JRadioButton("Off-Season");
//         current.setSelected(true);
        
//         BtnListener btnListener = new BtnListener();
//         btnOk = new JButton("OK");
//         btnCancel = new JButton("Cancel");
//         btnOk.addActionListener(btnListener);
//         btnCancel.addActionListener(btnListener);

//         JPanel pAdd = new JPanel();
//         pAdd.setLayout(layout);

//         a.addObjects(menuType, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(menuTypeDropDown, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(title, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(itemTitleField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(description, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(itemDescriptionField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(itemID, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(itemIDField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(price, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(itemPriceField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(count, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(itemCountField, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(status, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(current, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(offSeason, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(btnOk, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);
//         a.addObjects(btnCancel, pAdd, layout, gbc, WIDTH, WIDTH, WIDTH, HEIGHT);


//         this.setTitle("Update Item Details");
//         this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setVisible(true);
//     }

//     class BtnListener implements ActionListener {
//         public void actionPerformed(ActionEvent e) {
//             if(e.getSource() == btnOk) {
//                 addItem();
//                 dispose();
//             }
//             else if (e.getSource() == btnCancel) {
//                 dispose();
//             }
//         }
//     }

//     private void addItem() {
//         // Get the values from the text fields
//     }
// }
