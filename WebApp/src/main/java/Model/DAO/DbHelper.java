package Model.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException, NullPointerException, IOException {
        try(InputStream inputStream = DbHelper.class.getClassLoader().getResourceAsStream("config.properties"))
        {
            Properties prop = new Properties();
            prop.load(inputStream);
            String ConnectionURL = prop.getProperty("ConnectionString");
            String Username = prop.getProperty("Username");
            String Password = prop.getProperty("Password");


            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(ConnectionURL, Username, Password);
        }
    }
}
