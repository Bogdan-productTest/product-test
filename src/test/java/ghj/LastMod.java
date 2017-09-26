package ghj;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LastMod {

    String sitemapURL;
    String documentPath;
    String datePreviousMod;
    Document documentPrevious;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    File file;

//    @BeforeClass
//    @Parameters("url")
//    protected void getUrl(String sitemapURL) {
//        this.sitemapURL = sitemapURL;
//        this.documentPath = "C:/Users/Тестер/Desktop/sitemap_files/" + sitemapURL.substring(23);
//    }

    @DataProvider(name = "test1")
    public Object [] createData1() {
        return new Object [] {
//                 "https://product-test.ru/sitemap.xml",
                "https://product-test.ru/categories.xml"
        };
    }



    public void saveDocument (Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(documentPath);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public void parsePreviousDocument (Node n, Date d) throws ParserConfigurationException, SAXException, IOException {
        //список элементов loc в документе предыдущей проверки
        NodeList locList = documentPrevious.getElementsByTagName("loc");

        for (int j=0; j<locList.getLength();j++) {

//                    System.out.println("предыдущее изменение:" + locList.item(j).getNextSibling().getTextContent());
//                    System.out.println("страницы:" + locList.item(j).getTextContent());

            if (locList.item(j).getTextContent().equals(n.getPreviousSibling().getTextContent())){
                String s = locList.item(j).getParentNode().getTextContent();
                datePreviousMod = s.substring(s.length()-23);
                break;
            }
        }
    }

    public Document getSitemapPreviousModDocument () throws IOException, SAXException, ParserConfigurationException {
        //инициализируем сайтмап предыдущей проверки
        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder1 = factory1.newDocumentBuilder();
        Document document1 = documentBuilder1.parse(file);
        document1.normalize();
        return document1;
    }

    @Test(dataProvider = "test1")
    public void test(String sitemapURL) throws ParserConfigurationException, IOException, SAXException, ParseException, TransformerException {

        documentPath = "C:/Users/Тестер/Desktop/sitemap_files/" + sitemapURL.substring(23);

        file = new File(documentPath);

        documentPrevious = getSitemapPreviousModDocument ();

        //получаем дату последней проверки
        Node dateLastCheckElement = documentPrevious.getElementsByTagName("dateLastCheck").item(0);
        Date dateLastCheck = dateFormat.parse(dateLastCheckElement.getTextContent());

        //инициализируем сайтмап актуальный
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(sitemapURL);
        document.normalize();

        //получаем список всех элементов с тегом lastmod

            NodeList lastModList = document.getElementsByTagName("lastmod");
//            NodeList locList = document.getElementsByTagName("loc");

//        for (int k=0; k<locList.getLength();k++) {
//            System.out.println("Список: " + locList.item(k).getFirstChild().getTextContent());
//        }

        //проходимся по всем значениям и сравниваем с датой последней проверки
        for (int i=0; i<lastModList.getLength();i++) {

            Node n = lastModList.item(i);
//            System.out.println("последнее изменение: " + lastModList.item(i).getTextContent());
//            System.out.println("страницы: " + lastModList.item(i).getPreviousSibling().getTextContent());

            //дата последнего изменения
            Date dateLastMod = dateFormat.parse(lastModList.item(i).getTextContent().substring(0,16));

            //сравнение дат последней проверки и последнего изменения
            if (dateLastMod.after(dateLastCheck) && (dateLastMod.compareTo(dateLastCheck)!=0)) {

                parsePreviousDocument(lastModList.item(i), dateLastMod);

                System.out.println("Страница " + lastModList.item(i).getPreviousSibling().getTextContent() + " была изменена " +
                        lastModList.item(i).getTextContent() + " предыдущее изменение " + datePreviousMod);
            }
        }

        Element dateLastCheckElementNew = document.createElement("dateLastCheck");
        document.getDocumentElement().appendChild(dateLastCheckElementNew);

        Date date = new Date();
        String s = dateFormat.format(date).toString() + "+03:00";
        dateLastCheckElementNew.setTextContent(s);
        System.out.println("дата изменилась на " + dateLastCheckElementNew.getTextContent());

        saveDocument(document);
    }
}
