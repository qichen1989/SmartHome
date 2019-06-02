package ui.tabs;

import ui.SmartHomeUI;
import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {

    private SmartHomeUI controller;

    public Tab(SmartHomeUI controller){
        this.controller = controller;
    }

    //create and return row with button included
    public JPanel createButtonRow(JButton button){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button);
        return panel;
    }

    // return the SmartHomeUI controller
    public SmartHomeUI getController(){
        return controller;
    }
}
