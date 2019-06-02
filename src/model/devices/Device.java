package model.devices;

public interface Device {

    boolean isRunning();

    void turnOn();

    void turnOff();

    void setTemp(int temp);

    int getTemp();

    int minTemp();

    int maxTemp();

    String status();

}
