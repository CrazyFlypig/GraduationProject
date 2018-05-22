package com.xiyou.SerialPort.view;

import com.xiyou.SerialPort.tool.ParamMap;
import com.xiyou.SerialPort.tool.ProjectTool;
import com.xiyou.SerialPort.tool.XMLReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author cc
 */

public class MainView {
    private JFrame mainFrame = null;
    private Container container = null;

    private static Font ToptitleFont = null;//字体
    private static Font normalFont = null;
    private static Font textFont = null;
    private static Font btnFont = null;
    private static Font titleFont = null;
    private static Font unitFont = null;//显示单位的字体
    private JLabel jlblTitle = null;			//标题label
    private JLabel systemTimeLabel = null;		//时间显示

    /***********************参数设置***************************************/
    private JLabel jlblControllOption = null;	//参数设置
    private JLabel jlblTestRate = null;			//测试数据
    private JComboBox<String> jcmbTestRateBox = null;
    private JLabel jlblLineEncoding = null;		//线路编码
    private JComboBox<String> jcmbLineEncoding = null;
    private JLabel jlblErrorCorrectingcodes = null;//纠错编码
    private JComboBox<String> jcmbErrorCorrectingcodes = null;
    private JLabel jlblTestData = null;			//测试数据
    private JComboBox<String> jcmbTestData= null;

    private JButton BtnControllOption = null;//设置
    private JButton BtnTestData = null;//测试
    /***********************参数设置***************************************/

    /************************结果显示************************************/
    private JLabel jlblShowResult = null;//结果显示
    //测试时间
    private JLabel jlblTestTime = null;
    private JLabel jlblTestTimeDayText = null;//XX
    private JLabel jlblTestTimeDayUnit = null;//天
    private JLabel jlblTestTimeHourText = null;//XX
    private JLabel jlblTestTimeHourUnit = null;//小时
    private JLabel jlblTestTimeMinuteText = null;//XX
    private JLabel jlblTestTimeMinuteUnit = null;//分钟
    private JLabel jlblTestTimeSecondText = null;//XX
    private JLabel jlblTestTimeSecondUnit = null;//秒
    private JButton jbtnTestTimeClear = null;
    //接收数据速率
    private JLabel jlblReceiveDataRate = null;
    private JLabel jlblReceiveDataRateText = null;//内容
    private JLabel jlblReceiveDataRateUnit = null;//单位：Mbps
    private JButton jbtnReceiveDataRateClear = null;
    //接收数据误码率（BER）
    private JLabel jlblErrorCodeRate = null;
    private JLabel jlblErrorCodeRateText = null;
    private JLabel jlblErrorCodeRateUnit = null;//单位：*10-
    private JLabel jlblErrorCodeRateTextX = null;//最后一个X
    private JButton jbtnErrorCodeRateClear = null;
    //接收数据秒误码个数（ERR/S）
    private JLabel jlblEverySecondErrorCode = null;
    private JLabel jlblEverySecondErrorCodeText = null;
    private JButton jbtnEverySecondErrorCodeClear = null;
    //接收数据秒误码率（BER/S）
    private JLabel jlblEverySecondErrorCodeRate = null;
    private JLabel jlblEverySecondErrorCodeRateText = null;//部分内容
    private JLabel jlblEverySecondErrorCodeRateUnit = null;//单位：*10-
    private JButton jbtnEverySecondErrorCodeRateClear = null;
    private JLabel jlblEverySecondErrorCodeRateTextX = null;//最后一个X
    private JButton BtnAllClear = null;//全部清空
    /************************结果显示************************************/

    /************************通信设置**********************************/
    private JLabel communicationOption = null;//通信设置
    private JComboBox<String> jcmbSerialPort = null;//串口列表
    private JLabel jlblBaudRate = null;//波特率
    private JComboBox<String> jcmbBaudRate= null;
    private JLabel jlblChechBit = null;//校验位
    private JComboBox<String> jcmbChekBit = null;
    private JLabel jlblStopBit = null;//停止位
    private JComboBox<String> jcmbStopBit= null;

    private JButton jbtnScan = null;//扫描按钮
    private JButton openSerialPort = null;//打开串口按钮
    private JButton closeSerialPort = null;//关闭串口按钮
    /************************通信设置**********************************/

    private JLabel jlblVersionNumber = null;//版本号

    private static MainView mainView = null;

    private static XMLReader reader = null;

