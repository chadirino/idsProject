package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class AdminGUI extends JFrame {
    
    Container cp;
    JPanel pView, pUpdate;
    JTable tblView, tblUpdate;
    TableModel tmView, tmUpdate;
    JScrollPane spView, spUpdate;
    JButton btnUpdate, btnSave;
    ButtonListener buttonListen;
    
    public AdminGUI() {

        // ======================================================
        //                         data
        // ======================================================
        
        String[] columnNames = {"Employee No.","Username"};
        String[] data[] = {{"0000001","admin1"},{"0000002","admin2"},
        {"0000003","manager1"},{"0000004","manager2"},{"0000005","manager3"},
        {"0000006","staff1"},{"0000007","staff2"},{"0000008","staff3"},
        {"0000009","staff4"},{"0000010","staff5"},{"0000011","staff6"},
        {"0000012","staff7"},{"0000013","staff8"},{"0000014","staff9"}};

        // ======================================================
        //                       buttons
        // ======================================================

        // action listener for buttons
        buttonListen = new ButtonListener();

        // button to update user login table
        btnUpdate = new JButton("Update");
        btnUpdate.setPreferredSize(new Dimension(85,25));
        btnUpdate.addActionListener(buttonListen);

        // button to save update
        btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(85,25));
        btnSave.addActionListener(buttonListen);

        // ======================================================
        //                        tables
        // ======================================================

        // table models
        tmUpdate = new DefaultTableModel(data, columnNames) {};
        tmView = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // non-editable table
        tblView = new JTable(tmView);
        
        // editable table
        tblUpdate = new JTable(tmUpdate);

        // put tables inside scroll panes (give them scroll bars)
        spView = new JScrollPane(tblView);
        spView.setPreferredSize(new Dimension(375,200));
        spUpdate = new JScrollPane(tblUpdate);
        spUpdate.setPreferredSize(new Dimension(375,200));

        // ======================================================
        //                         window
        // ======================================================
        
        // content pane
        cp = getContentPane();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Admin Page");
        setSize(425,300);
        setLocationRelativeTo(null);
        setVisible(true);

        // open initial page
        openView();
    }

    // ======================================================
    //                         panels
    // ======================================================
    
    // purchases list page
    private void openView() {
        switchPage();
        pView = new JPanel();
        pView.add(spView);
        pView.add(btnUpdate);
        cp.add(pView);
    }

    // update purchase page
    private void openUpdate() {
        switchPage();
        pUpdate = new JPanel();
        pUpdate.add(spUpdate);
        pUpdate.add(btnSave);
        cp.add(pUpdate);
    }
    
    public static void main(String[] args) {
        new AdminGUI();
    }

    // ======================================================
    //                     event handler
    // ======================================================
    
    private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btnUpdate) {
                openUpdate();
            } else if (event.getSource() == btnSave) {
                // sql statement method
                openView();
            }
        }
    }

    // method to wipe content pane (for switching panels)
    private void switchPage() {
        cp.removeAll();
        cp.revalidate();
        cp.repaint();
    }

    // ======================================================
    //                   database interaction
    // ======================================================
    
    private void getLoginUsers() {
        // for making the table
    }
    
    private void addUserLogin() {

    }

    private void deleteUserLogin() {

    }
}