package com.git.client.ui.frame;

import com.git.client.ui.panel.JContact;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JPanel contentPane;

    private DefaultListModel model;
    private JPanel contactPanel;
    private JScrollPane scrollContacts;

    final private Frame loginFrame = new LoginFrame();

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

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnChat = new JMenu("Chat");
        menuBar.add(mnChat);

        JMenuItem mntmLogin = new JMenuItem("Login");
        mntmLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                loginFrame.setVisible(true);
            }
        });
        mnChat.add(mntmLogin);

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
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel contatsPanel = new JPanel();
        contentPane.add(contatsPanel, BorderLayout.CENTER);
        contatsPanel.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("default:grow"),},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("default:grow"),}));

        contactPanel = new JPanel();

        scrollContacts = new JScrollPane();
        scrollContacts.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContacts.setViewportView(contactPanel);
        contactPanel.setLayout(new GridLayout(110, 1, 0, 0));


        for (int i = 0; i < 100; i++) {
            JContact contact = new JContact("contact" + i);
            contactPanel.add(contact);
        }

        //p.setVisible(true);
        contatsPanel.add(scrollContacts, "2, 2, fill, fill");

        //init();
    }
}