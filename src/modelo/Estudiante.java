/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author josse
 */
    
public class Estudiante {
    private int idEstudiante;
    private String carnet;
    private String primerNombre;
    private String segundoNombre;
    private String apellidos;
    private String direccion;
    private int telefono;
    private String carrera;
    private String fechaNacimiento;   
    private String fechaIngreso;      

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String carnet, String primerNombre, String segundoNombre,
            String apellidos, String direccion, int telefono, String carrera,
            String fechaNacimiento, String fechaIngreso) {
        this.idEstudiante = idEstudiante;
        this.carnet = carnet;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.carrera = carrera;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }

    public String getCarnet() { return carnet; }
    public void setCarnet(String carnet) { this.carnet = carnet; }

    public String getPrimerNombre() { return primerNombre; }
    public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }

    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) { this.telefono = telefono; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    @Override
    public String toString() {
        return idEstudiante + " - " + carnet + " - " + primerNombre + " " + apellidos;
    }
}
