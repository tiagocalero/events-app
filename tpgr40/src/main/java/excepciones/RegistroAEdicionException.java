package main.java.excepciones;

import jakarta.xml.ws.WebFault;

@WebFault(name = "RegistroAEdicionException")
public class RegistroAEdicionException extends Exception {
    private static final long serialVersionUID = 1L;

    public RegistroAEdicionException(String message) {
        super(message);
    }
}