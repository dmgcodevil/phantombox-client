package com.git.client.ui.panel.setup;


import static com.git.domain.api.Constants.FILE_NAME;
import com.git.client.api.domain.IDeviceStatistic;
import com.git.client.api.exception.DeviceNotFoundException;
import com.git.client.api.exception.PropertiesException;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.device.IPropertiesManager;
import com.git.domain.impl.AudioDevice;
import com.git.domain.impl.Setup;
import com.git.domain.impl.VideoDevice;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JFrame;

/**
 * Class description.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 12:54 PM
 */
public class SetupMediator implements ISetupMediator {

    private Setup setup;

    private IPropertiesManager propertiesManager;

    private JFrame setupFrame;

    private DeviceTab deviceTab;

    private ControlPanel controlPanel;

    private IDeviceManager deviceManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(SetupMediator.class);

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public IPropertiesManager getPropertiesManager() {
        return propertiesManager;
    }

    public void setPropertiesManager(IPropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
    }

    public JFrame getSetupFrame() {
        return setupFrame;
    }

    public void setSetupFrame(JFrame setupFrame) {
        this.setupFrame = setupFrame;
    }

    public DeviceTab getDeviceTab() {
        return deviceTab;
    }

    public void setDeviceTab(DeviceTab deviceTab) {
        this.deviceTab = deviceTab;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public IDeviceManager getDeviceManager() {
        return deviceManager;
    }

    public void setDeviceManager(IDeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    @Override
    public IDeviceStatistic initDevices() {
        IDeviceStatistic deviceStatistic = null;
        try {
            deviceStatistic = deviceManager.initDevices();
        } catch (DeviceNotFoundException e) {
            LOGGER.error(ExceptionUtils.getMessage(e));
            throw new IllegalStateException(e);
        }

        return deviceStatistic;
    }


    public void saveSetup() throws PropertiesException {
        try {
            propertiesManager.save(setup, FILE_NAME);
        } catch (PropertiesException e) {
            LOGGER.error("Error save configuration.");
            throw e;
        }
    }

    public void setAudioDevice(String deviceName) {
        if (getSetup().getAudioDevice() != null && getSetup().getAudioDevice().getName() != null) {
            setup.getAudioDevice().setName(deviceName);
        } else {
            setup.setAudioDevice(new AudioDevice(deviceName));
        }
    }

    public void setVideoDevice(String deviceName) {
        if (getSetup().getVideoDevice() != null && getSetup().getVideoDevice().getName() != null) {
            setup.getVideoDevice().setName(deviceName);
        } else {
            setup.setVideoDevice(new VideoDevice(deviceName));
        }
    }


    @Override
    public boolean testDevice(String device) {
        boolean ready = true;
        try {
            deviceManager.getCaptureDeviceInfo(device);
        } catch (DeviceNotFoundException e) {
            ready = false;
            LOGGER.error(ExceptionUtils.getMessage(e));
        }
        return ready;
    }


    // TODO create copy object... maybe need to use memento
    private void setCleanState() {
        controlPanel.getBtnOk().setEnabled(false);
        controlPanel.getBtnApply().setEnabled(false);
    }

    private void setEditState() {
        controlPanel.getBtnOk().setEnabled(true);
        controlPanel.getBtnApply().setEnabled(true);
    }
}
