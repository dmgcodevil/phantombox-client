package com.git.client.ui.frame;

import com.git.client.ui.panel.ImagePanel;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CallRequestFrame extends JFrame {

    private JPanel contentPane;
    private JButton btnCancel;
    private JLabel lblContactName;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CallRequestFrame frame = new CallRequestFrame();
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
    public CallRequestFrame() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 205, 278);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(51dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(36dlu;default)"),},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(33dlu;default)"),
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(59dlu;default)"),
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,}));

        BufferedImage image = null;
        try {
            image = ImageIO.read(CallRequestFrame.class.getResource("/com/git/client/ui/ico/call.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        JPanel imagePanel = new ImagePanel(image);
        contentPane.add(imagePanel, "4, 4, 3, 1, fill, fill");

        JLabel lblCall = new JLabel("Call");
        contentPane.add(lblCall, "2, 2");

        lblContactName = new JLabel("Contact name");
        contentPane.add(lblContactName, "4, 2, 3, 1, left, default");

        btnCancel = new JButton("cancel");
        contentPane.add(btnCancel, "4, 8");
    }

    /**
     * Constructor with parameters.
     *
     * @param contactName
     */
    public CallRequestFrame(String contactName) {
        this();
        lblContactName.setText(contactName);
        addListeners();
    }

    private void addListeners() {
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
    }
}
