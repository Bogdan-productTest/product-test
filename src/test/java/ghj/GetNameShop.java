package ghj;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GetNameShop {
    static HSSFSheet sheet;
    static Object [][] o;
    static Row row;
    static HSSFWorkbook workbook;
    static int d=0;
    static int x=0;

    @BeforeClass
    public static void parseXML() throws IOException {

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(new File("C:/Users/Тестер/Desktop/тест2.xls"));

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

    @AfterClass
    public static void createFile () throws IOException {
        File file = new File("C:/Users/Тестер/Desktop/результат" + x + ".xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
        x= x+1;
    }

    @DataProvider(name = "offers")
    public static Object[][] credentials() {
        return o;
    }

    @Test(dataProvider = "offers")
    public static void parseNadavi(String id, String url, int k) throws Exception {

        // build a URL
        //double id1 = Integer.parseInt(id);
        String s = "http://info.price.nadavi.ru/p4g3.php?p4g_api_type_=json&p4g_partner_=77800&p4g_name_=Puky%204102%20Z2%20Lovely%20Pink&p4g_gid_="+ id +"&p4g_unique_offers_=1&p4g_u_ip_=" +
                "77.72.253.17";
        //  "178.219.186.12";
        // System.out.println(s);
        // read from the URL
        Scanner scan = new Scanner(new URL(s).openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

        // build a JSON object
        JSONObject obj = new JSONObject(str);

        JSONArray arrayShop = obj.getJSONArray("offers");

        if (d+arrayShop.length()>= 20) {
            createFile();
            d=0;

        }

if (arrayShop.length() > 0) {
    for (int i = 0; i < arrayShop.length(); i++) {
        JSONObject o = (JSONObject) arrayShop.get(i);
//        System.out.println(o.get("ShopName"));
//        System.out.println(o.getString("ShopName"));
            sheet.createRow(d + i);

        sheet.getRow(d + i).createCell(2).setCellValue(o.getString("ShopName"));
    }
    d = d + arrayShop.length();
    System.out.println(d);
}
    }


}
