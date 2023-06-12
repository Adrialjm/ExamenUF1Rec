package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {
    private List<ArticuloCompra> listaCompra;

    public Ejercicio2() {
        listaCompra = new ArrayList<>();
    }

    public void agregarArticulo(ArticuloCompra articulo) {
        listaCompra.add(articulo);
    }

    public static List<ArticuloCompra> capturarListaCompra() {
        List<ArticuloCompra> listaCompra = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Ingrese los artículos de la lista de compra (Ingrese 'fin' para finalizar):");
            while (true) {
                System.out.print("Nombre: ");
                String descripcion = br.readLine();
                if (descripcion.equalsIgnoreCase("fin")) {
                    break;
                }
                System.out.print("Cantidad: ");
                double cantidad = Double.parseDouble(br.readLine());

                System.out.print("Unidad: ");
                String unidad = br.readLine();

                System.out.print("Sección: ");
                String seccion = br.readLine();

                //PARTE 2: Añadir precio
                System.out.print("Precio: ");
                double precio = Double.parseDouble(br.readLine());

                System.out.println("Siguiente articulo (Ingrese 'fin' para finalizar): ");

                ArticuloCompra articulo = new ArticuloCompra(descripcion, cantidad, unidad, seccion, precio);
                listaCompra.add(articulo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaCompra;
    }

    public static void mostrarListaCompra(List<ArticuloCompra> listaCompra) {
        System.out.println("Lista de Compra:");
        for (ArticuloCompra articulo : listaCompra) {
            System.out.println(articulo);
        }
    }

    private static void generarArchivoXML(List<ArticuloCompra> listaCompra, String nombreArchivo) {
        try {
            // Crear el documento XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Crear el elemento raíz
            Element rootElement = doc.createElement("ListaCompra");
            doc.appendChild(rootElement);

            // Agregar los artículos a la lista de compra
            for (ArticuloCompra articulo : listaCompra) {
                Element articuloElement = doc.createElement("article");
                rootElement.appendChild(articuloElement);

                Element descripcionElement = doc.createElement("descripcio");
                descripcionElement.appendChild(doc.createTextNode(articulo.getDescripcion()));
                articuloElement.appendChild(descripcionElement);

                Element cantidadElement = doc.createElement("quantitat");
                cantidadElement.appendChild(doc.createTextNode(String.valueOf(articulo.getCantidad())));
                articuloElement.appendChild(cantidadElement);

                Element unidadElement = doc.createElement("unitat");
                unidadElement.appendChild(doc.createTextNode(articulo.getUnidad()));
                articuloElement.appendChild(unidadElement);

                Element seccionElement = doc.createElement("seccio");
                seccionElement.appendChild(doc.createTextNode(articulo.getSeccion()));
                articuloElement.appendChild(seccionElement);
            }

            // Escribir el contenido del documento XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nombreArchivo));
            transformer.transform(source, result);

            System.out.println("Archivo XML generado correctamente.");
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<ArticuloCompra> listaCompra = capturarListaCompra();
        mostrarListaCompra(listaCompra);

        // Generar y escribir el archivo XML de la lista de la compra
        generarArchivoXML(listaCompra, "compraXML.xml");
    }
}
