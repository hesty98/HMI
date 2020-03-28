package com.linushestermeyer.hmi.network;

import com.linushestermeyer.hmi.common.Messages.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connection implements IConnection {

    private static boolean isConnected;

    private static Connection single_instance = null;

    public static final String HOST = "IP Meines PC's";
    public static final int PORT = 22898;

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private IMessageReader messageReader =null;

    private Connection() {

    }

    public void setMessageReader(IMessageReader messageReader){
        if(this.messageReader ==null) {
            this.messageReader = messageReader;
        }
    }

    public static Connection getInstance(){
        if (single_instance == null){
            single_instance = new Connection();
        }
        return single_instance;
    }

    @Override
    public void startConnection() {
        if(!isConnected){
            initBootstrap(HOST, PORT);
        }
        while(isConnected){
            readPort();
        }
    }

    @Override
    public void stopConnection() {

    }

    @Override
    public void initBootstrap(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.input = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(IMessage msg) {
        try {
            //TODO: msg and parse this
            out.writeUTF("Hello World");
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void readPort() {
        try {
            String str =input.readUTF();
            //TODO: parse Object from binary
            messageReader.readSocket(null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isRunning(){
        return isConnected;
    }

}
