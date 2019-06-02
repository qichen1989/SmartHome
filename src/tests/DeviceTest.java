package tests;

import model.devices.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class DeviceTest {
    private Oven testOven;
    private Refrigerator testFridge;
    private AC testAC;
    private List<Device> devices;

    @Before
    public void setUp(){
        testOven = new Oven(0);
        testFridge = new Refrigerator(5);
        testAC = new AC(25);
        devices = new ArrayList<>();
        devices.add(testOven);
        devices.add(testFridge);
        devices.add(testAC);
    }

    @Test
    public void testConstructor(){
        assertEquals(0, testOven.getTemp());
        assertEquals(5, testFridge.getTemp());
        assertEquals(-5, testFridge.getFreezerTemp());
        assertEquals(25, testAC.getTemp());
        for(Device a: devices){
            assertFalse(a.isRunning());
        }
    }

    @Test
    public void testTurnOnOff(){
        testAC.turnOn();
        assertTrue(testAC.isRunning());
        testAC.turnOff();
        assertFalse(testAC.isRunning());
        testOven.turnOn();
        assertTrue(testOven.isRunning());
        testOven.turnOff();
        assertFalse(testOven.isRunning());
        testFridge.turnOn();
        assertTrue(testFridge.isRunning());
        testFridge.turnOff();
        assertFalse(testFridge.isRunning());
    }

    @Test
    public void testTemp(){
        testAC.setTemp(28);
        assertEquals(28, testAC.getTemp());
        testFridge.setFreezerTemp(-5);
        assertEquals(-5, testFridge.getFreezerTemp());
        testOven.setTemp(300);
        assertEquals(300, testOven.getTemp());
    }

    @Test
    public void testStatus(){
        assertEquals(testFridge.status(), "Fridge temperature is 5.");
        assertEquals(testOven.status(), "Oven temperature is 0.");
        assertEquals(testAC.status(), "Air Conditioning temperature is 25.");
    }

    @Test
    public void testMinMaxTemp(){
        assertEquals(testFridge.minTemp(), 0);
        assertEquals(testAC.minTemp(), 20);
        assertEquals(testOven.minTemp(), 0);

        assertEquals(testFridge.maxTemp(), 10);
        assertEquals(testOven.maxTemp(), 450);
        assertEquals(testAC.maxTemp(), 30);
    }

    @Test
    public void testOvenSettings(){
        assertTrue(testOven.getMode());
        testOven.bake();
        assertTrue(testOven.getMode());
        testOven.broil();
        assertFalse(testOven.getMode());
    }
}
