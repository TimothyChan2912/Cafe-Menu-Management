import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminCustomerManagerDelete extends JFrame {
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 400;

    private JButton btnYes;
    private JButton btnNo;

    private JLabel delete;

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private AddObjects a = new AddObjects();

    private Customer selectedCustomer;

    public AdminCustomerManagerDelete(Customer customer) {
        selectedCustomer = customer;

        btnYes = new JButton("Yes");
        btnNo = new JButton("No");

        BtnListener btnlistener = new BtnListener();
        btnYes.addActionListener(btnlistener);
        btnNo.addActionListener(btnlistener);

        delete = new JLabel("Are you sure you want to delete the selected user?");

        JPanel pDelete = new JPanel();
        pDelete.setLayout(layout);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        a.addObjects(delete, pDelete, layout, gbc, 0, 0, 1, 1, 50, 50);

        a.addObjects(btnYes, pDelete, layout, gbc, 0, 1, 1, 1, 25, 25);
        a.addObjects(btnNo, pDelete, layout, gbc, 1, 1, 1, 1, 25, 25);

        this.add(pDelete);
        this.setTitle("Confirm Deletion");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class BtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnYes) {
                if(selectedCustomer != null) {
                    if(AdminCustomerManager.inactiveModel.contains(selectedCustomer)) {
                        AdminCustomerManager.inactiveModel.removeElement(selectedCustomer);
                    } else {
                        AdminCustomerManager.activeModel.removeElement(selectedCustomer);
                    }
                }
                dispose();
            } 
            else if (e.getSource() == btnNo) {
                dispose();
            }
        }
    }
}