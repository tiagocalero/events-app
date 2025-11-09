
package main.java.publicar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtRegistro complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtRegistro">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="fecha" type="{http://publicar.java.main/}localDate" minOccurs="0"/>
 *         <element name="nombreEvento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreEdicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nicknameUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="patrocinio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         <element name="asistencia" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtRegistro", propOrder = {
    "costo",
    "fecha",
    "nombreEvento",
    "nombreEdicion",
    "tipo",
    "nicknameUsuario",
    "patrocinio",
    "asistencia"
})
public class DtRegistro {

    protected float costo;
    protected LocalDate fecha;
    protected String nombreEvento;
    protected String nombreEdicion;
    protected String tipo;
    protected String nicknameUsuario;
    protected Boolean patrocinio;
    protected Boolean asistencia;

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFecha(LocalDate value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEvento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEvento() {
        return nombreEvento;
    }

    /**
     * Define el valor de la propiedad nombreEvento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEvento(String value) {
        this.nombreEvento = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEdicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEdicion() {
        return nombreEdicion;
    }

    /**
     * Define el valor de la propiedad nombreEdicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEdicion(String value) {
        this.nombreEdicion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad nicknameUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    /**
     * Define el valor de la propiedad nicknameUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameUsuario(String value) {
        this.nicknameUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad patrocinio.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPatrocinio() {
        return patrocinio;
    }

    /**
     * Define el valor de la propiedad patrocinio.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPatrocinio(Boolean value) {
        this.patrocinio = value;
    }

    /**
     * Obtiene el valor de la propiedad asistencia.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAsistencia() {
        return asistencia;
    }

    /**
     * Define el valor de la propiedad asistencia.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAsistencia(Boolean value) {
        this.asistencia = value;
    }

}
