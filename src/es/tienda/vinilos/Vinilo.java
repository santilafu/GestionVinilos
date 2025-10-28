package es.tienda.vinilos;

// Importamos las anotaciones de JAXB
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

// Esta clase representa un vinilo individual del XML
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"titulo", "autor", "sello", "anno", "ejemplares", "precio"})
public class Vinilo {

    @XmlAttribute(name = "id")  // atributo del XML (<vinilo id="x">)
    private String id;

    @XmlElement
    private String titulo;

    @XmlElement
    private String autor;

    @XmlElement
    private String sello;

    @XmlElement
    private int anno;

    @XmlElement
    private int ejemplares;

    @XmlElement
    private double precio;

    // Getters y Setters (para acceder o modificar los datos)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getSello() { return sello; }
    public void setSello(String sello) { this.sello = sello; }

    public int getAnno() { return anno; }
    public void setAnno(int anno) { this.anno = anno; }

    public int getEjemplares() { return ejemplares; }
    public void setEjemplares(int ejemplares) { this.ejemplares = ejemplares; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    // Método toString para mostrar la información del vinilo
    @Override
    public String toString() {
        return String.format("%s (%d) - %s [%s] %.2f€ (%d uds)",
                titulo, anno, autor, sello, precio, ejemplares);
    }
}
