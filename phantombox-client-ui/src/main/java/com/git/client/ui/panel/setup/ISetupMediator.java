package com.git.client.ui.panel.setup;


import com.git.client.api.domain.IDeviceStatistic;
import com.git.client.api.exception.PropertiesException;
import com.git.client.api.webcam.device.IDeviceManager;
import com.git.client.api.webcam.device.IPropertiesManager;
import com.git.domain.impl.Setup;

import javax.swing.JFrame;

/**
 * Setup mediator interface.
 * <p/>
 * User: dmgcodevil
 * Date: 11/11/12
 * Time: 4:37 PM
 */
public interface ISetupMediator {

    /**
     * Gets setup.
     *
     * @return {@link Setup}
     */
    Setup getSetup();

    /**
     * Sets setup.
     *
     * @param setup {@link Setup}
     */
    void setSetup(Setup setup);

    /**
     * Gets properties manager.
     *
     * @return {@link IPropertiesManager}
     */
    IPropertiesManager getPropertiesManager();

    /**
     * Sets properties manager.
     *
     * @param propertiesManager {@link IPropertiesManager}
     */
    void setPropertiesManager(IPropertiesManager propertiesManager);

    JFrame getSetupFrame();

    void setSetupFrame(JFrame setupFrame);

    /**
     * Gets device tab.
     *
     * @return {@link DeviceTab}
     */
    DeviceTab getDeviceTab();

    /**
     * Sets device tab.
     *
     * @param deviceTab {@link DeviceTab}
     */
    void setDeviceTab(DeviceTab deviceTab);

    /**
     * Get control panel.
     *
     * @return {@link ControlPanel}
     */
    ControlPanel getControlPanel();

    /**
     * Sets control panel.
     *
     * @param controlPanel {@link ControlPanel}
     */
    void setControlPanel(ControlPanel controlPanel);

    /**
     * Saves setup.
     *
     * @throws PropertiesException
     */
    void saveSetup() throws PropertiesException;

    /**
     * Get device manager.
     *
     * @return {@link IDeviceManager}
     */
    IDeviceManager getDeviceManager();

    /**
     * Set device manager.
     *
     * @param deviceManager {@link IDeviceManager}
     */
    void setDeviceManager(IDeviceManager deviceManager);

    void setAudioDevice(String deviceName);

    void setVideoDevice(String deviceName);

    /**
     * Init devices.
     */
    IDeviceStatistic initDevices();

    /**
     * Test device.
     *
     * @param device device
     * @return boolean
     */
    boolean testDevice(String device);
}
