package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import db.*;

public class PurchaseGUI {

    Container cp;
    JFrame fView, fDateRange;
    JMenuBar menuBar;
    JMenu menu, subSort;
    JMenuItem miView, miAdd, miUpdate, miSortNew, miSortOld, miDate, miLogout;
    JPanel pView, pAdd, pDateRange, pUpdate, pLineView, pLineAdd, pLineUpdate;
    JDialog dlgDateRange;
    JButton btnSave, btnCancel, btnSearch, btnFilter, btnBack, btnDateRangeOK, btnDateRangeCancel;
    JLabel lblSearch, lblDateTo, lblDateFrom;
    JTextField tfSearch, tfDateTo, tfDateFrom;
    String inputSearch, inputDateFrom, inputDateTo;
    public static JTable tblEdit, tblNonEdit;
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

        btnDateRangeOK = new JButton("OK");
        btnDateRangeCancel = new JButton("Cancel");

        // action listener for buttons
        buttonListen = new ButtonListener();

        // add action listener to buttons
        btnSearch.addActionListener(buttonListen);
        btnSave.addActionListener(buttonListen);
        btnCancel.addActionListener(buttonListen);
        btnBack.addActionListener(buttonListen);
        btnDateRangeOK.addActionListener(buttonListen);
        btnDateRangeCancel.addActionListener(buttonListen);

        // ======================================================
        //                        tables
        // ======================================================
        
        // non-editable table for initial page
        tblNonEdit = new JTable();
        
        
        // editable table for update page
        tblEdit = new JTable();

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

        lblDateFrom = new JLabel("From: ");
        lblDateTo = new JLabel("To: ");

        tfDateFrom = new JTextField(10);
        tfDateTo = new JTextField(10);

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
        //                        panels
        // ======================================================

        // view
        pView = new JPanel();
        pView.add(lblSearch);
        pView.add(tfSearch);
        pView.add(btnSearch);
        pView.add(spNonEdit);
        
        // date range
        pDateRange = new JPanel();
        pDateRange.add(lblDateFrom);
        pDateRange.add(tfDateFrom);
        pDateRange.add(lblDateTo);
        pDateRange.add(tfDateTo);
        pDateRange.add(btnDateRangeOK);
        pDateRange.add(btnDateRangeCancel);

        // ======================================================
        //                    frames/dialogs
        // ======================================================

        // -------------------- view frame ----------------------
        
        fView = new JFrame();
        fView.add(pView);
        fView.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                fView.dispose(); 
            }
        });
        fView.setTitle("Purchases Manager");
        fView.setJMenuBar(menuBar);
        fView.setSize(450,325);
        fView.setLocationRelativeTo(null);
        

        // ----------------- data range dialog ------------------

        fDateRange = new JFrame();
        dlgDateRange = new JDialog(fDateRange);
        dlgDateRange.add(pDateRange);
        dlgDateRange.getRootPane().setDefaultButton(btnDateRangeOK);
        dlgDateRange.setTitle("Date Range");
        dlgDateRange.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                fDateRange.dispose(); 
            }
        });
        dlgDateRange.setSize(300,200);
        dlgDateRange.setLocationRelativeTo(null);

        // open initial page
        openViewWindow(); 
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
                openViewWindow();
            } else if (event.getSource() == btnCancel) {
                openViewWindow();
            } else if (event.getSource() == btnDateRangeOK) {
                setDateRange();
            } else if (event.getSource() == btnDateRangeCancel) {
                fDateRange.dispose();
            }
        }
    }
    
    // menu items
    private class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == miView) {
                openViewWindow(); 
            } else if (event.getSource() == miAdd) {
                openAdd(); 
            } else if (event.getSource() == miUpdate) {
                openUpdate(); 
            } else if (event.getSource() == miSortNew) {
                // sort newest first
            } else if (event.getSource() == miSortOld) {
                // sort oldest first
            } else if (event.getSource() == miDate) {
                openSetRangeDialog();
            } else if (event.getSource() == btnBack) {
                openViewWindow();
            } else if (event.getSource() == miLogout) {
                fView.dispose();
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

    private void openSetRangeDialog() {
        dlgDateRange.setVisible(true);
    }

    private void openViewWindow() {
        Sql.getPurchases();
        fView.setVisible(true);
    }
    
    private void setDateRange() {
        inputDateFrom = tfDateFrom.getText();
        inputDateTo = tfDateTo.getText();

        Sql.setDateRange(inputDateFrom, inputDateTo);
    }
    
    private void getPurchases() {
        // for making table maybe output array of records
    }
    
    private void getPurchaseItems() {

    }
    
    private void addPurchase() {

    }
}