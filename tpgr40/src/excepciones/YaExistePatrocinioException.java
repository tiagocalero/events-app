package excepciones;

// Excepción generada cuando ya existe un patrocinio para la
// institución y edición elegidas
public class YaExistePatrocinioException extends Exception {
    public YaExistePatrocinioException(String string) {
        super(string);
    }
}