package com.xiyou.SerialPort.demo;

import com.xiyou.SerialPort.action.CreatFileAction;
import com.xiyou.SerialPort.controller.ActionController;
import com.xiyou.SerialPort.tool.FileOperator;
import com.xiyou.SerialPort.view.MainView;

/**
 * @author cc
 */

public class Demo {
    public static void main(String[] args) {
        try {
            MainView.getInstance();//实例化界面
            ActionController.startActionListening();//添加 监听事件
            CreatFileAction.creat();//创建文件
            FileOperator.setTargetRxFile();//设置文件源
            FileOperator.setTargetTxFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
