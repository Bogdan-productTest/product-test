package Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Assert.DatabaseConnection.SQLConnect;

public class SQLQueries {
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
}
