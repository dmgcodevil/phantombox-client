package com.git.client.api.ui;

import com.git.broker.api.domain.IJmsExchanger;

/**
 * Enter class description.
 * <p/>
 * Date: 1/25/13
 * Time: 11:54 PM
 *
 * @author dmgcodevil
 */

public interface ISwingJmsExchanger extends IJmsExchanger {

    /**
     * Gets mediator.
     *
     * @return {@link IMediator}
     */
    IMediator getMediator();

    /**
     * Sets  mediator
     *
     * @param mediator mediator.
     */
    void setMediator(IMediator mediator);
}
