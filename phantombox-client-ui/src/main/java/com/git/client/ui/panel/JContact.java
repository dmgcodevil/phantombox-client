package com.git.client.ui.panel;

import com.git.domain.api.IContact;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

public class JContact extends JPanel {

    private JLabel contactName;

    private IContact contact;

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
            ColumnSpec.decode("max(35dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(47dlu;default)"),},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(23dlu;default)"),}));

        contactName = new JLabel("ContactName");
        add(contactName, "2, 2, 3, 1");

        JPanel panel = new JPanel();
        add(panel, "2, 4, 3, 1, fill, fill");
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        java.net.URL callImageURL = JContact.class.getResource("/com/git/client/ui/ico/phone_small.png");
        JButton btnCall = new JButton(new ImageIcon(callImageURL));
        //btnCall.setBorder(BorderFactory.createEmptyBorder());
        btnCall.setContentAreaFilled(false);
        panel.add(btnCall);

        java.net.URL delUserImageURL = JContact.class.getResource("/com/git/client/ui/ico/user_delete_small.png");
        JButton btnDel = new JButton(new ImageIcon(delUserImageURL));
        //btnDel.setBorder(BorderFactory.createEmptyBorder());
        btnDel.setContentAreaFilled(false);
        panel.add(btnDel);


    }

    public JContact(IContact contact) {
        this();
        this.contact = contact;
        this.contactName.setText(contact.getName());
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
