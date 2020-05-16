package com.linushestermeyer.hmi.network;

import Messages.IMessage;

public interface IMessageReader {
    void readSocket(IMessage msg);
}
