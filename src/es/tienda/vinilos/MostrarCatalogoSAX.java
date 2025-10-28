package es.tienda.vinilos;

// Importaciones mínimas
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

public class MostrarCatalogoSAX {

    // Método público que leerá el XML
    public static void leerCatalogo(String rutaXml) {
        try {
            // Preparamos el parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Creamos un manejador (handler) muy básico
            DefaultHandler manejador = new DefaultHandler() {

                // Guarda el texto temporal de las etiquetas
                String texto = "";

                // Cuando empieza una etiqueta <vinilo>, mostramos su ID
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equalsIgnoreCase("vinilo")) {
                        System.out.println("\nVINILO ID: " + attributes.getValue("id"));
                    }
                }

                // Capturamos el texto dentro de las etiquetas
                @Override
                public void characters(char[] ch, int start, int length) {
                    texto = new String(ch, start, length).trim();
                }

                // Cuando termina una etiqueta, mostramos su contenido
                @Override
                public void endElement(String uri, String localName, String qName) {
                    if (!texto.isEmpty() && !qName.equalsIgnoreCase("catalogo")) {
                        System.out.println("  " + qName + ": " + texto);
                        texto = "";
                    }
                }
            };

            //Le decimos al parser que lea nuestro XML usando el manejador
            parser.parse(new File(rutaXml), manejador);

        } catch (Exception e) {
            System.out.println("Error al leer el catálogo: " + e.getMessage());
        }
    }
}
