package model.devices;

public class AC implements Device {
    private int temp;
    private boolean running;

    public AC(int temp){
        this.temp = temp;
    }


    public void setTemp(int temp) {
        this.temp = temp;
    }
    public int getTemp() {
        return temp;
    }

     public int minTemp() {
        return 20;
     }

    public int maxTemp() {
        return 30;
    }
    // return true if AC is running
    public boolean isRunning() {
        return running;
    }
    public void turnOn() {
        running = true;
    }
    public void turnOff() {
        running = false;
    }
    // return AC status
    public String status() {
        return "Air Conditioning temperature is " + getTemp() + ".";
    }
}
