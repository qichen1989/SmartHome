package ui.tabs;

import ui.ButtonNameList;
import ui.SmartHomeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReportTab extends Tab {

    private static final String REPORT_MESSAGE = "Latest report: Today at ";

    private JScrollPane reportPane;
    private JTextArea reportText;
    private JLabel reportMessage;
    private String time;
    private SimpleDateFormat timeFormat;
    private Date date;

    // create report tab with buttons and status report
    public ReportTab(SmartHomeUI controller){
        super(controller);

        date = Calendar.getInstance().getTime();
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        updateTime();
        placeButton();
        JPanel reportBlock = new JPanel(new GridLayout(2, 1));
        reportBlock.setSize(SmartHomeUI.WIDTH *5 / 6, SmartHomeUI.HEIGHT * 5/ 6);
        reportMessage = new JLabel("");
        reportPane = new JScrollPane(new JTextArea(5, 40));
        reportText = new JTextArea("", 5, 40);
        reportText.setVisible(true);
        reportBlock.add(reportMessage);
        reportBlock.add(reportPane);
        add(reportBlock);
    }


    //add a generate report button
    private void placeButton(){
        JButton b1 = new JButton(ButtonNameList.GENERATE_REPORT.getValue());
        JPanel buttonRow = createButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 5);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if(buttonPressed.equals(ButtonNameList.GENERATE_REPORT.getValue())){
                    updateTime();
                    String message = REPORT_MESSAGE + time;
                    reportMessage.setText(message);
                    reportText.setText(getController().getSmartHome().devicesRunningStatus());
                    reportPane.setViewportView(reportText);
                }
            }
        });

        this.add(buttonRow);
    }

    // updates current time in format HH:mm:ss
    private void updateTime(){
        date = Calendar.getInstance().getTime();
        time = timeFormat.format(date);
    }
}
