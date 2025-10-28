package es.tienda.vinilos;

public class MainSAX {
    public static void main(String[] args) {
        String ruta = "Recursos/catalogo.xml";
        MostrarCatalogoSAX.leerCatalogo(ruta);
    }
}
