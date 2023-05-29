/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistema_de_gestión_de_estudiantes;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karim
 */
class Estudiante implements Serializable {

    private String nombre;
    private int numeroLista;
    private String grado;

    public Estudiante(String nombre, int numeroLista, String grado) {
        this.nombre = nombre;
        this.numeroLista = numeroLista;
        this.grado = grado;
    }

    // Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(int numeroLista) {
        this.numeroLista = numeroLista;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String toString() {
        return "nombre: " + nombre + '\''
                + ", numeroLista: " + numeroLista
                + ", grado: '" + grado;
    }
}

class StudentManagementSystem {

    private List<Estudiante> estudiantes;

    public StudentManagementSystem() {
        estudiantes = new ArrayList<>();
    }

    public void addStudent(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public Estudiante getStudent(int rollNumber) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroLista() == rollNumber) {
                return estudiante;
            }
        }
        return null;
    }

    public void storeData(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(estudiantes);
            System.out.println("Datos de los estudiantes almacenados en el archivo: " + filename);
        } catch (IOException e) {
            System.out.println("Error al almacenar los datos de los estudiantes en el archivo: " + e.getMessage());
        }
    }

    public void loadData(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            estudiantes = (List<Estudiante>) inputStream.readObject();
            System.out.println("Datos de los estudiantes cargados desde el archivo: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos de los estudiantes desde el archivo: " + e.getMessage());
        }
    }
}

public class SISTEMA_DE_GESTIÓN_DE_ESTUDIANTES {

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Estudiante estudiante1 = new Estudiante("NinoOxxo", 17, "Noveno grado");
        Estudiante estudiante2 = new Estudiante("Tilin", 14, "Décimo grado");
        Estudiante estudiante3 = new Estudiante("El Pepe", 2, "Onceavo grado");

        system.addStudent(estudiante1);
        system.addStudent(estudiante2);
        system.addStudent(estudiante3);

        system.storeData("estudiantes.dat");
        system = new StudentManagementSystem();
        system.loadData("estudiantes.dat");

        Estudiante estudianteRecuperado = system.getStudent(14);
        if (estudianteRecuperado != null) {
            System.out.println("Informacion del Estudiante que se pudo recuperar: " + estudianteRecuperado);
        }
    }
}
