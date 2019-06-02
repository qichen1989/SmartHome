package ui.tabs;

import model.devices.Device;
import ui.ButtonNameList;
import ui.SmartHomeUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsTab extends Tab {

    private static final String SETTINGS_TEXT = "Control Panel";

    private static final int COLS_IN_ROW = 3;
    private static final int ROW_HEIGHT = 70;
    private static final int LEFT_PADDING = 70;
    private static final int RIGHT_PADDING = 40;
    private static final int TOP_PADDING = 10;
    private static final int BOTTOM_PADDING = 10;
    private static final int NUMBER_OF_TICKS = 5;
    private static final int CENTER = SwingConstants.CENTER;
    private static final int TOP = SwingConstants.TOP;

    private Border border;
    private GridLayout rowLayout;

    // creates settings tab that sets on/off and temperature
    public SettingsTab(SmartHomeUI controller) {
        super(controller);

        border = BorderFactory.createEmptyBorder(TOP_PADDING , LEFT_PADDING, BOTTOM_PADDING, RIGHT_PADDING);
        rowLayout = new GridLayout(1, COLS_IN_ROW);

        JLabel settings = new JLabel(SETTINGS_TEXT);
        settings.setFont(new Font("Serif", Font.BOLD, 30));
        add(settings);

        JPanel headerRow = new JPanel(rowLayout);
        headerRow.setPreferredSize(new Dimension(SmartHomeUI.WIDTH - LEFT_PADDING, ROW_HEIGHT));
        Font f = new Font("Serif",Font.BOLD,15);
        JLabel titleAppliance = new JLabel("Device", CENTER);
        titleAppliance.setFont(f);
        JLabel titleTemperature = new JLabel("Temperature", CENTER);
        titleTemperature.setFont(f);
        JLabel titleOnOff = new JLabel("On/Off", CENTER);
        titleOnOff.setFont(f);
        headerRow.add(titleAppliance);
        headerRow.add(titleTemperature);
        headerRow.add(titleOnOff);
        add(headerRow);

        for (Device a : controller.getSmartHome().getAllDevices()) {
            this.add(makeOneDeviceControl(a));
        }

    }

    // create single row to control appliance on/off and temperature
    private JPanel makeOneDeviceControl(Device a) {
        JPanel row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(SmartHomeUI.WIDTH, ROW_HEIGHT));
        row.setBorder(border);

        JLabel name = new JLabel(a.getClass().getSimpleName());
        name.setVerticalAlignment(TOP);

        JSlider slider = getTempSlider(a);
        JPanel onOffButtons = getOnOffButtons(a, slider);

        row.add(name);
        row.add(slider);
        row.add(onOffButtons);
        return row;
    }


    // return slider to control temperature
    private JSlider getTempSlider(Device a) {
        JSlider slider = new JSlider(a.minTemp(), a.maxTemp(), a.getTemp());
        slider.setMajorTickSpacing(Math.round((a.maxTemp() - a.minTemp()) / NUMBER_OF_TICKS));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderListener(a));
        return slider;
    }


    // create on/off buttons in group with listeners for this appliance
    private JPanel getOnOffButtons(Device a, JSlider slider) {
        JRadioButton on = new JRadioButton(ButtonNameList.ON.getValue(), a.isRunning());
        JRadioButton off = new JRadioButton(ButtonNameList.OFF.getValue(), !a.isRunning());

        ButtonGroup buttons = new ButtonGroup();
        buttons.add(on);
        buttons.add(off);
        on.addActionListener(new OnOffListener(a, slider, buttons));
        off.addActionListener(new OnOffListener(a, slider, buttons));

        JPanel onOffButton = new JPanel();
        onOffButton.add(on);
        onOffButton.add(off);
        return onOffButton;
    }


    private class OnOffListener implements ActionListener {
        Device app;
        JSlider slider;
        ButtonGroup parent;

        public OnOffListener(Device a, JSlider s, ButtonGroup p) {
            app = a;
            slider = s;
            parent = p;
        }
        // if 'on' button clicked, turns on
        // if 'off' button clicked, turns off
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals(ButtonNameList.ON.getValue())) {
                app.turnOn();
                app.setTemp(slider.getValue());
            } else if (command.equals(ButtonNameList.OFF.getValue())) {
                app.turnOff();
            }
        }
    }
    private class SliderListener implements ChangeListener {
        Device a;

        public SliderListener(Device a) {
            this.a = a;
        }

        // set temperature of appliance to value of slider
        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            if (!slider.getValueIsAdjusting()) {
                a.setTemp(slider.getValue());
            }
        }
    }
}
