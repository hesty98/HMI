package com.linushestermeyer.hmi.common.Messages;


import com.linushestermeyer.hmi.common.Actions.ActionEnums;

public class ServiceActionCommand extends ServiceMessage {
    private ActionEnums action;
    private String serviceID;

    public ServiceActionCommand(ActionEnums action, String serviceID, String serviceSoftwareID) {
        super(serviceSoftwareID);
        this.action = action;
        this.serviceID = serviceID;

    }

    public ActionEnums getAction() {
        return action;
    }

    public void setAction(ActionEnums action) {
        this.action = action;
    }

    public String getCommunicatingService() {
        return serviceID;
    }

    public void setCommunicatingService(String serviceID) {
        this.serviceID = serviceID;
    }
}