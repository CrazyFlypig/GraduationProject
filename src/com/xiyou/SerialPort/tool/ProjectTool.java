package com.xiyou.SerialPort.tool;

import gnu.io.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cc
 */

public class ProjectTool {
    private static Map<String, Component> elementMap = null;
    private static final Map<String, Integer> serialPortParaMap = new HashMap<String, Integer>();

    private static Font digitFont = null;

    static {
        //控制列表
        elementMap = new HashMap<String, Component>();

        //串口参数列表
        serialPortParaMap.put("None", SerialPort.PARITY_NONE);
        serialPortParaMap.put("Even", SerialPort.PARITY_EVEN);
        serialPortParaMap.put("Odd", SerialPort.PARITY_ODD);
        serialPortParaMap.put("Mark", SerialPort.PARITY_MARK);
        serialPortParaMap.put("Space", SerialPort.PARITY_SPACE);
        serialPortParaMap.put("1", SerialPort.STOPBITS_1);
        serialPortParaMap.put("1.5", SerialPort.STOPBITS_1_5);
        serialPortParaMap.put("2", SerialPort.STOPBITS_2);
    }

    //加载外部字体
    static {
        File file = new File("resource/font/digifaw.ttf");
        if(file.exists()) {
            InputStream is = null;
            try {
                is = new FileInputStream(file);
                Font myFont = Font.createFont(Font.TRUETYPE_FONT, is);
                digitFont = myFont.deriveFont(Font.BOLD, 18);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    is = null;
                }
            }
        }
    }

    /**
     * 得到数码管字体
     * @return
     */
    public static Font getDigitFont() {
        return digitFont;
    }

    public static int getSerialPortParameteByName(String name) {
        if(serialPortParaMap.containsKey(name)) {
            return serialPortParaMap.get(name);
        }

        return -1;
    }

    /**
     * 将所有元素添加到map保存
     * @param eleName 元素id
     * @param element 元素
     */
    public static void addElementByName(String eleName, Component element) {
        if(!elementMap.containsKey(eleName)) {
            elementMap.put(eleName, element);
            return;
        }
    }

    /**
     * 通过元素id在map中取得元素
     * @param eleName 元素id
     * @return
     */
    public static Component getElementByName(String eleName) {
        if(elementMap.containsKey(eleName)) {
            return elementMap.get(eleName);
        }
        return null;
    }

    /**
     * 获取界面居中位置
     * @param frameHeight 界面高度
     * @param frameWidth  界面宽度
     * @return 返回合适居中的点坐标
     */
    public static Point getCenterLoaction(int frameHeight, int frameWidth) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int height = (int) dimension.getHeight();
        int width = (int) dimension.getWidth();
        int locationY = (height - frameHeight) / 2;
        int locationX = (width - frameWidth) / 2;

        return new Point(locationX, locationY);
    }

    /**
     * 在主界面显示错误信息（弹出框）
     * @param jfrm 主界面
     * @param message 错误信息
     */
    public static void showErrorMsg(JFrame jfrm, String message) {
        JOptionPane.showMessageDialog(jfrm, message, "����", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 将二进制字符串转换为int类型
     * @param str
     * @return
     */
    public static int stringToInt(String str) {
        int res = 0;

        char[] ch = str.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            if(ch[i] == '1') {
                res += Math.pow(2, ch.length - i - 1);
            }
        }

        return res;
    }
}
