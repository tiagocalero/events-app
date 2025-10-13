package excepciones;

// Excepción generada cuando el costo de los registros de un patrocinio
// supera el aporte económico
public class PatrocinioSuperaMontoException extends Exception {
    private static final long serialVersionUID = -4614909415791083298L;

	public PatrocinioSuperaMontoException(String string) {
        super(string);
    }
}