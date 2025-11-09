
package main.java.publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtEdicion complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtEdicion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="institucionesPatrocinadoras" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="tRegistros" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="asistentes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="organizador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="evento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="video" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtEdicion", propOrder = {
    "pais",
    "ciudad",
    "fechaInicio",
    "fechaFin",
    "fechaAlta",
    "sigla",
    "nombre",
    "institucionesPatrocinadoras",
    "tRegistros",
    "asistentes",
    "organizador",
    "evento",
    "imagen",
    "video"
})
public class DtEdicion {

    protected String pais;
    protected String ciudad;
    protected String fechaInicio;
    protected String fechaFin;
    protected String fechaAlta;
    protected String sigla;
    protected String nombre;
    @XmlElement(nillable = true)
    protected List<String> institucionesPatrocinadoras;
    @XmlElement(nillable = true)
    protected List<String> tRegistros;
    @XmlElement(nillable = true)
    protected List<String> asistentes;
    protected String organizador;
    protected String evento;
    protected String imagen;
    protected String video;

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the ciudad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets the value of the ciudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicio(String value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFin(String value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAlta(String value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the sigla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Sets the value of the sigla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigla(String value) {
        this.sigla = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the institucionesPatrocinadoras property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the institucionesPatrocinadoras property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getInstitucionesPatrocinadoras().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the institucionesPatrocinadoras property.
     */
    public List<String> getInstitucionesPatrocinadoras() {
        if (institucionesPatrocinadoras == null) {
            institucionesPatrocinadoras = new ArrayList<>();
        }
        return this.institucionesPatrocinadoras;
    }

    /**
     * Gets the value of the tRegistros property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tRegistros property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTRegistros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the tRegistros property.
     */
    public List<String> getTRegistros() {
        if (tRegistros == null) {
            tRegistros = new ArrayList<>();
        }
        return this.tRegistros;
    }

    /**
     * Gets the value of the asistentes property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the asistentes property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAsistentes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the asistentes property.
     */
    public List<String> getAsistentes() {
        if (asistentes == null) {
            asistentes = new ArrayList<>();
        }
        return this.asistentes;
    }

    /**
     * Gets the value of the organizador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizador() {
        return organizador;
    }

    /**
     * Sets the value of the organizador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizador(String value) {
        this.organizador = value;
    }

    /**
     * Gets the value of the evento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvento() {
        return evento;
    }

    /**
     * Sets the value of the evento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvento(String value) {
        this.evento = value;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the video property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }

    /**
     * Sets the value of the video property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideo(String value) {
        this.video = value;
    }

}
