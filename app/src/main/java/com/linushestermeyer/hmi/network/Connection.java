package com.linushestermeyer.hmi.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import Messages.IMessage;
import Messages.ServiceRegistrationMessage;

public class Connection implements IConnection {
    //todo: remove lower line
    private boolean firstRegistration;

    private static boolean isConnected;

    private static Connection single_instance = null;

    public static final String HOST = "192.168.178.145";
    public static final int PORT = 22620;

    private Socket socket = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream out = null;
    private IMessageReader messageReader =null;

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
        try{
            initBootstrap(HOST, PORT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void stopConnection() {
        Log.e("Tag", "connection stopped");
        try {
            input.close();
            out.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initBootstrap(String host, int port) {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Log.e("TAG","Trying to Connect to SAM.");
                    socket = new Socket(host, port);
                    input = new ObjectInputStream(socket.getInputStream());
                    out = new ObjectOutputStream(socket.getOutputStream());
                    Log.e("TAG", "Connection Successful.");
                    isConnected=true;
                    readPort();
                }catch(Exception e){
                    Log.e("Exception", e+"");
                    //isConnected=false;
                }
                return null;
            }
        }.execute();

    }

    @Override
    public void sendMessage(IMessage msg) {
        AsyncTask task =new  AsyncTask<Object,
                Object,
                Object>(){

            @Override
            protected Object doInBackground(Object... voids) {
                try {
                    Log.e("DEBUG", "trying to write and flush.");
                        out.writeObject(msg);
                        out.flush();
                        Log.e("SUCCESS", "sent Message");
                }catch(Exception e){
                    Log.e("Exception", e+"");
                }
                return null;
            }
        };
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void readPort() {
        while(isRunning()) {
            try {
                Log.e("IsRunning:", input+"");
                Object msg =  input.readObject();
                Log.e("SUCCESS", "Received "+msg);
                if(msg instanceof IMessage)
                    messageReader.readSocket((IMessage)msg);
            } catch (Exception e) {
                e.printStackTrace();
                isConnected=false;
            }
        }
        stopConnection();
        //Log.e()
        //startConnection();
    }

    public boolean isRunning(){
        return isConnected;
    }

}
