package com.git.client.ui.frame;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddContactFrame extends JFrame {

    private JLabel lblContactName;
    private JCheckBox chckbxFindByEmail;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddContactFrame frame = new AddContactFrame();
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
    public AddContactFrame() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 335, 171);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane
            .setLayout(new FormLayout(new ColumnSpec[]{
                FormFactory.RELATED_GAP_COLSPEC,
                FormFactory.DEFAULT_COLSPEC,
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("max(50dlu;default)"),
                FormFactory.RELATED_GAP_COLSPEC,
                ColumnSpec.decode("max(50dlu;default)"),},
                new RowSpec[]{FormFactory.RELATED_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.RELATED_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.RELATED_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,
                    FormFactory.RELATED_GAP_ROWSPEC,
                    FormFactory.DEFAULT_ROWSPEC,}));

        JLabel lblAddNewContact = new JLabel("Add new contact");
        lblAddNewContact.setFont(new Font("Arial", Font.BOLD, 12));
        contentPane.add(lblAddNewContact, "2, 2, 5, 1, center, default");

        lblContactName = new JLabel("Contact name");
        contentPane.add(lblContactName, "2, 4, left, default");

        textField = new JTextField();
        contentPane.add(textField, "4, 4, 3, 1, fill, default");
        textField.setColumns(10);

        chckbxFindByEmail = new JCheckBox("find by email");

        chckbxFindByEmail.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                if (!chckbxFindByEmail.isSelected()) {
                    lblContactName.setText("Contact name");
                } else {
                    lblContactName.setText("Contact email");
                }
                revalidate();
                repaint();
            }
        });

        contentPane.add(chckbxFindByEmail, "4, 6, left, default");

        JButton btnNewButton = new JButton("Find");
        contentPane.add(btnNewButton, "4, 8");

        JButton btnNewButton_1 = new JButton("Cancel");
        contentPane.add(btnNewButton_1, "6, 8");
    }

}
