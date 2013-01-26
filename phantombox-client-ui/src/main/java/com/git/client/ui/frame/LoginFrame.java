package com.git.client.ui.frame;


import com.git.client.api.exception.UserLoginException;
import com.git.client.ui.Mediator;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton btnLogin;
    private JButton btnClose;
    private Mediator mediator;

    /**
     * Create the frame.
     */
    public LoginFrame() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 355, 170);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(60dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(60dlu;default)"),},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,}));

        JLabel lblTitle = new JLabel("Welcome to PhantomBox");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 12));
        contentPane.add(lblTitle, "4, 2, 3, 1, center, default");

        JLabel lblLogin_1 = new JLabel("Login");
        contentPane.add(lblLogin_1, "2, 4, right, default");

        loginField = new JTextField();
        contentPane.add(loginField, "4, 4, 3, 1, fill, default");
        loginField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        contentPane.add(lblPassword, "2, 6, right, default");

        passwordField = new JPasswordField();
        contentPane.add(passwordField, "4, 6, 3, 1, fill, default");

        btnLogin = new JButton("Login");
        contentPane.add(btnLogin, "4, 8");

        btnClose = new JButton("Close");
        contentPane.add(btnClose, "6, 8");
    }

    public LoginFrame(Mediator mediator) throws HeadlessException {
        this();
        this.mediator = mediator;
        addListeners();
    }

    private void addListeners() {

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // TODO refactor it (passwordField.getText())
                    mediator.login(loginField.getText(), passwordField.getText());
                    dispose();
                } catch (UserLoginException ex) {
                    JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
