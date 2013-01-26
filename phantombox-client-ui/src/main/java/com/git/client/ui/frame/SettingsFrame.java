package com.git.client.ui.frame;

import static com.git.domain.api.Constants.FILE_NAME;
import com.git.client.api.exception.PropertiesException;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.device.IPropertiesManager;
import com.git.client.ui.panel.setup.ControlPanel;
import com.git.client.ui.panel.setup.DeviceTab;
import com.git.client.ui.panel.setup.ISetupMediator;
import com.git.client.ui.panel.setup.SetupMediator;
import com.git.client.ui.panel.setup.SetupPanel;
import com.git.client.webcam.device.DeviceManager;
import com.git.client.webcam.device.PropertiesManager;
import com.git.domain.impl.Setup;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SettingsFrame extends BaseFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SettingsFrame frame = new SettingsFrame();
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
    public SettingsFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("default:grow"),
            FormFactory.RELATED_GAP_COLSPEC,},
            new RowSpec[]{
                FormFactory.RELATED_GAP_ROWSPEC,
                RowSpec.decode("default:grow"),
                FormFactory.RELATED_GAP_ROWSPEC,}));

        init();
        JPanel panel = new SetupPanel(createSetupMediator());
        contentPane.add(panel, "2, 2, fill, fill");
    }

    private void init() {
        setBounds(100, 100, 565, 500);
        setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alignment(this);
    }

    private ISetupMediator createSetupMediator() {
        IDeviceManager deviceManager = new DeviceManager();
        ISetupMediator setupMediator = new SetupMediator();
        IPropertiesManager propertiesManager = new PropertiesManager();
        setupMediator.setDeviceManager(deviceManager);
        setupMediator.setPropertiesManager(propertiesManager);
        setupMediator.setSetupFrame(this);

        Setup setup = null;
        try {
            setup = propertiesManager.read(Setup.class, FILE_NAME);
        } catch (PropertiesException e) {
            // TODO dialog window..
            e.printStackTrace();
        }
        setupMediator.setSetup(setup);
        ControlPanel controlPanel = new ControlPanel(setupMediator);
        setupMediator.setControlPanel(controlPanel);
        DeviceTab deviceTab = new DeviceTab(setupMediator);
        setupMediator.setDeviceTab(deviceTab);
        return setupMediator;
    }
}
