package com.git.client.ui.frame;

import com.git.client.ui.Mediator;
import com.git.client.ui.panel.JContact;
import com.git.domain.api.IContact;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 * Main frame.
 * <p/>
 * Date: 12.11.12
 * Time: 21:42
 *
 * @author rpleshkov
 */
public class MainFrame extends JFrame {

    public static final String ADD_NEW_CONTACT = "Add new contact";
    public static final String LOGIN = "Login";
    private JMenuBar menuBar;
    private JPanel contentPane;
    private JPanel contactPanel;
    private JPanel contactsPanel;
    private JScrollPane scrollContacts;
    private JMenuItem mntmLogin;
    private JMenuItem mntmAddContact;
    private Mediator mediator;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Default constructor.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 243, 521);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnChat = new JMenu("PBox");
        menuBar.add(mnChat);

        mntmLogin = new JMenuItem(LOGIN);
        mnChat.add(mntmLogin);

        mntmAddContact = new JMenuItem(ADD_NEW_CONTACT);
        mnChat.add(mntmAddContact);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mnChat.add(mntmExit);

        JMenu mnTools = new JMenu("Tools");
        menuBar.add(mnTools);

        JMenuItem mntmSettings = new JMenuItem("Settings");
        mntmSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //Frame frame = new SettingsFrame();
                //frame.setVisible(true);
            }
        });

        mnTools.add(mntmSettings);

        JMenu mnAbout = new JMenu("About");
        menuBar.add(mnAbout);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        contactsPanel = new JPanel();
        contentPane.add(contactsPanel, BorderLayout.CENTER);
        contactsPanel.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("default:grow"),},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("default:grow"),}));

        contactPanel = new JPanel();

        scrollContacts = new JScrollPane();
        scrollContacts.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContacts.setViewportView(contactPanel);
        contactPanel.setLayout(new GridLayout(1, 1, 0, 0));

        contactsPanel.add(scrollContacts, "2, 2, fill, fill");

        //init();
    }

    public MainFrame(Mediator mediator) throws HeadlessException {
        this();
        this.mediator = mediator;
        addListeners();
    }

    private void addListeners() {

        mntmLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                LoginFrame loginFrame = new LoginFrame(mediator);
                loginFrame.setVisible(true);
            }
        });


        mntmAddContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AddContactFrame addContactFrame = new AddContactFrame(mediator);
                addContactFrame.setVisible(true);
            }
        });
    }

    public void refreshContactsList(Set<IContact> contacts) {
        //contactPanel = new JPanel();
        contactPanel.removeAll();
        contactPanel.setLayout(new GridLayout(contacts.size(), 1, 0, 0));
        for (IContact contact : contacts) {
            contactPanel.add(new JContact(mediator, contact));
        }
        //contactPanel.setVisible(true);
        contactPanel.revalidate();
        contactPanel.repaint();
    }
}
