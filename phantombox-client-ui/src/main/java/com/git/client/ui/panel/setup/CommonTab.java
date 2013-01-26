package com.git.client.ui.panel.setup;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

import javax.swing.JPanel;

public class CommonTab extends JPanel {
    private ISetupMediator setupMediator;


    /**
     * Default constructor.
     */
    public CommonTab() {
        initLayout();
        //populateComponents();

        JPanel controlPanel = new ControlPanel();
        add(controlPanel, "6, 22, fill, fill");
    }

    /**
     * Constructor with parameters.
     *
     * @param setupMediator {@link ISetupMediator}
     */
    public CommonTab(ISetupMediator setupMediator) {
        this.setupMediator = setupMediator;
        initLayout();
        populateComponents();

    }

    private void initLayout() {
        setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,
            new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("47dlu", true), Sizes.constant("50dlu", true)), 0),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(121dlu;default):grow"),
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,}));
    }

    private void populateComponents() {
        JPanel controlPanel = null;
        if (setupMediator != null && setupMediator.getControlPanel() != null) {
            controlPanel = setupMediator.getControlPanel();
        } else {
            controlPanel = new ControlPanel();
        }
        add(controlPanel, "6, 20, 3, 1, fill, fill");
    }
}
