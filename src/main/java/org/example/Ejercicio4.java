package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio4 {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        List<ArticuloCompra> listaCompra = capturarListaCompra();
        mostrarListaCompra(listaCompra);
        escribirListaSerializada(listaCompra);
    }

    // Captura los atributos de los artículos de compra y los almacena en una lista
    private static List<ArticuloCompra> capturarListaCompra() {
        List<ArticuloCompra> listaCompra = new ArrayList<>();

        while (true) {
            System.out.println("Captura de artículo de compra:");
            String descripcion = capturarAtributo("Nombre"); // Captura la descripción
            double cantidad = Double.parseDouble(capturarAtributo("Cantidad")); // Captura la cantidad
            String unidad = capturarAtributo("Unidad"); // Captura la unidad
            String seccion = capturarAtributo("Sección"); // Captura la sección
            double precio = Double.parseDouble((capturarAtributo("Precio"))); //Añadido precio

            // Crea un objeto ArticuloCompra con los atributos capturados y lo agrega a la lista
            ArticuloCompra articulo = new ArticuloCompra(descripcion, cantidad, unidad, seccion, precio);
            listaCompra.add(articulo);

            // Verifica si el usuario desea agregar otro artículo de compra
            if (!continuarCaptura()) {
                break; // Si no desea agregar más artículos, sale del bucle
            }
        }

        return listaCompra; // Devuelve la lista de compra completa
    }

    // Captura un atributo ingresado por el usuario
    private static String capturarAtributo(String nombreAtributo) {
        System.out.print(nombreAtributo + ": ");
        String atributo = null;
        try {
            atributo = reader.readLine(); // Lee la línea ingresada por el usuario
        } catch (IOException e) {
            e.printStackTrace();
        }
        return atributo; // Devuelve el atributo capturado
    }

    // Verifica si el usuario desea agregar otro artículo de compra
    private static boolean continuarCaptura() {
        System.out.print("¿Desea agregar otro artículo de compra? (S/N): ");
        String respuesta = null;
        try {
            respuesta = reader.readLine(); // Lee la respuesta ingresada por el usuario
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta != null && respuesta.equalsIgnoreCase("S"); // Devuelve true si la respuesta es "S" (sí)
    }

    // Muestra la lista de compra en la consola
    private static void mostrarListaCompra(List<ArticuloCompra> listaCompra) {
        System.out.println("Lista de compra:");
        for (ArticuloCompra articulo : listaCompra) {
            System.out.println(articulo);
        }
    }

    // Escribe la lista de compra como objeto serializado en un archivo
    private static void escribirListaSerializada(List<ArticuloCompra> listaCompra) {
        String nombreArchivo = "lista_compra.ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(listaCompra);
            System.out.println("Archivo serializado generado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
