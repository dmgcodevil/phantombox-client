package com.git.client.ui.frame;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 286, 170);
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

        textField = new JTextField();
        contentPane.add(textField, "4, 4, 3, 1, fill, default");
        textField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        contentPane.add(lblPassword, "2, 6, right, default");

        passwordField = new JPasswordField();
        contentPane.add(passwordField, "4, 6, 3, 1, fill, default");

        JButton btnNewButton = new JButton("Login");
        contentPane.add(btnNewButton, "4, 8");

        JButton btnNewButton_1 = new JButton("Close");
        contentPane.add(btnNewButton_1, "6, 8");
    }

}
