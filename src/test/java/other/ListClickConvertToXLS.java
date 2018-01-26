package other;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static Assert.MyConfig.getMobiguruApiKeyP;
import static Assert.MyConfig.getMobiguruApiKeyPT;

public class ListClickConvertToXLS {

    static String URLPT;
    static String URLP;
    static JSONObject obj;
    static Workbook workbook;
    static Sheet sheet;
    static Row row;
    static int page;
    static String URL = URLPT;

    public static JSONObject getJSON (String URL) throws IOException, JSONException {

        Scanner scan = new Scanner(new URL(URL).openStream());
        String str = new String();
        while (scan.hasNext())
            str += scan.nextLine();
        scan.close();

       JSONObject object = new JSONObject(str);

       return object;
    }

    @BeforeClass
    public static void initKey() {
        URLPT = "http://api.mobiguru.ru/v1/partner/clicks.json" + getMobiguruApiKeyPT() + "&period_begin=2018-01-11&count=1000";
        URLP = "http://api.mobiguru.ru/v1/partner/clicks.json" + getMobiguruApiKeyP() + "&period_begin=2018-01-11&count=1000";
    }

    @BeforeClass
    public static void getNumPage() throws IOException, JSONException {
       page =  getJSON(URL).getInt("total")/1000 + 1;
    }

    @BeforeClass
    public static void createXLSFile () {

        workbook = new HSSFWorkbook();
        workbook.createSheet();
        sheet = workbook.getSheetAt(0);
    }

    @Test
    public static void main() throws JSONException, IOException {

        for (int j = 0; j < page ; j++) {

            int lastRow = sheet.getLastRowNum();
            obj = getJSON(URL + "&page=" + (j + 1));

        JSONArray arrayData = obj.getJSONArray("data");
      //  System.out.println(arrayData.length());

            if (j==0) {
                sheet.createRow(0).createCell(0).setCellValue("click_id");
                sheet.getRow(0).createCell(1).setCellValue("date");
                sheet.getRow(0).createCell(2).setCellValue("action_id");
                sheet.getRow(0).createCell(3).setCellValue("user_ip");
                sheet.getRow(0).createCell(4).setCellValue("user_agent");
                sheet.getRow(0).createCell(5).setCellValue("offer_name");
                sheet.getRow(0).createCell(6).setCellValue("targ");
                sheet.getRow(0).createCell(7).setCellValue("bid");
                sheet.getRow(0).createCell(8).setCellValue("subid");
                sheet.getRow(0).createCell(9).setCellValue("partner_data");
                sheet.getRow(0).createCell(10).setCellValue("geo_id");
                sheet.getRow(0).createCell(11).setCellValue("click_status");
            }
            for (int i = 0; i< arrayData.length(); i++) {

                sheet.createRow( lastRow + i + 1);

                sheet.getRow(lastRow + i + 1).createCell(0).setCellValue(arrayData.getJSONObject(i).getString("click_id"));
                sheet.getRow(lastRow + i + 1).createCell(1).setCellValue(arrayData.getJSONObject(i).getString("date"));
                sheet.getRow(lastRow + i + 1).createCell(2).setCellValue(arrayData.getJSONObject(i).getString("action_id"));
                sheet.getRow(lastRow + i + 1).createCell(3).setCellValue(arrayData.getJSONObject(i).getString("user_ip"));
                sheet.getRow(lastRow + i + 1).createCell(4).setCellValue(arrayData.getJSONObject(i).getString("user_agent"));
                sheet.getRow(lastRow + i + 1).createCell(5).setCellValue(arrayData.getJSONObject(i).getString("offer_name"));
                sheet.getRow(lastRow + i + 1).createCell(6).setCellValue(arrayData.getJSONObject(i).getString("targ"));
                sheet.getRow(lastRow + i + 1).createCell(7).setCellValue(arrayData.getJSONObject(i).getString("bid"));
                sheet.getRow(lastRow + i + 1).createCell(8).setCellValue(arrayData.getJSONObject(i).getString("subid"));
                sheet.getRow(lastRow + i + 1).createCell(9).setCellValue(arrayData.getJSONObject(i).getString("partner_data"));
                sheet.getRow(lastRow + i + 1).createCell(10).setCellValue(arrayData.getJSONObject(i).getString("geo_id"));
                sheet.getRow(lastRow + i + 1).createCell(11).setCellValue(arrayData.getJSONObject(i).getString("click_status"));


        }
        }

    }
    @AfterClass
    public void saveXLSFile () throws IOException {
        File file = new File("C:/Users/Mirror/Desktop/product-test/listOfClicksPT.xls");
        file.getParentFile().mkdirs();
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
