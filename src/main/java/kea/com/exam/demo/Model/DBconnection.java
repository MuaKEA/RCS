package kea.com.exam.demo.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBconnection {

    static DBconnection instance = new DBconnection();
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    // Database url
    static final String DATABASE_URL = "jdbc:mysql://den1.mysql6.gear.host:3306/RCS1";
    static Connection con;

    public Connection createConnection(){
        con = null;
        try {
            Class.forName(JDBC_Driver);
            // credentials
            return con = DriverManager.getConnection(DATABASE_URL,"rcs1","Lt56vC0jR--6");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static DBconnection getInstance(){
        return instance;

    }



}