
package main.java.publicar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtRegistro complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtRegistro">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreEvento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreEdicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nicknameUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="patrocinio" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="asistencia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="confirmable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "asistencia",
    "confirmable"
})
public class DtRegistro {

    protected float costo;
    protected String fecha;
    protected String nombreEvento;
    protected String nombreEdicion;
    protected String tipo;
    protected String nicknameUsuario;
    protected boolean patrocinio;
    protected boolean asistencia;
    protected boolean confirmable;

    /**
     * Gets the value of the costo property.
     * 
     */
    public float getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(float value) {
        this.costo = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the nombreEvento property.
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
     * Sets the value of the nombreEvento property.
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
     * Gets the value of the nombreEdicion property.
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
     * Sets the value of the nombreEdicion property.
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
     * Gets the value of the tipo property.
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
     * Sets the value of the tipo property.
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
     * Gets the value of the nicknameUsuario property.
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
     * Sets the value of the nicknameUsuario property.
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
     * Gets the value of the patrocinio property.
     * 
     */
    public boolean isPatrocinio() {
        return patrocinio;
    }

    /**
     * Sets the value of the patrocinio property.
     * 
     */
    public void setPatrocinio(boolean value) {
        this.patrocinio = value;
    }

    /**
     * Gets the value of the asistencia property.
     * 
     */
    public boolean isAsistencia() {
        return asistencia;
    }

    /**
     * Sets the value of the asistencia property.
     * 
     */
    public void setAsistencia(boolean value) {
        this.asistencia = value;
    }

    /**
     * Gets the value of the confirmable property.
     * 
     */
    public boolean isConfirmable() {
        return confirmable;
    }

    /**
     * Sets the value of the confirmable property.
     * 
     */
    public void setConfirmable(boolean value) {
        this.confirmable = value;
    }

}
