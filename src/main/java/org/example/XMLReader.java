package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XMLReader {

    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\Adrial\\Desktop\\Recus\\Talentos\\UF 1\\PruebaExamen1\\CompraXML.xml";
            // Llamada al método para leer el archivo XML
            NodeList listaArticulos = leerArchivoXML(filePath);

            // Recorrer la lista de nodos y mostrar los datos
            for (int i = 0; i < listaArticulos.getLength(); i++) {
                Node articulo = listaArticulos.item(i);
                if (articulo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoArticulo = (Element) articulo;
                    String descripcion = obtenerContenido(elementoArticulo, "Descripcion");
                    double cantidad = obtenerContenidoDouble(elementoArticulo, "Cantidad");
                    String unidad = obtenerContenido(elementoArticulo, "Unidad");
                    String seccion = obtenerContenido(elementoArticulo, "Seccion");
                    double precio = obtenerContenidoDouble(elementoArticulo, "Precio");

                    // Hacer algo con los datos del artículo
                    System.out.println("Descripción: " + descripcion);
                    System.out.println("Cantidad: " + cantidad);
                    System.out.println("Unidad: " + unidad);
                    System.out.println("Sección: " + seccion);
                    System.out.println("Precio: " + precio);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NodeList leerArchivoXML(String filePath) throws Exception {
        File archivoXML = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(archivoXML);

        // Obtener la lista de nodos de los artículos
        NodeList listaArticulos = documento.getElementsByTagName("Articulo");

        return listaArticulos;
    }

    public static String obtenerContenido(Element elemento, String etiqueta) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta);
        if (nodos != null && nodos.getLength() > 0) {
            Node nodo = nodos.item(0);
            if (nodo != null && nodo.getNodeType() == Node.ELEMENT_NODE) {
                return nodo.getTextContent();
            }
        }
        return "";
    }

    public static double obtenerContenidoDouble(Element elemento, String etiqueta) {
        String contenido = obtenerContenido(elemento, etiqueta);
        try {
            return Double.parseDouble(contenido);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
