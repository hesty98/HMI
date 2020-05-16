package com.linushestermeyer.hmi.network;

import Messages.IMessage;

public interface IConnection {
    /**
     * Starts the Connection with the Server
     */
    void startConnection();

    /**
     * Cancels the connection with the Server
     */
    void stopConnection();


    /**
     * Creates the connection with the server.
     *
     * @param host serverhost
     * @param port serverport
     */
    void initBootstrap(String host, int port);


    /**
     * Send a message from the ControlUnit to the Server
     *
     * @param out message
     */
    void sendMessage(IMessage out);

    /**
     * Listens to the port, parses incoming messages and passes them to the controller.
     */
    void readPort();
}
