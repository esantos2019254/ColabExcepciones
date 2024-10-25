package org.example;

import java.io.*;

public class FileManager {

    static class ArchivoNoEncontradoException extends Exception {
        public ArchivoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    static class ArchivoYaExisteException extends Exception {
        public ArchivoYaExisteException(String mensaje) {
            super(mensaje);
        }
    }

    public static void verificarArchivo(String nombreArchivo) throws ArchivoNoEncontradoException {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            throw new ArchivoNoEncontradoException("El archivo no existe.");
        }
    }

    public static void crearArchivo(String nombreArchivo) throws ArchivoYaExisteException {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            throw new ArchivoYaExisteException("El archivo ya existe.");
        } else {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void agregarLinea(String nombreArchivo, String texto){
        try(FileWriter fw = new FileWriter(nombreArchivo, true)){
            fw.write(texto + System.lineSeparator());
            System.out.println("Linea agregada correctamente");
        }catch (IOException e){
            System.out.println("Ocurrio un error al escribir el texto " + e.getMessage());
        }
    }

    public static void mostrarArchivo(String nombreArchivo) throws ArchivoNoEncontradoException {
        try(BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))){
            String linea;
            while ((linea = reader.readLine()) != null){
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrio un error al escribir el texto " + e.getMessage());
        }
    }

    public static void mostrarLineaEspecifica(String nombreArchivo, int numeroLinea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int contador = 1;
            while ((linea = reader.readLine()) != null) {
                if (contador == numeroLinea) {
                    System.out.println(linea);
                    return;
                }
                contador++;
            }
            System.out.println("No se encontró la línea especificada.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }

}
