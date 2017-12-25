package other;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


public class Create_icml {

    String documentPath = "C:/Users/Тестер/Desktop/df.xml";
    String date="2017-11-16 10:50:00";

    static HSSFSheet sheet1;
    static Object [][] o;
    static Row row1;
    static HSSFWorkbook workbook1;
    static String name = "Заявка Product-test.ru с цветами";

    static String path = "C:/Users/Тестер/Desktop/";

    @BeforeClass
    public static void parseXML() throws IOException {

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File(path + name + ".xls"));

        // Get the workbook instance for XLS file
        workbook1 = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        sheet1 = workbook1.getSheetAt(0);

        o = new Object[sheet1.getLastRowNum()+1][4];
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet1.iterator();


        for (int i = 0; i<sheet1.getLastRowNum()+1; i++) {
            row1 = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row1.cellIterator();
            Cell c = cellIterator.next();
            o[i][0] = c.toString();
            c = cellIterator.next();
            o[i][1] = c.toString();
            c = cellIterator.next();
            o[i][2] = c.toString();
            c = cellIterator.next();
            o[i][3] = c.toString();
        }
    }




    public void saveDocument (Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(documentPath);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

//    public void parsePreviousDocument (Node n, Date d) throws ParserConfigurationException, SAXException, IOException {
//        //список элементов loc в документе предыдущей проверки
//        NodeList locList = documentPrevious.getElementsByTagName("loc");
//
//        for (int j=0; j<locList.getLength();j++) {
//
////                    System.out.println("предыдущее изменение:" + locList.item(j).getNextSibling().getTextContent());
////                    System.out.println("страницы:" + locList.item(j).getTextContent());
//
//            if (locList.item(j).getTextContent().equals(n.getPreviousSibling().getTextContent())){
//                String s = locList.item(j).getParentNode().getTextContent();
//                datePreviousMod = s.substring(s.length()-23);
//                break;
//            }
//        }
//    }
//
//    public Document getSitemapPreviousModDocument () throws IOException, SAXException, ParserConfigurationException {
//        //инициализируем сайтмап предыдущей проверки
//        DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
//        DocumentBuilder documentBuilder1 = factory1.newDocumentBuilder();
//        Document document1 = documentBuilder1.parse(file);
//        document1.normalize();
//        return document1;
//    }

    @Test
    public void test() throws ParserConfigurationException, IOException, SAXException, ParseException, TransformerException {


//        for(int i=0; i<o.length;i++){
//            System.out.println(o[i][0].toString());
//            System.out.println(o[i][1].toString());
//        }


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("yml_catalog");
        document.appendChild(root);

        root.setAttribute("date",date);

        //элемент shop
        Element shop = document.createElement("shop");
        root.appendChild(shop);

        //элемент name
        Element name = document.createElement("name");
        shop.appendChild(name);
        name.setTextContent("product-test.ru");

        //элемент company
        Element company = document.createElement("company");
        shop.appendChild(company);
        company.setTextContent("osnova-biznesa");

        //элемент categories
        Element categories = document.createElement("categories");
        shop.appendChild(categories);

        //создание категории смартфоны
        Element category = document.createElement("category");
        categories.appendChild(category);
        category.setAttribute("id", "1");
        category.setTextContent("Смартфоны");

        //создание категории планшеты
        category = document.createElement("category");
        categories.appendChild(category);
        category.setAttribute("id", "2");
        category.setTextContent("Планшеты");

        //создание категории наушники
        category = document.createElement("category");
        categories.appendChild(category);
        category.setAttribute("id", "3");
        category.setTextContent("Наушники");

        //элемент offers
        Element offers = document.createElement("offers");
        shop.appendChild(offers);


        for (int k=0; k<o.length;k++) {

            //System.out.println(o[k]);

            //элемент offer
            Element offer = document.createElement("offer");
            offers.appendChild(offer);

            //аттрибут id
            offer.setAttribute("id", String.valueOf(k+1));

            //аттрибут productId
            offer.setAttribute("productId", String.valueOf(k+1));

//                //аттрибут quantity
//                offer.setAttribute("quantity",String.valueOf((int) Double.parseDouble(o[k][1].toString())));

            //элемент name
            Element name1 = document.createElement("name");
            offer.appendChild(name1);
            name1.setTextContent(o[k][0].toString());

            //элемент purchasePrice
            Element purchasePrice = document.createElement("purchasePrice");
            offer.appendChild(purchasePrice);
            double price1= Double.parseDouble(o[k][2].toString());
            purchasePrice.setTextContent(String.valueOf((int) price1));

            //элемент categoryId
            Element categoryId = document.createElement("categoryId");
            offer.appendChild(categoryId);
            categoryId.setTextContent(String.valueOf((int) Double.parseDouble(o[k][3].toString())));
        }

        saveDocument(document);
        //ссылка https://getfile.dokpub.com/yandex/get/https://yadi.sk/d/rbvGiWWR3PkLw8
    }
}
