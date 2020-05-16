package com.linushestermeyer.hmi.network;

import Messages.IMessage;

public class MessageHandler {
    private static MessageHandler single_instance = null;
    private Connection connection;
    public String s;

    // private constructor restricted to this class itself
    private MessageHandler()
    {
        s = "Hello I am a string part of MessageHandler class";
    }

    // static method to create instance of MessageHandler class
    public static MessageHandler getInstance()
    {
        if (single_instance == null)
            single_instance = new MessageHandler();

        return single_instance;
    }

    public void manageMessage(IMessage msg) {

    }

    public void setConnection(Connection c){
        this.connection=c;
    }

    public void sendToSam(IMessage msg) {
        connection.sendMessage(msg);
    }
}
