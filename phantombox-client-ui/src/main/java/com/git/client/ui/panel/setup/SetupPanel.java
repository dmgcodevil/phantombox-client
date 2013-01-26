package com.git.client.ui.panel.setup;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SetupPanel extends JPanel {

    private ISetupMediator setupMediator;


    /**
     * Default constructor.
     */
    public SetupPanel() {
        initLayout();
        pupulateMockComponents();
    }

    /**
     * Constructor with parameters.
     *
     * @param setupMediator {@link ISetupMediator}
     */
    public SetupPanel(ISetupMediator setupMediator) {
        this.setupMediator = setupMediator;
        initLayout();
        pupulateComponents();
    }

    private void initLayout() {
        setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("default:grow"),
            FormFactory.RELATED_GAP_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("max(164dlu;default):grow"),
                FormFactory.RELATED_GAP_ROWSPEC,
                FormFactory.DEFAULT_ROWSPEC,
                FormFactory.RELATED_GAP_ROWSPEC,}));
    }

    private void pupulateComponents() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane, "2, 2, fill, fill");

        JPanel generalTab = new JPanel();
        tabbedPane.addTab("General", null, generalTab, null);

        JPanel deviceTab = new DeviceTab(setupMediator);
        tabbedPane.addTab("Device", null, deviceTab, null);

        JPanel navPanel = new JPanel();
        add(navPanel, "2, 4, fill, fill");
        navPanel.setLayout(new BorderLayout(0, 0));

        JPanel controlPanel = new ControlPanel(setupMediator);
        navPanel.add(controlPanel, BorderLayout.EAST);
    }

    // TODO remove it
    private void pupulateMockComponents() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane, "2, 2, fill, fill");

        JPanel generalTab = new JPanel();
        tabbedPane.addTab("General", null, generalTab, null);

        JPanel deviceTab = new DeviceTab();
        tabbedPane.addTab("Device", null, deviceTab, null);

        JPanel navPanel = new JPanel();
        add(navPanel, "2, 4, fill, fill");
        navPanel.setLayout(new BorderLayout(0, 0));

        JPanel controlPanel = new ControlPanel();
        navPanel.add(controlPanel, BorderLayout.EAST);
    }
}
