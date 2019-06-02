package model;

import model.devices.Device;

import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private List<Device> devices;
    private List<Device> devicesOn;

    public SmartHome(){
        devices = new ArrayList<>();
    }
    public List<Device> getAllDevices(){
        return devices;
    }

    //returns a list of devices which are running
    public List<Device> getDevicesRunning(){
        devicesOn = new ArrayList<>();
        for(Device a : devices){
            if(a.isRunning()){
                devicesOn.add(a);
            }
        }
        return devicesOn;
    }

    // add a device to SmartHome
    public void install(Device a){
        devices.add(a);
    }

    // set a to given temp
    public void setTemp(Device a, int temp){
        Device b = devices.get(devices.indexOf(a));
        b.setTemp(temp);
    }
    // set a to 'on'
    public void turnOn(Device a){
        a.turnOn();
    }
    // set a to 'off'
    public void turnOff(Device a){
        a.turnOff();
    }

    // return a String list of devices which are running
    public String devicesRunningStatus(){
        List<Device> running = getDevicesRunning();
        StringBuilder status = new StringBuilder();

        for(Device a: running){
            status.append("\n").append(a.status());
        }
        return status.toString();
    }
}
