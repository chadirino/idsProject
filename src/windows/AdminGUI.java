package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import db.*;

public class AdminGUI extends JFrame {
    
    Container cp;
    JPanel pView, pUpdate;
    public static JTable tblView, tblUpdate;
    TableModel tmView, tmUpdate;
    JScrollPane spView, spUpdate;
    JButton btnUpdate, btnSave;
    ButtonListener buttonListen;
    
    public AdminGUI() {

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

        // non-editable table
        tblView = new JTable();
        
        // editable table
        tblUpdate = new JTable();

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
        Sql.getUsers();
        pView = new JPanel();
        pView.add(spView);
        pView.add(btnUpdate);
        cp.add(pView);
    }

    // update purchase page
    private void openUpdate() {
        switchPage();
        Sql.getUsers();
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
    
    private void addUser() {

    }

    private void deleteUser() {

    }
}