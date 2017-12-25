package other;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class ParseJson {
    static HSSFSheet sheet;
    static Object [][] o;
    static Row row;
    static HSSFWorkbook workbook;
    static String fileName;
    static String ipKazan = "77.72.253.17";
    static String ipPiter = "87.249.59.255";
    static String ipMoscow = "178.219.186.12";

    @BeforeClass
    public static void parseXML() throws IOException {

        fileName =
         "кофемолки";


        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File("C:/Users/Mirror/Desktop/product-test/" + fileName + ".xls"));

        // Get the workbook instance for XLS file
        workbook = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        sheet = workbook.getSheetAt(0);

        System.out.println(sheet.getLastRowNum());
        o = new Object[sheet.getLastRowNum()+1][3];
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();


        for (int i = 0; i<sheet.getLastRowNum()+1; i++) {
            row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            for  (int j = 0; cellIterator.hasNext() == true; j++) {
                Cell c = cellIterator.next();
                //System.out.println(c.getCellTypeEnum()==CellType.NUMERIC);
                if (c.getCellType()==0) {
                    Double f = c.getNumericCellValue();
                    Integer l = f.intValue();
                    String r =  l.toString();
                    o[i][j]=r;
                } else o[i][j] = c.toString();
            }
            o[i][2] = i+1;
        }
    }

    @DataProvider(name = "offers")
    public static Object[][] credentials() {
        return o;
    }

    @Test(dataProvider = "offers")
    public static void parseNadavi(String id, String url, int k) throws Exception {
        // build a URL
        //double id1 = Integer.parseInt(id);
        String s = "http://info.price.nadavi.ru/p4g3.php?p4g_api_type_=json&p4g_partner_=77800&p4g_name_=Puky%204102%20Z2%20Lovely%20Pink&p4g_gid_="+ id +"&p4g_u_ip_=";

        // System.out.println(s);
        // read from the URL
        Scanner scan = new Scanner(new URL(s + ipPiter).openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);
        System.out.println("Название товара на PT: " + url);
        System.out.println("Название товара в Nadavi: " + obj.getString("GoodFullName"));
        sheet = workbook.getSheetAt(0);
        //sheet.getRow(k-1).createCell(2).setCellValue(obj.getString("GoodFullName"));
        //sheet.getRow(k-1).createCell(3).setCellValue(obj.getString("KatalogName"));

        JSONArray arrayOffers = obj.getJSONArray("offers");

        sheet.getRow(k-1).createCell(4).setCellValue(arrayOffers.length());

        scan = new Scanner(new URL(s + ipKazan).openStream());
        str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        // build a JSON object
        JSONObject obj1 = new JSONObject(str);
        System.out.println("Название товара на PT: " + url);
        System.out.println("Название товара в Nadavi: " + obj1.getString("GoodFullName"));
        sheet = workbook.getSheetAt(0);

        arrayOffers = obj1.getJSONArray("offers");

        sheet.getRow(k-1).createCell(5).setCellValue(arrayOffers.length());
        //row.createCell(2).setCellValue(obj.getString("GoodFullName"));
//        for (int i = 0; i < arrayOffers.length(); i++) {
//            JSONObject o = (JSONObject) arrayOffers.get(i);
////            System.out.println(o.getString("OfferName"));
//            sheet.getRow(k-1).createCell(i+5).setCellValue(o.getString("OfferName"));
//            //row.createCell(i+3).setCellValue(o.getString("OfferName"));
//        }

    }

    @AfterClass
    public void createFile () throws IOException {
        File file = new File("C:/Users/Mirror/Desktop/product-test/" + fileName  + "_результат.xls");
        file.getParentFile().mkdirs();
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}