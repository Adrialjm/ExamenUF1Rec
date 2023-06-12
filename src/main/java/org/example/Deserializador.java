package org.example;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class Deserializador {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Adrial\\Desktop\\Recus\\Talentos\\UF 1\\PruebaExamen1\\lista_compra.ser";
        List<ArticuloCompra> listaCompra = deserializarObjeto(filePath);

        // Hacer algo con la lista deserializada
        mostrarListaCompra(listaCompra);
    }

    public static List<ArticuloCompra> deserializarObjeto(String filePath) {
        List<ArticuloCompra> listaCompra = null;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            // Deserializar el objeto
            listaCompra = (List<ArticuloCompra>) objectIn.readObject();

            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompra;
    }

    public static void mostrarListaCompra(List<ArticuloCompra> listaCompra) {
        System.out.println("Lista de compra:");
        for (ArticuloCompra articulo : listaCompra) {
            System.out.println(articulo);
        }
    }
}
