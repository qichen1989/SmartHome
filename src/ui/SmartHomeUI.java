package ui;

import model.SmartHome;
import model.devices.Device;
import model.devices.Oven;
import model.devices.AC;
import model.devices.Refrigerator;
import ui.tabs.WelcomeTab;
import ui.tabs.ReportTab;
import ui.tabs.SettingsTab;
import javax.swing.*;

public class SmartHomeUI extends JFrame {
    private static final int WELCOME_TAB_INDEX = 0;
    private static final int SETTINGS_TAB_INDEX = 1;
    public static final int REPORT_TAB_INDEX = 2;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;
    private SmartHome smartHome;

    public static void main(String[] args) {
        new SmartHomeUI();
    }


    // create SmartHomeUI
    private SmartHomeUI() {
        super("SmartHome Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        smartHome = new SmartHome();
        loadDevices();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    // return SmartHome object
    public SmartHome getSmartHome(){
        return smartHome;
    }

    private void loadDevices(){
        Device fridge = new Refrigerator(5);
        Device oven = new Oven(0);
        Device ac = new AC(25);

        smartHome.install(fridge);
        smartHome.install(oven);
        smartHome.install(ac);
    }

    // add welcome tab, setting tab and report tab to this UI
    private void loadTabs() {
        JPanel homeTab = new WelcomeTab(this);
        JPanel settingsTab = new SettingsTab(this);
        JPanel reportTab = new ReportTab(this);

        sidebar.add(homeTab, WELCOME_TAB_INDEX);
        sidebar.setTitleAt(WELCOME_TAB_INDEX, "Welcome");
        sidebar.add(settingsTab, SETTINGS_TAB_INDEX);
        sidebar.setTitleAt(SETTINGS_TAB_INDEX, "Settings");
        sidebar.add(reportTab, REPORT_TAB_INDEX);
        sidebar.setTitleAt(REPORT_TAB_INDEX, "Report");
    }

    // return sidebar of this UI
    public JTabbedPane getTabbedPane(){
        return sidebar;
    }
}
