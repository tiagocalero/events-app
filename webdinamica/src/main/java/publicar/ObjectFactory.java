
package publicar;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PatrocinioSuperaMontoException_QNAME = new QName("http://publicar/", "PatrocinioSuperaMontoException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link YaExistePatrocinioException }
     * 
     * @return
     *     the new instance of {@link YaExistePatrocinioException }
     */
    public YaExistePatrocinioException createYaExistePatrocinioException() {
        return new YaExistePatrocinioException();
    }

    /**
     * Create an instance of {@link PatrocinioSuperaMontoException }
     * 
     * @return
     *     the new instance of {@link PatrocinioSuperaMontoException }
     */
    public PatrocinioSuperaMontoException createPatrocinioSuperaMontoException() {
        return new PatrocinioSuperaMontoException();
    }

    /**
     * Create an instance of {@link DttRegistro }
     * 
     * @return
     *     the new instance of {@link DttRegistro }
     */
    public DttRegistro createDttRegistro() {
        return new DttRegistro();
    }

    /**
     * Create an instance of {@link DtEvento }
     * 
     * @return
     *     the new instance of {@link DtEvento }
     */
    public DtEvento createDtEvento() {
        return new DtEvento();
    }

    /**
     * Create an instance of {@link DtEdicion }
     * 
     * @return
     *     the new instance of {@link DtEdicion }
     */
    public DtEdicion createDtEdicion() {
        return new DtEdicion();
    }

    /**
     * Create an instance of {@link DtPatrocinio }
     * 
     * @return
     *     the new instance of {@link DtPatrocinio }
     */
    public DtPatrocinio createDtPatrocinio() {
        return new DtPatrocinio();
    }

    /**
     * Create an instance of {@link DtRegistro }
     * 
     * @return
     *     the new instance of {@link DtRegistro }
     */
    public DtRegistro createDtRegistro() {
        return new DtRegistro();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     * @return
     *     the new instance of {@link ArrayList }
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link DtEventoArray }
     * 
     * @return
     *     the new instance of {@link DtEventoArray }
     */
    public DtEventoArray createDtEventoArray() {
        return new DtEventoArray();
    }

    /**
     * Create an instance of {@link DtEdicionArray }
     * 
     * @return
     *     the new instance of {@link DtEdicionArray }
     */
    public DtEdicionArray createDtEdicionArray() {
        return new DtEdicionArray();
    }

    /**
     * Create an instance of {@link DtRegistroArray }
     * 
     * @return
     *     the new instance of {@link DtRegistroArray }
     */
    public DtRegistroArray createDtRegistroArray() {
        return new DtRegistroArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "PatrocinioSuperaMontoException")
    public JAXBElement<Object> createPatrocinioSuperaMontoException(Object value) {
        return new JAXBElement<>(_PatrocinioSuperaMontoException_QNAME, Object.class, null, value);
    }

}
