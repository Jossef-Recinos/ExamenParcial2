/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author josse
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class conexion {
    static Properties confing = new Properties();
    String hostname, port, database, username, password;

    public conexion() {
        String path = "C:\\Users\\josse\\Documents\\Parcial Practica\\ExamenPracticaCorto\\src\\conexion\\db_confing.properties";
        try {
            confing.load(Files.newInputStream(Paths.get(path)));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        loadProperties();
    }

    public void loadProperties() {
        this.hostname = confing.getProperty("hostname");
        this.port = confing.getProperty("port");
        this.database = confing.getProperty("database");
        this.username = confing.getProperty("username");
        this.password = confing.getProperty("password");
    }

    public Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port
                + "/" + database;

        System.out.println("Conectado exitosamente");
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
