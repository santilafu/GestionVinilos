# 🧩 Proyecto: Gestión del stock de una tienda de discos de vinilo
### Unidad 1 – Módulo de Acceso a Datos
**Autor:** Santiago Lafuente Hernández  
**Colaboración técnica:** parte del desarrollo documental se realizó con ayuda de una IA (asistencia en redacción y organización del contenido).

---

## 📖 Introducción

Este proyecto forma parte de la **Unidad 1 del módulo Acceso a Datos** y tiene como objetivo desarrollar una aplicación en Java que permita **gestionar el catálogo de discos de vinilo** de una tienda.

El trabajo abarca desde la creación y validación de ficheros XML hasta su manipulación mediante diferentes tecnologías: **SAX, JAXB, DOM y XPath**.  
Con esta práctica se busca entender las distintas formas de **leer, modificar y consultar información estructurada** en un entorno realista, aplicando técnicas de acceso a datos y manejo de excepciones.

---

## 🧱 Estructura del proyecto

El proyecto se ha desarrollado en el IDE **IntelliJ IDEA Ultimate**, utilizando un paquete llamado `es.tienda.vinilos`, dentro del cual se incluyen las distintas clases Java según su función:

| Clase                     | Descripción |
|---------------------------|--------------|
| `MainSAX.java`            | Lee y muestra el XML utilizando SAX (lectura secuencial por eventos). |
| `MostrarCatalogoSAX.java` | Contiene el manejador SAX para recorrer y mostrar las etiquetas del XML. |
| `MainJAXB.java`           | Carga el XML en memoria como objetos Java, permite modificar datos y guardarlos de nuevo. |
| `Catalogo.java`           | Clase raíz del XML, contiene una lista de objetos `Vinilo`. |
| `Vinilo.java`             | Representa cada vinilo individual del catálogo, con sus campos y métodos. |
| `ConsultaXPath.java`      | Permite realizar consultas filtradas sobre el XML (por autor, año o precio). |

Además, dentro del directorio `Recursos/` se incluyen los archivos:
- `catalogo.xsd` → Esquema XML que define la estructura y los tipos de datos.
- `catalogo.xml` → Documento XML con los datos de la tienda.
- `catalogo_actualizado.xml` → Archivo generado automáticamente al modificar datos con JAXB.

---

## 📂 Descripción de los puntos desarrollados

### **1. Creación del esquema XML (XSD)**
Se definió un esquema `catalogo.xsd` que describe la estructura del catálogo de vinilos,  
incluyendo los elementos: `titulo`, `autor`, `sello`, `anno`, `ejemplares` y `precio`.  
El campo `id` se declaró como atributo único (`xs:ID`), garantizando que cada vinilo tenga un identificador propio.

Este esquema permite validar el XML y asegurar que todos los registros cumplen la estructura establecida.

---

### **2. Creación del documento XML**
Se creó el archivo `catalogo.xml`, que incluye una amplia colección de vinilos (20 artistas, entre ellos Marea, Extremoduro, Leiva, Fito & Fitipaldis, Estopa, Amaral, Zoo, etc.), con varios discos por cada uno.  
Cada registro sigue la estructura del XSD y contiene información coherente y realista.

El XML está correctamente indentado y validado con el esquema mediante el atributo:
```xml
xsi:noNamespaceSchemaLocation="catalogo.xsd"
```
---
### **3. Lectura del XML mediante SAX**

Para la lectura secuencial del XML se desarrollaron las clases `MainSAX` y `MostrarCatalogoSAX`.  
El parser **SAX** permite recorrer el archivo **por eventos**, detectando cuándo se abre y cierra cada etiqueta.

Con este enfoque:

- Se imprime cada vinilo con sus datos.
- No se guarda todo el XML en memoria (ideal para ficheros grandes).
- Se manejan las excepciones y errores de lectura.

Este punto cubre el **acceso secuencial a ficheros XML**.

---

### **4. Lectura, modificación y guardado del XML con JAXB**

En este bloque se implementó el acceso a los datos mediante **JAXB**, que permite convertir el XML en objetos Java (`Catalogo` y `Vinilo`).  
Esto se conoce como **mapeo objeto-XML** (*Object-XML Mapping*).

Se realizaron las siguientes acciones:

1. **Lectura del XML** con `Unmarshaller` (XML → Objetos).
2. **Modificación de un registro**, permitiendo cambiar el título de un vinilo introducido por el usuario.
3. **Guardado del catálogo actualizado** mediante `Marshaller` (Objetos → XML).

El nuevo archivo `catalogo_actualizado.xml` conserva el formato legible y respeta la estructura original.

---

### **5. Configuración y dependencias de Jakarta JAXB**

A partir de **Java 11**, el paquete `javax.xml.bind` fue eliminado del JDK.  
Por tanto, fue necesario añadir manualmente las dependencias **Jakarta JAXB (API y Runtime)** para poder usar la funcionalidad.

En IntelliJ se añadieron desde:

File → Project Structure → Modules → Dependencies → From Maven...


Y se incorporaron las siguientes librerías Maven:

```text
jakarta.xml.bind:jakarta.xml.bind-api:4.0.2
org.glassfish.jaxb:jaxb-runtime:4.0.2

```
Estas librerías permiten que las anotaciones @XmlRootElement, @XmlElement, @XmlAttribute y demás funcionen correctamente.
De este modo, el programa puede leer y escribir ficheros XML sin errores de compilación.

### **6. Gestión de excepciones**

Durante todas las operaciones se han controlado posibles errores mediante bloques try-catch,
mostrando mensajes claros al usuario en caso de que el archivo no exista, no se pueda leer o el dato introducido no sea válido.

Los mensajes de error permiten entender qué ha fallado y dónde, evitando que la aplicación se detenga de forma inesperada.

Este manejo de errores se aplica tanto en la lectura con SAX, como en el procesamiento con JAXB y en las consultas con XPath.
Gracias a esto, el programa tiene un comportamiento estable, seguro y predecible ante cualquier problema ⚙️.

### **7. Consultas avanzadas mediante DOM y XPath**

Para completar la aplicación, se desarrolló la clase ConsultaXPath.java.
Esta clase utiliza DOM (Document Object Model) para cargar el XML completo en memoria, y XPath para realizar búsquedas dentro de él.

De esta forma, el usuario puede realizar consultas filtradas sobre el catálogo según distintos criterios:

Buscar por autor, por ejemplo: autor='Marea'.

Buscar por año de publicación, por ejemplo: anno=2008.

Buscar por precio mínimo, por ejemplo: precio>=18.

La clase construye dinámicamente la expresión XPath a partir de los datos que introduce el usuario,
ejecuta la búsqueda y devuelve los títulos que cumplen las condiciones.

Con este apartado se demuestra el uso combinado de DOM y XPath para la consulta selectiva de datos XML,
completando así todas las formas de acceso y manipulación de información del temario de la Unidad 1 🔍.

## 🧰 Tecnologías utilizadas

| Herramienta | Uso principal |
|--------------|----------------|
| **Java 24** | Lenguaje de programación utilizado en todo el proyecto. |
| **IntelliJ IDEA Ultimate** | Entorno de desarrollo (IDE). |
| **Jakarta JAXB (jakarta.xml.bind)** | Lectura y escritura de objetos Java desde XML. |
| **SAX / DOM / XPath** | Lectura y consulta de documentos XML. |
| **XML Schema (XSD)** | Definición de la estructura del XML. |
