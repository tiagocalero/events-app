
package main.java.publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoNivelPatrocinio.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="tipoNivelPatrocinio">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Platino"/>
 *     <enumeration value="Oro"/>
 *     <enumeration value="Plata"/>
 *     <enumeration value="Bronce"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "tipoNivelPatrocinio")
@XmlEnum
public enum TipoNivelPatrocinio {

    @XmlEnumValue("Platino")
    PLATINO("Platino"),
    @XmlEnumValue("Oro")
    ORO("Oro"),
    @XmlEnumValue("Plata")
    PLATA("Plata"),
    @XmlEnumValue("Bronce")
    BRONCE("Bronce");
    private final String value;

    TipoNivelPatrocinio(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoNivelPatrocinio fromValue(String v) {
        for (TipoNivelPatrocinio c: TipoNivelPatrocinio.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
