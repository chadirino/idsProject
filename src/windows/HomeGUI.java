package windows;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

public class HomeGUI {
    
    public JFrame fUser, fAdmin;
    private JPanel pUser, pAdmin;
    private JButton btnInv, btnPurch, btnUsers;
    private BufferedImage icon; 
    private Image resizedIcon;
    private JLabel lblUserIcon, lblAdminIcon;
    private Font buttonFont;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbc;
    private ButtonListener buttonListen;

    public HomeGUI() {
        
        // ======================================================
        //                        buttons
        // ======================================================

        // font for home page buttons
        buttonFont = new Font("Arial", Font.PLAIN, 15);
        
        // button to open menu system
        btnInv = new JButton("Ingredients");
        btnInv.setPreferredSize(new Dimension(220,65));
        btnInv.setFont(buttonFont);

        // button to open inventory system
        btnPurch = new JButton("Purchases");
        btnPurch.setPreferredSize(new Dimension(220,65));
        btnPurch.setFont(buttonFont);

        // button to open admin page
        btnUsers = new JButton("Users");
        btnUsers.setPreferredSize(new Dimension(220,65));
        btnUsers.setFont(buttonFont);

        // action listener for buttons
        buttonListen = new ButtonListener();

        // add action listener to buttons
        btnInv.addActionListener(buttonListen);
        btnPurch.addActionListener(buttonListen);
        btnUsers.addActionListener(buttonListen);

        // ======================================================
        //                          icon
        // ======================================================
        
        // icon file path
        String iconPath = "images/Lambs-Are-Us.png";
        
        // create icon image
        try {
            icon = ImageIO.read(new File(iconPath));
        } catch (IOException error) {
            error.printStackTrace();
        }
        
        // resize icon image
        resizedIcon = icon.getScaledInstance(445, 150, Image.SCALE_SMOOTH);
        
        // format image icon as label
        lblUserIcon = new JLabel(new ImageIcon(resizedIcon));
        lblAdminIcon = new JLabel(new ImageIcon(resizedIcon));

        // ======================================================
        //                         panels
        // ======================================================

        // ------------------------ user ------------------------
        
        pUser = new JPanel();
        gbLayout = new GridBagLayout();
        pUser.setLayout(gbLayout);
        gbc = new GridBagConstraints();

        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 6, 0);
        pUser.add(lblUserIcon, gbc);

        gbc.gridx = 0; 
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 1, 0, 0);
        pUser.add(btnInv, gbc);
        
        gbc.gridx = 1; 
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 0);
        pUser.add(btnPurch, gbc); 
        
        // ----------------------- admin ------------------------
        
        pAdmin = new JPanel();
        pAdmin.add(lblAdminIcon);
        pAdmin.add(btnUsers);

        // ======================================================
        //                         frames
        // ======================================================
        
        // ------------------------ user ------------------------

        fUser = new JFrame();
        fUser.add(pUser); 
        fUser.setTitle("Lambs-Are-Us");
        fUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        fUser.setSize(575,350);
        fUser.setLocationRelativeTo(null);

        // ----------------------- admin ------------------------

        fAdmin = new JFrame();
        fAdmin.add(pAdmin); 
        fAdmin.setTitle("Lambs-Are-Us - Admin");
        fAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        fAdmin.setSize(600,350);
        fAdmin.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        openUserHome();   
    }
    
    // ======================================================
    //                     event handler
    // ======================================================

    class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btnInv) {
                new IngredientGUI();
            } else if (event.getSource() == btnPurch) {
                new PurchaseGUI(); 
            } else if (event.getSource() == btnUsers) {
                new AdminGUI();
            }
        }
    }

    public static void openUserHome() {
        new HomeGUI().fUser.setVisible(true);
    }

    public static void openAdminHome() {
        new HomeGUI().fAdmin.setVisible(true);
    }
}