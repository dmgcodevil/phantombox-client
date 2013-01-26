package com.git.client.ui.panel.setup;

import com.git.client.api.domain.IDeviceStatistic;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import org.apache.commons.collections.CollectionUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DeviceTab extends JPanel {

    private JLabel lblFindDevices;
    private JButton btnStart;
    private JButton btnStop;
    private JLabel lblAudioDevice;
    private JComboBox audioDevices;
    private JButton btnTestAD;
    private JLabel lblVideoDevice;
    private JComboBox videoDevices;
    private JButton btnTestVD;

    private ISetupMediator setupMediator;


    /**
     * Default constructor.
     */
    public DeviceTab() {
        initLayout();
        populateComponents();
        addListeners();
    }

    /**
     * Constructor with parameters.
     *
     * @param setupMediator {@link ISetupMediator}
     */
    public DeviceTab(ISetupMediator setupMediator) {
        this.setupMediator = setupMediator;
        this.setupMediator.initDevices();
        initLayout();
        populateComponents();
        addListeners();
    }


    private void initLayout() {
        setLayout(new FormLayout(new ColumnSpec[]{
            FormFactory.RELATED_GAP_COLSPEC,
            FormFactory.DEFAULT_COLSPEC,
            FormFactory.RELATED_GAP_COLSPEC,
            new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("47dlu", true), Sizes.constant("50dlu", true)), 0),
            FormFactory.RELATED_GAP_COLSPEC,
            ColumnSpec.decode("max(119dlu;default)"),
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
                FormFactory.RELATED_GAP_ROWSPEC,}));
    }

    private void populateComponents() {
        lblFindDevices = new JLabel("Find devices");
        add(lblFindDevices, "2, 2");

        btnStart = new JButton("start");
        add(btnStart, "4, 2");

        btnStop = new JButton("stop");
        btnStop.setVisible(false);

        add(btnStop, "4, 2");

        lblAudioDevice = new JLabel("Audio device");
        add(lblAudioDevice, "2, 4, right, default");

        addAudioDevices();

        btnTestAD = new JButton("test");
        add(btnTestAD, "8, 4");

        lblVideoDevice = new JLabel("Video device");
        add(lblVideoDevice, "2, 6, right, default");

        addVideoDevices();

        btnTestVD = new JButton("test");
        add(btnTestVD, "8, 6");

    }

    private void addListeners() {
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                IDeviceStatistic deviceStatistic = setupMediator.initDevices();
                JOptionPane.showMessageDialog(null,
                    deviceStatistic.getInfo(),
                    "Find device",
                    JOptionPane.INFORMATION_MESSAGE);

                addAudioDevices();
                addVideoDevices();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                btnStop.setVisible(false);
                btnStart.setVisible(true);
            }
        });

        audioDevices.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    setupMediator.setAudioDevice((String) itemEvent.getItem());
                }
            }
        });

        videoDevices.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    setupMediator.setVideoDevice((String) itemEvent.getItem());
                }
            }
        });

        btnTestAD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String device = (String) audioDevices.getItemAt(audioDevices.getSelectedIndex());
                showDeviceMsg(setupMediator.testDevice(device));
            }
        });

        btnTestVD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String device = (String) videoDevices.getItemAt(videoDevices.getSelectedIndex());
                showDeviceMsg(setupMediator.testDevice(device));
            }
        });
    }

    private void addAudioDevices() {
        audioDevices = new JComboBox();
        add(audioDevices, "4, 4, 3, 1, fill, default");
        revalidate();
        repaint();

        if (CollectionUtils.isNotEmpty(setupMediator.getDeviceManager().getAudioDevices())) {
            for (String device : setupMediator.getDeviceManager().getAudioDevices()) {
                audioDevices.addItem(device);
            }

            if (setupMediator.getSetup().getAudioDevice() != null && setupMediator.getSetup().getAudioDevice().getName() != null) {
                audioDevices.setSelectedItem(setupMediator.getSetup().getAudioDevice().getName());
            }
        }
    }

    private void addVideoDevices() {
        videoDevices = new JComboBox();
        add(videoDevices, "4, 6, 3, 1, fill, default");
        revalidate();
        repaint();

        if (CollectionUtils.isNotEmpty(setupMediator.getDeviceManager().getVideoDevices())) {
            for (String device : setupMediator.getDeviceManager().getVideoDevices()) {
                videoDevices.addItem(device);
            }

            if (setupMediator.getSetup().getVideoDevice() != null && setupMediator.getSetup().getVideoDevice().getName() != null) {
                videoDevices.setSelectedItem(setupMediator.getSetup().getVideoDevice().getName());
            }
        }
    }

    private void showDeviceMsg(boolean result) {
        if (!result) {
            JOptionPane.showMessageDialog(null,
                "Device not available.",
                "Device error.",
                JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "Device works currently.");
        }
    }

}
