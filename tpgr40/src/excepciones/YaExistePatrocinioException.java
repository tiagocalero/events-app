package excepciones;

import jakarta.xml.ws.WebFault;

// Excepción generada cuando ya existe un patrocinio para la
// institución y edición elegidas
@WebFault(name = "PatrocinioSuperaMontoException")
public class YaExistePatrocinioException extends Exception {
    private static final long serialVersionUID = 6300821669950948499L;

	public YaExistePatrocinioException(String string) {
        super(string);
    }
}