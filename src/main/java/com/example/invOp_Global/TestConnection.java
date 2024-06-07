package com.example.invOp_Global;
import java.sql.*;

public class TestConnection {
    public static void main(String[] args){
        realizarPruebaConexion();
    }

    public static void realizarPruebaConexion(){
        String url = "jdbc:mysql://localhost:3306/inventarioYDemanda";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println("Conexion exitosa");

        } catch(SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}