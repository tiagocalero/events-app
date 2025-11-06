package main.java.excepciones;

import jakarta.xml.ws.WebFault;

@WebFault(name = "AltaPatrocinioException")
public class AltaPatrocinioException extends Exception {
    private static final long serialVersionUID = 1L;

    public AltaPatrocinioException(String message) {
        super(message);
    }
}