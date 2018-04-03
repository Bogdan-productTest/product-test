package Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Assert.DatabaseConnection.SQLConnect;

public class SQLQueries {

    public static String getSqlResult(String query) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery(query);
        rs.next();
        return rs.getString(1);
    }

    public static String getCityIdFromCityName(String city) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery("SELECT CityId FROM dbo.Locations WHERE City='" + city + "'");
        rs.next();
        return rs.getString(1);
    }

    public static String getNadaviIdFromId(String id) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery("SELECT NadaviId FROM dbo.Products WHERE Id='" + id + "'");
        rs.next();
        return rs.getString(1);
    }

    public static String getMobiguruIdFromUrl(String id) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery("SELECT MailId FROM dbo.Products WHERE Url='" + id + "'");
        rs.next();
        return rs.getString(1);
    }

    public static String getIpFromCityName(String city) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery("SELECT ipStart FROM dbo.Locations WHERE City='" + city + "'");
        rs.next();
        return rs.getString(1);
    }

    public static String getIdFromUrl(String url) throws SQLException {
        ResultSet rs = SQLConnect().executeQuery("SELECT Id FROM dbo.Products WHERE Url='" + url + "'");
        rs.next();
        return rs.getString(1);
    }

}
