package com.git.client.ui.util;

import com.git.client.ui.frame.CallFrame;
import com.git.domain.api.IContact;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * CallFrameManager.
 */
public class CallFrameManager {

    private Map<IContact, CallFrame> callFramesPool;

    public Map<IContact, CallFrame> getCallFramesPool() {
        return callFramesPool;
    }

    /**
     * Add callFrame to pool.
     *
     * @param contact {@link IContact}
     * @param callFrame  {@link CallFrame}
     */
    public void add(IContact contact, CallFrame callFrame) {
        if (callFramesPool == null) {
            callFramesPool = new HashMap();
        }
        callFramesPool.put(contact, callFrame);
    }

    /**
     * Romove callFrame from pool sby contact.
     *
     * @param contact {@link IContact}
     */
    public CallFrame remove(IContact contact) {
        CallFrame callFrame = null;
        if (MapUtils.isNotEmpty(callFramesPool)) {
            callFrame = callFramesPool.remove(contact);
        }
        return callFrame;
    }

    /**
     * Get callFrame by contact.
     *
     * @param contact {@link IContact}
     * @return callFrame
     */
    public CallFrame get(IContact contact) {
        CallFrame callFrame = null;
        if (MapUtils.isNotEmpty(callFramesPool)) {
            callFrame = callFramesPool.get(contact);
        }
        return callFrame;
    }
}
