package es.tienda.vinilos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

// Representa el elemento raíz <catalogo> del XML
@XmlRootElement(name = "catalogo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalogo {

    @XmlElement(name = "vinilo")
    private List<Vinilo> vinilos;

    public List<Vinilo> getVinilos() {
        return vinilos;
    }

    public void setVinilos(List<Vinilo> vinilos) {
        this.vinilos = vinilos;
    }
}
