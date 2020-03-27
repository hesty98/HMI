package com.linushestermeyer.hmi.view;

public class Software {
    private String name;
    private String desc;
    private String price;

    public Software(String name, String desc, String price){
        this.name=name;
        this.desc =desc;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }
}
