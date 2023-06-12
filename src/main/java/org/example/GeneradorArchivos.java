package org.example;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GeneradorArchivos {
    private List<ArticuloCompra> listaCompra;

    public GeneradorArchivos() {
        listaCompra = new ArrayList<>();
    }

    public void agregarArticulo(ArticuloCompra articulo) {
        listaCompra.add(articulo);
    }

    public void generarArchivoSerializado(String nombreArchivo) {
        String rutaArchivo = "C:\\Users\\Adrial\\Desktop\\Recus\\Talentos\\UF 1\\Cosos\\" + nombreArchivo;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(listaCompra);
            System.out.println("Archivo serializado generado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generarArchivoXML(String nombreArchivo) {
        String rutaArchivo = "C:\\Users\\Adrial\\Desktop\\Recus\\Talentos\\UF 1\\Cosos\\" + nombreArchivo;
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(rutaArchivo))) {
            encoder.writeObject(listaCompra);
            System.out.println("Archivo XML generado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerArchivoSerializado(String nombreArchivo) {
        String rutaArchivo = "C:\\Users\\Adrial\\Desktop\\Recus\\Talentos\\UF 1\\Cosos\\" + nombreArchivo;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            List<ArticuloCompra> listaRecuperada = (List<ArticuloCompra>) ois.readObject();
            for (ArticuloCompra articulo : listaRecuperada) {
                System.out.println(articulo);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void leerArchivoXML(String nombreArchivo) {
        String rutaArchivo = "C:\\Users\\Adrial\\Desktop\\Recus\\Talentos\\UF 1\\Cosos\\" + nombreArchivo;
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(rutaArchivo))) {
            List<ArticuloCompra> listaRecuperada = (List<ArticuloCompra>) decoder.readObject();
            for (ArticuloCompra articulo : listaRecuperada) {
                System.out.println(articulo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        GeneradorArchivos generador = new GeneradorArchivos();

        // Agregar productos a la lista de compra
        generador.agregarArticulo(new ArticuloCompra("Jamones al vapor", 2.5, "kg", "Hamburgueseria", 10));
        generador.agregarArticulo(new ArticuloCompra("Tornillo frito", 6, "uds", "Fritos", 2));

        // Generar archivo serializado
        generador.generarArchivoSerializado("lista_compra.ser");

        // Generar archivo XML
        generador.generarArchivoXML("CompraXML.xml");

        // Leer y mostrar datos del archivo serializado
        generador.leerArchivoSerializado("lista_compra.ser");

        // Leer y mostrar datos del archivo XML
        generador.leerArchivoXML("CompraXML.xml");
    }

}
