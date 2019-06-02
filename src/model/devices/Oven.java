package model.devices;

public class Oven implements Device {
    private int temp;
    private boolean running;
    private boolean mode;

    public Oven(int temp){
        this.temp = temp;
        mode = true;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
    // set oven to bake mode
    public void bake() {
        mode = true;
    }
    // set oven to broil mode
    public void broil() {
        mode = false;
    }
    public int getTemp() {
        return temp;
    }
    // return the mode
    public boolean getMode() {
        return mode;
    }
    public int minTemp() {
        return 0;
    }
    public int maxTemp() {
        return 450;
    }
    // return true if oven is running
    public boolean isRunning() {
        return running;
    }

    public void turnOn() {
        running = true;
    }
    public void turnOff() {
        running = false;
    }
    // return oven status
    public String status() {
        return "Oven temperature is " + getTemp() + ".";
    }
}
