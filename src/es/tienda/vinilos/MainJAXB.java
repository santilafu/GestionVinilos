package es.tienda.vinilos;

// Importamos las clases necesarias
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        // 1️⃣ Indicamos la ruta de tu XML (ajústala si tu carpeta se llama distinto)
        String ruta = "Recursos/catalogo.xml";

        try {
            // 2️⃣ Creamos un contexto JAXB para la clase Catalogo (raíz del XML)
            JAXBContext contexto = JAXBContext.newInstance(Catalogo.class);

            // 3️⃣ Creamos un "unmarshaller", que transforma XML -> objetos
            Unmarshaller lector = contexto.createUnmarshaller();

            // 4️⃣ Le decimos que lea el XML y lo convierta a un objeto Catalogo
            Catalogo catalogo = (Catalogo) lector.unmarshal(new File(ruta));

            // 5️⃣ Mostramos el contenido
            System.out.println("📀 CATÁLOGO DE VINILOS:");
            for (Vinilo v : catalogo.getVinilos()) {
                System.out.println("- " + v);
            }

        } catch (JAXBException e) {
            System.out.println("Error al leer el XML con JAXB: " + e.getMessage());
        }
    }
}
