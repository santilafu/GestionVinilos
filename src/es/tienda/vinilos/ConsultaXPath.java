package es.tienda.vinilos;
// Importamos las clases necesarias para XPath
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.Scanner;
public class ConsultaXPath {
    public static void main(String[] args){

        // Ruta del archivo XML
        String ruta = "Recursos/catalogo.xml";

        try {
            // Cargamos el XML en la memoriia (Modelo DOM)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(ruta));

            // Creamos el objeto XPath que nos permitirá hacer consultas sobre el XML cargado
            XPath xPath = XPathFactory.newInstance().newXPath();

            //Pedimos al usuario el tipo de consulta que quiere hacer
            Scanner sc = new Scanner(System.in);
            System.out.println("Elige una consulta:");
            System.out.println("1. Buscar vinilos por autor");
            System.out.println("2. Buscar vinilos por año de publicación");
            System.out.println("3. Buscar vinilos por precio minimo");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();// Limpiamos salto de línea

            //Variable donde guardaremos la expresion XPath a ejecutar
            String expresion = "";

            // Construimos la expresión XPath según la opción elegida
            switch(opcion) {
                case 1:
                    System.out.print("Introduce el nombre del autor: ");
                    String autor = sc.nextLine();
                    expresion = "/catalogo/vinilo[autor='" + autor + "']";
                    break;
                case 2:
                    System.out.print("Introduce el año de publicación: ");
                    int anno = sc.nextInt();
                    expresion = "/catalogo/vinilo[anno=" + anno + "]";
                    break;
                case 3:
                    System.out.print("Introduce el precio mínimo: ");
                    double precio = sc.nextDouble();
                    expresion = "/catalogo/vinilo[precio>=" + precio + "]";
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }
            // Ejecutamos la consulta XPath y obtenemos los vinilos que cumplen la condición
            XPathExpression consulta = xPath.compile(expresion);
            NodeList resultados = (NodeList) consulta.evaluate(documento, javax.xml.xpath.XPathConstants.NODESET);
             // Mostramos los resultados obtenidos
            if (resultados.getLength() > 0) {
                System.out.println("\nResultados encontrados:");
                for (int i = 0; i < resultados.getLength(); i++) {
                    System.out.println("- " + resultados.item(i).getTextContent());
                }
            } else {
                System.out.println("\nNo se encontraron resultados para esa búsqueda.");
            }

        } catch (Exception e) {
            System.out.println("Error al procesar el XML: " + e.getMessage());
        }
    }
}
