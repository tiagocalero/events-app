package excepciones;

// Excepción generada cuando el costo de los registros de un patrocinio
// supera el aporte económico
public class PatrocinioSuperaMontoException extends Exception {
    public PatrocinioSuperaMontoException(String string) {
        super(string);
    }
}