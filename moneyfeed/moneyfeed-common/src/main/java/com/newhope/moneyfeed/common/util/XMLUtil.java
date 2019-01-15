package com.newhope.moneyfeed.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml和object相互转换工具类
 * create by liming
 */
public class XMLUtil {

    private final static Logger logger= LoggerFactory.getLogger(XMLUtil.class);


    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
//    public static String convertToXmlByFilter(Object obj) {
//        // 创建输出流
//        StringWriter out = new StringWriter();
//        try {
//            // 利用jdk中自带的转换类实现
//            JAXBContext context = JAXBContext.newInstance(obj.getClass());
//
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
//            // 格式化xml输出的格式
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
//                    Boolean.TRUE);
//            // 将对象转换成输出流形式的xml
//
//            OutputFormat format = new OutputFormat();
//            format.setIndent(true);
//            format.setNewlines(true);
//            format.setNewLineAfterDeclaration(false);
//            XMLWriter writer = new XMLWriter(out, format);
//
//            XMLFilterImpl nsfFilter = new XMLFilterImpl() {
//                private boolean ignoreNamespace = false;
//                private String rootNamespace = null;
//                private boolean isRootElement = true;
//
//                @Override
//                public void startDocument() throws SAXException {
//                    super.startDocument();
//                }
//
//                @Override
//                public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
//                    if (this.ignoreNamespace)
//                    {
//                        uri = "";
//                    }
//                    if(localName.equals("inv:P_BATCH_NUMBER")){
//                        out.write("</test>");
//                    }else {
//                        super.startElement(uri, localName, localName, atts);
//                    }
//                   /* super.startElement(uri, localName, localName, atts);*/
//                }
//
//                @Override
//                public void endElement(String uri, String localName, String qName) throws SAXException {
//                    if (this.ignoreNamespace) uri = "";
//                    super.endElement(uri, localName, localName);
//                }
//
//                @Override
//                public void startPrefixMapping(String prefix, String url) throws SAXException {
//                    if (this.rootNamespace != null) url = this.rootNamespace;
//                    if (!this.ignoreNamespace) super.startPrefixMapping("", url);
//
//                }
//            };
//            nsfFilter.setContentHandler(writer);
//            marshaller.marshal(obj, nsfFilter);
//        } catch (Exception e) {
//          logger.error("object转xml出错，错误原因",e);
//        }
//        return out.toString();
//    }




    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (Exception e) {
            logger.error("object转xml出错，错误原因",e);
        }
        return sw.toString();
    }


    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(String xmlStr,Class... clazz) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(sr));
            xmlObject = unmarshaller.unmarshal(source);
        } catch (Exception e) {
            logger.error("xml转object出错，错误原因",e);
        }
        return xmlObject;
    }


}