    static {
        ToptitleFont = new Font("宋体", Font.BOLD, 26);
        titleFont = new Font("宋体", Font.BOLD, 23);
        normalFont = new Font("微软雅黑", Font.PLAIN, 17);
        textFont = new Font("宋体", Font.PLAIN, 16);
        btnFont = new Font("宋体", Font.PLAIN, 15);
        unitFont = new Font("微软雅黑", Font.BOLD, 18);
    }

    static InputStream is;
    static {
        try {
            reader = new XMLReader();
            is = reader.loadXML("resource/config/paramConfig.xml");
            reader.readXML(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //单例模式
    public static MainView getInstance() throws Exception {
        if(mainView == null) {
            mainView = new MainView();
        }
        return mainView;
    }

    private MainView() throws Exception {
        initFrame();
        reinitFrame();
        loadElementToMap();
    }

    private void loadElementToMap() {
        //主界面
        ProjectTool.addElementByName("mainFrame", mainFrame);
        //系统时间
        ProjectTool.addElementByName("systemTimeLabel", systemTimeLabel);

        //控制设置模块
        ProjectTool.addElementByName("jcmbTestRateBox", jcmbTestRateBox);
        ProjectTool.addElementByName("jcmbLineEncoding", jcmbLineEncoding);
        ProjectTool.addElementByName("jcmbErrorCorrectingcodes", jcmbErrorCorrectingcodes);
        ProjectTool.addElementByName("jcmbTestData", jcmbTestData);
        ProjectTool.addElementByName("BtnControllOption", BtnControllOption);
        ProjectTool.addElementByName("BtnTestData", BtnTestData);

        //结果显示模块
        //测试时间
        ProjectTool.addElementByName("jlblTestTimeDayText", jlblTestTimeDayText);
        ProjectTool.addElementByName("jlblTestTimeHourText", jlblTestTimeHourText);
        ProjectTool.addElementByName("jlblTestTimeMinuteText", jlblTestTimeMinuteText);
        ProjectTool.addElementByName("jlblTestTimeSecondText", jlblTestTimeSecondText);
        ProjectTool.addElementByName("jbtnTestTimeClear", jbtnTestTimeClear);

        //接收数据速率
        ProjectTool.addElementByName("jlblReceiveDataRateText", jlblReceiveDataRateText);
        ProjectTool.addElementByName("jbtnReceiveDataRateClear", jbtnReceiveDataRateClear);

        //误码率
        ProjectTool.addElementByName("jlblErrorCodeRateText", jlblErrorCodeRateText);
        ProjectTool.addElementByName("jlblErrorCodeRateTextX", jlblErrorCodeRateTextX);
        ProjectTool.addElementByName("jbtnErrorCodeRateClear", jbtnErrorCodeRateClear);

        //秒误码个数
        ProjectTool.addElementByName("jlblEverySecondErrorCodeText", jlblEverySecondErrorCodeText);
        ProjectTool.addElementByName("jbtnEverySecondErrorCodeClear", jbtnEverySecondErrorCodeClear);

        //秒误码率
        ProjectTool.addElementByName("jlblEverySecondErrorCodeRateText", jlblEverySecondErrorCodeRateText);
        ProjectTool.addElementByName("jlblEverySecondErrorCodeRateTextX", jlblEverySecondErrorCodeRateTextX);
        ProjectTool.addElementByName("jbtnEverySecondErrorCodeRateClear", jbtnEverySecondErrorCodeRateClear);

        //全部清空
        ProjectTool.addElementByName("BtnAllClear", BtnAllClear);

        //通信设置模块
        ProjectTool.addElementByName("jcmbBaudRate", jcmbBaudRate);
        ProjectTool.addElementByName("jcmbChekBit", jcmbChekBit);
        ProjectTool.addElementByName("jcmbStopBit", jcmbStopBit);
        ProjectTool.addElementByName("openSerialPort", openSerialPort);//关闭串口
        ProjectTool.addElementByName("closeSerialPort", closeSerialPort);//打开串口
        ProjectTool.addElementByName("jbtnScan", jbtnScan);//扫描串口
        ProjectTool.addElementByName("jcmbSerialPort", jcmbSerialPort);//串口下拉列表
    }

    private void reinitFrame() {
        ArrayList<String> rateList = ParamMap.getRateList();
        for(int i = 0; i < rateList.size(); i++) {
            jcmbTestRateBox.addItem(rateList.get(i));
        }

        ArrayList<String> encodingList = ParamMap.getEncodingList();
        for(int i = 0; i < encodingList.size(); i++) {
            jcmbLineEncoding.addItem(encodingList.get(i));
        }

        ArrayList<String> errEncodingList = ParamMap.getErrCorrectCodingList();
        for(int i = 0; i < errEncodingList.size(); i++) {
            jcmbErrorCorrectingcodes.addItem(errEncodingList.get(i));
        }

        ArrayList<String> testDataList = ParamMap.getTestDataList();
        for(int i = 0; i < testDataList.size(); i++) {
            jcmbTestData.addItem(testDataList.get(i));
        }

        ArrayList<String> baudRateList = ParamMap.getBaudRateList();
        for(int i = 0; i < baudRateList.size(); i++) {
            jcmbBaudRate.addItem(baudRateList.get(i));
        }

        jcmbChekBit.addItem("None");
        jcmbChekBit.addItem("Even");
        jcmbChekBit.addItem("Odd");
        jcmbChekBit.addItem("Mark");
        jcmbChekBit.addItem("Space");

        jcmbStopBit.addItem("1");
        jcmbStopBit.addItem("1.5");
        jcmbStopBit.addItem("2");
    }

    private void initFrame() throws Exception {
        mainFrame = new JFrame();
        mainFrame.setLayout(null);
        mainFrame.setSize(12045/15, 8010/15);
        container = mainFrame.getContentPane();

        Image icon = Toolkit.getDefaultToolkit().getImage("resource/window.jpg");
        mainFrame.setIconImage(icon);

        ImageIcon background = new ImageIcon("resource/background.jpg");
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
        JPanel imagePanel = (JPanel) mainFrame.getContentPane();
        imagePanel.setOpaque(false);
        mainFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

        systemTimeLabel = new JLabel("2017-09-27 10:09:57");
        systemTimeLabel.setForeground(Color.gray);
        systemTimeLabel.setFont(ProjectTool.getDigitFont());
        systemTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        systemTimeLabel.setBounds(7900/15, 200/15, 4000/15, 360/15);
        container.add(systemTimeLabel);

        jlblTitle = new JLabel("水下无线光通信测试软件");
        jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTitle.setFont(ToptitleFont);
        jlblTitle.setForeground(new Color(77,108,191));
        jlblTitle.setBounds(2700/15, 120/15, 6000/15, 435/15);
        container.add(jlblTitle);

        jlblControllOption = new JLabel("参数设置");
        jlblControllOption.setBackground(new Color(203, 203, 203));
        jlblControllOption.setFont(titleFont);
        jlblControllOption.setBounds(2120/15, 1080/15, 1455/15, 375/15);
        container.add(jlblControllOption);

        jlblTestRate = new JLabel("测试速率：");
        jlblTestRate.setFont(normalFont);
        jlblTestRate.setBounds(340/15, 1695/15, 1600/15, 315/15);
        container.add(jlblTestRate);
        jcmbTestRateBox = new JComboBox<String>();
        jcmbTestRateBox.setBounds(1780/15, 1672/15, 2895/15, 360/15);
        jcmbTestRateBox.setFont(textFont);
        container.add(jcmbTestRateBox);

        jlblLineEncoding = new JLabel("线路编码：");
        jlblLineEncoding.setFont(normalFont);
        jlblLineEncoding.setBounds(340/15, 2265/15, 1600/15, 315/15);
        container.add(jlblLineEncoding);
        jcmbLineEncoding = new JComboBox<String>();
        jcmbLineEncoding.setBounds(1780/15, 2242/15, 2895/15, 360/15);
        jcmbLineEncoding.setFont(textFont);
        container.add(jcmbLineEncoding);

        jlblErrorCorrectingcodes = new JLabel("纠错编码：");
        jlblErrorCorrectingcodes.setFont(normalFont);
        jlblErrorCorrectingcodes.setBounds(340/15, 2835/15, 1600/15, 315/15);
        container.add(jlblErrorCorrectingcodes);
        jcmbErrorCorrectingcodes = new JComboBox<String>();
        jcmbErrorCorrectingcodes.setBounds(1780/15, 2812/15, 2895/15, 360/15);
        jcmbErrorCorrectingcodes.setFont(textFont);
        container.add(jcmbErrorCorrectingcodes);

        jlblTestData = new JLabel("测试数据：");
        jlblTestData.setFont(normalFont);
        jlblTestData.setBounds(340/15, 3405/15, 1600/15, 315/15);
        container.add(jlblTestData);
        jcmbTestData = new JComboBox<String>();
        jcmbTestData.setBounds(1780/15, 3382/15, 2895/15, 360/15);
        jcmbTestData.setFont(textFont);
        container.add(jcmbTestData);

        BtnControllOption = new JButton("设置");
        BtnControllOption.setFont(btnFont);
        BtnControllOption.setBounds(2100/15, 3960/15, 975/15, 495/15);
        container.add(BtnControllOption);

        BtnTestData = new JButton("测试");
        BtnTestData.setFont(btnFont);
        BtnTestData.setBounds(3300/15, 3960/15, 975/15, 495/15);
        container.add(BtnTestData);

        jlblShowResult = new JLabel("结果显示");
        jlblShowResult.setFont(titleFont);
        jlblShowResult.setBounds(7900/15, 1080/15, 3600/15, 360/15);
        container.add(jlblShowResult);


        jlblTestTime = new JLabel("测试时间:");
        jlblTestTime.setFont(normalFont);
        jlblTestTime.setBounds(5000/15, 1695/15, 4000/15, 315/15);
        container.add(jlblTestTime);

        jlblTestTimeDayText = new JLabel("00");
        jlblTestTimeDayText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeDayText.setFont(ProjectTool.getDigitFont());
        jlblTestTimeDayText.setBounds(6800/15, 1695/15, 500/15, 315/15);
        container.add(jlblTestTimeDayText);
        jlblTestTimeDayUnit = new JLabel("天");
        jlblTestTimeDayUnit.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeDayUnit.setFont(unitFont);
        jlblTestTimeDayUnit.setBounds(7250/15, 1695/15, 400/15, 315/15);
        container.add(jlblTestTimeDayUnit);

        jlblTestTimeHourText = new JLabel("00");
        jlblTestTimeHourText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeHourText.setFont(ProjectTool.getDigitFont());
        jlblTestTimeHourText.setBounds(7550/15, 1695/15, 600/15, 315/15);
        container.add(jlblTestTimeHourText);
        jlblTestTimeHourUnit = new JLabel("小时");
        jlblTestTimeHourUnit.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeHourUnit.setFont(unitFont);
        jlblTestTimeHourUnit.setBounds(8100/15, 1695/15, 600/15, 315/15);
        container.add(jlblTestTimeHourUnit);

        jlblTestTimeMinuteText = new JLabel("00");
        jlblTestTimeMinuteText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeMinuteText.setFont(ProjectTool.getDigitFont());
        jlblTestTimeMinuteText.setBounds(8600/15, 1695/15, 600/15, 315/15);
        container.add(jlblTestTimeMinuteText);
        jlblTestTimeMinuteUnit = new JLabel("分钟");
        jlblTestTimeMinuteUnit.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeMinuteUnit.setFont(unitFont);
        jlblTestTimeMinuteUnit.setBounds(9090/15, 1695/15, 600/15, 315/15);
        container.add(jlblTestTimeMinuteUnit);

        jlblTestTimeSecondText = new JLabel("00");
        jlblTestTimeSecondText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeSecondText.setFont(ProjectTool.getDigitFont());
        jlblTestTimeSecondText.setBounds(9600/15, 1695/15, 600/15, 315/15);
        container.add(jlblTestTimeSecondText);
        jlblTestTimeSecondUnit = new JLabel("秒");
        jlblTestTimeSecondUnit.setHorizontalAlignment(SwingConstants.CENTER);
        jlblTestTimeSecondUnit.setFont(unitFont);
        jlblTestTimeSecondUnit.setBounds(10000/15, 1695/15, 600/15, 315/15);
        container.add(jlblTestTimeSecondUnit);

        jbtnTestTimeClear = new JButton("清零");
        jbtnTestTimeClear.setFont(btnFont);
        jbtnTestTimeClear.setBounds((10200+700)/15, 1605/15, 975/15, 495/15);
        container.add(jbtnTestTimeClear);

        jlblReceiveDataRate = new JLabel("接收数据速率:");
        jlblReceiveDataRate.setFont(normalFont);
        jlblReceiveDataRate.setBounds(5000/15, 2205/15, 4000/15, 315/15);//1695
        container.add(jlblReceiveDataRate);
        jlblReceiveDataRateText = new JLabel("0.0000000");
        jlblReceiveDataRateText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblReceiveDataRateText.setFont(ProjectTool.getDigitFont());
        jlblReceiveDataRateText.setBounds(6800/15, 2205/15, 4000/15, 315/15);
        container.add(jlblReceiveDataRateText);
        jlblReceiveDataRateUnit = new JLabel("Mbps");
        jlblReceiveDataRateUnit.setFont(unitFont);
        jlblReceiveDataRateUnit.setBounds(8200/15, 2205/15, 4000/15, 315/15);
        jlblReceiveDataRateUnit.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jlblReceiveDataRateUnit);
        jbtnReceiveDataRateClear = new JButton("清零");
        jbtnReceiveDataRateClear.setFont(btnFont);
        jbtnReceiveDataRateClear.setBounds((10200+700)/15, 2115/15, 975/15, 495/15);
        container.add(jbtnReceiveDataRateClear);

        jlblErrorCodeRate = new JLabel("接收数据误码率(BER):");
        jlblErrorCodeRate.setFont(normalFont);
        jlblErrorCodeRate.setBounds(5000/15, 2720/15, 3900/15, 315/15);
        container.add(jlblErrorCodeRate);
        jlblErrorCodeRateText = new JLabel("0.000000");
        jlblErrorCodeRateText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblErrorCodeRateText.setFont(ProjectTool.getDigitFont());
        jlblErrorCodeRateText.setBounds(7300/15, 2720/15, 3000/15, 315/15);
        container.add(jlblErrorCodeRateText);
        jlblErrorCodeRateUnit = new JLabel("*10-");
        jlblErrorCodeRateUnit.setFont(unitFont);
        jlblErrorCodeRateUnit.setBounds(7900/15, 2720/15, 4000/15, 315/15);
        jlblErrorCodeRateUnit.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jlblErrorCodeRateUnit);
        jlblErrorCodeRateTextX = new JLabel("0");//最后一个X
        jlblErrorCodeRateTextX.setFont(ProjectTool.getDigitFont());
        jlblErrorCodeRateTextX.setBounds(8300/15, 2720/15, 4000/15, 315/15);
        jlblErrorCodeRateTextX.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jlblErrorCodeRateTextX);
        jbtnErrorCodeRateClear = new JButton("清零");
        jbtnErrorCodeRateClear.setFont(btnFont);
        jbtnErrorCodeRateClear.setBounds((10200+700)/15, 2630/15, 975/15, 495/15);
        container.add(jbtnErrorCodeRateClear);

        jlblEverySecondErrorCode = new JLabel("接收数据秒误码个数(ERR/S):");
        jlblEverySecondErrorCode.setFont(normalFont);
        jlblEverySecondErrorCode.setBounds(5000/15, 3210/15, 4500/15, 315/15);
        container.add(jlblEverySecondErrorCode);
        jlblEverySecondErrorCodeText = new JLabel("00000000");
        jlblEverySecondErrorCodeText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblEverySecondErrorCodeText.setFont(ProjectTool.getDigitFont());
        jlblEverySecondErrorCodeText.setBounds((8160+400)/15, 3210/15, 1815/15, 315/15);
        container.add(jlblEverySecondErrorCodeText);
        jbtnEverySecondErrorCodeClear = new JButton("清零");
        jbtnEverySecondErrorCodeClear.setFont(btnFont);
        jbtnEverySecondErrorCodeClear.setBounds((10200+700)/15, 3142/15, 975/15, 495/15);
        container.add(jbtnEverySecondErrorCodeClear);

        jlblEverySecondErrorCodeRate = new JLabel("接收数据秒误码率(BER/S):");
        jlblEverySecondErrorCodeRate.setFont(normalFont);
        jlblEverySecondErrorCodeRate.setBounds(5000/15, 3700/15, 4000/15, 315/15);
        container.add(jlblEverySecondErrorCodeRate);
        jlblEverySecondErrorCodeRateText = new JLabel("0.000000");
        jlblEverySecondErrorCodeRateText.setHorizontalAlignment(SwingConstants.CENTER);
        jlblEverySecondErrorCodeRateText.setFont(ProjectTool.getDigitFont());
        jlblEverySecondErrorCodeRateText.setBounds(7400/15, 3750/15, 3000/15, 315/15);
        container.add(jlblEverySecondErrorCodeRateText);
        jlblEverySecondErrorCodeRateUnit = new JLabel("*10-");
        jlblEverySecondErrorCodeRateUnit.setHorizontalAlignment(SwingConstants.CENTER);
        jlblEverySecondErrorCodeRateUnit.setBounds(8000/15, 3750/15, 4000/15, 315/15);
        jlblEverySecondErrorCodeRateUnit.setFont(unitFont);
        container.add(jlblEverySecondErrorCodeRateUnit);
        jlblEverySecondErrorCodeRateTextX = new JLabel("0");//最后一个X
        jlblEverySecondErrorCodeRateTextX.setFont(ProjectTool.getDigitFont());
        jlblEverySecondErrorCodeRateTextX.setBounds(9430/15, 3750/15, 2000/15, 315/15);
        jlblEverySecondErrorCodeRateTextX.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(jlblEverySecondErrorCodeRateTextX);
        jbtnEverySecondErrorCodeRateClear = new JButton("清零");
        jbtnEverySecondErrorCodeRateClear.setFont(btnFont);
        jbtnEverySecondErrorCodeRateClear.setBounds((10200+700)/15, 3645/15, 975/15, 495/15);//
        container.add(jbtnEverySecondErrorCodeRateClear);

        communicationOption = new JLabel("通信设置");
        communicationOption.setFont(titleFont);
        communicationOption.setBounds(5182/15, 4920/15, 1440/15, 360/15);
        container.add(communicationOption);

        jlblBaudRate = new JLabel("波特率：");
        jlblBaudRate.setFont(normalFont);
        jlblBaudRate.setBackground(new Color(184,184,184));
        jlblBaudRate.setBounds(1200/15, 5520/15, 1400/15, 315/15);
        container.add(jlblBaudRate);
        jcmbBaudRate = new JComboBox<String>();
        jcmbBaudRate.setBounds(2280/15, 5497/15, 1455/15, 360/15);
        jcmbBaudRate.setFont(textFont);
        container.add(jcmbBaudRate);

        jlblChechBit = new JLabel("校验位：");
        jlblChechBit.setFont(normalFont);
        jlblChechBit.setBackground(new Color(187,187,187));
        jlblChechBit.setBounds(4680/15, 5520/15, 1400/15, 315/15);
        container.add(jlblChechBit);
        jcmbChekBit = new JComboBox<String>();
        jcmbChekBit.setBounds(5760/15, 5497/15, 1455/15, 360/15);
        jcmbChekBit.setFont(textFont);
        container.add(jcmbChekBit);

        jlblStopBit = new JLabel("停止位：");
        jlblStopBit.setFont(normalFont);
        jlblStopBit.setBackground(new Color(189,189,189));
        jlblStopBit.setBounds(8250/15, 5520/15, 1400/15, 315/15);
        container.add(jlblStopBit);
        jcmbStopBit = new JComboBox<String>();
        jcmbStopBit.setBounds(9330/15, 5497/15, 1455/15, 360/15);
        jcmbStopBit.setFont(textFont);
        container.add(jcmbStopBit);

        jbtnScan = new JButton("扫描串口");
        jbtnScan.setBounds(6165/15, 6240/15, 1410/15, 495/15);
        jbtnScan.setFont(btnFont);
        container.add(jbtnScan);

        jcmbSerialPort = new JComboBox<String>();
        jcmbSerialPort.setBounds(4875/15, 6307/15, 1095/15, 360/15);
        jcmbSerialPort.setFont(textFont);
        container.add(jcmbSerialPort);

        openSerialPort = new JButton("打开串口");
        openSerialPort.setFont(btnFont);
        openSerialPort.setBounds(7770/15, 6240/15, 1410/15, 495/15);
        container.add(openSerialPort);

        closeSerialPort = new JButton("关闭串口");
        closeSerialPort.setFont(btnFont);
        closeSerialPort.setBounds(9375/15, 6240/15, 1410/15, 495/15);
        container.add(closeSerialPort);

//        jlblVersionNumber = new JLabel("软件版本:V0.1");
//        jlblVersionNumber.setFont(titleFont);
//        jlblVersionNumber.setBackground(new Color(184, 184, 184));
//        jlblVersionNumber.setBounds(4792/15, 6960/15, 2520/15, 360/15);
//        container.add(jlblVersionNumber);

        BtnAllClear = new JButton("全部清空");
        BtnAllClear.setFont(btnFont);
        BtnAllClear.setBounds(7920/15, 4400/15, 1695/15, 495/15);
        container.add(BtnAllClear);

        Point centerPoint = ProjectTool.getCenterLoaction(mainFrame.getHeight(), mainFrame.getWidth());
        mainFrame.setLocation(centerPoint);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
}
