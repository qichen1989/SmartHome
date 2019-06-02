package ui.tabs;

import ui.ButtonNameList;
import ui.SmartHomeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeTab extends Tab {

    private static final String INIT_GREETING = "Dear user, welcome home!";
    private JLabel greeting;
    private JLabel picture;
    private ImageIcon img;

    // build a welcome tab for console with buttons and a greeting
    public WelcomeTab(SmartHomeUI controller) {
        super(controller);
        setLayout(new GridLayout(3, 1));
        placeGreeting();
        placeIcon();
        placeStatusButton();
    }

    // create greeting at top of console
    private void placeGreeting(){
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        Font f = new Font("TimesRoman",Font.PLAIN,30);
        greeting.setSize(WIDTH, HEIGHT);
        greeting.setFont(f);
        greeting.setForeground(Color.blue);
        this.add(greeting);
    }

    private void placeIcon() {
        img = new ImageIcon("C:\\Users\\Qi\\Documents\\SmartHome\\src\\icon\\pngfind.com-smart-home-png-3683550 (1).png");
        picture = new JLabel(img, JLabel.CENTER);
        picture.setSize(WIDTH, HEIGHT);
        this.add(picture);
    }

    // create a button that switches to the report tab on the console
    private void placeStatusButton(){
        JPanel statusBlock = new JPanel();
        JButton statusButton = new JButton(ButtonNameList.GO_TO_REPORT.getValue());
        statusBlock.add(createButtonRow(statusButton));
        statusButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if(buttonPressed.equals(ButtonNameList.GO_TO_REPORT.getValue())){
                    getController().getTabbedPane().setSelectedIndex(SmartHomeUI.REPORT_TAB_INDEX);
                }
            }
        });
        this.add(statusBlock);
    }
}
