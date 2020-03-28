package com.linushestermeyer.hmi.common.Messages;

import com.linushestermeyer.hmi.common.EnvironmentObjects.ServiceDescription;

public class ServiceVerificationMessage extends ServiceMessage {
    private ServiceDescription desc;
    private boolean verified;
    private String updated_car_manifest;
    private long inquiryID;
    //TODO: look, what other UPTANE features can be integrated

    public ServiceVerificationMessage(ServiceDescription desc, boolean verified, String updated_car_manifest,long inquiryID, String serviceSoftwareID) {
        super(serviceSoftwareID);
        this.desc = desc;
        this.verified = verified;
        this.inquiryID=inquiryID;
        this.updated_car_manifest=updated_car_manifest;
    }

    public ServiceDescription getDesc() {
        return desc;
    }

    public void setDesc(ServiceDescription desc) {
        this.desc = desc;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getUpdated_car_manifest() {
        return updated_car_manifest;
    }

    public void setUpdated_car_manifest(String updated_car_manifest) {
        this.updated_car_manifest = updated_car_manifest;
    }

    public long getInquiryID() {
        return inquiryID;
    }

    public void setInquiryID(long inquiryID) {
        this.inquiryID = inquiryID;
    }
}