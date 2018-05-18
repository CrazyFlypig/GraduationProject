package com.xiyou.SerialPort.tool;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cc
 */

public class TimeTool implements Runnable {
    private boolean isGoon = true;
    private JLabel timeLabel = null;
    private String systime = null;

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public void startTime() {
        Thread t = new Thread(this);
        t.start();
    }

    public void stopTime() {
        isGoon = false;
    }

    @Override
    public void run() {
        while(isGoon) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式

            systime = dateFormat.format(now);
            timeLabel.setText(systime);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
