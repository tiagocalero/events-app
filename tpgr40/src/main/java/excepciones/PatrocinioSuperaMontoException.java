package main.java.excepciones;

import jakarta.xml.ws.WebFault;

// Excepción generada cuando el costo de los registros de un patrocinio
// supera el aporte económico
@WebFault(name = "PatrocinioSuperaMontoException")
public class PatrocinioSuperaMontoException extends Exception {
    private static final long serialVersionUID = -4614909415791083298L;

	public PatrocinioSuperaMontoException(String string) {
        super(string);
    }
}