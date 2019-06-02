package tests;


import model.SmartHome;
import model.devices.AC;
import model.devices.Oven;
import model.devices.Refrigerator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SmartHomeTest {

    private SmartHome testHome;
    private Oven testOven;
    private Refrigerator testFridge;
    private AC testAC;


    @Before
    public void setUp(){
        testHome = new SmartHome();
        testFridge = new Refrigerator(10);
        testOven = new Oven(0);
        testAC = new AC(25);

        testHome.install(testFridge);
        testHome.install(testAC);
        testHome.install(testOven);
        testFridge.turnOn();
        testAC.turnOn();
    }

    @Test
    public void testConstructor(){
        testHome = new SmartHome();
        assertEquals(0, testHome.getAllDevices().size());
    }


    @Test
    public void testInstall(){
        testHome = new SmartHome();
        testHome.install(testOven);
        assertEquals(1, testHome.getAllDevices().size());
        assertEquals(0, testHome.getDevicesRunning().size());
        testHome.install(testFridge);
        testHome.install(testAC);
        assertEquals(3, testHome.getAllDevices().size());
        assertEquals(2, testHome.getDevicesRunning().size());
    }

    @Test
    public void testTurnOnAndOff(){
        testHome.turnOn(testOven);
        int index = testHome.getDevicesRunning().indexOf(testOven);
        assertTrue(testHome.getDevicesRunning().get(index).isRunning());
        testHome.setTemp(testOven, 300);
        assertEquals(300, testOven.getTemp());
        testHome.turnOff(testOven);
        testHome.turnOff(testFridge);
        testHome.turnOff(testAC);
        assertEquals(0, testHome.getDevicesRunning().size());
    }

    @Test
    public void testSetTemp(){
        testHome.setTemp(testOven, 300);
        assertEquals(300, testOven.getTemp());
        testHome.setTemp(testFridge, 10);
        assertEquals(10, testFridge.getTemp());
        testHome.setTemp(testAC, 20);
        assertEquals(20, testAC.getTemp());
    }


    @Test
    public void testAllAppStatus(){
        StringBuilder status = new StringBuilder();
        status.append("\nFridge temperature is 10.");
        status.append("\nAir Conditioning temperature is 25.");

        assertEquals(2, testHome.getDevicesRunning().size());
        assertEquals(status.toString(), testHome.devicesRunningStatus());
        testHome.turnOn(testOven);
        status.append("\nOven temperature is 0.");
        assertEquals(status.toString(), testHome.devicesRunningStatus());
    }
}
