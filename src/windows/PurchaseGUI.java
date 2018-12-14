package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class PurchaseGUI extends JFrame {

    Container cp;
    JMenuBar menuBar;
    JMenu menu, subSort;
    JMenuItem miView, miAdd, miUpdate, miSortNew, miSortOld, miDate, miLogout;
    JPanel pView, pAdd, pUpdate, pLineView, pLineAdd, pLineUpdate;
    JButton btnSave, btnCancel, btnSearch, btnFilter, btnBack;
    JLabel lblSearch;
    JTextField tfSearch;
    String inputSearch;
    JTable tblEdit, tblNonEdit;
    JScrollPane spEdit, spNonEdit;
    TableModel tmNonEdit, tmEdit;
    MenuItemListener menuListen;
    ButtonListener buttonListen;

    public PurchaseGUI() {
        
        // ======================================================
        //                       buttons
        // ======================================================

        // button to search table
        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(75,20));

        // button to filter table
        btnFilter = new JButton("Filter Table");

        // button to save edit
        btnSave = new JButton("Save");

        // button to cancel edit
        btnCancel = new JButton("Cancel");

        // button to go back from purchase line window to purchases window
        btnBack = new JButton("Back");

        // action listener for buttons
        buttonListen = new ButtonListener();

        // add action listener to buttons
        btnSearch.addActionListener(buttonListen);
        btnSave.addActionListener(buttonListen);
        btnCancel.addActionListener(buttonListen);
        btnBack.addActionListener(buttonListen);

        // ======================================================
        //                         data
        // ======================================================

        // data for purchases list
        String[] columnNames = {"Order No.","Employee","Amount", "Date"};
        String[] data[] = {{"10001","Claudia E.","$93.62","12/01/18"},{"10002","Claudia E.","$87.21","11/28/18"}};

        // data for puchase lines list
        
       
        // ======================================================
        //                        tables
        // ======================================================

        // table models
        tmEdit = new DefaultTableModel(data, columnNames) {};
        tmNonEdit = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // non-editable table for initial page
        tblNonEdit = new JTable(tmNonEdit);
        
        // editable table for update page
        tblEdit = new JTable(tmEdit);

        // put tables inside scroll panes (give them scroll bars)
        spNonEdit = new JScrollPane(tblNonEdit);
        spNonEdit.setPreferredSize(new Dimension(375,200));
        spEdit = new JScrollPane(tblEdit);
        spEdit.setPreferredSize(new Dimension(375,200));

        // ======================================================
        //                     labels/fields
        // ======================================================

        lblSearch = new JLabel("Order No:");
        tfSearch = new JTextField(12);

        // ======================================================
        //                          menu
        // ======================================================

        // --------------------- menu items ---------------------

        // menu item listener
        menuListen = new MenuItemListener();

        // menu menu items
        miView = new JMenuItem("View list of purchases");
        miAdd = new JMenuItem("Add new purchase");
        miUpdate = new JMenuItem("Update existing purchase");
        miDate = new JMenuItem("Choose time frame");
        miLogout = new JMenuItem("Logout");
        
        // submenu menu items
        miSortNew = new JMenuItem("Most recent first");
        miSortOld = new JMenuItem("Most recent last");
        
        // add listener to menu items
        miView.addActionListener(menuListen);
        miAdd.addActionListener(menuListen);
        miUpdate.addActionListener(menuListen);
        miSortNew.addActionListener(menuListen);
        miSortOld.addActionListener(menuListen);
        miDate.addActionListener(menuListen);

        // ----------------------- menu ------------------------
        
        // sort sub menu
        subSort = new JMenu("Sort table");
        subSort.add(miSortNew);
        subSort.add(miSortOld);
        
        // main menu
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.add(miView);
        menu.add(miAdd);
        menu.add(miUpdate);
        menu.addSeparator();
        menu.add(subSort);
        menu.addSeparator();
        menu.add(miDate);
        menu.addSeparator();
        menu.add(miLogout);

        // ---------------------- menu bar ----------------------

        menuBar = new JMenuBar();
        menuBar.add(menu);
        
        // ======================================================
        //                         window
        // ======================================================
        
        // content pane
        cp = getContentPane();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                // ConnectDB.disconnect();
                dispose(); 
            }
        });

        setTitle("Purchases Manager");
        setJMenuBar(menuBar);
        setSize(450,325);
        setLocationRelativeTo(null);
        setVisible(true);

        // open initial page
        openView(); 
    }

    // ======================================================
    //                      main method
    // ======================================================

    public static void main(String[] args) {
        new PurchaseGUI();
    }

    // ======================================================
    //                         panels
    // ======================================================
    
    // purchases list page
    private void openView() {
        switchPage();
        pView = new JPanel();
        pView.add(lblSearch);
        pView.add(tfSearch);
        pView.add(btnSearch);
        pView.add(spNonEdit);
        cp.add(pView);
    }

    // add purchase page
    private void openAdd() {
        switchPage();
        pAdd = new JPanel();
        pAdd.add(spEdit);
        cp.add(pAdd);
    }

    // update purchase page
    private void openUpdate() {
        switchPage();
        pUpdate = new JPanel();
        pUpdate.add(spEdit);
        cp.add(pUpdate);
    }

    // ======================================================
    //                     event handler
    // ======================================================
    
    // buttons
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btnSearch) {
                inputSearch = tfSearch.getText();
                // search and focus on cell
            } else if (event.getSource() == btnSave) {
                // save update
                openView();
            } else if (event.getSource() == btnCancel) {
                openView();
            }
        }
    }
    
    // menu items
    private class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == miView) {
                openView(); 
            } else if (event.getSource() == miAdd) {
                openAdd(); 
            } else if (event.getSource() == miUpdate) {
                openUpdate(); 
            } else if (event.getSource() == miSortNew) {
                // sort newest first
            } else if (event.getSource() == miSortOld) {
                // sort oldest first
            } else if (event.getSource() == miDate) {
                // open dialog to set time frame
            } else if (event.getSource() == btnBack) {
                openView();
            } else if (event.getSource() == miLogout) {
                dispose();
                new LoginGUI();
            }
        }
    }

    // ======================================================
    //                      misc methods
    // ======================================================

    // method to wipe content pane (for switching panels)
    private void switchPage() {
        cp.removeAll();
        cp.revalidate();
        cp.repaint();
    }
}