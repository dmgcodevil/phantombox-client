package com.git.client.ui.frame;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.EventQueue;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CallAnswerFrame extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CallAnswerFrame frame = new CallAnswerFrame();
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
    public CallAnswerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 230, 175);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(20dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(20dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(20dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(20dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(33dlu;default)"),
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(27dlu;default)"),
                FormFactory.RELATED_GAP_ROWSPEC,}));

        JLabel lblCall = new JLabel("Call");
        contentPane.add(lblCall, "2, 2, left, default");

        JLabel lblNewLabel = new JLabel("New label");
        contentPane.add(lblNewLabel, "4, 2, 5, 1");

        URL answerImageURL = CallAnswerFrame.class.getResource("/com/git/client/ui/ico/phone.png");
        URL cancelImageURL = CallAnswerFrame.class.getResource("/com/git/client/ui/ico/cancel.png");
        JButton btnAnswer = new JButton(new ImageIcon(answerImageURL));
        contentPane.add(btnAnswer, "2, 4, 3, 1");

        JButton btnCancel = new JButton(new ImageIcon(cancelImageURL));
        contentPane.add(btnCancel, "6, 4, 3, 1");
    }

}
