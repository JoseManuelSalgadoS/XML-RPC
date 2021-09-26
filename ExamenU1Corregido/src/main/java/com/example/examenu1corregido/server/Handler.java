package com.example.examenu1corregido.server;

import com.example.examenu1corregido.database.ConnectionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;


    public boolean createUser(String name, String lastname, String email, String password){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("INSERT INTO `user`(name, lastname, email, password, date_registered, status)VALUES(?,?,?,?,NOW(),1);");
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error en el metodo registrarUsuario");
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones");
            }
        }
        return flag;
    }

    public List<String> mostrarUsuarios(){
        boolean flag = false;
        List<String> usuarios = new ArrayList<String>();
        try {
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("SELECT * FROM `user`");
            while (rs.next()){
                rs.getInt("id");
                rs.getString("name");
                rs.getString("lastname");
                rs.getString("email");
                rs.getString("password");

                flag = pstm.executeUpdate() == 1;
            }
        }catch (SQLException e){
            System.out.println("Error en el metodo mostrarUsuarios");
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones");
            }
        }

        return usuarios;
    }

    public boolean updateUser(int id, String name, String lastname, String email, String password){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("UPDATE `user` SET name = ?, lastname = ?, email = ?, password = ? WHERE id = ?;");
            pstm.setString(1, name);
            pstm.setString(2, lastname);
            pstm.setString(3, email);
            pstm.setString(4, password);
            pstm.setInt(5, id);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error en el metodo actualizarUsuario");
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones");
            }
        }

        return flag;
    }

    public boolean deleteUser(int id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            pstm = con.prepareCall("DELETE FROM `user` WHERE id = ?;");
            pstm.setInt(1, id);

            flag = pstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.println("Error en el metodo borrarUsuario");
        }finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            }catch (SQLException e){
                System.out.println("Error al cerrar conexiones");
            }
        }

        return flag;
    }

}
