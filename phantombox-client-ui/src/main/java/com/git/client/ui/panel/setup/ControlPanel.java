package com.git.client.ui.panel.setup;

import com.git.client.api.exception.PropertiesException;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    private JButton btnOk;

    private JButton btnApply;

    private JButton btnCancel;

    private ISetupMediator setupMediator;

    /**
     * Gets OK button.
     *
     * @return {@link JButton}
     */
    public JButton getBtnOk() {
        return btnOk;
    }

    /**
     * Sets OK button.
     *
     * @param btnOk {@link JButton}
     */
    public void setBtnOk(JButton btnOk) {
        this.btnOk = btnOk;
    }

    /**
     * Gets Apply button.
     *
     * @return {@link JButton}
     */
    public JButton getBtnApply() {
        return btnApply;
    }

    /**
     * Sets Apply button.
     *
     * @param btnApply {@link JButton}
     */
    public void setBtnApply(JButton btnApply) {
        this.btnApply = btnApply;
    }

    /**
     * Gets cancel button.
     *
     * @return {@link JButton}
     */
    public JButton getBtnCancel() {
        return btnCancel;
    }

    /**
     * Sets cancel button.
     *
     * @param btnCancel {@link JButton}
     */
    public void setBtnCancel(JButton btnCancel) {
        this.btnCancel = btnCancel;
    }

    /**
     * Default constructor.
     */
    public ControlPanel() {
        initLayout();
        populateComponents();
        addListeners();
    }

    /**
     * Constructor with parameters.
     */
    public ControlPanel(ISetupMediator setupMediator) {
        this();
        this.setupMediator = setupMediator;
    }

    private void initLayout() {
        setLayout(new FormLayout(new ColumnSpec[]{
            ColumnSpec.decode("max(60dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(60dlu;default)"),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(60dlu;default)"),},
            new RowSpec[]{
                FormFactory.DEFAULT_ROWSPEC,}));
    }

    private void populateComponents() {
        btnOk = new JButton("OK");
        add(btnOk, "1, 1");

        btnApply = new JButton("Apply");
        add(btnApply, "3, 1");

        btnCancel = new JButton("Cancel");
        add(btnCancel, "5, 1");
    }

    private void addListeners() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (setupMediator != null) {
                        setupMediator.saveSetup();
                    }
                } catch (PropertiesException ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupMediator.getSetupFrame().dispose();
            }
        });

    }
}
