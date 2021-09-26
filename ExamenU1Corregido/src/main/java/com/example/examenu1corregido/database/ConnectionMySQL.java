package com.example.examenu1corregido.database;

import java.sql.*;

public class ConnectionMySQL {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection("jdbc:mysql://" + "localhost" + "/" + "examenU1" +
                "?user=" + "root" + "&password=" + "root" + "&useSSL=false");
    }


    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println("(^_^) Connection successful ... ");
            con.close();
        } catch (SQLException e) {
            System.out.println("(o_O) Conection error ... " + e);
        }
    }
}
