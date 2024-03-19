package com.miaoshaproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleJdbcTest {
    public static void main(String[] args) {
        // 使用与 mybatis-generator.xml 中相同的连接信息
        String url = "jdbc:mysql://127.0.0.1:3306/miaosha?useSSL=false";
        String username = "root";
        String password = "root";

        // 加载 MySQL JDBC 驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
            return;
        }

        // 建立连接
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection to MySQL database successful!");
        } catch (SQLException e) {
            System.out.println("Connection to MySQL database failed!");
            e.printStackTrace();
        }
    }
}