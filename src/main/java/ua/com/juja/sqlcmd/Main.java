package ua.com.juja.sqlcmd;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String sql = "SELECT RDB$RELATION_NAME" +
                " FROM RDB$RELATIONS" +
                " WHERE RDB$SYSTEM_FLAG = 0 AND RDB$RELATION_TYPE = 0";

        try {
            Connection connection = getConnection(MyConstants.url + MyConstants.databaseName,
                    MyConstants.userName, MyConstants.password);
            Statement stmt = connection.createStatement();
            ResultSet dataSet = stmt.executeQuery(sql);
            while (dataSet.next()) {
                System.out.println(dataSet.getString("RDB$RELATION_NAME"));
            }
            dataSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection(String url, String userName, String password) throws SQLException {
        java.util.Properties info = new java.util.Properties();
        info.put("user", userName);
        info.put("password", password);
        info.put("charSet", "utf-8");
        return DriverManager.getConnection(url, info);
    }

}
