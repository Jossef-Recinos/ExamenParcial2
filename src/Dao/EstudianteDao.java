/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author josse
 */
import conexion.conexion;
import modelo.Estudiante;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDao {
    private final conexion conexion = new conexion();

    private static final String COLUMNAS =
            "id_estudiante, carnet, primer_nombre, segundo_nombre, apellidos, direccion, "
            + "telefono, carrera, fecha_nacimiento, fecha_ingreso";

    public List<Estudiante> obtenerTodos() throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT " + COLUMNAS + " FROM estudiante ORDER BY id_estudiante";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Estudiante obtenerPorId(int id) throws SQLException {
        String sql = "SELECT " + COLUMNAS + " FROM estudiante WHERE id_estudiante = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    public void guardar(Estudiante e) throws SQLException {
        String sql = "INSERT INTO estudiante (" + COLUMNAS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getIdEstudiante());
            ps.setString(2, e.getCarnet());
            ps.setString(3, e.getPrimerNombre());
            ps.setString(4, e.getSegundoNombre());
            ps.setString(5, e.getApellidos());
            ps.setString(6, e.getDireccion());
            ps.setInt(7, e.getTelefono());
            ps.setString(8, e.getCarrera());
            ps.setDate(9, Date.valueOf(e.getFechaNacimiento()));
            ps.setDate(10, Date.valueOf(e.getFechaIngreso()));
            ps.executeUpdate();
        }
    }

    /** @return false si no existe un estudiante con ese ID */
    public boolean actualizar(Estudiante e) throws SQLException {
        String sql = "UPDATE estudiante SET carnet = ?, primer_nombre = ?, segundo_nombre = ?, apellidos = ?, "
                + "direccion = ?, telefono = ?, carrera = ?, fecha_nacimiento = ?, fecha_ingreso = ?, "
                + " WHERE id_estudiante = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, e.getCarnet());
            ps.setString(2, e.getPrimerNombre());
            ps.setString(3, e.getSegundoNombre());
            ps.setString(4, e.getApellidos());
            ps.setString(5, e.getDireccion());
            ps.setInt(6, e.getTelefono());
            ps.setString(7, e.getCarrera());
            ps.setDate(8, Date.valueOf(e.getFechaNacimiento()));
            ps.setDate(9, Date.valueOf(e.getFechaIngreso()));
            ps.setInt(10, e.getIdEstudiante());
            return ps.executeUpdate() > 0;
        }
    }

    /** @return false si no existe un estudiante con ese ID */
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Estudiante mapear(ResultSet rs) throws SQLException {
        return new Estudiante(
                rs.getInt("id_estudiante"),
                rs.getString("carnet"),
                rs.getString("primer_nombre"),
                rs.getString("segundo_nombre"),
                rs.getString("apellidos"),
                rs.getString("direccion"),
                rs.getInt("telefono"),
                rs.getString("carrera"),
                fecha(rs.getDate("fecha_nacimiento")),
                fecha(rs.getDate("fecha_ingreso"))
        );
    }

    private String fecha(Date d) {
        return d == null ? "" : d.toString(); // aaaa-mm-dd
    }
}
