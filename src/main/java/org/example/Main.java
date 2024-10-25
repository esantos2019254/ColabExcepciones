package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("Ingrese el nombre del archivo:");
        String nombreArchivo = scanner.nextLine();

        try{
            FileManager.verificarArchivo(nombreArchivo);
            System.out.println("El archivo ya existe");
        } catch (FileManager.ArchivoNoEncontradoException e) {
            System.out.println(e.getMessage());
            try{
                FileManager.crearArchivo(nombreArchivo);
                System.out.println("Archivo creado correctamente.");
            }catch (FileManager.ArchivoYaExisteException ex){
                System.out.println(ex.getMessage());
            }
        }

        do{
            System.out.println("Ingrese la opcion que desea: \n" +
                                "1. Agregar nuevas líneas al archivo. \n" +
                                "2. Mostrar el contenido del archivo. \n" +
                                "3. Mostrar una línea específica del archivo. \n" +
                                "4. Salir.");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    System.out.println("Ingrese el texto que desea agregar.");
                    String texto = scanner.nextLine();
                    FileManager.agregarLinea(nombreArchivo, texto);
                    break;
                case 2:
                    try {
                        FileManager.mostrarArchivo(nombreArchivo);
                    } catch (FileManager.ArchivoNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el numero de linea que desea mostrar.");
                    int linea = scanner.nextInt();
                    FileManager.mostrarLineaEspecifica(nombreArchivo, linea);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }while(opcion != 4);
        scanner.close();
    }
}