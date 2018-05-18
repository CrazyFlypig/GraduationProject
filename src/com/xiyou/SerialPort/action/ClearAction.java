package com.xiyou.SerialPort.action;

import com.xiyou.SerialPort.tool.ShowDataUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author cc
 */

public class ClearAction implements IElements {
    /**
     * 全部清空
     */
    public static void allClear() {
        BtnAllClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ShowDataUtil.initArray(ShowResultAction.getShowString_000());//清空数组
                ShowResultAction.setIndex_000(7);//调整指针
                jlblTestTimeDayText.setText("00");//天
                jlblTestTimeHourText.setText("00");//小时
                jlblTestTimeMinuteText.setText("00");//分钟
                jlblTestTimeSecondText.setText("00");//秒

                ShowDataUtil.initArray(ShowResultAction.getShowString_001());//清空数组
                ShowResultAction.setIndex_001(7);//调整指针
                jlblReceiveDataRateText.setText("0.0000000");

                ShowDataUtil.initArray(ShowResultAction.getShowString_010());//清空数组
                ShowResultAction.setIndex_010(7);//调整指针
                jlblErrorCodeRateText.setText("0.000000");
                jlblErrorCodeRateTextX.setText("0");

                ShowDataUtil.initArray(ShowResultAction.getShowString_011());//清空数组
                ShowResultAction.setIndex_011(7);//调整指针
                jlblEverySecondErrorCodeText.setText("00000000");

                ShowDataUtil.initArray(ShowResultAction.getShowString_100());//清空数组
                ShowResultAction.setIndex_100(7);//调整指针
                jlblEverySecondErrorCodeRateText.setText("0.000000");
                jlblEverySecondErrorCodeRateTextX.setText("0");
            }
        });
    }

    /**
     * 单行清空
     */
    public static void singleLineClear() {
        //清零按钮

        /**
         * 测试时间清零按钮
         */
        jbtnTestTimeClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowDataUtil.initArray(ShowResultAction.getShowString_000());//清空数组
                ShowResultAction.setIndex_000(7);//调整指针
                jlblTestTimeDayText.setText("00");//天
                jlblTestTimeHourText.setText("00");//小时
                jlblTestTimeMinuteText.setText("00");//分钟
                jlblTestTimeSecondText.setText("00");//秒
            }
        });

        /**
         * 接收数据速率清零按钮
         */
        jbtnReceiveDataRateClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowDataUtil.initArray(ShowResultAction.getShowString_001());//清空数组
                ShowResultAction.setIndex_001(7);//调整指针
                jlblReceiveDataRateText.setText("0.0000000");
            }
        });

        /**
         * 误码率清零按钮
         */
        jbtnErrorCodeRateClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowDataUtil.initArray(ShowResultAction.getShowString_010());//清空数组
                ShowResultAction.setIndex_010(7);//调整指针
                jlblErrorCodeRateText.setText("0.000000");
                jlblErrorCodeRateTextX.setText("0");
            }
        });

        /**
         * 秒误码个数
         */
        jbtnEverySecondErrorCodeClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowDataUtil.initArray(ShowResultAction.getShowString_011());//清空数组
                ShowResultAction.setIndex_011(7);//调整指针
                jlblEverySecondErrorCodeText.setText("00000000");
            }
        });

        /**
         * 秒误码率清零按钮
         */
        jbtnEverySecondErrorCodeRateClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowDataUtil.initArray(ShowResultAction.getShowString_100());//清空数组
                ShowResultAction.setIndex_100(7);//调整指针
                jlblEverySecondErrorCodeRateText.setText("0.000000");
                jlblEverySecondErrorCodeRateTextX.setText("0");
            }
        });
    }
}
