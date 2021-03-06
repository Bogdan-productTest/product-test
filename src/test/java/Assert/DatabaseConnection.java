package Assert;

import static Assert.MyConfig.*;
import java.sql.*;

public class DatabaseConnection {

    static Statement stmt = null;
    static Connection conn = null;

    public static Statement SQLConnect() throws SQLException {
        String hostName = getHostName();
        String dbName = getDbName();
        String user = getUser();
        String password = getPassword();

        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);

        conn = DriverManager.getConnection(url);

        return stmt = conn.createStatement();

    }
}
