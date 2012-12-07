package com.git.client.ui.frame;

import com.git.client.ui.Mediator;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    public static final String CONTACT_EMAIL = "Contact email";
    private JLabel lblContactName;
    private JCheckBox chbxFindByEmail;
    private JPanel contentPane;
    private JTextField nameField;
    private JButton btnAdd;
    private JButton btnCancel;
    private Mediator mediator;
    private static final String ADD = "Add";
    private static final String CANCEL = "Cancel";
    private static final String FIND_BY_EMAIL = "find by email";
    private static final String CONTACT_NAME = "Contact name";
    private static final String ADD_NEW_CONTACT = "Add new contact";


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

        JLabel lblAddNewContact = new JLabel(ADD_NEW_CONTACT);
        lblAddNewContact.setFont(new Font("Arial", Font.BOLD, 12));
        contentPane.add(lblAddNewContact, "2, 2, 5, 1, center, default");

        lblContactName = new JLabel(CONTACT_NAME);
        contentPane.add(lblContactName, "2, 4, left, default");

        nameField = new JTextField();
        contentPane.add(nameField, "4, 4, 3, 1, fill, default");
        nameField.setColumns(10);

        chbxFindByEmail = new JCheckBox(FIND_BY_EMAIL);


        contentPane.add(chbxFindByEmail, "4, 6, left, default");

        btnAdd = new JButton(ADD);
        contentPane.add(btnAdd, "4, 8");

        btnCancel = new JButton(CANCEL);
        contentPane.add(btnCancel, "6, 8");
    }

    public AddContactFrame(Mediator mediator) throws HeadlessException {
        this();
        this.mediator = mediator;
        addListeners();
    }

    private void addListeners() {
        chbxFindByEmail.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                if (!chbxFindByEmail.isSelected()) {
                    lblContactName.setText(CONTACT_NAME);
                } else {
                    lblContactName.setText(CONTACT_EMAIL);
                }
                revalidate();
                repaint();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!chbxFindByEmail.isSelected()) {
                    mediator.addContactByUserName(nameField.getText());
                } else {
                    mediator.addContactByContactEmail(nameField.getText());
                }
                dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
