package com.git.client.ui.panel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

public class JContact extends JPanel {

    private JLabel contactName;

    public JLabel getContactName() {
        return contactName;
    }

    public void setContactName(JLabel contactName) {
        this.contactName = contactName;
    }

    /**
     * Create the panel.
     */
    public JContact() {
        setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

        JPopupMenu popupMenu = new JPopupMenu();
        addPopup(this, popupMenu);

        JMenuItem mntmInfo = new JMenuItem("info");
        popupMenu.add(mntmInfo);
        setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,}));

        contactName = new JLabel("ContactName");
        add(contactName, "2, 2");

        java.net.URL imageURL = JContact.class.getResource("/com/git/client/ui/ico/phone.png");
        JButton btnNewButton = new JButton(new ImageIcon(imageURL));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setOpaque(true);
        add(btnNewButton, "2, 4");

    }

    public JContact(String contactName) {
        this();
        this.contactName.setText(contactName);
    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }
}
