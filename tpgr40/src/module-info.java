/**
 * 
 */
/**
 * 
 */
module tpgr40 {
	    requires java.desktop;
		requires org.junit.jupiter.api;
		requires jakarta.xml.ws;
		requires jakarta.xml.bind;
	    opens logica to com.sun.xml.bind;
	    exports publicar;
}