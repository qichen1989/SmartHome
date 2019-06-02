package model.devices;

public class Refrigerator implements Device {
    private int fridgeTemp;
    private int freezerTemp;
    private boolean running;

    public Refrigerator(int fridgeTemp){
        this.fridgeTemp = fridgeTemp;
        this.freezerTemp = -5;
    }

    public void setTemp(int fridgeTemp) {
        this.fridgeTemp = fridgeTemp;
    }
    public void setFreezerTemp(int freezerTemp) {
        this.freezerTemp = freezerTemp;
    }
    // return the fridge temperature
    public int getTemp() {
        return fridgeTemp;
    }
    // return the freezer temperature
    public int getFreezerTemp() {
        return freezerTemp;
    }
    // return the min fridge temperature
    public int minTemp() {
        return 0;
    }
    // return the max fridge temperature
    public int maxTemp() {
        return 10;
    }
    // return true if refrigerator is running
    public boolean isRunning() {
        return running;
    }
    public void turnOn() {
        running = true;
    }
    public void turnOff() {
        running = false;
    }
    // return refrigerator status
    public String status() {
        return "Fridge temperature is " + getTemp() + ".";
    }
}

