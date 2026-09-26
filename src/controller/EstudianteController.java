/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author josse
 */

import Dao.EstudianteDao;
import modelo.Estudiante;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EstudianteController {

    private final EstudianteDao dao = new EstudianteDao();

    public List<Estudiante> obtenerEstudiantes() throws SQLException {
        return dao.obtenerTodos();
    }

    public Estudiante buscarEstudiante(String id) throws SQLException {
        return dao.obtenerPorId(entero(id, "ID estudiante"));
    }

    public void agregarEstudiante(String id, String carnet, String primerNombre, String segundoNombre,
            String apellidos, String direccion, String telefono, String carrera,
            String fechaNacimiento, String fechaIngreso) throws SQLException {

        dao.guardar(construir(id, carnet, primerNombre, segundoNombre, apellidos, direccion,
                telefono, carrera, fechaNacimiento, fechaIngreso));
    }

    /** @return false si no existe un estudiante con ese ID */
    public boolean actualizarEstudiante(String id, String carnet, String primerNombre, String segundoNombre,
            String apellidos, String direccion, String telefono, String carrera,
            String fechaNacimiento, String fechaIngreso) throws SQLException {

        return dao.actualizar(construir(id, carnet, primerNombre, segundoNombre, apellidos, direccion,
                telefono, carrera, fechaNacimiento, fechaIngreso));
    }

    /** @return false si no existe un estudiante con ese ID */
    public boolean eliminarEstudiante(String id) throws SQLException {
        return dao.eliminar(entero(id, "ID estudiante"));
    }

    // ===================== Validaciones =====================

    private Estudiante construir(String id, String carnet, String primerNombre, String segundoNombre,
            String apellidos, String direccion, String telefono, String carrera,
            String fechaNacimiento, String fechaIngreso) {

        int idNum = entero(id, "ID estudiante");
        String carnetOk = requerido(carnet, "Carnet");
        String primerOk = requerido(primerNombre, "Primer nombre");
        String apellidosOk = requerido(apellidos, "Apellidos");
        String direccionOk = requerido(direccion, "Dirección");
        int telefonoNum = entero(telefono, "Teléfono");
        String carreraOk = requerido(carrera, "Carrera");
        LocalDate nacimiento = fecha(fechaNacimiento, "Fecha de nacimiento");
        LocalDate ingreso = fecha(fechaIngreso, "Fecha de ingreso");

        if (idNum <= 0) {
            throw new IllegalArgumentException("El ID estudiante debe ser mayor que 0.");
        }
        if (!ingreso.isAfter(nacimiento)) {
            throw new IllegalArgumentException("La fecha de ingreso debe ser posterior a la fecha de nacimiento.");
        }

        // El segundo nombre es opcional: si viene vacio se guarda como NULL
        String segundoOk = (segundoNombre == null || segundoNombre.isBlank()) ? null : segundoNombre.trim();

        return new Estudiante(idNum, carnetOk, primerOk, segundoOk, apellidosOk, direccionOk,
                telefonoNum, carreraOk, nacimiento.toString(), ingreso.toString());
    }

    private String requerido(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo \"" + campo + "\" es obligatorio.");
        }
        return valor.trim();
    }

    private int entero(String valor, String campo) {
        String v = requerido(valor, campo);
        try {
            return Integer.parseInt(v);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo \"" + campo + "\" debe ser un número entero (sin guiones ni espacios).");
        }
    }

    private LocalDate fecha(String valor, String campo) {
        String v = requerido(valor, campo);
        try {
            return LocalDate.parse(v); // aaaa-mm-dd
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("El campo \"" + campo + "\" debe tener el formato aaaa-mm-dd, por ejemplo 2005-03-14");
        }
    }
}
