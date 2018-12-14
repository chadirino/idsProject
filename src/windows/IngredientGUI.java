package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class IngredientGUI extends JFrame {

    Container cp;
    JMenuBar menuBar;
    JMenu menu, subSort;
    JMenuItem miView, miAdd, miUpdate, miSortDesc, miSortAsc, miLogout;
    JPanel pView, pAdd, pUpdate;
    JButton btnSave, btnCancel;
    JTable tblEdit, tblNonEdit;
    JScrollPane spEdit, spNonEdit;
    TableModel tmNonEdit, tmEdit;
    MenuItemListener menuListen;

    public IngredientGUI() {
        
        // ======================================================
        //                         data
        // ======================================================

        String[] columnNames = {"#","Name","Unit","QOH"};
        String[] data[] = {{"001","Salt","lbs","17","10"},
        {"002","Pepper","lbs","14"},{"101","Chicken","lbs","24"},
        {"102","Beef","lbs","30"},{"103","Pork","lbs","22"},
        {"104","Lamb","lbs","15"},{"105","Goat","lbs","18"},
        {"201","Rice","lbs","44"},{"202","Bread","lbs","14"},
        {"203","Pasta","lbs","18"},{"301","Whole Milk","gal","16"},
        {"302","Cheese","lbs","16"},{"303","Yogurt","lbs","20"},
        {"304","Butter","lbs","16"},{"305","2% Milk","gal","12"},
        {"","","",""}};
       
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
        tblNonEdit.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                JTable table =(JTable) event.getSource();
                Point point = event.getPoint();
                int row = table.rowAtPoint(point);
                if (event.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.print(row); 
                }
            }
        });
        
        // editable table for update page
        tblEdit = new JTable(tmEdit);
        tblNonEdit.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                JTable table =(JTable) event.getSource();
                Point point = event.getPoint();
                int row = table.rowAtPoint(point);
                if (event.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    System.out.print(row); 
                }
            }
        });

        // put tables inside scroll panes (give them scroll bars)
        spNonEdit = new JScrollPane(tblNonEdit);
        spNonEdit.setPreferredSize(new Dimension(375,200));
        spEdit = new JScrollPane(tblEdit);
        spEdit.setPreferredSize(new Dimension(375,200));

        // ======================================================
        //                          menu
        // ======================================================

        // --------------------- menu items ---------------------

        // menu item listener
        menuListen = new MenuItemListener();

        // menu menu items
        miView = new JMenuItem("View list of ingredients");
        miAdd = new JMenuItem("Add new ingredient");
        miUpdate = new JMenuItem("Update existing ingredient");
        miLogout = new JMenuItem("Logout");
        
        // submenu menu items
        miSortDesc = new JMenuItem("From A to Z");
        miSortAsc = new JMenuItem("From Z to A");

        // add listener to menu items
        miView.addActionListener(menuListen);
        miAdd.addActionListener(menuListen);
        miUpdate.addActionListener(menuListen);
        miSortDesc.addActionListener(menuListen);
        miSortAsc.addActionListener(menuListen);

        // ----------------------- menu -------------------------
        
        // sort sub menu
        subSort = new JMenu("Sort table");
        subSort.add(miSortDesc);
        subSort.add(miSortAsc);
        
        // main menu
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.add(miView);
        menu.add(miAdd);
        menu.add(miUpdate);
        menu.addSeparator();
        menu.add(subSort);
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
        setTitle("Ingredient Manager");
        setJMenuBar(menuBar);
        setSize(425,300);
        setLocationRelativeTo(null);
        setVisible(true);

        // open initial page
        openView(); 
    }
    
    // ======================================================
    //                      main method
    // ======================================================

    public static void main(String[] args) {
        new IngredientGUI();
    }

    // ======================================================
    //                         panels
    // ======================================================
    
    // ingredient list page
    private void openView() {
        switchPage();
        pView = new JPanel();
        pView.add(spNonEdit);
        cp.add(pView);
    }

    // new ingredient page
    private void openAdd() {
        switchPage();
        pAdd = new JPanel();
        pAdd.add(spEdit);
        cp.add(pAdd);
    }

    // ingredient update page
    private void openUpdate() {
        switchPage();
        pUpdate = new JPanel();
        pUpdate.add(spEdit);
        cp.add(pUpdate);
    }

    // ======================================================
    //                       buttons
    // ======================================================
    
    // ======================================================
    //                     event handler
    // ======================================================
    
    // menu items
    private class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == miView) {
                openView(); 
            } else if (event.getSource() == miAdd) {
                openAdd(); 
            } else if (event.getSource() == miUpdate) {
                openUpdate(); 
            } else if (event.getSource() == miSortDesc) {
                // sort descending
            } else if (event.getSource() == miSortAsc) {
                // sort ascending
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