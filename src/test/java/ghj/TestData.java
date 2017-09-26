package ghj;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestData {


    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test_data";
    private static final String userName = "root";
    private static final String password = "test";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Driver driver;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main (String[] args)
    {
        Connection conn = null;
        try
        {
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Database connection established");
        }
        catch (Exception e)
        {
            System.err.println ("Cannot connect to database server");
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { }
            }
        }
    }
}



