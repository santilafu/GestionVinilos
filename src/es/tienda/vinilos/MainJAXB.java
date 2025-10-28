package es.tienda.vinilos;

// Importamos las clases necesarias para trabajar con JAXB
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Scanner;

// Clase principal para leer, modificar y guardar el XML usando JAXB
public class MainJAXB {
    public static void main(String[] args) {

        // Indicamos la ruta del archivo XML original
        String ruta = "Recursos/catalogo.xml";

        // Creamos un objeto Scanner para leer los datos que introduzca el usuario
        Scanner sc = new Scanner(System.in);

        try {
            //Creamos el contexto JAXB, indicando la clase raíz del XML
            JAXBContext contexto = JAXBContext.newInstance(Catalogo.class);

            //Creamos un "Unmarshaller" para leer el XML y convertirlo en objetos Java
            Unmarshaller lector = contexto.createUnmarshaller();

            //Leemos el archivo XML y lo convertimos en un objeto Catalogo
            Catalogo catalogo = (Catalogo) lector.unmarshal(new File(ruta));

            //Mostramos los títulos actuales de todos los vinilos del catálogo
            System.out.println("Catálogo actual de vinilos:");
            for (Vinilo v : catalogo.getVinilos()) {
                System.out.println("- " + v.getTitulo());
            }

            //Pedimos al usuario que escriba el título del vinilo que quiere modificar
            System.out.print("\nIntroduce el título del vinilo a modificar: ");
            String tituloAntiguo = sc.nextLine();

            // Buscamos en la lista el vinilo cuyo título coincida con el que ha introducido el usuario
            Vinilo encontrado = null;
            for (Vinilo v : catalogo.getVinilos()) {
                if (v.getTitulo().equalsIgnoreCase(tituloAntiguo)) {
                    encontrado = v; // guardamos el vinilo encontrado
                    break; // salimos del bucle, ya lo hemos localizado
                }
            }

            // Si no se encuentra ningún vinilo con ese título, avisamos y salimos del programa
            if (encontrado == null) {
                System.out.println("No se encontró ningún vinilo con ese título.");
                return;
            }

            // Si lo encontramos, pedimos al usuario el nuevo título
            System.out.print("Introduce el nuevo título: ");
            String nuevoTitulo = sc.nextLine();

            //Asignamos el nuevo título al objeto encontrado
            encontrado.setTitulo(nuevoTitulo);

            //Mostramos por pantalla el resultado de la modificación
            System.out.println("\nTítulo modificado correctamente:");
            System.out.println("Nuevo registro -> " + encontrado);

            //Creamos un "Marshaller" para volver a escribir el XML actualizado
            Marshaller escritor = contexto.createMarshaller();

            //Configuramos el Marshaller para que el XML resultante quede formateado
            escritor.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Guardamos el catálogo actualizado en un nuevo archivo XML
            escritor.marshal(catalogo, new File("Recursos/catalogo_actualizado.xml"));

            //Confirmamos al usuario que el proceso se ha completado
            System.out.println("\nCatálogo actualizado guardado en 'catalogo_actualizado.xml'.");

            //Si ocurre algún error durante el proceso, mostramos un mensaje
        } catch (JAXBException e) {
            System.out.println("Error al procesar el XML con JAXB: " + e.getMessage());
        }
    }
}
