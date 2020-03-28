package com.linushestermeyer.hmi.common.Messages;


import com.linushestermeyer.hmi.common.EnvironmentObjects.Software;

public class SoftwareInstallationPackage extends SoftwareMessage {
    private Software software;

    public SoftwareInstallationPackage(String softwareID, Software software) {
        super(softwareID);
        this.software=software;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }
}
