package windows;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import db.*;

public class LoginGUI {
    
    public JDialog dlgLogin, dlgSetPswd;
    private JFrame fLogin, fSetPswd;
    private JPanel pLogin, pSetPswd;
    private BufferedImage lambIcon;
    private Image resizedLambIcon;
    private JLabel lblUser, lblPswd, lblSetPswd1, lblSetPswd2, lblLambIcon;
    private JTextField tfUser;
    private JPasswordField pfPswd, pfSetPswd1, pfSetPswd2;
    private String strPswdInput, strSetPswd1Input, strSetPswd2Input;
    private static String strUserInput;
    private char[] pswdInput, setPswd1Input, setPswd2Input;
    private JButton btnLogin, btnCancel, btnSetPswdOK, btnSetPswdCancel;
    private GridBagLayout gbLayout;
    private GridBagConstraints gbc;
    private ButtonListener buttonListen;

    public LoginGUI() {

        // ======================================================
        //                         images
        // ======================================================
        
        // ----------------- lambs-are-us icon ------------------ 
        
        String lambIconPath = "images/Lambs-Are-Us.png";
        
        try {
            lambIcon = ImageIO.read(new File(lambIconPath));
        } catch (IOException error) {
            error.printStackTrace();
        }
        
        resizedLambIcon = lambIcon.getScaledInstance(445, 150, Image.SCALE_SMOOTH);
        lblLambIcon = new JLabel(new ImageIcon(resizedLambIcon));

        // ======================================================
        //                        buttons
        // ======================================================

        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(123,25));
        btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(123,25));
        btnSetPswdOK = new JButton("OK");
        btnSetPswdCancel = new JButton("Cancel");

        // assign listener to buttons
        buttonListen = new ButtonListener();
        btnLogin.addActionListener(buttonListen);
        btnCancel.addActionListener(buttonListen);
        btnSetPswdOK.addActionListener(buttonListen);
        btnSetPswdCancel.addActionListener(buttonListen);

        // ======================================================
        //                     labels/fields
        // ======================================================

        // labels
        lblUser = new JLabel("Username:", SwingConstants.RIGHT);
        lblPswd = new JLabel("Password:", SwingConstants.RIGHT);
        lblSetPswd1 = new JLabel("Password:", SwingConstants.RIGHT);
        lblSetPswd2 = new JLabel("Confirm:", SwingConstants.RIGHT);

        // text fields
        tfUser = new JTextField(20);
        tfUser.setPreferredSize(new Dimension(54,23));

        // password fields
        pfPswd = new JPasswordField(20);
        pfPswd.setPreferredSize(new Dimension(54,23));
        pfSetPswd1 = new JPasswordField(20);
        pfSetPswd2 = new JPasswordField(20);

        // ======================================================
        //                         panels
        // ======================================================

        // --------------------- user login ---------------------

        pLogin = new JPanel();
        gbLayout = new GridBagLayout();
        pLogin.setLayout(gbLayout);
        gbc = new GridBagConstraints();

        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 13, 0); 
        pLogin.add(lblLambIcon, gbc); 
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 90, 9, 3);
        pLogin.add(lblUser, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 9, 0);
        pLogin.add(tfUser, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 12, 3);
        pLogin.add(lblPswd, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 12, 0);
        pLogin.add(pfPswd, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 90, 0, 0);
        pLogin.add(btnLogin, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 0, 0, 0);
        pLogin.add(btnCancel, gbc);

        // -------------------- set password --------------------

        pSetPswd = new JPanel();
        pSetPswd.add(lblSetPswd1);
        pSetPswd.add(pfSetPswd1);
        pSetPswd.add(lblSetPswd2); 
        pSetPswd.add(pfSetPswd2); 
        pSetPswd.add(btnSetPswdOK); 
        pSetPswd.add(btnSetPswdCancel); 

        // ======================================================
        //                     frames/dialogs
        // ======================================================

        // --------------------- user login ---------------------
        
        fLogin = new JFrame();
        dlgLogin = new JDialog(fLogin);
        dlgLogin.add(pLogin);
        dlgLogin.getRootPane().setDefaultButton(btnLogin);
        dlgLogin.setTitle("User Login");
        dlgLogin.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        dlgLogin.setSize(560,380);
        dlgLogin.setLocationRelativeTo(null);
        dlgLogin.setVisible(true);

        // -------------------- set password --------------------

        fSetPswd = new JFrame();
        dlgSetPswd = new JDialog(fSetPswd);
        dlgSetPswd.add(pSetPswd);
        dlgSetPswd.getRootPane().setDefaultButton(btnSetPswdOK);
        dlgSetPswd.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                fSetPswd.dispose();
            }
        });
        dlgSetPswd.setTitle("Set Password");
        dlgSetPswd.setSize(312,128);
        dlgSetPswd.setLocationRelativeTo(null);
    }

    // ======================================================
    //                      main method
    // ======================================================
    
    public static void main(String[] args) {
        new LoginGUI();
        // System.out.println(Sql.validCredentials("jbond", "jbond1"));
    }

    // ======================================================
    //                 buttton event handler
    // ======================================================

    private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btnLogin) {
                validateLogin();
            } else if (event.getSource() == btnCancel) {
                System.exit(0);
            } else if (event.getSource() == btnSetPswdOK) {
                checkPswdMatch();
            } else if (event.getSource() == btnSetPswdCancel) {
                fSetPswd.dispose();
            }
        }
    }

    // ======================================================
    //                    validate login
    // ======================================================

    // ------------------- method handler -------------------

    private void validateLogin() {
        
        strUserInput = tfUser.getText();
        pswdInput = pfPswd.getPassword();
        strPswdInput = new String(pswdInput);
        
        if (loginFilled(strUserInput, strPswdInput) == false) {
            giveFillWarning(strUserInput, strPswdInput);
        } else if (validCredentials(strUserInput, strPswdInput) == false) {
            giveInvalidWarning();
        } else if (firstLogin(strPswdInput) == true) {
            dlgSetPswd.setVisible(true);
        } else {
            System.out.println("opening home...");
            openHomeGUI(getUserType(strUserInput));
            System.out.println("opened");
        }
    }

    // ------------------- get user type --------------------
    
    private String getUserType(String userName) {
        return Sql.getUserType(userName);
    }
    
    // ----------- check if completely filled out -----------
    
    private Boolean loginFilled(String user, String pswd) {
        if (user.equals("") || pswd.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    // ------ warn user that not completely filled out ------

    private void giveFillWarning(String user, String pswd) {
        if (user.equals("") && pswd.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter your username and password.");
        } else if (user.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter your username.");
        } else if (pswd.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter your password.");
        } 
    }

    // ----------- check if credentials are valid -----------

    private Boolean validCredentials(String user, String pswd) {
        return Sql.validCredentials(user, pswd);
    }

    // ------ warns user that credentials are invalid -------
    
    private void giveInvalidWarning() {
        JOptionPane.showMessageDialog(null, "Invalid username/password.", "Alert", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    // ------------- checks if first user login -------------

    private Boolean firstLogin(String pswd) {
        if (pswd.equals("password")) {
            return true;
        } else {
            return false;
        }
    }
    
    // ------------- checks if passwords match --------------

    private void checkPswdMatch() {
        
        setPswd1Input = pfSetPswd1.getPassword();
        setPswd2Input = pfSetPswd2.getPassword();
        strSetPswd1Input = new String(setPswd1Input);
        strSetPswd2Input = new String(setPswd2Input);
        
        if (strSetPswd1Input.equals(strSetPswd2Input)) {
            fSetPswd.dispose();
            JOptionPane.showMessageDialog(null, "Password successfully set.");
            setPassword(strUserInput, strSetPswd1Input);
            openHomeGUI(getUserType(strUserInput));
        } else {
            pfSetPswd1.setText("");
            pfSetPswd2.setText("");
            JOptionPane.showMessageDialog(null, "Password does not match.", "Alert", 
                JOptionPane.WARNING_MESSAGE);
            pfSetPswd1.requestFocusInWindow();
        }
    }

    // ---------------- change user password ----------------

    private void setPassword(String user, String pswd) {
        Sql.setPassword(user, pswd);
    }

    // ======================================================
    //                    open home window
    // ====================================================== 

    private void openHomeGUI(String userType) {
        if (userType.equals("Staff")) {
            System.out.println();
            HomeGUI.openUserHome();
        } else if (userType.equals("Manager")) {
            HomeGUI.openUserHome();
        } else if (userType.equals("Admin")) {
            HomeGUI.openAdminHome();
        }
        fLogin.dispose();
    }
}