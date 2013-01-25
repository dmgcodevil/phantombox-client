package com.git.client.ui.util;

import com.git.client.ui.frame.CallFrame;
import com.git.domain.api.IConnection;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * CallFrameManager.
 */
public class CallFrameManager {

    private Map<IConnection, CallFrame> callFramesPool;

    public Map<IConnection, CallFrame> getCallFramesPool() {
        return callFramesPool;
    }

    /**
     * Add callFrame to pool.
     *
     * @param connection {@link IConnection}
     * @param callFrame  {@link CallFrame}
     */
    public void add(IConnection connection, CallFrame callFrame) {
        if (callFramesPool == null) {
            callFramesPool = new HashMap();
        }
        callFramesPool.put(connection, callFrame);
    }

    /**
     * Romove callFrame from pool sby connection.
     *
     * @param connection {@link IConnection}
     */
    public void remove(IConnection connection) {
        if (MapUtils.isNotEmpty(callFramesPool)) {
            callFramesPool.remove(connection);
        }
    }

    /**
     * Get callFrame by connection.
     *
     * @param connection {@link IConnection}
     * @return callFrame
     */
    public CallFrame get(IConnection connection) {
        CallFrame callFrame = null;
        if (MapUtils.isNotEmpty(callFramesPool)) {
            callFrame = callFramesPool.get(connection);
        }
        return callFrame;
    }
}
