package com.xiyou.SerialPort.tool;

import com.xiyou.SerialPort.exception.ResourceNotExistException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author cc
 */

public class XMLReader {
    private static DocumentBuilder db = null;
    private InputStream is = null;

    static {
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public InputStream loadXML(String path) throws Exception {
        //is = XMLReader.class.getResourceAsStream(path);
        File file = new File("resource/config/paramConfig.xml");
        System.out.println(file.getAbsolutePath());
        is = new FileInputStream(file);
        if(is == null) {
            throw new ResourceNotExistException("给定路径【" + path + "】资源不存在！");
        }

        return is;
    }

    public void readXML(InputStream is) throws Exception {
        Document doc = db.parse(is);

        NodeList rateList = doc.getElementsByTagName("rate");
        for(int i = 0; i < rateList.getLength(); i++) {
            Element element = (Element) rateList.item(i);
            String code = element.getAttribute("code");
            String rate = element.getTextContent().trim();

            ParamMap.setRateMap(code, rate);//存入Map
        }

        NodeList encodingList = doc.getElementsByTagName("encoding");
        for(int i = 0; i < encodingList.getLength(); i++) {
            Element element = (Element) encodingList.item(i);
            String code = element.getAttribute("code");
            String encoding = element.getTextContent().trim();

            ParamMap.setEncodingMap(code, encoding);
        }

        NodeList errCorrectCodingList = doc.getElementsByTagName("errCorrectCoding");
        for(int i = 0; i < errCorrectCodingList.getLength(); i++) {
            Element element = (Element) errCorrectCodingList.item(i);
            String code = element.getAttribute("code");
            String errCorrectCoding = element.getTextContent().trim();

            ParamMap.setErrCorrectCodingMap(code, errCorrectCoding);
        }

        NodeList testDataList = doc.getElementsByTagName("testData");
        for(int i = 0; i < testDataList.getLength(); i++) {
            Element element = (Element) testDataList.item(i);
            String code = element.getAttribute("code");
            String testData = element.getTextContent().trim();

            ParamMap.setTestDataMap(code, testData);
        }

        NodeList baudRateList = doc.getElementsByTagName("baudRate");
        for(int i = 0; i < baudRateList.getLength(); i++) {
            Element element = (Element) baudRateList.item(i);
            String baudRate = element.getTextContent().trim();

            ParamMap.setBaudRate(baudRate);
        }
    }

    public void closeSource() {
        if(is != null) {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
