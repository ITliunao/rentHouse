package com.xmmxjy.generator;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class WolfXmlUtil {
    public WolfXmlUtil() {
    }

    private void getAddStrutsElemant(String filePath, String nodexPath) throws Exception {
        Document document = this.getPath(filePath, "utf-8");
        Element element = document.getRootElement();
        Element nextElement = element.element("package");
        Element newElement = nextElement.addElement("action");
        newElement.addComment("系统自动创建");
        newElement.addAttribute("name", "test");
        newElement.addAttribute("class", "");
        newElement.addAttribute("method", "");
        newElement.addText("hello");
    }

    public void getAddNode(String filePath, String xPath, String newNode, Map<String, String> attrMap, String text) throws Exception {
        if(this.getQueryNode(filePath, xPath, newNode, attrMap, text) < 1) {
            Document document = this.getPath(filePath, "UTF-8");
            List list = document.selectNodes(xPath);
            System.out.println(xPath);
            Element element = (Element)list.get(0);
            Element newElement = element.addElement(newNode);
            Iterator var11 = attrMap.entrySet().iterator();

            while(var11.hasNext()) {
                Entry entry = (Entry)var11.next();
                newElement.addAttribute((String)entry.getKey(), (String)entry.getValue());
            }

            if(text != null && text.trim().length() > 0) {
                newElement.addText(text);
            }

            this.getXMLWrite(document, filePath);
            System.out.println("修改" + xPath + "成功");
        } else {
            System.out.println("已添");
        }

    }

    public int getQueryNode(String filePath, String xPath, String newNode, Map<String, String> attrMap, String text) throws Exception {
        int count = 0;
        Document document = this.getPath(filePath, "UTF-8");
        StringBuffer sb = new StringBuffer();
        Iterator i = attrMap.entrySet().iterator();

        while(i.hasNext()) {
            Entry list = (Entry)i.next();
            sb.append("[@" + (String)list.getKey() + "=\'" + (String)list.getValue() + "\']");
        }

        xPath = xPath + "/" + newNode + sb.toString();
        System.out.println("xPath=" + xPath);
        document.selectNodes(xPath);
        List var13 = document.selectNodes(xPath);

        for(int var12 = 0; var12 < var13.size(); ++var12) {
            Element element = (Element)var13.get(var12);
            if(element.getText().equals(text)) {
                ++count;
            }
        }

        return count;
    }

    public void getXMLWrite(Document document, String filePath) throws Exception {
        OutputFormat of = new OutputFormat(" ", true);
        of.setEncoding("UTF-8");
        XMLWriter xw = new XMLWriter(new FileWriter(filePath), of);
        xw.setEscapeText(false);
        xw.write(document);
        xw.close();
        System.out.println(document.asXML());
    }

    public void getEditNode(String filePath, String xPath, Map<String, String> attrMap, String text) throws Exception {
        Document document = this.getPath(filePath, "UTF-8");
        List list = document.selectNodes(xPath);
        Element element = (Element)list.get(0);
        if(attrMap != null) {
            Iterator i = attrMap.entrySet().iterator();

            while(i.hasNext()) {
                Entry nodelist = (Entry)i.next();
                element.addAttribute((String)nodelist.getKey(), (String)nodelist.getValue());
            }
        }

        List var11 = element.elements();

        for(int var12 = 0; var12 < var11.size(); ++var12) {
            Element nodeElement = (Element)var11.get(var12);
            nodeElement.getParent().remove(nodeElement);
        }

        element.setText(text);
        this.getXMLWrite(document, filePath);
    }

    public Document getPath(String filePath, String coding) {
        SAXReader saxReader = new SAXReader();
        Document document = null;

        try {
            saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            BufferedReader e = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), coding));
            document = saxReader.read(e);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return document;
    }

    public static void main(String[] args) {
        WolfXmlUtil xml = new WolfXmlUtil();
        String filePath1 = "D:\\MyEclipse 8.5\\ssi\\src\\com\\wei\\ssi\\conf\\sqlmap\\ProUserSQL.xml";
        String filePath = "D:\\MyEclipse 8.5\\ssi\\src\\com\\wei\\ssi\\conf\\struts2\\struts2-ssi-proWbType.xml";

        try {
            HashMap e = new HashMap();
            e.put("file", "no");
            xml.getEditNode(filePath1, "/sqlMap/select[@id=\'getProUserList\']", e, "嘿嘿");
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
