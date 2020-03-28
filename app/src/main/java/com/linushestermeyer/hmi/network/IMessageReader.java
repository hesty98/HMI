package com.linushestermeyer.hmi.network;

import com.linushestermeyer.hmi.common.Messages.*;

public interface IMessageReader {
    void readSocket(IMessage msg);
}
