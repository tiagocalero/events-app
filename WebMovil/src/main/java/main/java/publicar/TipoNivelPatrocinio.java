
package main.java.publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for tipoNivelPatrocinio</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
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

    /**
     * Gets the value associated to the enum constant.
     * 
     * @return
     *     The value linked to the enum.
     */
    public String value() {
        return value;
    }

    /**
     * Gets the enum associated to the value passed as parameter.
     * 
     * @param v
     *     The value to get the enum from.
     * @return
     *     The enum which corresponds to the value, if it exists.
     * @throws IllegalArgumentException
     *     If no value matches in the enum declaration.
     */
    public static TipoNivelPatrocinio fromValue(String v) {
        for (TipoNivelPatrocinio c: TipoNivelPatrocinio.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
