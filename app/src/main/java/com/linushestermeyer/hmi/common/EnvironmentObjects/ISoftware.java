package com.linushestermeyer.hmi.common.EnvironmentObjects;

import com.linushestermeyer.hmi.common.Messages.*;

import java.io.Serializable;

public interface ISoftware extends Serializable {
    /**
     * This message is implemented by every created software. The software needs to further decide on what to happen with specific messages.
     * @param msg
     */
    void handleMessage(IMessage msg);
}
